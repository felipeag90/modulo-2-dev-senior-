import java.util.Scanner;

public class Menu {

    public static void mostrar(Scanner entrada) {
        Operaciones.inicializarServicios();

        int opcion;
        do {
            System.out.println("\nMenu de opciones:");
            System.out.println("1. ver servicios");
            System.out.println("2. hacer reserva");
            System.out.println("3. ver reservas");
            System.out.println("4. cancelar una reserva");
            System.out.println("5. total ganancias" );
            System.out.println("6. salir");
            System.out.print("Ingrese una opción: ");
            System.out.flush();

            opcion = Validador.leerOpcion(entrada, "");
            if (opcion == -1) {
                break;
            }

            switch (opcion) {
                case 1:
                    Operaciones.mostrarServicios();
                    break;
                case 2:
                    Operaciones.hacerReserva(entrada);
                    break;
                case 3:
                    Operaciones.mostrarReservas();
                    break;
                case 4:
                    System.out.println("Cancelando reserva...");
                    Operaciones.cancelarReserva(entrada);
                    break;
                case 5:
                    Operaciones.mostrarTotalesDia();
                    break;
                case 6:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 6);
    }
}

