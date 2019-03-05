/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase HiloRecepcionReserva actuara como un hilo que se encargar de recibir
 * los tickets enviados por el admin de sistema
 *
 * @author Francisco Villegas Ostos
 * @author Ivan Campallo Sequera
 * @version 04/03/2019
 *
 */
public class HiloRecepcionReserva implements Runnable {

    // Atributos de la clase
    private Modelo modelo;
    private TicketVO ticket;
    private ObjectInputStream entrada;
    private Socket socket;

    /**
     * Constructor de HiloRecepcionReserva donde le pasaremos el modelo
     *
     * @param modelo modelo
     */
    public HiloRecepcionReserva(Modelo modelo) {

        this.modelo = modelo;
        this.ticket = new TicketVO();

    }

    /**
     * Método que ejecuta el hilo encargado de recibir los tickets que el admin
     * S manda
     *
     * @param socket del sistema
     */
    public void ejecutarHiloSistema(Socket socket) {

        this.socket = socket;

        new Thread(this).start();

    }

    /**
     * Método para obtener el ticket que el hilo ha leído
     *
     * @return ticketVO devuelve el ticket
     */
    public TicketVO devolverTicket() {

        return this.ticket;
    }

    /**
     * Método para poner el ticket a null, lo usaremos después de cada lectura
     */
    public void inicializarTicket() {

        this.ticket = null;

    }

    /**
     * Metodo run que estara esperando a que responda un admin de sistema un
     * ticket para leerlo
     */
    @Override
    public void run() {

        while (true) {

            try {

                entrada = new ObjectInputStream(this.socket.getInputStream());
                Object o = entrada.readObject();

                if (o.getClass().equals(TicketVO.class)) {//Si el objeto es de clase "TicketVO"

                    this.ticket = (TicketVO) o;

                }

            } catch (IOException ex) {
                Logger.getLogger(HiloRecepcionReserva.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HiloRecepcionReserva.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
