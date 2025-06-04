package dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Model.Consola;
import Util.DBUtil;
import dao.ConsolaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del DAO para la entidad Consola.
 *
 * @author Blinnnnd
 */
public class ConsolaDAOimpl implements ConsolaDAO {

    private static final Logger logger = LogManager.getLogger(ConsolaDAOimpl.class);

    @Override
    public void guardar(Consola consola) {
        String sql = "INSERT INTO consola (titulo, fabricante) VALUES (?, ?)";

        try (Connection conn = DBUtil.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, consola.getTitulo());
            stmt.setString(2, consola.getFabricante());

            stmt.executeUpdate();
            logger.info("Consola guardada: {} ({})", consola.getTitulo(), consola.getFabricante());

        } catch (SQLException e) {
            logger.error("Error al guardar consola", e);
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM consola WHERE id = ?";

        try (Connection conn = DBUtil.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            logger.info("Consola con ID {} eliminada.", id);

        } catch (SQLException e) {
            logger.error("Error al eliminar consola con ID " + id, e);
        }
    }

    @Override
    public Consola buscarPorId(int id) {
        String sql = "SELECT * FROM consola WHERE id = ?";

        try (Connection conn = DBUtil.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Consola c = new Consola(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("fabricante")
                );
                logger.info("Consola encontrada: {}", c.getTitulo());
                return c;
            } else {
                logger.warn("No se encontró consola con ID {}", id);
            }
        } catch (SQLException e) {
            logger.error("Error al buscar consola con ID " + id, e);
        }

        return null;
    }

    @Override
    public List<Consola> ListarTodas() {
        List<Consola> consolas = new ArrayList<>();
        String sql = "SELECT * FROM consola";

        try (Connection conn = DBUtil.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                consolas.add(new Consola(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("fabricante")
                ));
            }
            logger.info("Se listaron {} consolas.", consolas.size());

        } catch (SQLException e) {
            logger.error("Error al listar consolas", e);
        }

        return consolas;
    }

    @Override
    public void actualizar(Consola consola) {
        String sql = "UPDATE consola SET titulo = ?, fabricante = ? WHERE id = ?";

        try (Connection conn = DBUtil.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, consola.getTitulo());
            stmt.setString(2, consola.getFabricante());
            stmt.setInt(3, consola.getId());

            stmt.executeUpdate();
            logger.info("Consola actualizada: ID {}, Título: {}", consola.getId(), consola.getTitulo());

        } catch (SQLException e) {
            logger.error("Error al actualizar consola con ID " + consola.getId(), e);
        }
    }
}
