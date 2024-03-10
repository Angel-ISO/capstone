public class Capstone {

    public static void main(String[] args) {
        if (args.length != 6) {
            System.out.println("Debe ingresar 6 argumentos.");
            return;
        } else {
            System.out.println("Ha ingresado correctamente los 6 argumentos. pero revise que sean los solicitados.");
        }


        int w = 0;
        int h = 0;
        int g = 0;
        int s = 0;
        String p = "";
        int e = 0;


        for (String arg : args) {
            String[] partesitas = arg.split("=");
            if (partesitas.length == 2) {
                String nombre = partesitas[0].trim();
                String valor = partesitas[1].trim();
                switch (nombre) {
                    case "w":
                        w = Integer.parseInt(valor);
                        break;
                    case "h":
                        h = Integer.parseInt(valor);
                        break;
                    case "g":
                        g = Integer.parseInt(valor);
                        break;
                    case "s":
                        s = Integer.parseInt(valor);
                        break;
                    case "p":
                        p = valor;
                        break;
                    case "e":
                        e = Integer.parseInt(valor);
                        break;
                }
            }
        }

        if (!validarArgumentos(w, h, g, s, p, e)) {
            System.out.println("Argumentos no válidos.");
            return;
        }


        System.out.println("los argumentos fueron : \t" + w + "\t" + h+"\t"+ g +"\t" +s + "\t" +p  );


        System.out.println("patron inicial");
        Tabla tablero = new Tabla(w, h);
        tablero.ubicacionPatron(p);
        tablero.mostrarTablero();



int generacionActual = 0;
        // Bajo este simple bucle paso las generaciones como límite de ejecución para mi juego
        for (int i = 0; i < g; i++) {
            System.out.println("generacion numero " + (i + 1));
            generacionActual++;
            if (generacionActual == e) {
                tablero.invertirCelula();
                tablero.reescritoPat();
            } else {
                tablero.siguienteGeneracion();
            }
            tablero.mostrarTablero();
            pausas(s);
        }


    }

    private static boolean validarArgumentos(int w, int h, int g, int s, String p, int e) {
        boolean medidasValidas = (w == 10 || w == 20 || w == 30 || w == 80) && (h == 10 || h == 20 || h == 40);
        boolean generacionesValidas = g >= 0;
        boolean velocidadValida = s == 250 || s == 1000 || s == 3000;
        boolean patrónValido = validarPatron(p);
        boolean revezPatron = e <= g;
        return medidasValidas && generacionesValidas && velocidadValida && patrónValido && revezPatron;
    }

    private static boolean validarPatron(String p) {
        for (char c : p.toCharArray()) {
            if (c != '1' && c != '0' && c != '#') {
                System.out.println("asegurece en el parametro p pasar solamente los caracteres (1,0,#)  #para nueva fila, 0 para celula muerta y 1 para celula viva. Porfa");
                return false;
            }
        }
        return true;
    }


    private static void pausas(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException ex) {
            System.out.println("error");
        }
    }


}



class Tabla {
    private int ancho;
    private int alto;
    private boolean[][] celulas;


    public Tabla(int ancho, int alto ) {
        this.ancho = ancho;
        this.alto = alto;
        celulas = new boolean[alto][ancho];
    }




    public boolean[][] getCelulas() {
        return celulas;
    }

    public void setCelulas(boolean[][] celulas) {
        this.celulas = celulas;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public void ubicacionPatron(String patronInicial) {
        String[] filas = patronInicial.split("#");
        for (int i = 0; i < Math.min(alto, filas.length); i++) {
            String fila = filas[i];
            for (int j = 0; j < Math.min(ancho, fila.length()); j++) {
                celulas[i][j] = (fila.charAt(j) == '1');
            }
        }
    }

    public void mostrarTablero() {
        for (boolean[] fila : celulas) {
            for (boolean celula : fila) {
                System.out.print(celula ? "1" : "0");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void siguienteGeneracion() {
        boolean[][] nuevoEstado = new boolean[alto][ancho];
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                int vecinosVivos = vecinosV(i, j);
                nuevoEstado[i][j] = reglasj(celulas[i][j], vecinosVivos);
            }
        }
        celulas = nuevoEstado;
    }

    private int vecinosV(int fila, int columna) {
        int vivos = 0;
        for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = columna - 1; j <= columna + 1; j++) {
                if (i >= 0 && i < alto && j >= 0 && j < ancho && !(i == fila && j == columna) && celulas[i][j]) {
                    vivos++;
                }
            }
        }
        return vivos;
    }

    private boolean reglasj(boolean celulaSeg, int vecinitos) {
        if (celulaSeg) {
            return vecinitos == 2 || vecinitos == 3;
        } else {
            return vecinitos == 3;
        }
    }

    public void invertirCelula() {
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                celulas[i][j] = !celulas[i][j];
            }
        }
    }


    public void reescritoPat() {
        StringBuilder nuevoPatron = new StringBuilder();
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                nuevoPatron.append(celulas[i][j] ? "1" : "0");
            }
            nuevoPatron.append("#");
        }

        nuevoPatron.deleteCharAt(nuevoPatron.length() - 1);
        ubicacionPatron(nuevoPatron.toString());
    }


}