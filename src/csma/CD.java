package csma;

public class CD extends Thread {

    public static int colisionesT = 0;
    public static int estadoI = 0;
    public static int fallos = 0;

    private int contColisiones = 0;
    private String nombre;
    private int id;
    private int retraso;
    private long cronometro = 0;

    Timer timer = new Timer();

    String black = "\033[30m";
    String red = "\033[31m";
    String green = "\033[32m";
    String yellow = "\033[33m";
    String blue = "\033[34m";
    String purple = "\033[35m";
    String cyan = "\033[36m";
    String white = "\033[37m";
    String reset = "\u001B[0m";

    /**
     * 1 --> Clear to send 2 --> Request to send 0 --> Iniciando
     *
     * @param name
     * @param id
     * @param retraso
     */
    public CD(String name, int id, int retraso) {
        this.nombre = name;
        this.id = id;
        this.retraso = retraso;
        //System.out.println("***********************************************************************************************");
        System.out.println("| Nombre: " + this.nombre + " | id: " + this.id + " | Retraso de: " + this.retraso + "ms | ");
        //System.out.println("***********************************************************************************************\n");
    }

    public void tiempo() {
        cronometro = java.lang.System.currentTimeMillis();
    }

    public void pararCronómetro() {
        long timeChrono2 = java.lang.System.currentTimeMillis();
        cronometro = timeChrono2 - cronometro;
        System.out.println(nombre + " retraso de " + cronometro + " ms");
    }

    @Override
    public void run() {
        tiempo();
        for (int noPaquete = 1; noPaquete <= id; noPaquete++) {
            contColisiones = 0;
            while (contColisiones < 10) {
                if (estadoI == 0) {
                    //System.out.println("");
                    System.out.println(nombre + " está iniciando...");
                    try {
                        Thread.sleep(retraso);
                        System.out.println(blue + nombre + " Entro a hibernación por un tiempo de: " + retraso + reset);
                    } catch (InterruptedException e) {
                        System.err.println("Fallo de interrumpción");
                    }
                    estadoI++;
                }
                try {
                    Thread.sleep(retraso);
                } catch (InterruptedException e) {
                    System.err.println("Fallo de interrumpción");
                }
                if (estadoI == 1) {
                    System.out.println(cyan + nombre + " se encuentra en 'CLEAR TO SEND'" + reset);
                    System.err.println(green + nombre + " Paquete no. °" + noPaquete + " se envió con éxito" + reset);
                    estadoI = 0;
                    break;
                }
                if (estadoI >= 2) {
                    System.out.println(cyan + nombre + " se encuentra en 'REQUEST TO SEND'" + reset);
                    contColisiones++;
                    colisionesT++;
                    System.out.println(red + nombre + " Paquete no. °" + noPaquete + " colisionó!" + reset);
                    estadoI = 0;
                    try {
                        //mandando valor a Timer.......
                        int sleepingTimeRandom = (2 * retraso * timer.randomHibernación(contColisiones));
                        Thread.sleep(sleepingTimeRandom);
                        System.out.println(cyan + nombre + " Entrando en hibernación en función del número de colisiones hechas por: " + sleepingTimeRandom + reset);
                    } catch (InterruptedException e) {
                        System.err.println("Fallo de interrumpción");
                    }
                }
            }
            if (contColisiones >= 10) {
                System.err.println("El Host " + nombre + " con el paquete no." + noPaquete + " ha fallado en la transmisión ");
                fallos++;
            }
        }
        pararCronómetro();
        System.err.println(purple + nombre + " ¡TODOS LOS PAQUETES SE HAN ENVIADO CON ÉXITO!" + reset);
        System.out.println(purple + nombre +" No. total de colisiones: " + colisionesT + reset);
        System.out.println(purple + nombre +" No. total de fallas : " + fallos + reset);
       //System.out.println("\n\n\n");
        
    }
}
