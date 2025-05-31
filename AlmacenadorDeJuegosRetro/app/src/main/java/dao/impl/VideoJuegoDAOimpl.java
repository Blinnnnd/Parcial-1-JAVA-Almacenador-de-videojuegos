/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao.impl;

import Model.Consola;
import Model.VideoJuego;
import java.sql.Connection;
import java.util.List;
import Util.DBUtil;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import dao.VideoJuegoDAO;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Blinnnnd
 * 
 */

public class VideoJuegoDAOimpl implements VideoJuegoDAO{

    @Override
    public void guardar(VideoJuego juego) {
        String sql = "INSERT INTO videojuego (nombre, añodelanzamiento, consola_id) VALUES (?, ?, ?)";

        try (Connection conn = DBUtil.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, juego.getNombre());
            stmt.setInt(2, juego.getAñodelanzamiento());
            stmt.setInt(3, juego.getConsola().getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al guardar videojuego: " + e.getMessage());
        }
    }

    @Override
    public VideoJuego buscarPorId(int id) {
        String sql = "SELECT v.id, v.nombre, v.añodelanzamiento, c.id AS consola_id, c.titulo, c.fabricante " +
                     "FROM videojuego v JOIN consola c ON v.consola_id = c.id WHERE v.id = ?";

        try (Connection conn = DBUtil.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Consola consola = new Consola(
                        rs.getInt("consola_id"),
                        rs.getString("titulo"),
                        rs.getString("fabricante")
                );

                return new VideoJuego(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("añodelanzamiento"),
                        consola
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar videojuego: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<VideoJuego> listarTodos() {
        List<VideoJuego> lista = new ArrayList<>();
        String sql = "SELECT v.id, v.nombre, v.añodelanzamiento, c.id AS consola_id, c.titulo, c.fabricante " +
                     "FROM videojuego v JOIN consola c ON v.consola_id = c.id";

        try (Connection conn = DBUtil.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Consola consola = new Consola(
                        rs.getInt("consola_id"),
                        rs.getString("titulo"),
                        rs.getString("fabricante")
                );

                VideoJuego juego = new VideoJuego(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("añodelanzamiento"),
                        consola
                );

                lista.add(juego);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar videojuegos: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public void actualizar(VideoJuego juego) {
        String sql = "UPDATE videojuego SET nombre = ?, añodelanzamiento = ?, consola_id = ? WHERE id = ?";

        try (Connection conn = DBUtil.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, juego.getNombre());
            stmt.setInt(2, juego.getAñodelanzamiento());
            stmt.setInt(3, juego.getConsola().getId());
            stmt.setInt(4, juego.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar videojuego: " + e.getMessage());
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM videojuego WHERE id = ?";

        try (Connection conn = DBUtil.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar videojuego: " + e.getMessage());
        }
    }

    @Override
    public void eliminarPorID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
