/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_sp;

/**
 * @author Alessandro Gramcko
 * @author massimo Gramcko
 */
public class Planificador {

    public PCB seleccionarProceso(Cola colaListos, String algoritmo) {
        if (colaListos.estaVacia()) {
            return null;
        }

        switch (algoritmo) {
            case "SJF No Apropiativo":
                return seleccionarSJF(colaListos);
            
            case "FCFS":
            default:
                // La lógica FCFS es simplemente tomar el primero de la cola.
                return colaListos.desencolar();
        }
    }

    private PCB seleccionarSJF(Cola colaListos) {
        Nodo actual = colaListos.getFrente();
        PCB procesoMasCorto = actual.getPcb();
        Nodo nodoDelMasCorto = actual;
        Nodo nodoAnteriorAlMasCorto = null;
        
        Nodo anterior = null;
        Nodo iterador = actual;

        // 1. Buscamos el proceso con la menor cantidad de instrucciones.
        while (iterador != null) {
            if (iterador.getPcb().getProcesoInfo().getNumeroInstrucciones() < procesoMasCorto.getProcesoInfo().getNumeroInstrucciones()) {
                procesoMasCorto = iterador.getPcb();
                nodoDelMasCorto = iterador;
                nodoAnteriorAlMasCorto = anterior;
            }
            anterior = iterador;
            iterador = iterador.getSiguiente();
        }

        // 2. Extraemos el nodo más corto de la cola.
        if (nodoAnteriorAlMasCorto == null) {
            // El proceso más corto es el primero de la cola.
            return colaListos.desencolar();
        } else {
            // El proceso más corto está en medio o al final de la cola.
            nodoAnteriorAlMasCorto.setSiguiente(nodoDelMasCorto.getSiguiente());
            if (nodoDelMasCorto.getSiguiente() == null) {
                // Si era el último, actualizamos el 'fin' de la cola.
                colaListos.setFin(nodoAnteriorAlMasCorto);
            }
        }
        
        return procesoMasCorto;
    }
}
