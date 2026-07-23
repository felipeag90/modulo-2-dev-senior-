import java.util.Scanner;

class Validador {

    public static int leerOpcion(Scanner entrada, String mensaje) {
        while (true) {
            if (!entrada.hasNextLine()) {
                return -1;
            }

            String linea = entrada.nextLine().trim();

            try {
                int opcion = Integer.parseInt(linea);
                return opcion;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entre 1 y 5.");
            }
        }
    }

    public static String nombreValido(Scanner entrada, String mensaje) {
        while (true) {
            if (!entrada.hasNextLine()) {
                return "";
            }

            System.out.print((mensaje != null && !mensaje.isEmpty() ? mensaje : "Digite su nombre") + ": ");
            String nombre = entrada.nextLine();
            if (nombre != null) {
                nombre = nombre.trim();
                boolean esValido = nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+");
                if (esValido && !nombre.isEmpty()) {
                    return nombre;
                }
            }
            System.out.println("Entrada inválida. Por favor, ingrese un nombre válido.");
        }
    }

    public static int horaReserva(Scanner entrada, String mensaje) {
        while (true) {
            if (!entrada.hasNextLine()) {
                return -1;
            }

            System.out.println(mensaje != null && !mensaje.isEmpty() ? mensaje : "Escoge tu hora de reserva entre 8 y 17:");
            String linea = entrada.nextLine().trim();

            try {
                int opcion = Integer.parseInt(linea);
                if (opcion >= 8 && opcion <= 17) {
                    return opcion;
                }
                System.out.println("Entrada inválida. Por favor, ingrese un número entre 8 y 17.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entre 8 y 17.");
            }
        }
    }

    public static int servicioValido(Scanner entrada, String mensaje) {
        while (true) {
            if (!entrada.hasNextLine()) {
                return -1;
            }

            System.out.println(mensaje != null && !mensaje.isEmpty() ? mensaje : "Escoge tu servicio (1-3):");
            String linea = entrada.nextLine().trim();

            try {
                int opcion = Integer.parseInt(linea);
                return opcion;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entre 1 y 3.");
            }
        }
    }

    public static double totalGanancias(Scanner entrada, String mensaje) {
        while (true) {
            if (!entrada.hasNextLine()) {
                return -1;
            }

            System.out.println(mensaje != null && !mensaje.isEmpty() ? mensaje : "el total de ganancias de hoy es:");
            String linea = entrada.nextLine().trim();
            try {
                double total = Double.parseDouble(linea);
                return total;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número válido.");
            }
        }
    }


}
