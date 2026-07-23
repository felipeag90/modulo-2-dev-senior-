package CuentaBanco;

public class EjemploBanco {
    public static class App {
        public static void main(String[] args) {
        CuentaBancaria cuenta = new CuentaBancaria("Ana Alvarado", "1001", 50000.0);

        System.out.println(cuenta);

        cuenta.depositar(20000);
        cuenta.retirar(100000);
        cuenta.retirar(30000);
        cuenta.depositar(-500);

        System.out.println("Saldo final (getter): $" + cuenta.getSaldo());
        System.out.println(cuenta);

    }

   
}

}
