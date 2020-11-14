package csma;

import java.lang.Thread;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CA extends Thread {

    private String nombre;
    private int id;
    private int paqEnviar;

    public static int estado = 0;
    public static int retraso;
    public static String key = "";
    
    String black = "\033[30m";
    String red = "\033[31m";
    String green = "\033[32m";
    String yellow = "\033[33m";
    String blue = "\033[34m";
    String purple = "\033[35m";
    String cyan = "\033[36m";
    String white = "\033[37m";
    String reset = "\u001B[0m";


    public CA(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
        this.paqEnviar = id;
        retraso = (2 * paqEnviar) * 100;
        System.out.println("| Nombre: " + this.nombre + " | id: " + this.id);
    }

    public void sendPacket(int noPack) {
        System.out.println(this.nombre + " Paquete en tránsito " + noPack);
        if (noPack == this.id) {
            estado = 0;
            System.out.println(blue + "Se encuentra en estado ACK para el paquete " + this.nombre + "\n"+reset);
        }
    }

    @Override
    public void run() {
        int packetNb = 0;
        while (this.paqEnviar > 0) {
            if (estado == 0 && this.paqEnviar == this.id && key == "") {
                key = this.nombre;
                estado = 1;
                if (key == this.nombre) {
                    System.out.println(blue + this.nombre + "Estatus: RTC el siguiente número de paquetes: " + this.paqEnviar + reset);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(CA.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println(blue + "Nos encontramos en CLT " + this.paqEnviar + " paquetes " + "de " + this.nombre + reset);
                    packetNb += 1;
                    this.paqEnviar -= 1;
                    System.out.println(green + this.nombre + " Enviando número de paquetes: " + packetNb + reset);
                }
            } else if (estado == 1 && key == this.nombre) {
                this.paqEnviar -= 1;
                packetNb += 1;
                System.out.println(green + this.nombre + " Enviando número de paquetes: " + packetNb + reset);
                if (this.paqEnviar == 0) {
                    System.out.println(blue + "Se encuentra en estado ACK para el paquete " + this.nombre + reset);
                    estado = 0;
                    key = "";
                }
            } else {
                try {
                    System.out.println(yellow + this.nombre + "Cargando UwU..." + reset);
                    Thread.sleep(retraso);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(purple+this.nombre + "Terminó UwU \n"+reset);
    }
}
