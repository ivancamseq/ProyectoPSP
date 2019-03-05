/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import Excepciones.Excepciones;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que implementara la interfaz DAO
 *
 * @author Francisco Villegas Ostos
 * @author Ivan Campallo Sequera
 * @version 04/03/2019
 *
 */
public class DAOJDBC implements DAO {

    ConexionJDBC conexion;

    @Override
    public List<TicketVO> cargarTickets(int idAdmin, int idCaso) throws Excepciones {

        List<TicketVO> lista = new ArrayList<>();

        try {
            conexion = new ConexionJDBC();
            Statement comando = conexion.getConnection().createStatement();

            ResultSet registro = comando
                    .executeQuery("select idTicket,fecha,asunto,prioridad,descripcion, estado from tickets where idCaso =" + idCaso + " and idAdminR = " + idAdmin + " ");
            while (registro.next() == true) {
                lista.add(new TicketVO(registro.getInt("idTicket"), registro.getString("fecha"), registro.getString("asunto"), registro.getString("prioridad"), registro.getString("descripcion"), registro.getString("estado")));
            }

            return lista;

        } catch (SQLException | ClassNotFoundException e) {

            throw new Excepciones("Error: " + e.getMessage());

        }

    }

    @Override
    public List<CasoVO> cargarCasos(int id) throws Excepciones {

        List<CasoVO> lista = new ArrayList<>();

        try {

            conexion = new ConexionJDBC();
            Statement comando = conexion.getConnection().createStatement();

            ResultSet registro = comando
                    .executeQuery("select idCaso, COUNT(idTicket) ,estado from tickets where idAdminR =" + id + " group by idCaso");
            while (registro.next() == true) {
                lista.add(new CasoVO(registro.getInt("idCaso"), registro.getInt("COUNT(idTicket)"), registro.getString("estado")));
            }

            return lista;

        } catch (SQLException | ClassNotFoundException e) {

            throw new Excepciones("Error: " + e.getMessage());

        }

    }

    @Override
    public void introducirTicket(TicketVO ticket) throws Excepciones {

        try {

            conexion = new ConexionJDBC();
            Statement comando = conexion.getConnection().createStatement();

            comando.executeUpdate("insert into tickets(idAdminR,idCaso,idTicket,fecha,asunto,prioridad,descripcion,estado) values (" + ticket.getIdAdminR() + "," + ticket.getIdCaso() + ","
                    + ticket.getId() + ",'" + ticket.getFecha() + "','" + ticket.getAsunto() + "','" + ticket.getPrioridad()
                    + "','" + ticket.getDescripcion() + "','" + ticket.getEstado() + "')");

        } catch (SQLException | ClassNotFoundException e) {

            throw new Excepciones("Error: " + e.getMessage());

        }

    }

    @Override
    public List<Integer> cargarIdAdminReserva() throws Excepciones {

        try {

            List<Integer> lista = new ArrayList<Integer>();

            conexion = new ConexionJDBC();
            Statement comando = conexion.getConnection().createStatement();

            ResultSet registro = comando
                    .executeQuery("select idAdminR from tickets group by idAdminR");

            while (registro.next()) {

                lista.add(registro.getInt("idAdminR"));

            }

            return lista;

        } catch (SQLException | ClassNotFoundException e) {

            throw new Excepciones("Error: " + e.getMessage());

        }

    }

    @Override
    public void cerrarCaso(CasoVO caso, int idAdminR) throws Excepciones {

        try {

            conexion = new ConexionJDBC();
            Statement comando = conexion.getConnection().createStatement();

            //update tickets set estado='cerrado' where (idCaso=1 and idAdminR=1);
            System.out.println("Caso: " + caso.getIdcaso());
            System.out.println("IdAdminR " + idAdminR);

            comando.executeUpdate("update tickets set estado='cerrado' where (idCaso=" + caso.getIdcaso() + " and idAdminR=" + idAdminR + ")");

        } catch (SQLException | ClassNotFoundException e) {

            e.printStackTrace();

            throw new Excepciones("Error: " + e.getMessage());

        }

    }

}
