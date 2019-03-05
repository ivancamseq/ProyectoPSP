/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

/**
 * 
 * Clase POJO CasosVO
 * @author Francisco Villegas Ostos
 * @author Ivan Campallo Sequera
 * @version 04/03/2019
 *
 */
public class CasoVO implements Serializable {

    // Atributos de la clase
    
    private int idcaso;
    private int numTickets;
    private String estado;

    
    /** 
     * Constructor vacío
     */
    
    public CasoVO() {

    }

    /** 
     * Constructor de CasoVO
     * @param idcaso del caso
     * @param numTickets numero de tickets que hay en el caso
     * @param estado del caso
     */
    
    public CasoVO(int idcaso, int numTickets, String estado) {
        this.idcaso = idcaso;
        this.numTickets = numTickets;
        this.estado = estado;
    }
    
    
    // GET AND SET

    public int getIdcaso() {
        return idcaso;
    }

    public void setIdcaso(int idcaso) {
        this.idcaso = idcaso;
    }

    public int getNumTickets() {
        return numTickets;
    }

    public void setNumTickets(int numTickets) {
        this.numTickets = numTickets;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
