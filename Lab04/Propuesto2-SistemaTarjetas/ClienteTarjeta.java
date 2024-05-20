package SistemaTarjetas;

import java.rmi.Naming;
import java.util.Scanner;

public class ClienteTarjeta {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Ingrese el número de la tarjeta:");
        String numero = sc.next();
        
        TarjetaCreditoInterface tarjeta = null;
        
        if (numero.startsWith("4")) {
            tarjeta = (TarjetaCreditoInterface) Naming.lookup("rmi://localhost/TarjetaService1");
        } else if (numero.startsWith("5")) {
            tarjeta = (TarjetaCreditoInterface) Naming.lookup("rmi://localhost/TarjetaService2");
        } else {
            System.out.println("Número de tarjeta desconocido.");
            sc.close();
            return;
        }
        
        System.out.println("Seleccione una opción:\n" +
                           "1: Validar tarjeta\n" +
                           "2: Obtener banco\n" +
                           "3: Realizar compra\n" +
                           "4: Obtener saldo");

        int seleccion = sc.nextInt();

        switch (seleccion) {
            case 1:
                if (tarjeta.validar(numero)) {
                    System.out.println("Tarjeta válida.");
                } else {
                    System.out.println("Tarjeta inválida.");
                }
                break;

            case 2:
                System.out.println("Banco: " + tarjeta.obtenerBanco(numero));
                break;

            case 3:
                System.out.println("Ingrese el monto de la compra:");
                double monto = sc.nextDouble();
                if (tarjeta.realizarCompra(numero, monto)) {
                    System.out.println("Compra realizada.");
                } else {
                    System.out.println("Fondos insuficientes.");
                }
                break;

            case 4:
                double saldo = tarjeta.obtenerSaldo(numero);
                if (saldo != -1) {
                    System.out.println("Saldo: " + saldo);
                } else {
                    System.out.println("Número de tarjeta inválido.");
                }
                break;

            default:
                System.out.println("Opción no válida.");
                break;
        }

        sc.close();
    }
}
