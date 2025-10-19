/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_sp;

/**
 *
 * @author massi
 */
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final String FILENAME = "log.txt";
    private static PrintWriter writer;

    // Inicializa el logger y borra el contenido anterior del archivo
    public static void iniciar() {
        try {
            writer = new PrintWriter(new FileWriter(FILENAME, false)); // 'false' para sobrescribir
            log("--- INICIO DE LA SIMULACIÓN ---");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Escribe un mensaje en el archivo
    public static void log(String mensaje) {
        if (writer != null) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"));
            writer.println("[" + timestamp + "] " + mensaje);
            writer.flush(); // Asegura que el mensaje se escriba inmediatamente
        }
    }

    // Cierra el archivo de log
    public static void cerrar() {
        if (writer != null) {
            log("--- FIN DE LA SIMULACIÓN ---");
            writer.close();
        }
    }
}
