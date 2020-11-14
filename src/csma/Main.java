package csma;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        mainMethod();
    }

    public static void mainMethod() {

        Scanner scan = new Scanner(System.in);
        String nbClients, nbPackets, delay = "", patern;
        int numberHost = 0, nbInfo = 0;
        int time = 0;

        try {
            boolean validar = false;
            boolean validar2 = false;
            do {
                //System.out.println("No supe como reiniciar el programa sin meterme con los hilos xD");
                System.out.println("");
                System.out.print("***********************************************************************************************************"
                        + "\n**BIENVENIDO, ESTE PROGRAMA SIMULA EL ENVÍO DE INFORMACIÓN IMPLEMENTANDO LOS ALGORITMOS CSMA/CA O CSMA/CD**"
                        + "\n***********************************************************************************************************\n\n"
                        + "\n1. Porfavor ingresa el número de host en la red (no mayor a 999): "
                        + "\n------> ");
                nbClients = scan.next();

                System.out.print("2. ¿Cuántos paquetes quieren enviar por host? (no mayor a 999) "
                        + "\n------> ");
                nbPackets = scan.next();

                System.out.print("3. Elige entre CSMA/CA y CSMA/CD"
                        + "\n   a ------> CSMA/CA"
                        + "\n   d ------> CSMA/CD"
                        + "\n****El mas chido es el CD xD***"
                        + "\n------> ");
                patern = scan.next();

                if (!(patern.equalsIgnoreCase("a") || patern.equalsIgnoreCase("d"))) {
                    System.out.println(patern);
                    validar = false;
                    System.err.println("No existe esa opción");
                } else {

                    try {

                        numberHost = Integer.parseInt(nbClients);
                        nbInfo = Integer.parseInt(nbPackets);
                        if (numberHost <= 0 || nbInfo <= 0 || numberHost > 999 || nbInfo > 999) {
                            System.err.println("PON DATOS VÁLIDOS");
                            validar = false;
                        } else {
                            validar = true;
                        }

                    } catch (NumberFormatException e) {
                        System.err.println("PON DATOS VÁLIDOS");
                        validar = false;
                    }
                }
            } while (validar == false);

            //******************************************************************************************************************************************************************
            if (patern.equalsIgnoreCase("d")) {
                do {
                    if (patern.equalsIgnoreCase("d")) {
                        System.out.print("3. Ingresa el tiempo de retraso de cada proceso (milisegundos)\n------> ");
                        delay = scan.next();
                        System.out.println("");

                    }

                    try {
                        time = Math.round(Integer.parseInt(delay));
                        if (time > 0) {
                            validar2 = true;
                        } else {
                            validar2 = false;
                            System.err.println("TIEMPO DE RETRASO NO VÁLIDO, DEBEN DE SER ENTEROS");
                        }

                    } catch (NumberFormatException e) {
                        System.err.println("TIEMPO DE RETRASO NO VÁLIDO, DEBEN DE SER ENTEROS");
                        validar2 = false;
                    }
                } while (validar2 != true);
                CD[] hostArrayD = new CD[numberHost];

                for (int i = 0; i < numberHost; i++) {
                    hostArrayD[i] = new CD("Host " + (i + 1) + "", nbInfo, time);
                }
                for (int j = 0; j < numberHost; j++) {
                    hostArrayD[j].start();
                }
            } else if (patern.equalsIgnoreCase("a")) {
                CA[] hostArrayA = new CA[numberHost];

                for (int i = 0; i < numberHost; i++) {
                    hostArrayA[i] = new CA("HOST:" + (i + 1) + ":", nbInfo);
                }

                for (int j = 0; j < numberHost; j++) {
                    hostArrayA[j].start();
                }

            }

        } catch (NumberFormatException e) {
            System.err.println("PON DATOS VÁLIDOS");
        }

    }

}
