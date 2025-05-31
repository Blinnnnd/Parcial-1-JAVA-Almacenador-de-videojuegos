
package Util;

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
    
    private static final String SEPARATOR = File.separator;
    private static final String LOCATION = System.getProperty("user.dir") + SEPARATOR;
    private static final String DB_FILE = LOCATION + "almacenVJ"; 
    private static final String URL = "jdbc:h2:" + DB_FILE;
    private static final String USER = "";
    private static final String PASSWORD = "";

    public static Connection obtenerConexion() {
        Connection c = null;
        try {
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            Class.forName("org.h2.Driver");
            if (c == null) {
                throw new Exception("No se pudo conectar a la base de datos");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Controlador de base de datos no encontrado: " + e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return c;
    }
}
