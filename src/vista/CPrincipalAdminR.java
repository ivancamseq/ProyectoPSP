/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.ControladorPrincipalR;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import modelo.CasoVO;
import modelo.HiloRecepcionReserva;
import modelo.Modelo;

/**
 * @author Francisco Villegas Ostos
 * @author Ivan Campallo Sequera
 * @version 04/03/2019
 *
 */
public class CPrincipalAdminR extends javax.swing.JFrame {

    /**
     * Constructor de la ventana principal del administrador de reserva
     */
    public CPrincipalAdminR(int idAdmin, Modelo modelo) {

        this.idAdmin = idAdmin;
        this.modelo = modelo;
        hE = new HiloRecepcionReserva(this.modelo); //Se crea el hilo para la recepción de tickets
        initComponents();

    }

    /** 
     * Metodo que se encarga de inicializar toda la parte visual de la vista
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelLista = new javax.swing.JScrollPane();
        listaCasos = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        bNuevo = new javax.swing.JButton();
        bContinuar = new javax.swing.JButton();
        bLeer = new javax.swing.JButton();
        bActualizar = new javax.swing.JButton();
        bCerrarCaso = new javax.swing.JButton();
        bEnviarCorreo = new javax.swing.JButton();
        bRecibirCorreo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ControladorPrincipalR controlador = new ControladorPrincipalR(this, this.modelo, this.hE);

        panelLista.setViewportView(listaCasos);

        jLabel1.setText("Casos disponibles:");

        bNuevo.setText("Nuevo caso");
        bNuevo.addActionListener(controlador);

        bContinuar.setText("Continuar caso");
        bContinuar.addActionListener(controlador);

        bLeer.setText("Leer caso");
        bLeer.addActionListener(controlador);

        bActualizar.setText("Actualizar");
        bActualizar.addActionListener(controlador);

        bCerrarCaso.setText("Cerrar Caso");
        bCerrarCaso.addActionListener(controlador);

        bEnviarCorreo.setText("Enviar Correo");
        bEnviarCorreo.addActionListener(controlador);

        bRecibirCorreo.setText("Recibir Correo");
        bRecibirCorreo.addActionListener(controlador);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(panelLista, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(bCerrarCaso, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bActualizar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(139, 139, 139)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(bRecibirCorreo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(bEnviarCorreo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(bNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(bContinuar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(bLeer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(18, 18, 18)
                                                .addComponent(panelLista, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(bNuevo)
                                                .addGap(18, 18, 18)
                                                .addComponent(bContinuar)
                                                .addGap(18, 18, 18)
                                                .addComponent(bLeer)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(bCerrarCaso)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(bActualizar)
                                                .addContainerGap())
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(39, 39, 39)
                                                .addComponent(bEnviarCorreo)
                                                .addGap(18, 18, 18)
                                                .addComponent(bRecibirCorreo)
                                                .addContainerGap(32, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }

    private javax.swing.JButton bActualizar;
    private javax.swing.JButton bCerrarCaso;
    private javax.swing.JButton bContinuar;
    private javax.swing.JButton bLeer;
    private javax.swing.JButton bNuevo;
    private javax.swing.JButton bEnviarCorreo;
    private javax.swing.JButton bRecibirCorreo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JList<String> listaCasos;
    private javax.swing.JScrollPane panelLista;
    private final int idAdmin;
    private Modelo modelo;
    private List<CasoVO> listaC;
    private HiloRecepcionReserva hE;

    //Getters and Setters:
    public String getbActualizar() {
        return bActualizar.getText();
    }

    public void setbActualizar(JButton bActualizar) {
        this.bActualizar = bActualizar;
    }

    public JButton getbCerrarCaso() {
        return bCerrarCaso;
    }

    public void setbCerrarCaso(JButton bCerrarCaso) {
        this.bCerrarCaso = bCerrarCaso;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public List<CasoVO> getListaC() {
        return listaC;
    }

    public void setListaC(List<CasoVO> listaC) {
        this.listaC = listaC;
    }

    public String getbContinuar() {
        return bContinuar.getText();
    }

    public void setbContinuar(JButton bContinuar) {
        this.bContinuar = bContinuar;
    }

    public String getbLeer() {
        return bLeer.getText();
    }

    public void setbLeer(JButton bLeer) {
        this.bLeer = bLeer;
    }

    public String getbNuevo() {
        return bNuevo.getText();
    }

    public void setbNuevo(JButton bNuevo) {
        this.bNuevo = bNuevo;
    }

    public JLabel getjLabel1() {
        return jLabel1;
    }

    public void setjLabel1(JLabel jLabel1) {
        this.jLabel1 = jLabel1;
    }

    public JPanel getjPanel1() {
        return jPanel1;
    }

    public void setjPanel1(JPanel jPanel1) {
        this.jPanel1 = jPanel1;
    }

    public JList<String> getListaCasos() {
        return listaCasos;
    }

    public void setListaCasos(DefaultListModel<String> jList1) {
        this.listaCasos.setModel(jList1);
    }

    public JScrollPane getPanelLista() {
        return panelLista;
    }

    public void setPanelLista(JScrollPane panelLista) {
        this.panelLista = panelLista;
    }

    public String getbEnviarCorreo() {
        return bEnviarCorreo.getText();
    }

    public void setbEnviarCorreo(JButton bEnviarCorreo) {
        this.bEnviarCorreo = bEnviarCorreo;
    }

    public String getbRecibirCorreo() {
        return bRecibirCorreo.getText();
    }

    public void setbRecibirCorreo(JButton bRecibirCorreo) {
        this.bRecibirCorreo = bRecibirCorreo;
    }

}
