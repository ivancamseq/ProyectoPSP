/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Modelo;
import vista.VentanaCorreo;

/**
 * @author Francisco Villegas Ostos
 * @author Ivan Campallo Sequera
 * @version 04/03/2019
 *
 */
public class ControladorVentanaCorreo implements ActionListener {

    private VentanaCorreo ventanaCorreo;
    private Modelo modelo;

    /**
     * Constructor para el controlador de la ventana de envío de correos
     *
     * @param ventanaCorreo
     * @param modelo
     */
    public ControladorVentanaCorreo(VentanaCorreo ventanaCorreo, Modelo modelo) {

        this.ventanaCorreo = ventanaCorreo;

        this.modelo = modelo;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String accion = e.getActionCommand();

        if (accion.equals(ventanaCorreo.getbAceptar())) { //Pulsa aceptar

            if (ventanaCorreo.getDestinatario().contains("@gmail.com")) { //Para asegurarnos de que ha puesto un correo electrónico

                modelo.enviarEmail(ventanaCorreo.getDestinatario(), ventanaCorreo.getAsunto(), ventanaCorreo.getMensaje());
                JOptionPane.showMessageDialog(null, "Mensaje Enviado", "CORREO CORRECTO", JOptionPane.INFORMATION_MESSAGE);

            } else {

                JOptionPane.showMessageDialog(null, "Debes introducir un correo electrónico", "CORREO INCORRECTO", JOptionPane.ERROR_MESSAGE);

            }

        } else { //Pulsa cancelar

            ventanaCorreo.dispose();

        }

    }

}
