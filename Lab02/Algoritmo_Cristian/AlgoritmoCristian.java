import java.util.Date;

public class AlgoritmoCristian {

    public static void main(String[] args) {
        // Simulamos que el servidor tiene el tiempo actual
        Date tiempoServidor = new Date();
        
        // Cliente solicita el tiempo al servidor
        Date tiempoSolicitudCliente = new Date();
        
        // Simulamos un tiempo de red
        long retardoRedEnMillis = 1000; // 1 segundo de retraso
        
        // Calculamos el tiempo que toma al mensaje ir y volver del servidor al cliente
        long tiempoAServidor = tiempoSolicitudCliente.getTime() + retardoRedEnMillis / 2;
        
        // Corregimos el tiempo recibido del servidor
        Date horaServidorRecibida = new Date(tiempoServidor.getTime() + tiempoAServidor);
        
        // Calculamos la desviación entre el tiempo del cliente y el del servidor
        long desviacion = horaServidorRecibida.getTime() - System.currentTimeMillis();
        
        // Resultados
        System.out.println("Desviación: " + desviacion + " ms");
        System.out.println("Hora local antes de ajuste: " + System.currentTimeMillis() + " ms");
        System.out.println("Hora del servidor: " + horaServidorRecibida.getTime() + " ms");
        System.out.println("Ajustando hora local...");
        long tiempoLocalAjustado = System.currentTimeMillis() + desviacion;
        System.out.println("Hora local después de ajuste: " + tiempoLocalAjustado + " ms");
    }
}
