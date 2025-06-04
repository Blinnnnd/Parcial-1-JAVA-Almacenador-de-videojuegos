package Util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * Blinnnnd
 *
 */
public class DBInicializador {

    private static final Logger logger = LogManager.getLogger(DBInicializador.class);

    public static void crearTablas() {
        String sqlConsola = """
                CREATE TABLE IF NOT EXISTS consola (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    titulo VARCHAR(100) NOT NULL,
                    fabricante VARCHAR(100) NOT NULL
                );
                """;

        String sqlVideoJuego = """
                CREATE TABLE IF NOT EXISTS videojuego (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    nombre VARCHAR(100) NOT NULL,
                    añodelanzamiento INT NOT NULL,
                    consola_id INT,
                    FOREIGN KEY (consola_id) REFERENCES consola(id)
                );
                """;

        try (Connection conn = DBUtil.obtenerConexion();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sqlConsola);
            stmt.execute(sqlVideoJuego);
            logger.info("✅ Tablas creadas o verificadas correctamente. Todo está funcionando correctamente ✅");

        } catch (SQLException e) {
            logger.error("❌ Error al crear las tablas ❌: {}", e.getMessage(), e);
        }
    }
}
