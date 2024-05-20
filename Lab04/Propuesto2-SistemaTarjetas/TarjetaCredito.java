package SistemaTarjetas;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class TarjetaCredito extends UnicastRemoteObject implements TarjetaCreditoInterface {
    private String numero;
    private String banco;
    private double saldo;

    public TarjetaCredito(String numero, String banco, double saldo) throws RemoteException {
        super();
        this.numero = numero;
        this.banco = banco;
        this.saldo = saldo;
    }

    @Override
    public boolean validar(String numero) throws RemoteException {
        return this.numero.equals(numero);
    }

    @Override
    public String obtenerBanco(String numero) throws RemoteException {
        if (this.numero.equals(numero)) {
            return this.banco;
        } else {
            return "Desconocido";
        }
    }

    @Override
    public boolean realizarCompra(String numero, double monto) throws RemoteException {
        if (this.numero.equals(numero) && this.saldo >= monto) {
            this.saldo -= monto;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public double obtenerSaldo(String numero) throws RemoteException {
        if (this.numero.equals(numero)) {
            return this.saldo;
        } else {
            return -1;
        }
    }
}
