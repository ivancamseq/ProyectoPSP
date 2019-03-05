/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Excepciones.Excepciones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import modelo.HiloRecepcionReserva;
import modelo.Modelo;
import modelo.TicketVO;
import vista.CTicketAdminR;

/**
 * @author Francisco Villegas Ostos
 * @author Ivan Campallo Sequera
 * @version 04/03/2019
 *
 */
public class ControladorTicketAdminR implements ActionListener, MouseListener {

    private CTicketAdminR vista;
    private Modelo modelo;
    private HiloRecepcionReserva hR;
    private DefaultListModel combo;

    /**
     * Constructor del controlador de la ventana de tickets de los
     * administradores de reserva
     *
     * @param vista
     * @param modelo
     * @param hR
     */
    public ControladorTicketAdminR(CTicketAdminR vista, Modelo modelo, HiloRecepcionReserva hR) {

        this.vista = vista;
        this.modelo = modelo;
        this.hR = hR; //Hilo para recibir los tickets que lleguen por sockets desde el admin sistema
        cargarTicket();//Método para cargar los tickets en una lista de tickets

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String o = e.getActionCommand();

        if (o.equals(vista.getStringAceptar())) { //Si pulsa el botón "Aceptar"

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");//Decimos el formato de fecha que queremos
            Date date = new Date();//Sacamos la fecha actual

            String fecha = dateFormat.format(date);//Lo guardamos en un String

            //Envio desde el Admin. Reserva
            if (vista.getIdCaso() == 0) { //Nuevo caso:

                TicketVO ticket = new TicketVO(1, CalcularIdCaso(), vista.getIdAdmin(), fecha, vista.getTxtAsunto().getText(), vista.getCbPrioridad().getSelectedItem().toString(),
                        vista.getTxtDescripcion().getText(), "abierto");

                modelo.enviarTicketReservaHilo(ticket);

            } else if (vista.getIdCaso() != 0) { //Continuar caso:

                TicketVO ticket = new TicketVO(CalcularIdTicket(), vista.getIdCaso(), vista.getIdAdmin(), fecha, vista.getTxtAsunto().getText(), vista.getCbPrioridad().getSelectedItem().toString(),
                        vista.getTxtDescripcion().getText(), "abierto");

                modelo.enviarTicketReservaHilo(ticket);

            }

            vista.dispose(); //Cerramos la ventana

        } else if (o.equals(vista.getbCancelar())) { //Si pulsa el botón "Cancelar"

            vista.dispose();

        } else if (o.equals(vista.getStringActualizar())) { //Si pulsa el botón "Actualizar"

            if (hR.devolverTicket() != null) { //Si el ticket no es null

                TicketVO ticketNuevo = hR.devolverTicket(); //Obtenemos el ticket que recibe el hilo

                hR.inicializarTicket(); //Ponemos el ticket a null

                //Lo añadimos a la lista:
                combo.addElement(ticketNuevo.getAsunto());

                vista.getListaT().add(ticketNuevo);

                vista.getListaTickets().repaint();

            }

        }

    }

    /**
     * Método para cargar los tickets en la lista de tickets
     */
    private void cargarTicket() {

        try {

            combo = new DefaultListModel();

            vista.setListaT(this.modelo.cargarDatosTickets(vista.getIdAdmin(), vista.getIdCaso()));

            for (TicketVO a : vista.getListaT()) {

                combo.addElement(a.getAsunto());

                vista.setListaTickets(combo);

            }

        } catch (Excepciones e) { //Si se produce algún error

            JOptionPane.showMessageDialog(null, e.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    /**
     * Método para obtener la id del caso que debe continuar. Se usará al crear
     * nuevos casos.
     *
     * @return
     */
    private int CalcularIdCaso() {

        try {
            return modelo.ultimoCaso(vista.getIdAdmin()) + 1;
        } catch (Excepciones ex) {
            Logger.getLogger(ControladorTicketAdminR.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }

    /**
     * Método para calcular la id del ticket que debe continuar. Se usará cada
     * vez que se continue un caso.
     *
     * @param ticket
     */
    private int CalcularIdTicket() {

        return vista.getListaTickets().getModel().getSize() + 1;

    }

    /**
     * Método para rellenar los campos del formulario con los datos del ticket
     * seleccionado
     */
    private void obtenerDatos() {

        vista.setTxtFecha(vista.getListaT().get(vista.getListaTickets().getSelectedIndex()).getFecha());
        vista.setTxtAsunto(vista.getListaT().get(vista.getListaTickets().getSelectedIndex()).getAsunto());
        vista.setTxtDescripcion(vista.getListaT().get(vista.getListaTickets().getSelectedIndex()).getDescripcion());

        if (vista.getListaT().get(vista.getListaTickets().getSelectedIndex()).getPrioridad().equals("Baja")) {

            vista.setCbPrioridad(0);

        } else if (vista.getListaT().get(vista.getListaTickets().getSelectedIndex()).getPrioridad().equals("Media")) {

            vista.setCbPrioridad(1);

        } else {

            vista.setCbPrioridad(2);
        }

    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {

        obtenerDatos(); //Controlamos que se pulse en la lista de tickets

    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {

    }

}
