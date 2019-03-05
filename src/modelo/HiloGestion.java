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
import vista.CPrincipalAdminS;

/**
 * Clase HiloGestion actuara como un hilo que controlara las conexiones
 *
 * @author Francisco Villegas Ostos
 * @author Ivan Campallo Sequera
 * @version 04/03/2019
 *
 */
public class HiloGestion implements Runnable {

    // Atributos de la clase
    private HiloRecepcion hilo;
    private Modelo modelo;
    private ObjectInputStream entrada;

    /**
     * Constructor de HiloGestion le pasamos el HiloRecepcion y el modelo
     *
     * @param hilo de HiloRecepcion
     * @param modelo referente al modelo
     */
    public HiloGestion(HiloRecepcion hilo, Modelo modelo) {
        this.hilo = hilo;
        this.modelo = modelo;
    }

    /**
     * Metodo run del hilo. Su funcion sera la siguiente: Controlar las
     * conexiones El servidor de quedara esperando a que un cliente se conecte,
     * una vez que se conecte recibiremos el id mediante un flujo de entrada y
     * la meteremos en el mapa creado junto con el id y el socket
     */
    @Override
    public void run() {

        while (true) {

            try {

                Socket socket = new Socket();
                socket = modelo.getServidor().accept(); // Esperamos la conexion del cliente
                System.out.println("Conectado " + socket.getPort());
                entrada = new ObjectInputStream(socket.getInputStream()); // Abrimos flujo de entrada para recibir la ID
                Object o = entrada.readObject();

                if (o.getClass().equals(Integer.class)) {

                    Integer idAdminR = (Integer) o;
                    modelo.getMapa().put(idAdminR, socket); // Añadimos al mapa la ID y su Socket.
                    hilo.ejecutarHilo(socket, entrada);

                    System.out.println("Conectado " + socket.getPort());

                }

            } catch (IOException ex) {
                Logger.getLogger(CPrincipalAdminS.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HiloGestion.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
