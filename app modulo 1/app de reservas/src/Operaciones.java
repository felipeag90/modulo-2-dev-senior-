import java.util.ArrayList;
import java.util.Scanner;

class Operaciones {
    public static ArrayList<String> reservas = new ArrayList<>();
    public static ArrayList<Integer> horasReservas = new ArrayList<>();
    public static ArrayList<Integer> duracionesReservas = new ArrayList<>();
    public static ArrayList<String> servicios = new ArrayList<>();
    public static ArrayList<Integer> precios = new ArrayList<>();
    public static ArrayList<String> horarios = new ArrayList<>();
    public static ArrayList<Double>  total = new ArrayList<>();


    public static void inicializarServicios() {
        if (!servicios.isEmpty()) {
            return;
        }

        servicios.add("corte de cabello");
        precios.add(25000);
        horarios.add("8:00 - 17:00");

        servicios.add("tinte");
        precios.add(60000);
        horarios.add("8:00 - 17:00");

        servicios.add("manicure");
        precios.add(30000);
        horarios.add("8:00 - 17:00");
    }

    public static void mostrarServicios() {
        System.out.println("\nservicios disponibles:");
        for (int i = 0; i < servicios.size(); i++) {
            System.out.println((i + 1) + ". " + servicios.get(i)
                    + " - Precio: $" + precios.get(i)
                    + " - Horario: " + horarios.get(i));
        }
    }

    public static void verServicios() {
        mostrarServicios();
    }

    public static void hacerReserva(Scanner entrada) {
        mostrarServicios();

        System.out.print("Nombre: ");
        System.out.flush();
        String nombre = entrada.nextLine().trim();
        while (nombre.isEmpty()) {
            System.out.print("Nombre inválido. Ingrese un nombre válido: ");
            System.out.flush();
            nombre = entrada.nextLine().trim();
        }

        int cantidadServicios = leerEntero(entrada, "¿Cuántos servicios desea reservar? (1-3): ", 1, 3);
        if (cantidadServicios < 1 || cantidadServicios > 3) {
            System.out.println("Cantidad inválida.");
            return;
        }

        int[] serviciosSeleccionados = new int[cantidadServicios];
        for (int i = 0; i < serviciosSeleccionados.length; i++) {
            int indiceServicio = leerEntero(entrada, "Seleccione el servicio " + (i + 1) + " (1-3): ", 1, servicios.size()) - 1;

            if (indiceServicio < 0 || indiceServicio >= servicios.size()) {
                System.out.println("Servicio inválido.");
                return;
            }

            for (int j = 0; j < i; j++) {
                if (serviciosSeleccionados[j] == indiceServicio) {
                    System.out.println("No puede seleccionar el mismo servicio dos veces.");
                    return;
                }
            }

            serviciosSeleccionados[i] = indiceServicio;
        }

        int hora;
        int horaMaxima = calcularHoraMaximaDisponible(cantidadServicios);
        if (horaMaxima < 8) {
            System.out.println("No hay horarios disponibles para una reserva de " + cantidadServicios + " horas.");
            return;
        }

        while (true) {
            hora = leerEntero(entrada, "Hora de reserva (8-" + horaMaxima + "): ", 8, horaMaxima);
            if (hora == -1) {
                return;
            }

            if (!hayConflictoDeHora(hora, cantidadServicios)) {
                break;
            }
            System.out.println("La hora " + hora + " ya está reservada o se solapa con otra reserva. Elija otra.");
        }

        String resumen = nombre + " reservó ";
        for (int i = 0; i < serviciosSeleccionados.length; i++) {
            resumen += servicios.get(serviciosSeleccionados[i]);
            if (i < serviciosSeleccionados.length - 1) {
                resumen += ", ";
            }
        }
        resumen += " para las " + hora;

        double precioTotal = 0;
        for (int i = 0; i < serviciosSeleccionados.length; i++) {
            precioTotal += precios.get(serviciosSeleccionados[i]);
        }
        
        reservas.add(resumen);
        horasReservas.add(hora);
        duracionesReservas.add(cantidadServicios);
        total.add(precioTotal);
        System.out.println("Reserva registrada correctamente.");
        System.out.println("Total de esta reserva: $" + precioTotal);
    }

    public static void mostrarReservas() {
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas registradas.");
            return;
        }

        System.out.println("\nreservas registradas:");
        for (int i = 0; i < reservas.size(); i++) {
            System.out.println((i + 1) + ". " + reservas.get(i) 
            + " (inicio: " + horasReservas.get(i) + ", duración: " + duracionesReservas.get(i) + " horas)");
        }
    }

    public static void cancelarReserva(Scanner entrada) {
        System.out.println("Ingrese el nombre de quien hizo la reserva:");

        if (reservas.isEmpty()) {
            System.out.println("No hay reservas para cancelar.");
            return;
        }

        mostrarReservas();
        System.out.print("Nombre: ");
        System.out.flush();
        String nombreBuscado = entrada.nextLine().trim();

        if (nombreBuscado.isEmpty()) {
            System.out.println("Nombre inválido.");
            return;
        }

        boolean encontrada = false;
        for (int i = 0; i < reservas.size(); i++) {
            String reserva = reservas.get(i);
            if (reserva.toLowerCase().startsWith(nombreBuscado.toLowerCase())) {
                reservas.remove(i);
                horasReservas.remove(i);
                duracionesReservas.remove(i);
                total.remove(i);
                System.out.println("Reserva cancelada correctamente.");
                encontrada = true;
                break;
            }
        }

        if (!encontrada) {
            System.out.println("No se encontró una reserva para ese nombre.");
        }
    }

    public static boolean hayConflictoDeHora(int hora, int duracion) {
        int finNuevaReserva = hora + duracion;

        for (int i = 0; i < horasReservas.size(); i++) {
            int horaReservada = horasReservas.get(i);
            int duracionReservada = duracionesReservas.get(i);
            int finReservaExistente = horaReservada + duracionReservada;

            boolean agenda = Math.max(hora, horaReservada) < Math.min(finNuevaReserva, finReservaExistente);
            if (agenda) {
                return true;
            }
        }
        return false;
    }

    public static int calcularHoraMaximaDisponible(int duracion) {
        int maximo = -1;
        int ultimaHoraInicio = 17 - duracion;
        for (int hora = 8; hora <= ultimaHoraInicio; hora++) {
            if (!hayConflictoDeHora(hora, duracion)) {
                maximo = hora;
            }
        }
        return maximo;
    }

    public static void mostrarTotalesDia() {
        double totalDia = 0;
        for (double monto : total) {
            totalDia += monto;
        }

        int totalServicios = 0;
        for (int cantidad : duracionesReservas) {
            totalServicios += cantidad;
        }

        System.out.println("\nTotal ingresos del dia: $" + totalDia);
        System.out.println("Total servicios reservados del dia: " + totalServicios);
    }

    public static int leerEntero(Scanner entrada, String mensaje, int minimo, int maximo) {
        while (true) {
            if (mensaje != null && !mensaje.isEmpty()) {
                System.out.print(mensaje);
            }
            System.out.flush();
            if (!entrada.hasNextLine()) {
                return -1;
            }

            String linea = entrada.nextLine().trim();
            try {
                int valor = Integer.parseInt(linea);
                if (valor >= minimo && valor <= maximo) {
                    return valor;
                }
            } catch (NumberFormatException e) {
                // ignore and ask again
            }

            System.out.println("Entrada inválida. Debe ser un número entre " + minimo + " y " + maximo + ".");
        }
    }
}
    
