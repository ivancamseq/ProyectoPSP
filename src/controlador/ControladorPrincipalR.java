/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Excepciones.Excepciones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import modelo.CasoVO;
import modelo.HiloRecepcionReserva;
import modelo.Modelo;
import vista.CPrincipalAdminR;
import vista.CTicketAdminR;
import vista.VentanaCorreo;
import vista.VistaRecibirCorreo;

/**
 * @author Francisco Villegas Ostos
 * @author Ivan Campallo Sequera
 * @version 04/03/2019
 *
 */
public class ControladorPrincipalR implements ActionListener {

    private CPrincipalAdminR vista;
    private Modelo modelo;
    private HiloRecepcionReserva hR;

    /**
     * Constructor para la ventana principal de los administradores de reserva
     *
     * @param vista
     * @param modelo
     * @param hR
     */
    public ControladorPrincipalR(CPrincipalAdminR vista, Modelo modelo, HiloRecepcionReserva hR) {

        this.vista = vista;
        this.modelo = modelo;
        this.hR = hR;
        this.hR.ejecutarHiloSistema(this.modelo.getCliente());
        //Cargamos los datos del administrador de reserva en función de su id:
        cargar(vista.getIdAdmin());

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String o = e.getActionCommand();

        if (o.equals(vista.getbNuevo())) { //Si pulsa el botón "Nuevo caso"

            CTicketAdminR ticketNuevo = new CTicketAdminR(vista.getIdAdmin(), 0, 1, this.modelo, this.hR);

            ticketNuevo.setVisible(true);

            ticketNuevo.getjLabel1().setVisible(false);
            ticketNuevo.getPanelLista().setVisible(false);
            ticketNuevo.getTxtFecha().setVisible(false);
            ticketNuevo.getbActualizar().setVisible(false);

        } else if (o.equals(vista.getbContinuar())) { //Si pulsa el botón "Continuar caso"

            if (vista.getListaCasos().getSelectedIndex() == -1) { //Si no selecciona caso:

                JOptionPane.showMessageDialog(null, "Selecciona el caso que quieres continuar", "Información", JOptionPane.INFORMATION_MESSAGE);

            } else if (vista.getListaC().get(vista.getListaCasos().getSelectedIndex()).getEstado().equals("cerrado")) { //Si el caso seleccionado está cerrado

                JOptionPane.showMessageDialog(null, "El caso ha sido cerrado, no puedes continuarlo", "Información", JOptionPane.INFORMATION_MESSAGE);

            } else {

                CTicketAdminR ticketContinuar = new CTicketAdminR(vista.getIdAdmin(), vista.getListaC().get(vista.getListaCasos().getSelectedIndex()).getIdcaso(), 1, this.modelo, this.hR);

                ticketContinuar.setVisible(true);

                ticketContinuar.getjLabel1().setVisible(false);
                ticketContinuar.getPanelLista().setVisible(false);
                ticketContinuar.getTxtFecha().setVisible(false);
                ticketContinuar.getbActualizar().setVisible(false);

            }

        } else if (o.equals(vista.getbActualizar())) { //Si pulsa el botón "Actualizar"

            cargar(vista.getIdAdmin()); //Se vuelven a cargar los datos

        } else if (o.equals(vista.getbCerrarCaso().getText())) { //Si pulsa el botón "Cerrar caso"

            if (vista.getListaCasos().getSelectedIndex() == -1) { //Si no ha seleccionado caso:

                JOptionPane.showMessageDialog(null, "Selecciona el caso que quieres cerrar", "Información", JOptionPane.INFORMATION_MESSAGE);

            } else {

                try {

                    CasoVO caso = vista.getListaC().get(vista.getListaCasos().getSelectedIndex());
                    modelo.cerrarCaso(caso, vista.getIdAdmin());
                    vista.getListaC().get(vista.getListaCasos().getSelectedIndex()).setEstado("cerrado");
                    vista.getListaCasos().repaint();

                } catch (Excepciones ex) {

                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);

                }

            }

        } else if (o.equals(vista.getbLeer())) { //Si pulsa el botón "Leer caso"

            if (vista.getListaCasos().getSelectedIndex() == -1) { //Si no selecciona caso:

                JOptionPane.showMessageDialog(null, "Selecciona el caso que quieres leer", "Información", JOptionPane.INFORMATION_MESSAGE);

            } else {

                CTicketAdminR ticketLeer = new CTicketAdminR(vista.getIdAdmin(), vista.getListaC().get(vista.getListaCasos().getSelectedIndex()).getIdcaso(), 1, this.modelo, this.hR);
                ticketLeer.setVisible(true);
                ticketLeer.getTxtFecha().setEditable(false);
                ticketLeer.getTxtDescripcion().setEditable(false);
                ticketLeer.getTxtAsunto().setEditable(false);
                ticketLeer.getCbPrioridad().setEnabled(false);
                ticketLeer.getbAceptar().setVisible(false);

            }
        } else if (o.equals(vista.getbEnviarCorreo())) { //Si pulsa el botón "Enviar correo"

            VentanaCorreo ventana = new VentanaCorreo(modelo);

            ventana.setVisible(true);

        } else if (o.equals(vista.getbRecibirCorreo())) { //Si pulsa el botón "Recibir correo"

            VistaRecibirCorreo ventana = new VistaRecibirCorreo(modelo);

            ventana.getTxtAsunto().setEditable(false);
            ventana.getTxtMensaje().setEditable(false);
            ventana.getTxtRemitente().setEditable(false);

            ventana.setVisible(true);

        }

    }

    /**
     * Método para cargar los datos del administrador de reserva en función de
     * su id:
     *
     * @param id - La id que el administrador de reserva que ha introducido en
     * el login
     */
    private void cargar(int id) {

        try {

            DefaultListModel combo = new DefaultListModel();

            vista.setListaC(this.modelo.cargarDatosCasos(id));

            for (CasoVO a : vista.getListaC()) {

                combo.addElement(a.getIdcaso() + ":" + a.getNumTickets() + ":" + a.getEstado());

                vista.setListaCasos(combo);

            }

        } catch (Excepciones e) { //Si se produce algún error:

            JOptionPane.showMessageDialog(null, e.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
        }

    }

}
