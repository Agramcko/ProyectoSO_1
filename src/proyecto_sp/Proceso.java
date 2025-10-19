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

    // 1. Este es tu nuevo constructor "maestro". Hace todo el trabajo.
    public Proceso(String nombre, int numeroInstrucciones, boolean esIoBound, int instruccionBloqueo, int prioridad) {
        this.nombre = nombre;
        this.numeroInstrucciones = numeroInstrucciones;
        this.esIoBound = esIoBound;
        this.instruccionBloqueo = instruccionBloqueo;
        this.prioridad = prioridad;
    }

    // 2. Este es el constructor que usabas para procesos con E/S.
    //    Ahora llama al constructor maestro, pasando una prioridad por defecto de 10.
    public Proceso(String nombre, int numeroInstrucciones, boolean esIoBound, int instruccionBloqueo) {
        this(nombre, numeroInstrucciones, esIoBound, instruccionBloqueo, 10); // Llama al constructor de arriba
    }

    // 3. Este es tu constructor original y más simple.
    //    Ahora también llama al maestro, pasando valores por defecto para E/S y prioridad.
    public Proceso(String nombre, int numeroInstrucciones) {
        this(nombre, numeroInstrucciones, false, -1, 10); // Llama al constructor de arriba
    }

    // --- Añade los Getters para las nuevas variables ---
    public int getPrioridad() {
        return prioridad;
    }
    public boolean esIoBound() {
        return esIoBound;
    }

    public int getInstruccionBloqueo() {
        return instruccionBloqueo;
    }
    // Getters
    public String getNombre() {
        return nombre;
    }

    public int getNumeroInstrucciones() {
        return numeroInstrucciones;
    }
}
