import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        mostrarMenu(entrada);
        entrada.close();
    }

    public static void mostrarMenu(Scanner entrada) {
        Menu.mostrar(entrada);
    }
}

   