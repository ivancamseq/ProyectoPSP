/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import Excepciones.Excepciones;
import java.util.List;

/**
 * Interfaz encargada de definir los métodos que implementaremos en el JDBC
 *
 * @author Francisco Villegas Ostos
 * @author Ivan Campallo Sequera
 * @version 04/03/2019
 *
 */
public interface DAO {

    /**
     * Método encargado de cargar los tickets de la base de datos
     *
     * @param idAdmin del admin de reserva que le pasaremos para cargar sus
     * tickets
     * @param idCaso del caso que le pasaremos para cargar los tickets de ese
     * caso
     * @return devolveremos una lista de tickets donde utilizaremos la clase
     * POJO
     * @throws Excepciones que sera una clase propia donde controlaremos las
     * excepciones
     */
    public List<TicketVO> cargarTickets(int idAdmin, int idCaso) throws Excepciones;

    /**
     * Método encargado de cargar los casos de la base de datos
     *
     * @param id del admin para cargar los casos de dicho admin
     * @return devolveremos una lista de casos donde utilizaremos la clase POJO
     * @throws Excepciones que sera una clase propia donde controlaremos las
     * excepciones
     */
    public List<CasoVO> cargarCasos(int id) throws Excepciones;

    /**
     * Método que utilizaremos para introducir un ticket en la base de datos
     *
     * @param ticket le pasamos el ticket que vamos a introducir
     * @throws Excepciones que sera una clase propia donde controlaremos las
     * excepciones
     */
    public void introducirTicket(TicketVO ticket) throws Excepciones;

    /**
     * Método que utilizaremos para cargar las id de los admin de reservas de la
     * base de datos
     *
     * @return devolveremos una lista Integer con las id de los admin de
     * reservas
     * @throws Excepciones que sera una clase propia donde controlaremos las
     * excepciones
     */
    public List<Integer> cargarIdAdminReserva() throws Excepciones;

    /**
     * Método que utilizaremos para cerrar un caso determinado
     *
     * @param caso le pasaremos el caso que queremos cerrar
     * @param idAdminR del admin de reserva
     * @throws Excepciones que sera una clase propia donde controlaremos las
     * excepciones
     */
    public void cerrarCaso(CasoVO caso, int idAdminR) throws Excepciones;

}
