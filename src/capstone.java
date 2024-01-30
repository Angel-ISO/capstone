public class capstone {
    public static void main(String[] args) {
        if (args.length != 5) {
            System.out.println("5 argumentos");
            return;
        }else{
            System.out.println("pase 5 argumentos mano");
        }

        int w = Integer.parseInt(args[0]);
        int h = Integer.parseInt(args[1]);
        int g = Integer.parseInt(args[2]);
        int s = Integer.parseInt(args[3]);
        String p = args[4];

        if (!(w == 10 || w == 20 || w == 30 || w == 80) || !(h == 10 || h == 20 || h == 40) || g <= 0 || !(s == 250 || s == 1000)) {
            System.out.println("no válidos.");
            return;
        }

        imprimirUbicacionCelulas(w, h, p);
    }

    private static void imprimirUbicacionCelulas(int w, int h, String p) {
        char[][] tablero = new char[h][w];
        String[] filas = p.split("#");

        for (int i = 0; i < h && i < filas.length; i++) {
            char[] filaChars = filas[i].toCharArray();
            for (int j = 0; j < w && j < filaChars.length; j++) {
                tablero[i][j] = filaChars[j];
            }
        }

        System.out.println("ubicar celula:");
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                System.out.print(tablero[i][j]);
            }
            System.out.println();
        }
    }
}
