/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.ControladorPrincipalS;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import modelo.CasoVO;
import modelo.HiloGestion;
import modelo.HiloRecepcion;
import modelo.Modelo;

/**
 *
 * @author usuario
 */
public class CPrincipalAdminS extends javax.swing.JFrame {

    /**
     * Constructor de la consola principal del administrador de sistema.
     */
    public CPrincipalAdminS() {

        modelo = new Modelo(0);
        hilo = new HiloRecepcion(modelo); //Hilo que recepcionará los tickets de los administradores de reserva
        hG = new HiloGestion(hilo, modelo); //Hilo que esperará las conexiones de los administradores de reserva
        initComponents();

    }

 /** 
     * Metodo que se encarga de inicializar toda la parte visual de la vista
     */
    @SuppressWarnings("unchecked")

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaCasosDisp = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaAdminR = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        bResponder = new javax.swing.JButton();
        bLeer = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        bActualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ControladorPrincipalS controlador = new ControladorPrincipalS(this, modelo, hilo, hG);

        jScrollPane1.setViewportView(listaCasosDisp);

        jLabel1.setText("Administradores de Reserva:");

        listaAdminR.addMouseListener(controlador);
        jScrollPane2.setViewportView(listaAdminR);

        jLabel2.setText("Casos abiertos:");

        bResponder.setText("Responder");
        bResponder.addActionListener(controlador);
        bLeer.setText("Leer");
        bLeer.addActionListener(controlador);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Agencia de vuelos");

        bActualizar.setText("Actualizar");
        bActualizar.addActionListener(controlador);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(181, 181, 181)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(305, 305, 305)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(bResponder)
                                                                        .addComponent(bLeer, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(51, 51, 51)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(bActualizar)
                                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(bResponder)
                                                .addGap(18, 18, 18)
                                                .addComponent(bLeer)))
                                .addGap(18, 18, 18)
                                .addComponent(bActualizar)
                                .addGap(53, 53, 53))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }


    private void listaAdminRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaAdminRMouseClicked


    }//GEN-LAST:event_listaAdminRMouseClicked

    private void bActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bActualizarActionPerformed


    }//GEN-LAST:event_bActualizarActionPerformed

    private javax.swing.JButton bActualizar;
    private javax.swing.JButton bLeer;
    private javax.swing.JButton bResponder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listaAdminR;
    private javax.swing.JList<String> listaCasosDisp;
    private List<CasoVO> casos;
    private Modelo modelo;
    private List<Integer> listaIdAdmin;
    private HiloRecepcion hilo;
    private HiloGestion hG;

    //Getters y Setters:
    public JButton getbActualizar() {
        return bActualizar;
    }

    public void setbActualizar(JButton bActualizar) {
        this.bActualizar = bActualizar;
    }

    public JButton getbLeer() {
        return bLeer;
    }

    public void setbLeer(JButton bLeer) {
        this.bLeer = bLeer;
    }

    public JButton getbResponder() {
        return bResponder;
    }

    public void setbResponder(JButton bResponder) {
        this.bResponder = bResponder;
    }

    public List<CasoVO> getCasos() {
        return casos;
    }

    public List<Integer> getListaIdAdmin() {
        return listaIdAdmin;
    }

    public void setListaIdAdmin(List<Integer> listaIdAdmin) {
        this.listaIdAdmin = listaIdAdmin;
    }

    public void setCasos(List<CasoVO> casos) {
        this.casos = casos;
    }

    public JList<String> getListaAdminR() {
        return listaAdminR;
    }

    public void setListaAdminR(DefaultListModel<String> model) {
        this.listaAdminR.setModel(model);
    }

    public JList<String> getListaCasosDisp() {
        return listaCasosDisp;
    }

    public void setListaCasosDisp(DefaultListModel<String> model) {
        this.listaCasosDisp.setModel(model);
    }

}
