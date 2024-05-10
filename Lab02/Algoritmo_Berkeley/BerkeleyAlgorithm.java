package Algoritmo_Berkeley;
import java.util.ArrayList;
import java.util.List;

// Clase para el algoritmo de Berkeley
public class BerkeleyAlgorithm {

    // Función para calcular el promedio de una lista de tiempos
    private static int calcularPromedio(List<Integer> tiempos) {
        int suma = 0;
        for (int tiempo : tiempos) {
            suma += tiempo;
        }
        return suma / tiempos.size();
    }

    // Función principal del algoritmo
    public static void sincronizarTiempos(Nodo nodoMaestro, List<Nodo> nodosEsclavos) {
        // Calcular el tiempo del nodo maestro
        int tiempoMaestro = nodoMaestro.getTiempo();

        // Recolectar los tiempos de los nodos esclavos
        List<Integer> tiemposEsclavos = new ArrayList<>();
        for (Nodo nodo : nodosEsclavos) {
            tiemposEsclavos.add(nodo.getTiempo());
        }

        // Calcular el promedio de los tiempos de los nodos esclavos
        int promedioEsclavos = calcularPromedio(tiemposEsclavos);

        // Calcular el desfase entre el tiempo del nodo maestro y el promedio de los esclavos
        int desfase = promedioEsclavos - tiempoMaestro;

        // Ajustar el tiempo de los nodos esclavos
        for (Nodo nodo : nodosEsclavos) {
            int nuevoTiempo = nodo.getTiempo() - desfase;
            nodo.setTiempo(nuevoTiempo);
            System.out.println("El tiempo del nodo " + nodo.getNombre() + " ha sido ajustado a " + nuevoTiempo);
        }
    }
}