/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_sp;

/**
 * @author Alessandro Gramcko
 * @author massimo Gramcko
 */

public class Nodo {
    private PCB pcb; // El dato que guardará el nodo
    private Nodo siguiente; // La referencia al siguiente nodo

    public Nodo(PCB pcb) {
        this.pcb = pcb;
        this.siguiente = null;
    }

    // Getters y Setters
    public PCB getPcb() {
        return pcb;
    }

    public void setPcb(PCB pcb) {
        this.pcb = pcb;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}