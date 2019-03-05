/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.DefaultListModel;
import modelo.Correo;
import modelo.Modelo;
import vista.VistaRecibirCorreo;

/**
 * @author Francisco Villegas Ostos
 * @author Ivan Campallo Sequera
 * @version 04/03/2019
 *
 */
public class ControladorVentanaRecibir implements ActionListener, MouseListener {

    private VistaRecibirCorreo vista;
    private Modelo modelo;
    private DefaultListModel combo;

    /**
     * Constructor del controlador de la ventana de recibir correo
     *
     * @param vista
     * @param modelo
     */
    public ControladorVentanaRecibir(VistaRecibirCorreo vista, Modelo modelo) {

        this.vista = vista;
        this.modelo = modelo;
        this.modelo.cargarEmails();
        llenarLista(); //Llenamos la lista con los correos

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String o = e.getActionCommand();

        if (o.equals(vista.getjButton1().getText())) { //Si pulsa en el botón "Salir"

            vista.dispose();

        } else if (o.equals(vista.getjButton2().getText())) { //Si pulsa en el botón "Actualizar"

            modelo.cargarEmails(); //Cargamos los emails
            llenarLista(); //Llenamos la lista

        }

    }

    /**
     * Método para llenar la lista de los emails.
     */
    public void llenarLista() {

        combo = new DefaultListModel();

        for (Correo c : modelo.getListaEmails()) {

            combo.addElement(c.getAsunto());

        }

        vista.setjList1(combo);

    }

    /**
     * Método para llenar los campos del formulario con los datos del correo
     * seleccionado de la lista
     */
    public void llenarDatos() {

        vista.setTxtAsunto(modelo.getListaEmails().get(vista.getjList1().getSelectedIndex()).getAsunto());
        vista.setTxtRemitente(modelo.getListaEmails().get(vista.getjList1().getSelectedIndex()).getPropietario());
        vista.setTxtMensaje(modelo.getListaEmails().get(vista.getjList1().getSelectedIndex()).getMensaje());

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        llenarDatos(); //Controlamos que al pusar sobre la lista de emails se muestren los datos

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
