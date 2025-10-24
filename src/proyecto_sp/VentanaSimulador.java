/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto_sp;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import java.awt.BorderLayout;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Semaphore;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JFileChooser;
import java.util.Random;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
/**
 * @author Alessandro Gramcko
 * @author massimo Gramcko
 */
public class VentanaSimulador extends javax.swing.JFrame implements Runnable {

    private static final int QUANTUM = 4;
    private static final int MEMORIA_TOTAL_MB = 2048; 
    private Cola colaListos;
    private Cola colaTerminados;
    private Cola colaBloqueados;
    private Cola colaListosSuspendidos;
    private Cola colaBloqueadosSuspendidos;
    private int cicloGlobal;
    private int ciclosOcupado;
    private int memoriaEnUso;
    private PCB procesoEnCpu;
    private Planificador planificador;
    private XYSeries seriesUtilizacionCPU;
    private JFreeChart grafico;
    private final Semaphore mutex = new Semaphore(1); // Semáforo con 1 permiso (mutex)
    private volatile boolean simulacionPausada = false;
    private final Random random = new Random();
    private final Map<String, MetricasAlgoritmo> metricasEnTiempoReal = new HashMap<>();
    private final java.util.List<Proceso> listaMaestraProcesos = new java.util.ArrayList<>();
    
    
    
    public VentanaSimulador() {
    initComponents();
    
    for (int i = 0; i < cmbAlgoritmo.getItemCount(); i++) {
    String algoritmo = cmbAlgoritmo.getItemAt(i);
    metricasEnTiempoReal.put(algoritmo, new MetricasAlgoritmo());
}
    
    // --- AÑADE ESTAS LÍNEAS ---
    this.colaListos = new Cola();
    this.colaTerminados = new Cola();
    this.colaBloqueados = new Cola();
    this.colaListosSuspendidos = new Cola();
    this.colaBloqueadosSuspendidos = new Cola();
    this.cicloGlobal = 0;
    this.ciclosOcupado = 0;
    this.procesoEnCpu = null;
    this.planificador = new Planificador();
    inicializarGrafico();
    cargarConfiguracion();
   
    addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            guardarConfiguracion();
        }
    });
    // -------------------------

    // Ahora el resto de tu código funcionará correctamente
    //Proceso p1 = new Proceso("Navegador Web", 10);
    //Proceso p2 = new Proceso("Editor de Texto", 7);
   // Proceso p3 = new Proceso("Música", 12);

    // Creamos su PCB (Process Control Block)
   // PCB pcb1 = new PCB(p1);
    //PCB pcb2 = new PCB(p2);
   // PCB pcb3 = new PCB(p3);

    // Los procesos "llegan" al sistema y se encolan en la lista de listos.
   // pcb1.setEstado(PCB.EstadoProceso.LISTO);
   // colaListos.encolar(pcb1);
   // pcb2.setEstado(PCB.EstadoProceso.LISTO);
   // colaListos.encolar(pcb2);
   // pcb3.setEstado(PCB.EstadoProceso.LISTO);
   // colaListos.encolar(pcb3);
}
    
private void actualizarGUI() {
    // Actualiza el ciclo actual
    lblCicloActual.setText(String.valueOf(cicloGlobal));

    // Actualiza la información del proceso en CPU y el modo de ejecución
    if (procesoEnCpu != null) {
        // Modo Usuario: Un proceso está en la CPU
        lblModoEjecucion.setText("Modo: Usuario"); // <-- LÍNEA AÑADIDA
        lblProcesoCPU.setText(procesoEnCpu.getProcesoInfo().getNombre() + " (ID: " + procesoEnCpu.getId() + ")");
        lblProgramCounter.setText(String.valueOf(procesoEnCpu.getProgramCounter()));
        lblMAR.setText(String.valueOf(procesoEnCpu.getProgramCounter()));
    } else {
        // Modo Kernel: La CPU está libre, el SO está decidiendo qué hacer
        lblModoEjecucion.setText("Modo: Kernel"); // <-- LÍNEA AÑADIDA
        lblProcesoCPU.setText("N/A");
        lblProgramCounter.setText("0");
        lblMAR.setText("-");
    }

    // Actualiza el JTextArea de las colas (esto no cambia)
    txtColaListos.setText(colaListos.toString());
    txtTerminados.setText(colaTerminados.toString());
    txtColaBloqueados.setText(colaBloqueados.toString());
    txtColaListosSuspendidos.setText(colaListosSuspendidos.toString());
    txtColaBloqueadosSuspendidos.setText(colaBloqueadosSuspendidos.toString());
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        Pestaña1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtColaListos = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtColaListosSuspendidos = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtColaBloqueadosSuspendidos = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtColaBloqueados = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtTerminados = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtNombreProceso = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        spnInstrucciones = new javax.swing.JSpinner();
        chkIoBound = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        spnInstruccionIO = new javax.swing.JSpinner();
        jLabel12 = new javax.swing.JLabel();
        spnPrioridad = new javax.swing.JSpinner();
        jLabel13 = new javax.swing.JLabel();
        spnTamaño = new javax.swing.JSpinner();
        btnCrearProceso = new javax.swing.JButton();
        btnCrearRandom = new javax.swing.JButton();
        btnCargarArchivo = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblProcesoCPU = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblProgramCounter = new javax.swing.JLabel();
        lblModoEjecucion = new javax.swing.JLabel();
        lblMAR = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblCicloActual = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cmbAlgoritmo = new javax.swing.JComboBox<>();
        btnIniciar = new javax.swing.JButton();
        btnPausa = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        spnVelocidad = new javax.swing.JSpinner();
        panelGrafico = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtMetricas = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtProcesosCreados = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        panelGraficoThroughputs = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        panelGraficoDistribucion = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        panelGraficoTiempos = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(153, 204, 255));

        jLabel1.setText("                    Cola de Listos");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtColaListos.setColumns(20);
        txtColaListos.setRows(5);
        jScrollPane1.setViewportView(txtColaListos);

        txtColaListosSuspendidos.setColumns(20);
        txtColaListosSuspendidos.setRows(5);
        jScrollPane5.setViewportView(txtColaListosSuspendidos);

        jLabel14.setText("           Cola de Listos-Suspendidos");
        jLabel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel16.setText("    Cola de Bloqueados-Suspendidos");
        jLabel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtColaBloqueadosSuspendidos.setColumns(20);
        txtColaBloqueadosSuspendidos.setRows(5);
        jScrollPane6.setViewportView(txtColaBloqueadosSuspendidos);

        txtColaBloqueados.setColumns(20);
        txtColaBloqueados.setRows(5);
        jScrollPane2.setViewportView(txtColaBloqueados);

        jLabel2.setText("              Cola de Bloqueados");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("            Procesos Terminados");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtTerminados.setColumns(20);
        txtTerminados.setRows(5);
        jScrollPane3.setViewportView(txtTerminados);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
                .addGap(39, 39, 39)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6))
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(23, 23, 23))
        );

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));

        jLabel7.setText("Nombre del Proceso:");

        jLabel8.setText("Número de Instrucciones:");

        chkIoBound.setText("Es I/O-Bound");

        jLabel10.setText("Instrucción de E/S:");

        jLabel12.setText("Prioridad:");

        spnPrioridad.setModel(new javax.swing.SpinnerNumberModel(10, null, null, 1));

        jLabel13.setText("Tamaño (MB):");

        spnTamaño.setModel(new javax.swing.SpinnerNumberModel(100, null, null, 1));

        btnCrearProceso.setText("Crear Proceso");
        btnCrearProceso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearProcesoActionPerformed(evt);
            }
        });

        btnCrearRandom.setText("Crear 20 Random");
        btnCrearRandom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearRandomActionPerformed(evt);
            }
        });

        btnCargarArchivo.setText("Cargar Archivo");
        btnCargarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarArchivoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCrearRandom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCrearProceso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spnInstrucciones, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombreProceso, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(chkIoBound)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spnPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(spnInstruccionIO, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spnTamaño, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnCargarArchivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtNombreProceso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(spnInstrucciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkIoBound)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnInstruccionIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(spnPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(spnTamaño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnCrearProceso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCrearRandom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCargarArchivo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));

        jLabel4.setText("Proceso en CPU:");

        lblProcesoCPU.setBackground(new java.awt.Color(255, 255, 255));
        lblProcesoCPU.setText("N/A");

        jLabel5.setText("Program Counter:");

        lblProgramCounter.setText("0");

        lblModoEjecucion.setText("Modo: Kernel");

        lblMAR.setText("MAR: -");

        jLabel6.setText("Ciclo Actual:");

        lblCicloActual.setText("0");

        jLabel9.setText("Algoritmo:");

        cmbAlgoritmo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FCFS", "SJF No Apropiativo", "Round Robin", "Prioridad No Apropiativo", "Prioridad Apropiativo", "SRT (Shortest Remaining Time)" }));

        btnIniciar.setText("Iniciar Simulación");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        btnPausa.setText("Pausar");
        btnPausa.setEnabled(false);
        btnPausa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPausaActionPerformed(evt);
            }
        });

        jLabel15.setText("Velocidad (ms):");

        spnVelocidad.setModel(new javax.swing.SpinnerNumberModel(500, null, null, 1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblProgramCounter, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnPausa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnIniciar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblProcesoCPU, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblModoEjecucion, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMAR, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCicloActual, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbAlgoritmo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spnVelocidad, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblProcesoCPU))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblProgramCounter))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblModoEjecucion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMAR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblCicloActual))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cmbAlgoritmo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(spnVelocidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(btnIniciar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPausa)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelGraficoLayout = new javax.swing.GroupLayout(panelGrafico);
        panelGrafico.setLayout(panelGraficoLayout);
        panelGraficoLayout.setHorizontalGroup(
            panelGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 636, Short.MAX_VALUE)
        );
        panelGraficoLayout.setVerticalGroup(
            panelGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 455, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(153, 204, 255));

        jLabel11.setText("                                    Reporte de Rendimiento");
        jLabel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtMetricas.setColumns(20);
        txtMetricas.setRows(5);
        jScrollPane4.setViewportView(txtMetricas);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(153, 204, 255));

        jLabel17.setText("                                                                                       Procesos Creados");
        jLabel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtProcesosCreados.setColumns(20);
        txtProcesosCreados.setRows(5);
        jScrollPane7.setViewportView(txtProcesosCreados);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
                    .addComponent(jScrollPane7))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout Pestaña1Layout = new javax.swing.GroupLayout(Pestaña1);
        Pestaña1.setLayout(Pestaña1Layout);
        Pestaña1Layout.setHorizontalGroup(
            Pestaña1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Pestaña1Layout.createSequentialGroup()
                .addGroup(Pestaña1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Pestaña1Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34)
                .addGroup(Pestaña1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Pestaña1Layout.setVerticalGroup(
            Pestaña1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Pestaña1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Pestaña1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Pestaña1Layout.createSequentialGroup()
                        .addGroup(Pestaña1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Pestaña1Layout.createSequentialGroup()
                        .addComponent(panelGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Principal - Simulación", Pestaña1);

        panelGraficoThroughputs.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(330, 330, 330)
                .addComponent(panelGraficoThroughputs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(932, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addComponent(panelGraficoThroughputs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(648, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Comparación Throughputs", jPanel6);

        panelGraficoDistribucion.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(379, 379, 379)
                .addComponent(panelGraficoDistribucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(883, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(panelGraficoDistribucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(681, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Distribución I/O vs CPU", jPanel7);

        panelGraficoTiempos.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(398, 398, 398)
                .addComponent(panelGraficoTiempos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(864, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addComponent(panelGraficoTiempos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(657, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Comparación Tiempos de Politicas", jPanel8);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 838, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed

        btnIniciar.setEnabled(false);
        btnPausa.setEnabled(true);
        Logger.iniciar();
        Thread hiloSimulacion = new Thread(this);
        hiloSimulacion.start();
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void btnCrearProcesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearProcesoActionPerformed
       String nombre = txtNombreProceso.getText();
    int instrucciones = (int) spnInstrucciones.getValue();
    boolean esIO = chkIoBound.isSelected();
    int instruccionIO = (int) spnInstruccionIO.getValue();
    int prioridad = (int) spnPrioridad.getValue();
    int tamaño = (int) spnTamaño.getValue();

    if (nombre.isEmpty() || instrucciones <= 0 || tamaño <= 0) {
        JOptionPane.showMessageDialog(this, "Por favor, complete los campos válidamente.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    Proceso nuevoProceso = new Proceso(nombre, instrucciones, esIO, instruccionIO, prioridad, tamaño);
    
    // --- LÓGICA SIMPLIFICADA ---
    // Ahora solo llamamos al método que tiene la lógica de gestión de memoria.
    crearYAnadirPCB(nuevoProceso); 
    
    limpiarCamposCreacion();
    actualizarGUI();
    }//GEN-LAST:event_btnCrearProcesoActionPerformed

    private void btnPausaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPausaActionPerformed
        simulacionPausada = !simulacionPausada; // Invierte el estado de la pausa

    if (simulacionPausada) {
        btnPausa.setText("Continuar");
        Logger.log("--- SIMULACIÓN PAUSADA ---");
    } else {
        btnPausa.setText("Pausar");
        Logger.log("--- SIMULACIÓN REANUDADA ---");
    }
    }//GEN-LAST:event_btnPausaActionPerformed

    private void btnCargarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarArchivoActionPerformed
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setCurrentDirectory(new File(".")); // Abre en la carpeta del proyecto
    int resultado = fileChooser.showOpenDialog(this);

    if (resultado == JFileChooser.APPROVE_OPTION) {
        // Aquí es donde se define la variable 'archivo' que faltaba
        File archivo = fileChooser.getSelectedFile();

        // Ahora, el bloque 'try' funcionará porque 'archivo' ya existe
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");

                String nombre = datos[0];
                int instrucciones = Integer.parseInt(datos[1]);
                boolean esIoBound = Boolean.parseBoolean(datos[2]);
                int instruccionBloqueo = Integer.parseInt(datos[3]);
                int prioridad = Integer.parseInt(datos[4]);
                int tamaño = Integer.parseInt(datos[5]);

                Proceso nuevoProceso = new Proceso(
                    nombre,
                    instrucciones,
                    esIoBound,
                    instruccionBloqueo,
                    prioridad,
                    tamaño
                );

                crearYAnadirPCB(nuevoProceso);
            }
            Logger.log("Procesos cargados desde el archivo: " + archivo.getName());
            actualizarGUI();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al leer el archivo. Verifique que el formato CSV sea correcto.", "Error de Archivo", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_btnCargarArchivoActionPerformed

    private void btnCrearRandomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearRandomActionPerformed
        for (int i = 1; i <= 20; i++) {
        // Generamos los datos aleatorios para el proceso
        String nombre = "Proceso-Rand-" + i;
        int instrucciones = random.nextInt(51) + 10; // Entre 10 y 60 instrucciones
        boolean esIoBound = random.nextBoolean();
        
        int instruccionBloqueo = 0;
        if (esIoBound) {
            // Si es I/O, el bloqueo ocurre antes de la última instrucción
            instruccionBloqueo = random.nextInt(instrucciones - 1) + 1;
        }
        
        int prioridad = random.nextInt(10) + 1; // Prioridad entre 1 y 10
        int tamaño = random.nextInt(251) + 50; // Tamaño entre 50 y 300 MB

        // Creamos el objeto Proceso con los datos aleatorios
        Proceso procesoAleatorio = new Proceso(
            nombre,
            instrucciones,
            esIoBound,
            instruccionBloqueo,
            prioridad,
            tamaño
        );

        // Reutilizamos el método que ya tienes para añadir el proceso al sistema
        crearYAnadirPCB(procesoAleatorio);
    }

    // Actualizamos la GUI una sola vez al final para ver todos los nuevos procesos
    actualizarGUI();
    Logger.log("--- 20 procesos aleatorios han sido creados ---");
    }//GEN-LAST:event_btnCrearRandomActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Pestaña1;
    private javax.swing.JButton btnCargarArchivo;
    private javax.swing.JButton btnCrearProceso;
    private javax.swing.JButton btnCrearRandom;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnPausa;
    private javax.swing.JCheckBox chkIoBound;
    private javax.swing.JComboBox<String> cmbAlgoritmo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblCicloActual;
    private javax.swing.JLabel lblMAR;
    private javax.swing.JLabel lblModoEjecucion;
    private javax.swing.JLabel lblProcesoCPU;
    private javax.swing.JLabel lblProgramCounter;
    private javax.swing.JPanel panelGrafico;
    private javax.swing.JPanel panelGraficoDistribucion;
    private javax.swing.JPanel panelGraficoThroughputs;
    private javax.swing.JPanel panelGraficoTiempos;
    private javax.swing.JSpinner spnInstruccionIO;
    private javax.swing.JSpinner spnInstrucciones;
    private javax.swing.JSpinner spnPrioridad;
    private javax.swing.JSpinner spnTamaño;
    private javax.swing.JSpinner spnVelocidad;
    private javax.swing.JTextArea txtColaBloqueados;
    private javax.swing.JTextArea txtColaBloqueadosSuspendidos;
    private javax.swing.JTextArea txtColaListos;
    private javax.swing.JTextArea txtColaListosSuspendidos;
    private javax.swing.JTextArea txtMetricas;
    private javax.swing.JTextField txtNombreProceso;
    private javax.swing.JTextArea txtProcesosCreados;
    private javax.swing.JTextArea txtTerminados;
    // End of variables declaration//GEN-END:variables

@Override
public void run() {
    while (procesoEnCpu != null || !colaListos.estaVacia() || !colaBloqueados.estaVacia() || !colaListosSuspendidos.estaVacia()) {

        // --- LÓGICA DE PAUSA (No cambia) ---
        while (simulacionPausada) {
            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        try {
            mutex.acquire();

            // --- Bloque para capturar métricas en vivo ---
            String algoritmoActual = (String) cmbAlgoritmo.getSelectedItem();
            MetricasAlgoritmo metricasActuales = metricasEnTiempoReal.get(algoritmoActual);
            metricasActuales.ciclosActivos++;

            gestionarColaBloqueados();
            gestionarColaSuspendidos();
            
            // --- LÓGICA DE PLANIFICACIÓN (No cambia) ---
            if (procesoEnCpu == null) {
                procesoEnCpu = planificador.seleccionarProceso(colaListos, algoritmoActual);
                if (procesoEnCpu != null) {
                    Logger.log("Ciclo " + cicloGlobal + ": Planificador selecciona Proceso ID " + procesoEnCpu.getId() + " (" + algoritmoActual + ").");
                    procesoEnCpu.setEstado(PCB.EstadoProceso.EJECUCION);
                    if (algoritmoActual.equals("Round Robin")) {
                        procesoEnCpu.setQuantumRestante(QUANTUM); 
                    }
                }
            }
            if (procesoEnCpu != null && !colaListos.estaVacia()) {
                 if (algoritmoActual.equals("Prioridad Apropiativo")) {
                    PCB masPrioritario = planificador.verProcesoMasPrioritario(colaListos);
                    if (masPrioritario.getProcesoInfo().getPrioridad() < procesoEnCpu.getProcesoInfo().getPrioridad()) {
                        Logger.log("Ciclo " + cicloGlobal + ": Proceso ID " + procesoEnCpu.getId() + " interrumpido por Proceso ID " + masPrioritario.getId() + " (Prioridad).");
                        procesoEnCpu.setEstado(PCB.EstadoProceso.LISTO);
                        colaListos.encolar(procesoEnCpu);
                        procesoEnCpu = planificador.seleccionarProceso(colaListos, algoritmoActual);
                    }
                } else if (algoritmoActual.equals("SRT (Shortest Remaining Time)")) {
                    PCB masCorto = planificador.verProcesoMasCortoRestante(colaListos);
                    if (masCorto.getTiempoEjecucionRestante() < procesoEnCpu.getTiempoEjecucionRestante()) {
                        Logger.log("Ciclo " + cicloGlobal + ": Proceso ID " + procesoEnCpu.getId() + " interrumpido por Proceso ID " + masCorto.getId() + " (SRT).");
                        procesoEnCpu.setEstado(PCB.EstadoProceso.LISTO);
                        colaListos.encolar(procesoEnCpu);
                        procesoEnCpu = planificador.seleccionarProceso(colaListos, algoritmoActual);
                    }
                }
            }
            
            cicloGlobal++;

            // --- LÓGICA DE EJECUCIÓN DEL CICLO ---
            if (procesoEnCpu != null) {
                ciclosOcupado++; 
                procesoEnCpu.setTiempoEjecucionRestante(procesoEnCpu.getTiempoEjecucionRestante() - 1);

                if (algoritmoActual.equals("Round Robin")) {
                    procesoEnCpu.setQuantumRestante(procesoEnCpu.getQuantumRestante() - 1);
                }
                
                if (procesoEnCpu.getProcesoInfo().esIoBound() && 
                    procesoEnCpu.getProgramCounter() == procesoEnCpu.getProcesoInfo().getInstruccionBloqueo()) {
                    
                    Logger.log("Ciclo " + cicloGlobal + ": Proceso ID " + procesoEnCpu.getId() + " se bloquea por E/S.");
                    procesoEnCpu.setEstado(PCB.EstadoProceso.BLOQUEADO);
                    procesoEnCpu.setTiempoRestanteBloqueo(10);
                    colaBloqueados.encolar(procesoEnCpu);
                    procesoEnCpu.setProgramCounter(procesoEnCpu.getProgramCounter() + 1);
                    procesoEnCpu = null;

                // <-- INICIO: Bloque de finalización de proceso ACTUALIZADO -->
                } else if (procesoEnCpu.getProgramCounter() >= procesoEnCpu.getProcesoInfo().getNumeroInstrucciones()) {
                    Logger.log("Ciclo " + cicloGlobal + ": Proceso ID " + procesoEnCpu.getId() + " ha terminado.");
                    procesoEnCpu.setEstado(PCB.EstadoProceso.TERMINADO);
                    procesoEnCpu.setTiempoDeFinalizacion(cicloGlobal);
                    memoriaEnUso -= procesoEnCpu.getProcesoInfo().getTamañoEnMemoria(); 
                    colaTerminados.encolar(procesoEnCpu);
                    
                    // --- Bloque de cálculo añadido ---
                    metricasActuales.procesosTerminados++;
                    
                    long tiempoRetorno = procesoEnCpu.getTiempoDeFinalizacion() - procesoEnCpu.getTiempoDeLlegada();
                    long tiempoEspera = tiempoRetorno - procesoEnCpu.getProcesoInfo().getNumeroInstrucciones();
                    
                    metricasActuales.sumaTiemposRetorno += tiempoRetorno;
                    metricasActuales.sumaTiemposEspera += tiempoEspera;
                    // --- Fin del bloque de cálculo ---
                    
                    procesoEnCpu = null;
                // <-- FIN: Bloque de finalización de proceso ACTUALIZADO -->

                } else if (algoritmoActual.equals("Round Robin") && procesoEnCpu.getQuantumRestante() <= 0) {
                    Logger.log("Ciclo " + cicloGlobal + ": Proceso ID " + procesoEnCpu.getId() + " fin de quantum (Round Robin).");
                    procesoEnCpu.setEstado(PCB.EstadoProceso.LISTO);
                    colaListos.encolar(procesoEnCpu);
                    procesoEnCpu.setProgramCounter(procesoEnCpu.getProgramCounter() + 1);
                    procesoEnCpu = null;

                } else {
                    procesoEnCpu.setProgramCounter(procesoEnCpu.getProgramCounter() + 1);
                }
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            mutex.release();
        }

        // <-- INICIO: Bloque de actualización de GUI ACTUALIZADO -->
        SwingUtilities.invokeLater(() -> {
            actualizarGUI();
            actualizarGrafico();
            actualizarGraficoComparativo();
            actualizarGraficoDistribucion();
            actualizarGraficoTiempos(); // Asegúrate de que esta llamada también esté aquí
        });
        // <-- FIN: Bloque de actualización de GUI ACTUALIZADO -->

        try { 
            int velocidad = (int) spnVelocidad.getValue();
            Thread.sleep(velocidad); 
        } catch (InterruptedException e) { 
            Thread.currentThread().interrupt(); 
        }
    }
    
    // --- CÓDIGO DE FINALIZACIÓN (No cambia) ---
    System.out.println("--- Simulación Finalizada ---");
    calcularYMostrarMetricas();
    Logger.cerrar();
    SwingUtilities.invokeLater(() -> {
        btnIniciar.setEnabled(true);
        btnPausa.setEnabled(false);
        btnPausa.setText("Pausar");
    });
}// <--- FIN DEL MÉTODO run()




private void gestionarColaBloqueados() {
    // Procesa la cola de bloqueados en memoria
    if (!colaBloqueados.estaVacia()) {
        Nodo actual = colaBloqueados.getFrente();
        while (actual != null) {
            PCB pcb = actual.getPcb();
            pcb.setTiempoRestanteBloqueo(pcb.getTiempoRestanteBloqueo() - 1);
            actual = actual.getSiguiente();
        }
        
        while (!colaBloqueados.estaVacia() && colaBloqueados.verFrente().getTiempoRestanteBloqueo() <= 0) {
            PCB pcbListo = colaBloqueados.desencolar();
            pcbListo.setEstado(PCB.EstadoProceso.LISTO);
            colaListos.encolar(pcbListo);
            Logger.log("Ciclo " + cicloGlobal + ": Proceso ID " + pcbListo.getId() + " fin de bloqueo. Va a Listos.");
        }
    }
    
    // --- NUEVO: Procesa la cola de bloqueados-suspendidos ---
    if (!colaBloqueadosSuspendidos.estaVacia()) {
        Nodo actual = colaBloqueadosSuspendidos.getFrente();
        while (actual != null) {
            PCB pcb = actual.getPcb();
            pcb.setTiempoRestanteBloqueo(pcb.getTiempoRestanteBloqueo() - 1);
            actual = actual.getSiguiente();
        }
        
        while (!colaBloqueadosSuspendidos.estaVacia() && colaBloqueadosSuspendidos.verFrente().getTiempoRestanteBloqueo() <= 0) {
            PCB pcbListo = colaBloqueadosSuspendidos.desencolar();
            pcbListo.setEstado(PCB.EstadoProceso.LISTO_SUSPENDIDO);
            colaListosSuspendidos.encolar(pcbListo); // Va a Listos-Suspendido
            Logger.log("Ciclo " + cicloGlobal + ": Proceso ID " + pcbListo.getId() + " fin de bloqueo (suspendido). Va a Listos-Suspendido.");
        }
    }
}


private void calcularYMostrarMetricas() {
    if (colaTerminados.estaVacia()) return;

    StringBuilder reporte = new StringBuilder();
    reporte.append("--- REPORTE DE RENDIMIENTO ---\n");
    
    int totalRetorno = 0;
    int totalEspera = 0;
    int numProcesos = 0;

    Nodo actual = colaTerminados.getFrente();
    while(actual != null) {
        // ... (tu código existente para calcular retorno y espera) ...
        // Esto no cambia
        PCB pcb = actual.getPcb();
        int retorno = pcb.getTiempoDeFinalizacion() - pcb.getTiempoDeLlegada();
        pcb.setTiempoDeRetorno(retorno);
        int espera = retorno - pcb.getProcesoInfo().getNumeroInstrucciones();
        pcb.setTiempoDeEspera(espera);
        totalRetorno += retorno;
        totalEspera += espera;
        numProcesos++;
        reporte.append("ID: ").append(pcb.getId())
               .append(" | T. Retorno: ").append(retorno)
               .append(" | T. Espera: ").append(espera).append("\n");
        actual = actual.getSiguiente();
    }

    // --- INICIO DE LAS NUEVAS MÉTRICAS ---
    double promedioRetorno = (double) totalRetorno / numProcesos;
    double promedioEspera = (double) totalEspera / numProcesos;
    
    // Throughput: Procesos completados por unidad de tiempo
    double throughput = (double) numProcesos / cicloGlobal;
    
    // Utilización de CPU: Porcentaje de tiempo que la CPU estuvo ocupada
    double utilizacionCPU = ((double) ciclosOcupado / cicloGlobal) * 100.0;

    reporte.append("---------------------------------\n");
    reporte.append(String.format("Promedio de T. de Retorno: %.2f\n", promedioRetorno));
    reporte.append(String.format("Promedio de T. de Espera: %.2f\n", promedioEspera));
    reporte.append(String.format("Throughput: %.3f procesos/ciclo\n", throughput));
    reporte.append(String.format("Utilización de CPU: %.2f%%\n", utilizacionCPU));
    // --- FIN DE LAS NUEVAS MÉTRICAS ---

    txtMetricas.setText(reporte.toString());
    }

private void inicializarGrafico() {
    // 1. Crear el conjunto de datos
    this.seriesUtilizacionCPU = new XYSeries("Utilización de CPU");
    XYSeriesCollection dataset = new XYSeriesCollection(this.seriesUtilizacionCPU);

    // 2. Crear el gráfico
    this.grafico = ChartFactory.createXYLineChart(
        "Rendimiento del Sistema", // Título del gráfico
        "Ciclo",                   // Etiqueta del eje X
        "Utilización de CPU (%)",  // Etiqueta del eje Y
        dataset                    // Los datos
    );

    // 3. Crear el panel del gráfico y añadirlo a nuestro JPanel
    ChartPanel chartPanel = new ChartPanel(grafico);
    panelGrafico.setLayout(new java.awt.BorderLayout());
    panelGrafico.add(chartPanel, BorderLayout.CENTER);
    panelGrafico.validate();
    }

private void actualizarGrafico() {
    if (cicloGlobal > 0) {
        // Calculamos la utilización de CPU actual
        double utilizacionActual = ((double) ciclosOcupado / cicloGlobal) * 100.0;
        
        // Añadimos el nuevo punto de datos (ciclo, utilización) a la serie
        this.seriesUtilizacionCPU.add(cicloGlobal, utilizacionActual);
    }
   }

private void limpiarCamposCreacion() {
    // Resetea los campos del formulario a sus valores por defecto
    txtNombreProceso.setText("");
    spnInstrucciones.setValue(0);
    chkIoBound.setSelected(false);
    spnInstruccionIO.setValue(0);
    spnPrioridad.setValue(10);
    spnTamaño.setValue(100);
    
    }

private void gestionarColaSuspendidos() {
    if (!colaListosSuspendidos.estaVacia()) {
        PCB procesoInteresado = colaListosSuspendidos.verFrente();
        
        if ((memoriaEnUso + procesoInteresado.getProcesoInfo().getTamañoEnMemoria()) <= MEMORIA_TOTAL_MB) {
            // Caso 1: Hay espacio, se admite el proceso
            PCB procesoAdmitido = colaListosSuspendidos.desencolar();
            procesoAdmitido.setEstado(PCB.EstadoProceso.LISTO);
            memoriaEnUso += procesoAdmitido.getProcesoInfo().getTamañoEnMemoria();
            colaListos.encolar(procesoAdmitido);
            Logger.log("Ciclo " + cicloGlobal + ": Proceso ID " + procesoAdmitido.getId() + " admitido a memoria. Uso: " + memoriaEnUso + "MB.");
        
        } else {
            // Caso 2: No hay espacio. ¿Podemos hacer espacio?
            // Esto solo tiene sentido si no hay nada en la cola de listos y la CPU está ociosa.
            if (colaListos.estaVacia() && procesoEnCpu == null && !colaBloqueados.estaVacia()) {
                
                // Sacamos a un proceso bloqueado para liberar su memoria
                PCB procesoASuspender = colaBloqueados.desencolar();
                memoriaEnUso -= procesoASuspender.getProcesoInfo().getTamañoEnMemoria();
                
                procesoASuspender.setEstado(PCB.EstadoProceso.BLOQUEADO_SUSPENDIDO);
                colaBloqueadosSuspendidos.encolar(procesoASuspender);
                Logger.log("Ciclo " + cicloGlobal + ": Swapping. Proceso ID " + procesoASuspender.getId() + " movido a Bloqueado-Suspendido para liberar memoria.");
            }
        }
    }
}

private void guardarConfiguracion() {
    // Creamos un objeto JSON
    JSONObject config = new JSONObject();

    // Ponemos el valor actual del spinner en el objeto JSON
    config.put("velocidadSimulacion", (int) spnVelocidad.getValue());

    // Usamos un try-with-resources para escribir el archivo y asegurarnos de que se cierre
    try (FileWriter file = new FileWriter("config.json")) {
        file.write(config.toString(4)); // El '4' es para que se guarde con formato legible
        System.out.println("Configuración guardada en config.json");
    } catch (IOException e) {
        e.printStackTrace();
    }
}

private void cargarConfiguracion() {
    try {
        // Leemos el contenido del archivo a un string
        String contenido = new String(Files.readAllBytes(Paths.get("config.json")));

        // Convertimos el string a un objeto JSON
        JSONObject config = new JSONObject(contenido);

        // Obtenemos el valor guardado y lo ponemos en el spinner
        int velocidadGuardada = config.getInt("velocidadSimulacion");
        spnVelocidad.setValue(velocidadGuardada);
        System.out.println("Configuración cargada desde config.json");

    } catch (IOException e) {
        // Si el archivo no existe, no es un error. Simplemente usamos los valores por defecto.
        System.out.println("No se encontró config.json. Usando valores por defecto.");
    }
}

private void cargarProcesosDesdeArchivo(File archivo) {
    try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(",");

            // Asumimos que el CSV tiene 6 columnas en el orden correcto
            String nombre = datos[0];
            int instrucciones = Integer.parseInt(datos[1]);
            boolean esIoBound = Boolean.parseBoolean(datos[2]);
            int instruccionBloqueo = Integer.parseInt(datos[3]);
            int prioridad = Integer.parseInt(datos[4]);
            int tamaño = Integer.parseInt(datos[5]);

            Proceso nuevoProceso = new Proceso(
                nombre,
                instrucciones,
                esIoBound,
                instruccionBloqueo,
                prioridad,
                tamaño
            );

            // Reutilizamos la lógica que ya tenías para añadir un proceso
            crearYAnadirPCB(nuevoProceso);
        }
        Logger.log("Procesos cargados desde el archivo: " + archivo.getName());
        actualizarGUI(); // Actualizamos la GUI para ver los nuevos procesos

    } catch (Exception e) {
        e.printStackTrace();
        // Opcional: Mostrar un mensaje de error al usuario
    }
   }
private void crearYAnadirPCB(Proceso nuevoProceso) {
    // La lista maestra debe guardar el objeto Proceso, no el PCB
    listaMaestraProcesos.add(nuevoProceso);

    // Creamos el PCB, pasando el tiempo de llegada directamente en el constructor
    PCB nuevoPcb = new PCB(nuevoProceso, cicloGlobal);
    
    // La línea "nuevoPcb.setTiempoDeLlegada(cicloGlobal);" se ha eliminado porque ya no es necesaria.

    // --- LÓGICA DE GESTIÓN DE MEMORIA (No cambia) ---
    if ((memoriaEnUso + nuevoProceso.getTamañoEnMemoria()) <= MEMORIA_TOTAL_MB) {
        memoriaEnUso += nuevoProceso.getTamañoEnMemoria();
        nuevoPcb.setEstado(PCB.EstadoProceso.LISTO);
        colaListos.encolar(nuevoPcb);
        Logger.log("Proceso " + nuevoPcb.getId() + " admitido en memoria.");
    } else {
        nuevoPcb.setEstado(PCB.EstadoProceso.LISTO_SUSPENDIDO);
        colaListosSuspendidos.encolar(nuevoPcb);
        Logger.log("Proceso " + nuevoPcb.getId() + " enviado a suspendidos por falta de memoria.");
    }

    // --- LÓGICA PARA MOSTRAR DETALLES (No cambia) ---
    String tipoProceso;
    if (nuevoProceso.esIoBound()) {
        tipoProceso = String.format("I/O (bloqueo en %d)", nuevoProceso.getInstruccionBloqueo());
    } else {
        tipoProceso = "CPU Bound";
    }
    
    String infoProceso = String.format("ID-%d: %s, %d inst, %s, Prio: %d, Tamaño: %d MB\n",
            nuevoPcb.getId(),
            nuevoProceso.getNombre(),
            nuevoProceso.getNumeroInstrucciones(),
            tipoProceso,
            nuevoProceso.getPrioridad(),
            nuevoProceso.getTamañoEnMemoria()
    );
    
    txtProcesosCreados.append(infoProceso);
    
    // No olvides actualizar la GUI principal también
    actualizarGUI();
}
private void actualizarGraficoComparativo() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    for (Map.Entry<String, MetricasAlgoritmo> entry : metricasEnTiempoReal.entrySet()) {
        String algoritmo = entry.getKey();
        MetricasAlgoritmo metricas = entry.getValue();
        double throughputActual = 0;
        
        if (metricas.ciclosActivos > 0) {
            throughputActual = (double) metricas.procesosTerminados / metricas.ciclosActivos;
        }
        dataset.addValue(throughputActual, "Throughput", algoritmo);
    }

    JFreeChart barChart = ChartFactory.createBarChart(
            "Comparación de Throughputs", "Algoritmo", "Throughput (procesos/ciclo)",
            dataset, PlotOrientation.VERTICAL, false, true, false);

    CategoryPlot plot = barChart.getCategoryPlot();
    
    // <-- INICIO: Bloque modificado -->
    // 1. Usamos nuestro renderizador personalizado para los colores
    plot.setRenderer(new CustomRenderer());
    
    // 2. Arreglamos el texto del eje X rotando las etiquetas
    org.jfree.chart.axis.CategoryAxis domainAxis = plot.getDomainAxis();
    domainAxis.setCategoryLabelPositions(
        org.jfree.chart.axis.CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
    );
    // <-- FIN: Bloque modificado -->

    ChartPanel chartPanel = new ChartPanel(barChart);
    panelGraficoThroughputs.removeAll();
    panelGraficoThroughputs.add(chartPanel, java.awt.BorderLayout.CENTER);
    panelGraficoThroughputs.revalidate();
    panelGraficoThroughputs.repaint();
    }
private void actualizarGraficoDistribucion() {
    // 1. Contamos los procesos de cada tipo
    int cpuBoundCount = 0;
    int ioBoundCount = 0;
    for (Proceso p : listaMaestraProcesos) {
        if (p.esIoBound()) {
            ioBoundCount++;
        } else {
            cpuBoundCount++;
        }
    }

    // 2. Creamos el conjunto de datos para el pastel
    org.jfree.data.general.DefaultPieDataset dataset = new org.jfree.data.general.DefaultPieDataset();
    dataset.setValue("CPU Bound", cpuBoundCount);
    dataset.setValue("I/O Bound", ioBoundCount);

    // 3. Creamos el gráfico
    JFreeChart pieChart = ChartFactory.createPieChart("Distribución de Tipos de Procesos", dataset, true, true, false);
    
    // 4. Personalizamos los colores
    org.jfree.chart.plot.PiePlot plot = (org.jfree.chart.plot.PiePlot) pieChart.getPlot();
    plot.setSectionPaint("CPU Bound", new Color(255, 87, 87));
    plot.setSectionPaint("I/O Bound", new Color(87, 117, 255));

    // 5. Mostramos el gráfico en su panel dedicado
    ChartPanel chartPanel = new ChartPanel(pieChart);
    panelGraficoDistribucion.removeAll();
    panelGraficoDistribucion.add(chartPanel, java.awt.BorderLayout.CENTER);
    panelGraficoDistribucion.revalidate();
    panelGraficoDistribucion.repaint();
    }
private void actualizarGraficoTiempos() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    for (Map.Entry<String, MetricasAlgoritmo> entry : metricasEnTiempoReal.entrySet()) {
        String algoritmo = entry.getKey();
        MetricasAlgoritmo metricas = entry.getValue();
        
        double promedioEspera = 0;
        double promedioRetorno = 0;

        // Calculamos los promedios solo si han terminado procesos para ese algoritmo
        if (metricas.procesosTerminados > 0) {
            promedioEspera = (double) metricas.sumaTiemposEspera / metricas.procesosTerminados;
            promedioRetorno = (double) metricas.sumaTiemposRetorno / metricas.procesosTerminados;
        }
        
        dataset.addValue(promedioEspera, "Tiempo de Espera Prom.", algoritmo);
        dataset.addValue(promedioRetorno, "Tiempo de Retorno Prom.", algoritmo);
    }

    JFreeChart barChart = ChartFactory.createBarChart(
            "Comparación de Tiempos de Politicas", "Algoritmo", "Ciclos",
            dataset, PlotOrientation.VERTICAL, true, true, false);
            
    // Rotamos las etiquetas para que se vean bien
    CategoryPlot plot = barChart.getCategoryPlot();
    org.jfree.chart.axis.CategoryAxis domainAxis = plot.getDomainAxis();
    domainAxis.setCategoryLabelPositions(
        org.jfree.chart.axis.CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
    );

    // Dibujamos en el panel correspondiente (asegúrate de que se llame así)
    ChartPanel chartPanel = new ChartPanel(barChart);
    panelGraficoTiempos.removeAll();
    panelGraficoTiempos.add(chartPanel, java.awt.BorderLayout.CENTER);
    panelGraficoTiempos.revalidate();
    panelGraficoTiempos.repaint();
 }
}
