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
import vista.CLoginAdminR;
import vista.CPrincipalAdminR;

/**
 * @author Francisco Villegas Ostos
 * @author Ivan Campallo Sequera
 * @version 04/03/2019
 *
 */
public class ControladorLogin implements ActionListener {

    private CLoginAdminR vista;
    private Modelo modelo;

    /**
     * Constructor de la ventana login para los administradores de reserva
     *
     * @param vista
     */
    public ControladorLogin(CLoginAdminR vista) {

        this.vista = vista;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String o = e.getActionCommand();

        try {

            if (o.equals(vista.getbAceptar())) { //Si pulsa el botón "Aceptar"

                if (vista.getTxtIdAdmin().equals("")) { //Si la id está vacía:

                    JOptionPane.showMessageDialog(null, "Introduzca su id para iniciar sesión", "ID VACÍA", JOptionPane.ERROR_MESSAGE);

                } else { //Sino abrimos la ventana principal:

                    modelo = new Modelo(Integer.parseInt(vista.getTxtIdAdmin()));
                    System.out.println(vista.getTxtIdAdmin());
                    CPrincipalAdminR ventanaPricipal = new CPrincipalAdminR(Integer.valueOf(vista.getTxtIdAdmin()), modelo);

                    ventanaPricipal.setVisible(true);

                }

            } else if (o.equals(vista.getbCancelar())) { //Si pulsa el botón "Cancelar"

                vista.dispose();  // Cerramos la ventana

            }

        } catch (NumberFormatException f) { //Si introduce otro carácter que no sea numérico:

            JOptionPane.showMessageDialog(null, "Introduzca su id para iniciar sesión", "ID INCORRECTA", JOptionPane.ERROR_MESSAGE);

        }

    }

}
