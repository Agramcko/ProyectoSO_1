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
    // Otros atributos como tipo (CPU/IO-bound), etc.
    private boolean esIoBound; 
    private int instruccionBloqueo; 
    
    public Proceso(String nombre, int numeroInstrucciones, boolean esIoBound, int instruccionBloqueo) {
        this.nombre = nombre;
        this.numeroInstrucciones = numeroInstrucciones;
        this.esIoBound = esIoBound;
        this.instruccionBloqueo = instruccionBloqueo;
    }
    
    public Proceso(String nombre, int numeroInstrucciones) {
        this(nombre, numeroInstrucciones, false, -1); // Un proceso normal no es I/O bound
    }

    // --- Añade los Getters para las nuevas variables ---
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
