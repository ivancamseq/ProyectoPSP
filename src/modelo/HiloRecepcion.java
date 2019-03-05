/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import Excepciones.Excepciones;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase HiloRecepcion actuara como un hilo que se encargar de recibir los
 * tickets enviados por el admin de reserva
 *
 * @author Francisco Villegas Ostos
 * @author Ivan Campallo Sequera
 * @version 04/03/2019
 *
 */
public class HiloRecepcion implements Runnable {

    // Atributos de la clase
    private Modelo modelo;
    private TicketVO ticket;
    private Socket socket;
    private ObjectInputStream entrada;

    /**
     * Constructor de HiloRecepcion donde le pasaremos el modelo
     *
     * @param modelo modelo
     */
    public HiloRecepcion(Modelo modelo) {

        this.modelo = modelo;
        this.ticket = new TicketVO();

    }

    /**
     * Método para obtener el ticket enviado por el admin r
     *
     * @return ticketVO
     */
    public TicketVO obtenerTicket() {

        return this.ticket;

    }

    /**
     * Método que ejecuta el hilo que se encargará de esperar a que lleguen
     * nuevos tickets
     *
     * @param socket socket del cliente
     * @param entrada flujo de entrada
     */
    public void ejecutarHilo(Socket socket, ObjectInputStream entrada) {

        this.socket = socket;
        this.entrada = entrada;

        new Thread(this).start();

    }

    /**
     * Método para poner el ticket null, después de leer usaremos este método
     */
    public void inicializarTicket() {

        this.ticket = null;

    }

    /**
     * Metodo run que estara esperando a que envie un admin de reserva un ticket
     * para leerlo
     */
    @Override
    public void run() {

        while (true) {

            try {

                Object o = entrada.readObject();

                if (o.getClass().equals(TicketVO.class)) { //Si el objeto recibido es de clase TicketVO:

                    this.ticket = (TicketVO) o;

                    try {
                        this.modelo.guardarNuevo(ticket);
                    } catch (Excepciones ex) {
                        Logger.getLogger(HiloRecepcion.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            } catch (IOException ex) {
                Logger.getLogger(HiloRecepcionReserva.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HiloRecepcion.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
