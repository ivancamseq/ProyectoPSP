/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Excepciones.Excepciones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import modelo.CasoVO;
import modelo.HiloGestion;
import modelo.HiloRecepcion;
import modelo.Modelo;
import modelo.TicketVO;
import vista.CPrincipalAdminS;
import vista.CTicketAdminS;

/**
 * @author Francisco Villegas Ostos
 * @author Ivan Campallo Sequera
 * @version 04/03/2019
 *
 */
public class ControladorPrincipalS implements ActionListener, MouseListener {

    private CPrincipalAdminS vista;
    private Modelo modelo;
    private HiloRecepcion hR;
    private HiloGestion hG;

    /**
     * Constructor del controlador de la ventana principal del administrador de
     * sistema
     *
     * @param vista
     * @param modelo
     * @param hR
     * @param hG
     */
    public ControladorPrincipalS(CPrincipalAdminS vista, Modelo modelo, HiloRecepcion hR, HiloGestion hG) {

        this.vista = vista;
        this.modelo = modelo;
        this.hR = hR;
        this.hG = hG;
        //Cargamos los datos de los administradores de reserva que hay en la BBDD
        cargarAdmin();
        new Thread(this.hG).start(); //Iniciamos el hilo que espera conexiones

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String o = e.getActionCommand();

        if (o.equals(vista.getbResponder().getText())) { //Si pulsa el botón "Responder"

            if (vista.getCasos().get(vista.getListaCasosDisp().getSelectedIndex()).getEstado().equals("cerrado")) {//Si el caso está cerrado

                JOptionPane.showMessageDialog(null, "El caso está cerrado", "Información", JOptionPane.INFORMATION_MESSAGE);

            } else if (vista.getCasos().get(vista.getListaCasosDisp().getSelectedIndex()).getEstado().equals("abierto")) { //Si el caso ya ha sido respondido

                JOptionPane.showMessageDialog(null, "El caso ya ha sido respondido", "Información", JOptionPane.INFORMATION_MESSAGE);

            } else {

                CTicketAdminS ticketResponder = new CTicketAdminS(vista.getListaIdAdmin().get(vista.getListaAdminR().getSelectedIndex()), vista.getCasos().get(vista.getListaCasosDisp().getSelectedIndex()).getIdcaso(), 0, modelo, hR);
                ticketResponder.setVisible(true);
                ticketResponder.getPanelLista().setVisible(false);
                ticketResponder.getTxtFecha().setVisible(false);
                ticketResponder.getjLabel1().setVisible(false);
                ticketResponder.getbActualizar().setVisible(false);

            }

        } else if (o.equals(vista.getbLeer().getText())) { //Si pulsa el botón "Leer"

            if (vista.getListaCasosDisp().getSelectedIndex() == -1) {//Si no selecciona ningún caso

                JOptionPane.showMessageDialog(null, "Selecciona el caso que quieres leer", "Información", JOptionPane.INFORMATION_MESSAGE);

            } else { //Abre una ventana para leer los tickets

                CTicketAdminS ticketLeer = new CTicketAdminS(vista.getListaIdAdmin().get(vista.getListaAdminR().getSelectedIndex()), vista.getCasos().get(vista.getListaCasosDisp().getSelectedIndex()).getIdcaso(), 1, modelo, hR);
                ticketLeer.setVisible(true);
                ticketLeer.getTxtFecha().setEditable(false);
                ticketLeer.getTxtDescripcion().setEditable(false);
                ticketLeer.getTxtAsunto().setEditable(false);
                ticketLeer.getCbPrioridad().setEnabled(false);
                ticketLeer.getbAceptar().setVisible(false);

                //A esta ventana llegarán los tickets mediante sockets e hilos
            }
        } else if (o.equals(vista.getbActualizar().getText())) { //Si pulsa el botón actualizar

            cargarAdmin();

        }

    }

    /**
     * Método para cargar los id de los administradores de reserva que han
     * mandado tickets
     */
    private void cargarAdmin() {

        try {

            DefaultListModel combo = new DefaultListModel();

            vista.setListaIdAdmin(this.modelo.obtenerIdAdminR());

            for (Integer id : vista.getListaIdAdmin()) {

                combo.addElement(id);

                vista.setListaAdminR(combo);

            }

        } catch (Excepciones e) { //Si se produce algún error:

            JOptionPane.showMessageDialog(null, e.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    /**
     * Método para cargar los datos de los casos en la lista de casos
     *
     * @param id
     */
    private void cargarCasos(int id) {

        try {

            vista.setCasos(this.modelo.cargarDatosCasos(id));

            DefaultListModel combo = new DefaultListModel();

            for (CasoVO a : vista.getCasos()) {

                verEstadoTickets(a);
                combo.addElement(a.getIdcaso() + ":" + a.getNumTickets() + ":" + a.getEstado());
                System.out.println(a.getEstado());
                vista.setListaCasosDisp(combo);

            }

        } catch (Excepciones e) { //Si se produce algún error
            JOptionPane.showMessageDialog(null, e.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    /**
     * Método para ver el estado de los casos
     *
     * @param caso
     */
    private void verEstadoTickets(CasoVO caso) {

        try {

            List<TicketVO> lista = this.modelo.cargarDatosTickets(vista.getListaIdAdmin().get(vista.getListaAdminR().getSelectedIndex()), caso.getIdcaso());

            TicketVO ticket = lista.get(lista.size() - 1);

            if (ticket.getId() == 0) { //Si ha respondido el admin de sistema

                caso.setEstado("abierto");

            } else if (ticket.getEstado().equals("cerrado")) { //Si el admin de reservas ha cerrado el caso

                caso.setEstado("cerrado");

            } else { //De otra forma estará pendiente por responder

                caso.setEstado("pendiente");
            }

        } catch (Excepciones e) { //Si se produce algún error

            JOptionPane.showMessageDialog(null, e.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);

        }

    }

    @Override
    public void mouseClicked(MouseEvent e) { //Método para controlar los clicks en la lista de administradores

        cargarCasos(vista.getListaIdAdmin().get(vista.getListaAdminR().getSelectedIndex()));

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
