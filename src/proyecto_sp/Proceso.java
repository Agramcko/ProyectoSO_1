/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_sp;

/**
 * @author Alessandro Gramcko
 * @author massimo Gramcko
 */

public class Proceso {
    private String nombre;
    private int numeroInstrucciones;
    private boolean esIoBound;
    private int instruccionBloqueo;
    private int prioridad;
    private int tamañoEnMemoria;

    // 1. Constructor "maestro" que incluye todos los parámetros.
    public Proceso(String nombre, int numeroInstrucciones, boolean esIoBound, int instruccionBloqueo, int prioridad, int tamañoEnMemoria) {
        this.nombre = nombre;
        this.numeroInstrucciones = numeroInstrucciones;
        this.esIoBound = esIoBound;
        this.instruccionBloqueo = instruccionBloqueo;
        this.prioridad = prioridad;
        this.tamañoEnMemoria = tamañoEnMemoria;
    }

    // 2. Constructores antiguos que llaman al "maestro" con valores por defecto.
    public Proceso(String nombre, int numeroInstrucciones, boolean esIoBound, int instruccionBloqueo, int prioridad) {
        this(nombre, numeroInstrucciones, esIoBound, instruccionBloqueo, prioridad, 100); 
    }
    
    public Proceso(String nombre, int numeroInstrucciones, boolean esIoBound, int instruccionBloqueo) {
        this(nombre, numeroInstrucciones, esIoBound, instruccionBloqueo, 10, 100);
    }

    public Proceso(String nombre, int numeroInstrucciones) {
        this(nombre, numeroInstrucciones, false, -1, 10, 100);
    }

    // --- Getters ---
    public String getNombre() { return nombre; }
    public int getNumeroInstrucciones() { return numeroInstrucciones; }
    public boolean esIoBound() { return esIoBound; }
    public int getInstruccionBloqueo() { return instruccionBloqueo; }
    public int getPrioridad() { return prioridad; }
    public int getTamañoEnMemoria() { return tamañoEnMemoria; }
}
