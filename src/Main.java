import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AVL arbol = new AVL();
        NodoAVL raiz = null;

        int opcion;

        do {
            System.out.println("\n--- MENÚ ÁRBOL AVL ---");
            System.out.println("1. Insertar");
            System.out.println("2. Eliminar");
            System.out.println("3. Mostrar árbol");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el número a insertar: ");
                    int ins = sc.nextInt();
                    raiz = arbol.insertar(raiz, ins);
                    break;

                case 2:
                    System.out.print("Ingrese el número a eliminar: ");
                    int del = sc.nextInt();
                    raiz = arbol.eliminar(raiz, del);
                    break;

                case 3:
                    System.out.println("\nÁrbol AVL:");
                    arbol.imprimirArbol(raiz, "", false);
                    break;

                case 4:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida.");
                    break;
            }

        } while (opcion != 4);

        sc.close();
    }
}
