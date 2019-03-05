/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import Excepciones.Excepciones;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.swing.JOptionPane;
import org.apache.commons.net.pop3.POP3MessageInfo;
import org.apache.commons.net.pop3.POP3SClient;
import org.apache.commons.net.smtp.AuthenticatingSMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.apache.commons.net.smtp.SimpleSMTPHeader;

/**
 * Clase Modelo de la aplicacion. Tendra toda la logica de la aplicacion y
 * servira intermediario entre el controlador y la clase JDBC
 *
 * @author Francisco Villegas Ostos
 * @author Ivan Campallo Sequera
 * @version 04/03/2019
 *
 */
public class Modelo {

    // Atributos de la clase
    private DAOJDBC ticketsdao;
    private Socket cliente;
    private ServerSocket servidor;
    private ObjectOutputStream salida;
    private Socket enviaS;
    private Map<Integer, Socket> mapa;
    private String host = "pop.gmail.com";
    private String username = "proyectoPSPvalleinclan@gmail.com";
    private String password = "pspproyect2019";
    private String from = "";
    private String subject = "";
    private String envio = "";
    private POP3MessageInfo[] messages;
    private POP3SClient pop3;
    private List<Correo> listaEmails;
    private Correo correo;

    /**
     * Constructor de la clase que usaremos para iniciar el socket servidor y el
     * cliente En el caso del cliente creamos socket y abriremos flujo de salida
     * para enviar su id al admin de sistema. En el caso del admin de sistema
     * crearemos el servidor y el mapa para guardar los sockets de los clientes
     * y sus id
     *
     * @param num nos servira para identificar cuando es un Admin de reserva y
     * cuando es un admin de sistema
     */
    public Modelo(int num) { //El número se corresponde con la id del admin

        if (num == 0) { //Si es el de sistema su id será 0

            try {

                servidor = new ServerSocket(5555); //Por lo tanto creamos el servidor
                mapa = new HashMap<>();

            } catch (IOException ex) {
                Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else { //En caso contrario será un cliente

            try {

                cliente = new Socket("localhost", 5555); //Creamos el cliente
                salida = new ObjectOutputStream(cliente.getOutputStream());
                salida.writeObject(num); //Enviamos la id del admin r por el flujo de salida

            } catch (IOException ex) {
                Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    /**
     * Método para cargar los datos de los tickets. Llamara al metodo cargar del
     * JDBC
     *
     * @param idAdmin del admin de reserva
     * @param idCaso del caso
     * @return lista de tickets donde guardaremos los tickets guardados
     * @throws Excepciones que sera una clase propia donde controlaremos las
     * excepciones
     */
    public List<TicketVO> cargarDatosTickets(int idAdmin, int idCaso) throws Excepciones {

        ticketsdao = new DAOJDBC();

        return ticketsdao.cargarTickets(idAdmin, idCaso);

    }

    /**
     * Método que llamara al cargar del JDBC
     *
     * @param id del admin de reserva
     * @return lista de casos donde guardaremos todos los casos
     * @throws Excepciones que sera una clase propia donde controlaremos las
     * excepciones
     */
    public List<CasoVO> cargarDatosCasos(int id) throws Excepciones {

        ticketsdao = new DAOJDBC();
        return ticketsdao.cargarCasos(id);

    }

    /**
     * Método para guardar un nuevo ticket. Llamara al metodo introducir del
     * JDBC
     *
     * @param ticket que vamos a guardar
     * @throws Excepciones que sera una clase propia donde controlaremos las
     * excepciones
     */
    public void guardarNuevo(TicketVO ticket) throws Excepciones {

        ticketsdao = new DAOJDBC();
        ticketsdao.introducirTicket(ticket);

    }

    /**
     * Método para obtener una lista con todas las id de los Admin R . Llamara
     * al metodo cargarIdAdmin del JDBC
     *
     * @return lista de id donde guardaremos las id de los admin
     * @throws Excepciones que sera una clase propia donde controlaremos las
     * excepciones
     */
    public List<Integer> obtenerIdAdminR() throws Excepciones {

        ticketsdao = new DAOJDBC();
        return ticketsdao.cargarIdAdminReserva();

    }

    /**
     * Método para obtener la id del último caso
     *
     * @param id del admin
     * @return id del último caso
     * @throws Excepciones que sera una clase propia donde controlaremos las
     * excepciones
     */
    public int ultimoCaso(int id) throws Excepciones {

        List<Integer> idCasos = new LinkedList<>();
        ticketsdao = ticketsdao = new DAOJDBC();

        for (CasoVO cVO : ticketsdao.cargarCasos(id)) { //Obtenemos los casos de la bd

            idCasos.add(cVO.getIdcaso());

        }

        if (idCasos.isEmpty()) { //Si la lista está vacía no hay casos

            return 0; //Devolvemos 0

        } else { //En caso contrario devolveremos el número mayor, que será el último caso

            return Collections.max(idCasos);

        }

    }

    /**
     * Método que utilizaremos para mandar los tickets desde el admin de reserva
     * al de sistema. Utilizaremos el flujo de salida para mandar el ticket
     *
     * @param ticket que vamos a mandar
     */
    public void enviarTicketReservaHilo(TicketVO ticket) {

        try {
            salida.writeObject(ticket);
        } catch (IOException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método que utilizaremos para mandar los tickets desde el admin de sistema
     * al de reserva. Utilizara el flujo de salida para mandar el ticket y
     * mediante el mapa creado en el constructor , podremos acceder a que admin
     * de reserva enviar el ticket
     *
     * @param ticket
     */
    public void enviarTicketSistemaHilo(TicketVO ticket) {

        Socket sistema = mapa.get(ticket.getIdAdminR());

        try {

            salida = new ObjectOutputStream(sistema.getOutputStream());
            salida.writeObject(ticket);
        } catch (IOException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método que utilizaremos para enviar un correo
     *
     * @param destinatario sera al que le vamos a mandar el correo
     * @param asunto del correo
     * @param mensaje contenido del correo
     */
    public void enviarEmail(String destinatario, String asunto, String mensaje) {

        AuthenticatingSMTPClient cliente = new AuthenticatingSMTPClient();

        String servidor = "smtp.gmail.com";

        String nomUsuario = "proyectoPSPvalleinclan@gmail.com";

        String contraseña = "pspproyect2019";

        int puerto = 587;

        String remitente = "proyectoPSPvalleinclan@gmail.com";

        try {

            int respuesta;

            // Creación de la clave para establecer un canal seguro
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(null, null);
            KeyManager km = kmf.getKeyManagers()[0];

            // nos conectamos al servidor SMTP
            cliente.connect(servidor, puerto);
            System.out.println("1 - " + cliente.getReplyString());
            // se establece la clave para la comunicación segura
            cliente.setKeyManager(km);

            respuesta = cliente.getReplyCode();
            if (!SMTPReply.isPositiveCompletion(respuesta)) {
                cliente.disconnect();
                JOptionPane.showMessageDialog(null, "CONEXIÓN RECHAZADA.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

            // se envía el commando EHLO
            cliente.ehlo(servidor);// necesario
            System.out.println("2 - " + cliente.getReplyString());

            // NECESITA NEGOCIACIÓN TLS - MODO NO IMPLICITO
            // Se ejecuta el comando STARTTLS y se comprueba si es true
            if (cliente.execTLS()) {
                System.out.println("3 - " + cliente.getReplyString());

                // se realiza la autenticación con el servidor
                if (cliente.auth(AuthenticatingSMTPClient.AUTH_METHOD.LOGIN, nomUsuario, contraseña)) {
                    System.out.println("4 - " + cliente.getReplyString());

                    // Es al que le vas a enviar el correo
                    // se crea la cabecera
                    SimpleSMTPHeader cabecera = new SimpleSMTPHeader(remitente, destinatario, asunto);

                    // el nombre de usuario y el email de origen coinciden
                    cliente.setSender(remitente);
                    cliente.addRecipient(destinatario);
                    System.out.println("5 - " + cliente.getReplyString());

                    // se envia DATA
                    Writer writer = cliente.sendMessageData();
                    if (writer == null) { // fallo
                        JOptionPane.showMessageDialog(null, "FALLO AL ENVIAR EL CORREO.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }

                    writer.write(cabecera.toString()); // cabecera
                    writer.write("<inicio>" + mensaje + "\n" + "<final>");// luego mensaje
                    writer.close();
                    System.out.println("6 - " + cliente.getReplyString());

                    boolean exito = cliente.completePendingCommand();
                    System.out.println("7 - " + cliente.getReplyString());

                    if (!exito) { // fallo
                        JOptionPane.showMessageDialog(null, "FALLO AL FINALIZAR TRANSACCIÓN.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        System.out.println("MENSAJE ENVIADO CON EXITO......");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "USUARIO NO AUTENTICADO.", "FALLO EN LA AUTENTIFICACIÓN", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "FALLO AL EJECUTAR  STARTTLS.", "FALLO EN LA EJECUCIÓN", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO CONECTAR CON EL SERVIDOR.", "FALLO EN LA CONEXIÓN", JOptionPane.ERROR_MESSAGE);
        }
        try {
            cliente.disconnect();
        } catch (IOException f) {
            JOptionPane.showMessageDialog(null, f.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        System.out.println("Fin de envío.");

    }

    /**
     * Método que utilizaremos para cargar los emails de nuestro correo
     */
    public void cargarEmails() {

        pop3 = new POP3SClient(true);
        listaEmails = new ArrayList();

        try {
            pop3.connect(host); // conectamos al servidor
            pop3.login(username, password); // logeamos
            messages = pop3.listMessages(); // guardamos los correos en el array creado
            if (messages == null) { // si es nulo
                System.err.println("Could not retrieve message list.");
                pop3.disconnect();
                return;
            } else if (messages.length == 0) { // si esta vacio
                System.out.println("No messages");
                pop3.logout();
                pop3.disconnect();
                return;
            }
            for (POP3MessageInfo msginfo : messages) { // recorremos la lista
                BufferedReader reader = (BufferedReader) pop3.retrieveMessage(msginfo.number);

                if (reader == null) {
                    System.err.println("Could not retrieve message header.");
                    pop3.disconnect();
                    System.exit(1);
                }
                String linea;
                boolean bandera = false;
                // y sacamos toda la informacion que necesitamos
                while ((linea = reader.readLine()) != null) {
                    String lower = linea.toLowerCase(Locale.ENGLISH);
                    if (lower.startsWith("from: ")) {
                        from = linea.substring(6).trim();
                    } else if (lower.startsWith("subject: ")) {
                        subject = linea.substring(9).trim();
                    } else if (lower.startsWith("<inicio>")) {
                        envio = linea.substring(8).trim();
                        bandera = true;
                    } else if (lower.startsWith("<final>")) {

                        bandera = false;
                    } else if (bandera == true) {

                        envio = envio + "\n" + linea.substring(0);

                    }

                }
                // la guardamos en una lista que creamos a parte
                correo = new Correo(from, subject, envio);
                listaEmails.add(correo);

            }
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Método para cerrar un caso. Llamara al cerrar caso del JDBC
     *
     * @param caso caso que queremos cerrar
     * @param idAdminR del admin de reserva
     * @throws Excepciones que sera una clase propia donde controlaremos las
     * excepciones
     */
    public void cerrarCaso(CasoVO caso, int idAdminR) throws Excepciones {

        ticketsdao.cerrarCaso(caso, idAdminR);

    }

    // GET AND SET
    public Socket getCliente() {
        return cliente;
    }

    public void setCliente(Socket cliente) {
        this.cliente = cliente;
    }

    public ServerSocket getServidor() {
        return servidor;
    }

    public void setServidor(ServerSocket servidor) {
        this.servidor = servidor;
    }

    public Socket getEnviaS() {
        return enviaS;
    }

    public void setEnviaS(Socket enviaS) {
        this.enviaS = enviaS;
    }

    public Map<Integer, Socket> getMapa() {
        return mapa;
    }

    public void setMapa(Map<Integer, Socket> mapa) {
        this.mapa = mapa;
    }

    public List<Correo> getListaEmails() {
        return listaEmails;
    }

    public void setListaEmails(List<Correo> listaEmails) {
        this.listaEmails = listaEmails;
    }

}
