/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_sp;

/**
 * @author Alessandro Gramcko
 * @author massimo Gramcko
 */

public class Cola {
    private Nodo frente; // El primer nodo de la cola
    private Nodo fin;   // El último nodo de la cola

    public Cola() {
        this.frente = null;
        this.fin = null;
    }

    // Método para agregar un PCB al final de la cola
    public void encolar(PCB pcb) {
        Nodo nuevoNodo = new Nodo(pcb);
        if (estaVacia()) {
            frente = nuevoNodo;
            fin = nuevoNodo;
        } else {
            fin.setSiguiente(nuevoNodo);
            fin = nuevoNodo;
        }
    }

    // Método para sacar el primer PCB de la cola
    public PCB desencolar() {
        if (estaVacia()) {
            return null;
        }
        Nodo nodoEliminado = frente;
        frente = frente.getSiguiente();
        if (frente == null) {
            fin = null; // La cola quedó vacía
        }
        return nodoEliminado.getPcb();
    }

    public boolean estaVacia() {
        return frente == null;
    }
    
    // Método para imprimir el contenido de la cola (útil para depurar)
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Nodo actual = frente;
        while (actual != null) {
            sb.append("ID:").append(actual.getPcb().getId());
            if (actual.getSiguiente() != null) {
                sb.append(", ");
            }
            actual = actual.getSiguiente();
        }
        sb.append("]");
        return sb.toString();
    }
}
