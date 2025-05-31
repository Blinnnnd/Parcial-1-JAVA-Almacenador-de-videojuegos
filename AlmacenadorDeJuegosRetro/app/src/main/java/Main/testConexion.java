

package Main;

import Util.DBUtil;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Blinnnnd
 * 
 */

public class testConexion {
    
    public static void main(String[] args) {
        try (Connection conn = DBUtil.obtenerConexion()) {
            System.out.println("✅ ¡Conexión establecida correctamente!");
        } catch (SQLException e) {
            System.out.println("❌ Error de conexión: " + e.getMessage());
        }
    }
}

