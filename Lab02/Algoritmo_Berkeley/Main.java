package Algoritmo_Berkeley;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Crear nodos
        Nodo nodoMaestro = new Nodo("Maestro", 100);
        Nodo nodoEsclavo1 = new Nodo("Esclavo1", 90);
        Nodo nodoEsclavo2 = new Nodo("Esclavo2", 20);
        Nodo nodoEsclavo3 = new Nodo("Esclavo3", 89);
        Nodo nodoEsclavo4 = new Nodo("Esclavo4", 0);

        // Lista de nodos esclavos
        List<Nodo> nodosEsclavos = new ArrayList<>();
        nodosEsclavos.add(nodoEsclavo1);
        nodosEsclavos.add(nodoEsclavo2);
        nodosEsclavos.add(nodoEsclavo3);
        nodosEsclavos.add(nodoEsclavo4);

        // Sincronizar los tiempos
        BerkeleyAlgorithm.sincronizarTiempos(nodoMaestro, nodosEsclavos);
    }
}
	
	

