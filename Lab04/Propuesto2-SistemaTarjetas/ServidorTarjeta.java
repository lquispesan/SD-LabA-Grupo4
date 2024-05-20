package SistemaTarjetas;

import java.rmi.Naming;
import java.rmi.RemoteException;

public class ServidorTarjeta {
    public ServidorTarjeta() {
        try {
            TarjetaCreditoInterface tarjeta1 = new TarjetaCredito("4111111111111111", "Visa", 5000);
            TarjetaCreditoInterface tarjeta2 = new TarjetaCredito("5111111111111111", "Mastercard", 3000);
            
            Naming.rebind("rmi://localhost/TarjetaService1", tarjeta1);
            Naming.rebind("rmi://localhost/TarjetaService2", tarjeta2);
            
            System.out.println("Servidor de tarjetas listo.");
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }

    public static void main(String args[]) {
        new ServidorTarjeta();
    }
}
