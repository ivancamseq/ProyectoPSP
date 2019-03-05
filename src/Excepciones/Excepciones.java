/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepciones;

/**
 *
 * @author usuario
 */
public class Excepciones extends Exception {
    
     /**
     * Constructor vacío
     */ 
    
    public Excepciones () {
        
        super();
    }
    
    /**
     * Constructor al que le pasaremos el mensaje que vamos a mostrar 
     * @param mensaje , mensaje que vamos a mostrar
     */
    
    public Excepciones (String mensaje) {
        
        super(mensaje);
        
    }
    
    
    
}
