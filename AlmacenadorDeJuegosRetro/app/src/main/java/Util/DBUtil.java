
package Util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 *
 * @author Blinnnnd
 * 
 */

public class DBUtil {

    private static final Logger logger = LogManager.getLogger(DBUtil.class);

    private static final String SEPARATOR = File.separator;
    private static final String LOCATION = System.getProperty("user.dir") + SEPARATOR;
    private static final String DB_FILE = LOCATION + "almacenVJ";
    private static final String URL = "jdbc:h2:" + DB_FILE;
    private static final String USER = "";
    private static final String PASSWORD = "";

    public static Connection obtenerConexion() {
        Connection c = null;
        try {
            Class.forName("org.h2.Driver"); // Cargar driver
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            if (c != null) {
                logger.info("Conexión a la base de datos establecida correctamente en {}", DB_FILE);
            } else {
                throw new SQLException("Conexión devuelta como nula");
            }
        } catch (ClassNotFoundException e) {
            logger.error("Controlador de base de datos no encontrado", e);
        } catch (SQLException e) {
            logger.error("Error al establecer conexión con la base de datos", e);
        } catch (Exception e) {
            logger.error("Error inesperado al obtener la conexión", e);
        }
        return c;
    }
}
