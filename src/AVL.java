public class AVL {

    private int altura(NodoAVL nodo) {
        return nodo == null ? 0 : nodo.altura;
    }

    private int factorBalance(NodoAVL nodo) {
        return nodo == null ? 0 : altura(nodo.izquierdo) - altura(nodo.derecho);
    }

    private NodoAVL rotacionDerecha(NodoAVL y) {
        NodoAVL x = y.izquierdo;
        NodoAVL T2 = x.derecho;

        x.derecho = y;
        y.izquierdo = T2;

        y.altura = Math.max(altura(y.izquierdo), altura(y.derecho)) + 1;
        x.altura = Math.max(altura(x.izquierdo), altura(x.derecho)) + 1;

        return x;
    }

    private NodoAVL rotacionIzquierda(NodoAVL x) {
        NodoAVL y = x.derecho;
        NodoAVL T2 = y.izquierdo;

        y.izquierdo = x;
        x.derecho = T2;

        x.altura = Math.max(altura(x.izquierdo), altura(x.derecho)) + 1;
        y.altura = Math.max(altura(y.izquierdo), altura(y.derecho)) + 1;

        return y;
    }

    public NodoAVL insertar(NodoAVL nodo, int valor) {
        if (nodo == null) {
            return new NodoAVL(valor);
        }

        if (valor < nodo.valor)
            nodo.izquierdo = insertar(nodo.izquierdo, valor);
        else if (valor > nodo.valor)
            nodo.derecho = insertar(nodo.derecho, valor);
        else
            return nodo; // No se permiten duplicados

        nodo.altura = 1 + Math.max(altura(nodo.izquierdo), altura(nodo.derecho));
        int balance = factorBalance(nodo);

        // Rotación LL
        if (balance > 1 && valor < nodo.izquierdo.valor)
            return rotacionDerecha(nodo);

        // Rotación RR
        if (balance < -1 && valor > nodo.derecho.valor)
            return rotacionIzquierda(nodo);

        // Rotación LR
        if (balance > 1 && valor > nodo.izquierdo.valor) {
            nodo.izquierdo = rotacionIzquierda(nodo.izquierdo);
            return rotacionDerecha(nodo);
        }

        // Rotación RL
        if (balance < -1 && valor < nodo.derecho.valor) {
            nodo.derecho = rotacionDerecha(nodo.derecho);
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    private NodoAVL nodoMinimo(NodoAVL nodo) {
        while (nodo.izquierdo != null)
            nodo = nodo.izquierdo;
        return nodo;
    }

    public NodoAVL eliminar(NodoAVL raiz, int valor) {
        if (raiz == null)
            return raiz;

        if (valor < raiz.valor)
            raiz.izquierdo = eliminar(raiz.izquierdo, valor);

        else if (valor > raiz.valor)
            raiz.derecho = eliminar(raiz.derecho, valor);

        else {
            if ((raiz.izquierdo == null) || (raiz.derecho == null)) {
                NodoAVL temp = (raiz.izquierdo != null) ? raiz.izquierdo : raiz.derecho;

                if (temp == null) {
                    raiz = null;
                } else {
                    raiz = temp;
                }

            } else {
                NodoAVL temp = nodoMinimo(raiz.derecho);
                raiz.valor = temp.valor;
                raiz.derecho = eliminar(raiz.derecho, temp.valor);
            }
        }

        if (raiz == null)
            return raiz;

        raiz.altura = Math.max(altura(raiz.izquierdo), altura(raiz.derecho)) + 1;
        int balance = factorBalance(raiz);

        // LL
        if (balance > 1 && factorBalance(raiz.izquierdo) >= 0)
            return rotacionDerecha(raiz);

        // LR
        if (balance > 1 && factorBalance(raiz.izquierdo) < 0) {
            raiz.izquierdo = rotacionIzquierda(raiz.izquierdo);
            return rotacionDerecha(raiz);
        }

        // RR
        if (balance < -1 && factorBalance(raiz.derecho) <= 0)
            return rotacionIzquierda(raiz);

        // RL
        if (balance < -1 && factorBalance(raiz.derecho) > 0) {
            raiz.derecho = rotacionDerecha(raiz.derecho);
            return rotacionIzquierda(raiz);
        }

        return raiz;
    }

    // Impresión visual en árbol (bonita)
    public void imprimirArbol(NodoAVL nodo, String prefijo, boolean esIzquierdo) {
        if (nodo != null) {
            System.out.println(prefijo + (esIzquierdo ? "├── " : "└── ") + nodo.valor);
            imprimirArbol(nodo.izquierdo, prefijo + (esIzquierdo ? "│   " : "    "), true);
            imprimirArbol(nodo.derecho, prefijo + (esIzquierdo ? "│   " : "    "), false);
        }
    }
}
