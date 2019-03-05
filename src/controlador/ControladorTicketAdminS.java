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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import modelo.HiloRecepcion;
import modelo.Modelo;
import modelo.TicketVO;
import vista.CTicketAdminS;

/**
 * @author Francisco Villegas Ostos
 * @author Ivan Campallo Sequera
 * @version 04/03/2019
 *
 */
public class ControladorTicketAdminS implements ActionListener, MouseListener {

    private CTicketAdminS vista;
    private Modelo modelo;
    private HiloRecepcion hR;
    private DefaultListModel combo;
    private int id;

    /**
     * Constructor del controlador de la ventana de tickets del administrador de
     * sistema.
     *
     * @param vista
     * @param modelo
     * @param hR
     * @param id
     */
    public ControladorTicketAdminS(CTicketAdminS vista, Modelo modelo, HiloRecepcion hR, int id) {

        this.vista = vista;
        this.modelo = modelo;
        this.hR = hR; //Hilo que recepcionará los tickets de los administradores de reserva
        this.id = id;
        cargarTicket();//Método para cargar los tickets en la lista de tickets

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String o = e.getActionCommand();

        if (o.equals(vista.getbAceptar().getText())) { //Envia

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");//Decimos el formato de fecha que queremos
            Date date = new Date();//Sacamos la fecha actual

            String fecha = dateFormat.format(date);//Lo guardamos en un String

            try {

                TicketVO ticket = new TicketVO(0, vista.getIdCaso(), vista.getIdAdmin(), fecha, vista.getTxtAsunto().getText(), vista.getCbPrioridad().getSelectedItem().toString(),
                        vista.getTxtDescripcion().getText(), "abierto");

                this.modelo.guardarNuevo(ticket);

                modelo.enviarTicketSistemaHilo(ticket);

            } catch (Excepciones ex) { //Si se produce algún error
                Logger.getLogger(CTicketAdminS.class.getName()).log(Level.SEVERE, null, ex);
            }

            vista.dispose(); //Cerramos la ventana

        } else if (o.equals(vista.getbActualizar().getText())) { //Recepciona

            if (hR.obtenerTicket() != null) { //Comprobamos si la recepción es null

                TicketVO ticketNuevo = hR.obtenerTicket();

                if (ticketNuevo.getIdAdminR() == id) {  //Si la id del ticket recepcionado es el mismo que para el caso que 
                    //se está visualizando se añadirá a a la lista de tickets

                    hR.inicializarTicket(); //Lo ponemos a null para que se deje de leer.

                    //Lo añadimos a la lista de tickets:
                    combo.addElement(ticketNuevo.getAsunto());

                    vista.getListaT().add(ticketNuevo);

                    vista.getListaTickets().repaint();

                }

            }
        } else if (o.equals(vista.getbCancelar().getText())) {

            vista.dispose(); //Cerramos la ventana

        }

    }

    /**
     * Método para cargar los tickets en la lista de tickets, en función de la
     * id del administrador de reserva que se ha elegido anteriormente.
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
     * Método para obtener rellenar los campos del formulario con los datos del
     * ticket seleccionado de la lista
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
    public void mouseClicked(MouseEvent e) {

        obtenerDatos(); //Controlamos que cuando se pulse en la lista de tickets se muestren los datos

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
