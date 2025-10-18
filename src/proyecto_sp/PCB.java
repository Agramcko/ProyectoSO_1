/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_sp;

/**
 * @author Alessandro Gramcko
 * @author massimo Gramcko
 */

public class PCB {
    private static int contadorId = 0; // Para generar IDs únicos
    private int id;
    private EstadoProceso estado;
    private int programCounter;
    private int memoryAddressRegister;
    private Proceso procesoInfo; // Referencia a la información del proceso

    // Enum para los estados del proceso
    public enum EstadoProceso {
        NUEVO, LISTO, EJECUCION, BLOQUEADO, TERMINADO
    }

    public PCB(Proceso procesoInfo) {
        this.id = ++contadorId;
        this.procesoInfo = procesoInfo;
        this.estado = EstadoProceso.NUEVO;
        this.programCounter = 0;
        this.memoryAddressRegister = 0;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public EstadoProceso getEstado() {
        return estado;
    }

    public void setEstado(EstadoProceso estado) {
        this.estado = estado;
    }

    public int getProgramCounter() {
        return programCounter;
    }

    public void setProgramCounter(int programCounter) {
        this.programCounter = programCounter;
    }

    public int getMemoryAddressRegister() {
        return memoryAddressRegister;
    }

    public void setMemoryAddressRegister(int memoryAddressRegister) {
        this.memoryAddressRegister = memoryAddressRegister;
    }

    public Proceso getProcesoInfo() {
        return procesoInfo;
    }
}
