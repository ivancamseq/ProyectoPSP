/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 * Clase POJO donde guardaremos los datos de los correos
 *
 * @author Francisco Villegas Ostos
 * @author Ivan Campallo Sequera
 * @version 04/03/2019
 *
 */
public class Correo {

    // Atributos de la clase
    private String propietario;
    private String asunto;
    private String mensaje;

    /**
     * Constructor vacio de la clase Correo
     */
    public Correo() {

    }

    /**
     * Constructor de la clase Correo
     *
     * @param propietario donde guardaremos el propietario del correo
     * @param asunto del correo
     * @param mensaje contenido del correo
     */
    public Correo(String propietario, String asunto, String mensaje) {
        this.propietario = propietario;
        this.asunto = asunto;
        this.mensaje = mensaje;
    }

    // GET AND SET
    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
