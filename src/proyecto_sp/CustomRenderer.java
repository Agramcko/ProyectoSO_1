/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_sp;

/**
 * @author Alessandro Gramcko
 * @author massimo Gramcko
 */
import java.awt.Color;
import java.awt.Paint;
import org.jfree.chart.renderer.category.BarRenderer;

public class CustomRenderer extends BarRenderer {

    private final Paint[] colors;

    public CustomRenderer() {
        // Define aquí la paleta de colores que se usará para las barras
        this.colors = new Paint[]{
            new Color(255, 87, 87),  // Rojo
            new Color(87, 117, 255), // Azul
            new Color(87, 255, 106), // Verde
            new Color(255, 165, 0),  // Naranja
            new Color(238, 130, 238), // Violeta
            new Color(0, 255, 255)   // Cian
        };
    }

    // Este método se llama para cada barra que se va a dibujar
    @Override
    public Paint getItemPaint(final int row, final int column) {
        // Devuelve un color de nuestra paleta basado en el índice de la columna (del algoritmo)
        return this.colors[column % this.colors.length];
    }
}
