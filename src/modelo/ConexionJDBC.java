/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Francisco Villegas Ostos
 * @author Ivan Campallo Sequera
 * @version 04/03/2019
 *
 */
public class ConexionJDBC {

    Connection conn = null;

    /**
     * Constructor de DbConnection
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ConexionJDBC() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost/proyectopsp", "root", "");

    }

    /**
     * Get de Connection
     *
     * @return conn devuelve la conexion
     */
    public Connection getConnection() {
        return conn;
    }

    /**
     * Permite terminar la conección
     */
    public void desconectar() {
        conn = null;
    }

}
