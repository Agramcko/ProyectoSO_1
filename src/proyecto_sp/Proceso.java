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

    public Proceso(String nombre, int numeroInstrucciones) {
        this.nombre = nombre;
        this.numeroInstrucciones = numeroInstrucciones;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public int getNumeroInstrucciones() {
        return numeroInstrucciones;
    }
}
