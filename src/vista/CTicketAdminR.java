/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.ControladorTicketAdminR;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import modelo.HiloRecepcionReserva;
import modelo.HiloRecepcion;
import modelo.TicketVO;
import modelo.Modelo;

/**
 * @author Francisco Villegas Ostos
 * @author Ivan Campallo Sequera
 * @version 04/03/2019
 *
 */
public class CTicketAdminR extends javax.swing.JFrame {

    public CTicketAdminR(int id, int idCaso, int comprobar, Modelo modelo, HiloRecepcionReserva hiloE) {

        this.idAdmin = id;
        this.idCaso = idCaso;
        this.comprobar = comprobar;
        this.modelo = modelo;
        this.hE = hiloE; //Hilo que recepcionará los tickets del administrador de sistema

        initComponents();

        if (idCaso != 0) {//Si está continuando un caso
            setLblNomCaso("Caso: " + idCaso);
        } else {//Si está creando un nuevo caso
            setLblNomCaso("Nuevo Caso");
        }

        hR = new HiloRecepcion(this.modelo); //Hilo que recepciona los tickets de los administradores de reservas

    }

    /** 
     * Metodo que se encarga de inicializar toda la parte visual de la vista
     */
    @SuppressWarnings("unchecked")

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblNomCaso = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtAsunto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        panelLista = new javax.swing.JScrollPane();
        listaTickets = new javax.swing.JList<>();
        jLabel4 = new javax.swing.JLabel();
        cbPrioridad = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        bAceptar = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();
        bActualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ControladorTicketAdminR controlador = new ControladorTicketAdminR(this, this.modelo, this.hE);

        lblNomCaso.setText("Caso X");
        lblNomCaso.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("Descripción:");

        jLabel3.setText("Asunto:");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        listaTickets.addMouseListener(controlador);
        panelLista.setViewportView(listaTickets);

        jLabel4.setText("Prioridad:");

        cbPrioridad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Baja", "Media", "Alta"}));
        cbPrioridad.addActionListener(controlador);

        jLabel1.setText("Fecha:");

        bAceptar.setText("Aceptar");
        bAceptar.addActionListener(controlador);

        bCancelar.setText("Cancelar");
        bCancelar.addActionListener(controlador);

        bActualizar.setText("Actualizar");
        bActualizar.addActionListener(controlador);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblNomCaso, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(panelLista, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                                .addGap(23, 23, 23)
                                                                .addComponent(bActualizar)))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(bAceptar)
                                                                .addGap(35, 35, 35)
                                                                .addComponent(bCancelar))
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(txtAsunto)
                                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                                                                .addComponent(cbPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(33, 33, 33))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(lblNomCaso, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(panelLista, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(bAceptar)
                                        .addComponent(bCancelar)
                                        .addComponent(bActualizar))
                                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }

    private void cbPrioridadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPrioridadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbPrioridadActionPerformed

    private void bAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAceptarActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_bAceptarActionPerformed

    private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed
        // TODO add your handling code here:

        this.dispose();

    }//GEN-LAST:event_bCancelarActionPerformed

    private void listaTicketsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaTicketsMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_listaTicketsMouseClicked

    private void bActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bActualizarActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_bActualizarActionPerformed

    private javax.swing.JButton bAceptar;
    private javax.swing.JButton bActualizar;
    private javax.swing.JButton bCancelar;
    private javax.swing.JComboBox<String> cbPrioridad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNomCaso;
    private javax.swing.JList<String> listaTickets;
    private javax.swing.JScrollPane panelLista;
    private javax.swing.JTextField txtAsunto;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtFecha;

    private final int idAdmin;
    private Modelo modelo;
    private int idCaso;
    private List<TicketVO> listaT;
    private int comprobar;
    private DefaultListModel combo;
    private HiloRecepcionReserva hE;
    private HiloRecepcion hR;

    //Getters and Setters
    public DefaultListModel getCombo() {
        return combo;
    }

    public void setCombo(DefaultListModel combo) {
        this.combo = combo;
    }

    public List<TicketVO> getListaT() {
        return listaT;
    }

    public void setListaT(List<TicketVO> listaT) {
        this.listaT = listaT;
    }

    public int getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(int idCaso) {
        this.idCaso = idCaso;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public JButton getbAceptar() {
        return bAceptar;
    }

    public String getStringAceptar() {

        return bAceptar.getText();
    }

    public String getStringActualizar() {

        return bActualizar.getText();
    }

    public void setbAceptar(JButton bAceptar) {
        this.bAceptar = bAceptar;
    }

    public String getbCancelar() {
        return bCancelar.getText();
    }

    public void setbCancelar(JButton bCancelar) {
        this.bCancelar = bCancelar;
    }

    public JButton getbActualizar() {
        return bActualizar;
    }

    public void setbActualizar(JButton bActualizar) {
        this.bActualizar = bActualizar;
    }

    public JList<String> getListaTickets() {
        return listaTickets;
    }

    public void setListaTickets(DefaultListModel<String> listaTickets) {
        this.listaTickets.setModel(listaTickets);
    }

    public JTextField getTxtFecha() {
        return txtFecha;
    }

    public void setTxtFecha(String txtFecha) {
        this.txtFecha.setText(txtFecha);
    }

    public JLabel getLblNomCaso() {
        return lblNomCaso;
    }

    public void setLblNomCaso(String lblNomCaso) {
        this.lblNomCaso.setText(lblNomCaso);
    }

    public JScrollPane getPanelLista() {
        return panelLista;
    }

    public void setPanelLista(JScrollPane panelLista) {
        this.panelLista = panelLista;
    }

    public JLabel getjLabel1() {
        return jLabel1;
    }

    public void setjLabel1(JLabel jLabel1) {
        this.jLabel1 = jLabel1;
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }

    public JTextField getTxtAsunto() {
        return txtAsunto;
    }

    public void setTxtAsunto(String txtAsunto) {
        this.txtAsunto.setText(txtAsunto);
    }

    public JTextArea getTxtDescripcion() {
        return txtDescripcion;
    }

    public void setTxtDescripcion(String txtDescripcion) {
        this.txtDescripcion.setText(txtDescripcion);
    }

    public JComboBox<String> getCbPrioridad() {
        return cbPrioridad;
    }

    public void setCbPrioridad(int cbPrioridad) {
        this.cbPrioridad.setSelectedIndex(cbPrioridad);
    }

}
