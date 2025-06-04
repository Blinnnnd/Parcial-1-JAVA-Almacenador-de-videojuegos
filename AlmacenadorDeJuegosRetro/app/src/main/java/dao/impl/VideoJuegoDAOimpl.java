package dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Model.Consola;
import Model.VideoJuego;
import Util.DBUtil;
import dao.VideoJuegoDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Blinnnnd
 */
public class VideoJuegoDAOimpl implements VideoJuegoDAO {

    private static final Logger logger = LogManager.getLogger(VideoJuegoDAOimpl.class);

    @Override
    public void guardar(VideoJuego juego) {
        String sql = "INSERT INTO videojuego (nombre, añodelanzamiento, consola_id) VALUES (?, ?, ?)";

        try (Connection conn = DBUtil.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, juego.getNombre());
            stmt.setInt(2, juego.getAñodelanzamiento());
            stmt.setInt(3, juego.getConsola().getId());

            stmt.executeUpdate();
            logger.info("Videojuego guardado: {}", juego.getNombre());

        } catch (SQLException e) {
            logger.error("Error al guardar videojuego", e);
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

                VideoJuego juego = new VideoJuego(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("añodelanzamiento"),
                        consola
                );

                logger.info("Videojuego encontrado: {}", juego.getNombre());
                return juego;
            }
        } catch (SQLException e) {
            logger.error("Error al buscar videojuego", e);
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
            logger.info("Se listaron {} videojuegos", lista.size());
        } catch (SQLException e) {
            logger.error("Error al listar videojuegos", e);
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
            logger.info("Videojuego actualizado: {}", juego.getNombre());
        } catch (SQLException e) {
            logger.error("Error al actualizar videojuego", e);
        }
    }


    public void eliminar(int id) {
        String sql = "DELETE FROM videojuego WHERE id = ?";

        try (Connection conn = DBUtil.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            logger.info("Videojuego eliminado con ID: {}", id);
        } catch (SQLException e) {
            logger.error("Error al eliminar videojuego", e);
        }
    }

    @Override
    public void eliminarPorID(int id) {
        eliminar(id); // Ya está implementado arriba
    }
}

