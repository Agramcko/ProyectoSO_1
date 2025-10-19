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
            case "Prioridad No Apropiativo": // <-- NUEVO CASO
                return seleccionarPorPrioridad(colaListos);
            
            case "SJF No Apropiativo":
                return seleccionarSJF(colaListos);
            
            case "Round Robin": // <-- NOTA: Round Robin también usa FCFS para elegir al siguiente
            case "FCFS":
            default:
                return colaListos.desencolar();
        }
    }

    // --- MÉTODO COMPLETAMENTE NUEVO ---
    // Busca en la cola el proceso con el número de prioridad más bajo (más prioritario).
    private PCB seleccionarPorPrioridad(Cola colaListos) {
        Nodo actual = colaListos.getFrente();
        PCB procesoMasPrioritario = actual.getPcb();
        Nodo nodoDelMasPrioritario = actual;
        Nodo nodoAnteriorAlMasPrioritario = null;
        
        Nodo anterior = null;
        Nodo iterador = actual;

        // 1. Buscamos el proceso con el número de prioridad más bajo.
        while (iterador != null) {
            if (iterador.getPcb().getProcesoInfo().getPrioridad() < procesoMasPrioritario.getProcesoInfo().getPrioridad()) {
                procesoMasPrioritario = iterador.getPcb();
                nodoDelMasPrioritario = iterador;
                nodoAnteriorAlMasPrioritario = anterior;
            }
            anterior = iterador;
            iterador = iterador.getSiguiente();
        }

        // 2. Extraemos ese nodo de la cola.
        if (nodoAnteriorAlMasPrioritario == null) {
            // El proceso más prioritario era el primero de la cola.
            return colaListos.desencolar();
        } else {
            // El proceso está en medio o al final de la cola.
            nodoAnteriorAlMasPrioritario.setSiguiente(nodoDelMasPrioritario.getSiguiente());
            if (nodoDelMasPrioritario.getSiguiente() == null) {
                // Si era el último, actualizamos el 'fin' de la cola.
                colaListos.setFin(nodoAnteriorAlMasPrioritario);
            }
        }
        
        return procesoMasPrioritario;
    }

    private PCB seleccionarSJF(Cola colaListos) {
        Nodo actual = colaListos.getFrente();
        PCB procesoMasCorto = actual.getPcb();
        Nodo nodoDelMasCorto = actual;
        Nodo nodoAnteriorAlMasCorto = null;
        
        Nodo anterior = null;
        Nodo iterador = actual;

        while (iterador != null) {
            if (iterador.getPcb().getProcesoInfo().getNumeroInstrucciones() < procesoMasCorto.getProcesoInfo().getNumeroInstrucciones()) {
                procesoMasCorto = iterador.getPcb();
                nodoDelMasCorto = iterador;
                nodoAnteriorAlMasCorto = anterior;
            }
            anterior = iterador;
            iterador = iterador.getSiguiente();
        }

        if (nodoAnteriorAlMasCorto == null) {
            return colaListos.desencolar();
        } else {
            nodoAnteriorAlMasCorto.setSiguiente(nodoDelMasCorto.getSiguiente());
            if (nodoDelMasCorto.getSiguiente() == null) {
                colaListos.setFin(nodoAnteriorAlMasCorto);
            }
        }
        
        return procesoMasCorto;
    }
}
