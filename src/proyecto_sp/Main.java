/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_sp;

/**
 * @author Alessandro Gramcko
 * @author massimo Gramcko
 */
public class Main {

    public static void main(String[] args) {
        // 1. Inicialización de las estructuras del S.O.
        Cola colaListos = new Cola();
        // En el futuro, aquí crearás también la cola de bloqueados y terminados.
        int cicloGlobal = 0;

        // 2. Creación de procesos (Hardcode para la prueba inicial)
        // Creamos la información estática del proceso
        Proceso p1 = new Proceso("Navegador Web", 10);
        Proceso p2 = new Proceso("Editor de Texto", 7);
        Proceso p3 = new Proceso("Música", 12);

        // Creamos su PCB (Process Control Block)
        PCB pcb1 = new PCB(p1);
        PCB pcb2 = new PCB(p2);
        PCB pcb3 = new PCB(p3);
        
        // Los procesos "llegan" al sistema y se encolan en la lista de listos.
        pcb1.setEstado(PCB.EstadoProceso.LISTO);
        colaListos.encolar(pcb1);
        pcb2.setEstado(PCB.EstadoProceso.LISTO);
        colaListos.encolar(pcb2);
        pcb3.setEstado(PCB.EstadoProceso.LISTO);
        colaListos.encolar(pcb3);

        // 3. Bucle principal de simulación
        System.out.println("Iniciando simulación FCFS...");
        
        PCB procesoEnCpu = null; // Al principio la CPU está libre

        // El bucle principal se ejecuta mientras haya procesos por ejecutar o en la cola.
        while (procesoEnCpu != null || !colaListos.estaVacia()) {
            
            // SI LA CPU ESTÁ LIBRE, el planificador (FCFS) selecciona el siguiente proceso
            if (procesoEnCpu == null) {
                procesoEnCpu = colaListos.desencolar(); // Saca el primero de la cola
                if (procesoEnCpu != null) {
                   procesoEnCpu.setEstado(PCB.EstadoProceso.EJECUCION);
                   System.out.println("\n--- CICLO " + cicloGlobal + ": [SO] Planificador asigna CPU a " + procesoEnCpu.getProcesoInfo().getNombre() + " (ID: " + procesoEnCpu.getId() + ") ---");
                }
            }

            // Simular un ciclo de reloj
            cicloGlobal++;
            System.out.print("CICLO " + cicloGlobal + ": ");

            if (procesoEnCpu != null) {
                // [Proceso de Usuario] La CPU ejecuta una instrucción
                procesoEnCpu.setProgramCounter(procesoEnCpu.getProgramCounter() + 1);
                // El MAR también avanza, según el PDF [cite: 86]
                procesoEnCpu.setMemoryAddressRegister(procesoEnCpu.getProgramCounter()); 
                
                System.out.println("[Usuario] CPU ejecuta " + procesoEnCpu.getProcesoInfo().getNombre() + 
                                   ", PC=" + procesoEnCpu.getProgramCounter() + 
                                   ". Cola de Listos: " + colaListos.toString());

                // Verificar si el proceso terminó su ejecución
                if (procesoEnCpu.getProgramCounter() >= procesoEnCpu.getProcesoInfo().getNumeroInstrucciones()) {
                    procesoEnCpu.setEstado(PCB.EstadoProceso.TERMINADO);
                    System.out.println("----> [SO] Proceso " + procesoEnCpu.getProcesoInfo().getNombre() + " (ID: " + procesoEnCpu.getId() + ") ha terminado.");
                    procesoEnCpu = null; // La CPU queda libre
                }
            } else {
                System.out.println("[SO] CPU inactiva. Esperando procesos.");
            }

            // Pausa para poder leer la salida en la consola y simular el paso del tiempo
            try {
                Thread.sleep(500); // 500 milisegundos
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Simulación interrumpida");
            }
        }
        
        System.out.println("\n--- Simulación Finalizada --- Todos los procesos han terminado.");
    }
}
