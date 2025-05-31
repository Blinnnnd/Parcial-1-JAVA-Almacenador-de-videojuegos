
package dao.impl;

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
 *
 * @author Blinnnnd
 * 
 */

public class ConsolaDAOimpl implements ConsolaDAO{
    
    @Override
    public void guardar(Consola consola) {
        String sql = "INSERT INTO consola (titulo, fabricante) VALUES (?, ?)";

        try (Connection conn = DBUtil.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, consola.getTitulo());
            stmt.setString(2, consola.getFabricante());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al guardar consola: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM Consola WHERE id = ?";

        try (Connection conn = DBUtil.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar consola: " + e.getMessage());
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
                return new Consola(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("fabricante")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar consola: " + e.getMessage());
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
        } catch (SQLException e) {
            System.out.println("Error al listar consolas: " + e.getMessage());
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
        } catch (SQLException e) {
            System.out.println("Error al actualizar consola: " + e.getMessage());
        }
    }
}
