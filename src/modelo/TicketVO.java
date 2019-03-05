/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

/**
 * Clase POJO TicketVO que representa a la tabla tickets de la base de datos
 *
 * @author Francisco Villegas Ostos
 * @author Ivan Campallo Sequera
 * @version 04/03/2019
 *
 */
public class TicketVO implements Serializable {

    // Atributos de la clase
    private int id;
    private int idCaso;
    private int idAdminR;
    private String fecha;
    private String asunto;
    private String prioridad;
    private String descripcion;
    private String estado;

    /**
     * Constructor vacío de TicketVO
     */
    public TicketVO() {

    }

    /**
     * Constructor de TicketVO
     *
     * @param id del ticket
     * @param idCaso del caso
     * @param idAdminR del administrador de reserva
     * @param fecha del ticket
     * @param asunto del ticket
     * @param prioridad del ticket
     * @param descripcion del ticket
     * @param estado del ticket
     */
    public TicketVO(int id, int idCaso, int idAdminR, String fecha, String asunto, String prioridad, String descripcion, String estado) {
        this.id = id;
        this.idCaso = idCaso;
        this.idAdminR = idAdminR;
        this.fecha = fecha;
        this.asunto = asunto;
        this.prioridad = prioridad;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    /**
     * Constructor que utilizamos para el método cargarTickets del JDBC
     *
     * @param id del ticket
     * @param fecha del ticket
     * @param asunto del ticket
     * @param prioridad del ticket
     * @param descripcion del ticket
     * @param estado del ticket
     */
    public TicketVO(int id, String fecha, String asunto, String prioridad, String descripcion, String estado) {
        this.id = id;
        this.fecha = fecha;
        this.asunto = asunto;
        this.prioridad = prioridad;
        this.descripcion = descripcion;
        this.estado = estado;
    }

//    public TicketVO(String fecha, String asunto, String prioridad, String descripcion, String estado) {
//        this.fecha = fecha;
//        this.asunto = asunto;
//        this.prioridad = prioridad;
//        this.descripcion = descripcion;
//        this.estado = estado;
//    }
    // GET AND SET
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(int idCaso) {
        this.idCaso = idCaso;
    }

    public int getIdAdminR() {
        return idAdminR;
    }

    public void setIdAdminR(int idAdminR) {
        this.idAdminR = idAdminR;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
