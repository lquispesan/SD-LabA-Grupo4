package SistemaTarjetas;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TarjetaCreditoInterface extends Remote {
    public boolean validar(String numero) throws RemoteException;
    public String obtenerBanco(String numero) throws RemoteException;
    public boolean realizarCompra(String numero, double monto) throws RemoteException;
    public double obtenerSaldo(String numero) throws RemoteException;
}
