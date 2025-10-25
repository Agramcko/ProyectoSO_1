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

    /**
     * Método principal de selección.
     * Corregido: Se quitó <PCB> de los parámetros.
     */
    public PCB seleccionarProceso(Cola colaListos, String algoritmo, int cicloGlobal) {
        if (colaListos.estaVacia()) {
            return null;
        }

        switch (algoritmo) {
            case "SJF No Apropiativo":
                return seleccionarSJF(colaListos);
                
            case "Prioridad No Apropiativo":
                return seleccionarPorPrioridad(colaListos);
            
            case "HRRN (Highest Response Ratio Next)":
                return seleccionarHRRN(colaListos, cicloGlobal);

            // Algoritmos que simplemente sacan el primero de la cola
            case "Round Robin":
            case "Prioridad Apropiativo":
            case "SRT (Shortest Remaining Time)":
            case "FCFS":
            default:
                return colaListos.desencolar();
        }
    }

    /**
     * Lógica para SJF No Apropiativo.
     * Corregido: Se quitó <PCB>
     */
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

    /**
     * Lógica para Prioridad No Apropiativo.
     * Corregido: Se quitó <PCB>
     */
    private PCB seleccionarPorPrioridad(Cola colaListos) {
        Nodo actual = colaListos.getFrente();
        PCB procesoMasPrioritario = actual.getPcb();
        Nodo nodoDelMasPrioritario = actual;
        Nodo nodoAnteriorAlMasPrioritario = null;
        
        Nodo anterior = null;
        Nodo iterador = actual;

        while (iterador != null) {
            if (iterador.getPcb().getProcesoInfo().getPrioridad() < procesoMasPrioritario.getProcesoInfo().getPrioridad()) {
                procesoMasPrioritario = iterador.getPcb();
                nodoDelMasPrioritario = iterador;
                nodoAnteriorAlMasPrioritario = anterior;
            }
            anterior = iterador;
            iterador = iterador.getSiguiente();
        }

        if (nodoAnteriorAlMasPrioritario == null) {
            return colaListos.desencolar();
        } else {
            nodoAnteriorAlMasPrioritario.setSiguiente(nodoDelMasPrioritario.getSiguiente());
            if (nodoDelMasPrioritario.getSiguiente() == null) {
                colaListos.setFin(nodoAnteriorAlMasPrioritario);
            }
        }
        return procesoMasPrioritario;
    }

    /**
     * LÓGICA NUEVA PARA HRRN.
     * Corregido: Se quitó <PCB>
     */
    private PCB seleccionarHRRN(Cola colaListos, int cicloGlobal) {
        Nodo actual = colaListos.getFrente();
        PCB mejorProceso = actual.getPcb();
        Nodo nodoDelMejor = actual;
        Nodo nodoAnteriorAlMejor = null;
        
        double maxRatio = -1.0;

        Nodo anterior = null;
        Nodo iterador = actual;

        while (iterador != null) {
            PCB pcbActual = iterador.getPcb();
            int tiempoEnEspera = cicloGlobal - pcbActual.getTiempoDeLlegada();
            int tiempoDeRafaga = pcbActual.getProcesoInfo().getNumeroInstrucciones();
            
            double ratioRespuesta = 1.0;
            if (tiempoDeRafaga > 0) {
                 ratioRespuesta = (double) (tiempoEnEspera + tiempoDeRafaga) / tiempoDeRafaga;
            }

            if (ratioRespuesta > maxRatio) {
                maxRatio = ratioRespuesta;
                mejorProceso = pcbActual;
                nodoDelMejor = iterador;
                nodoAnteriorAlMejor = anterior;
            }
            anterior = iterador;
            iterador = iterador.getSiguiente();
        }

        if (nodoAnteriorAlMejor == null) {
            return colaListos.desencolar();
        } else {
            nodoAnteriorAlMejor.setSiguiente(nodoDelMejor.getSiguiente());
            if (nodoDelMejor.getSiguiente() == null) {
                colaListos.setFin(nodoAnteriorAlMejor);
            }
        }
        return mejorProceso;
    }

    // --- MÉTODOS DE "ESPIAR" ---

    /** Corregido: Se quitó <PCB> */
    public PCB verProcesoMasPrioritario(Cola colaListos) {
        if (colaListos.estaVacia()) {
            return null;
        }
        
        Nodo iterador = colaListos.getFrente();
        PCB procesoMasPrioritario = iterador.getPcb();

        while (iterador != null) {
            if (iterador.getPcb().getProcesoInfo().getPrioridad() < procesoMasPrioritario.getProcesoInfo().getPrioridad()) {
                procesoMasPrioritario = iterador.getPcb();
            }
            iterador = iterador.getSiguiente();
        }
        return procesoMasPrioritario;
    }
    
    /** Corregido: Se quitó <PCB> */
    public PCB verProcesoMasCortoRestante(Cola colaListos) {
        if (colaListos.estaVacia()) {
            return null;
        }

        Nodo iterador = colaListos.getFrente();
        PCB procesoMasCorto = iterador.getPcb();

        while (iterador != null) {
            if (iterador.getPcb().getTiempoEjecucionRestante() < procesoMasCorto.getTiempoEjecucionRestante()) {
                procesoMasCorto = iterador.getPcb();
            }
            iterador = iterador.getSiguiente();
        }
        return procesoMasCorto;
    }
}