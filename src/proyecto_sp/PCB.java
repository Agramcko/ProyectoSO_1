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
    private int tiempoRestanteBloqueo;
    private int quantumRestante;
    private int tiempoDeLlegada;
    private int tiempoDeFinalizacion;
    private int tiempoDeRetorno;
    private int tiempoDeEspera;
    private int tiempoEjecucionRestante;

    // Enum para los estados del proceso
    public enum EstadoProceso {
        NUEVO, LISTO, EJECUCION, BLOQUEADO, TERMINADO, LISTO_SUSPENDIDO
    }

    public PCB(Proceso procesoInfo) {
        this.id = ++contadorId;
        this.procesoInfo = procesoInfo;
        this.tiempoEjecucionRestante = procesoInfo.getNumeroInstrucciones();
        this.estado = EstadoProceso.NUEVO;
        this.programCounter = 0;
        this.memoryAddressRegister = 0;
        this.tiempoRestanteBloqueo = 0;
        this.quantumRestante = 0;
    }

    // Getters y Setters
    public int getTiempoEjecucionRestante() {
        return tiempoEjecucionRestante;
    }

    public void setTiempoEjecucionRestante(int tiempoEjecucionRestante) {
        this.tiempoEjecucionRestante = tiempoEjecucionRestante;
    }
    
    public int getTiempoDeLlegada() {
    return tiempoDeLlegada;
    }

    public void setTiempoDeLlegada(int tiempoDeLlegada) {
    this.tiempoDeLlegada = tiempoDeLlegada;
    }

    public int getTiempoDeFinalizacion() {
    return tiempoDeFinalizacion;
    }

    public void setTiempoDeFinalizacion(int tiempoDeFinalizacion) {
    this.tiempoDeFinalizacion = tiempoDeFinalizacion;
    }

    public int getTiempoDeRetorno() {
    return tiempoDeRetorno;
    }

    public void setTiempoDeRetorno(int tiempoDeRetorno) {
    this.tiempoDeRetorno = tiempoDeRetorno;
    }

    public int getTiempoDeEspera() {
    return tiempoDeEspera;
    }

    public void setTiempoDeEspera(int tiempoDeEspera) {
    this.tiempoDeEspera = tiempoDeEspera;
    }
    
    public int getQuantumRestante() {
        return quantumRestante;
    }

    public void setQuantumRestante(int quantumRestante) {
        this.quantumRestante = quantumRestante;
    }
    
    public int getTiempoRestanteBloqueo() {
        return tiempoRestanteBloqueo;
    }

    public void setTiempoRestanteBloqueo(int tiempoRestanteBloqueo) {
        this.tiempoRestanteBloqueo = tiempoRestanteBloqueo;
    }
    
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
