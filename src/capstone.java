import java.util.Scanner;



public class capstone {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        System.out.print("Ingresa filas: ");
        int filas = myScanner.nextInt();

        System.out.print("Ingresa columnas: ");
        int columnas = myScanner.nextInt();

        int[][] tablero = new int[filas][columnas];

        System.out.print("Ingresa el número de celulas vivas porfavor: ");
        int numCelulasVivas = myScanner.nextInt();

        for (int i = 0; i < numCelulasVivas; i++) {
            System.out.print("Ingresa la fila de la celula " + (i + 1) + ": ");
            int fila = myScanner.nextInt();

            System.out.print("Ingresa la columna de la celula " + (i + 1) + ": ");
            int columna = myScanner.nextInt();

            if (fila >= 0 && fila < filas && columna >= 0 && columna < columnas) {
                tablero[fila][columna] = 1;
            } else {
                System.out.println("esa ubicacion no existe");
                i--;
            }
        }

        imprimirTablero(tablero);
    }

    private static void imprimirTablero(int[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j] == 1 ? "{X}" : "{}");
            }
            System.out.println();
        }
    }
}