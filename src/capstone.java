public class capstone {
    public static void main(String[] args) {
        if (args.length != 5) {
            System.out.println("5 argumentos mano.");
            return;
        } else {
            System.out.println("argumentos correctos.");
        }

        int w = Integer.parseInt(args[0]);
        int h = Integer.parseInt(args[1]);
        int g = Integer.parseInt(args[2]);
        int s = Integer.parseInt(args[3]);
        String p = args[4];

        if (!validarArgumentos(w, h, g, s)) {
            System.out.println("no validos.");
            return;
        }

        System.out.println("los argumentos fueron \t:" + w + "\t" + h+"\t"+ g +"\t" +s + "\t" +p  );
    }

    private static boolean validarArgumentos(int w, int h, int g, int s) {
        boolean medidas = (w == 10 || w == 20 || w == 30 || w == 80) && (h == 10 || h == 20 || h == 40);
        boolean generaciones = g > 0;
        boolean pausas = s == 250 || s == 1000;
        return medidas && generaciones && pausas;
    }
}
