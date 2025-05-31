package Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBInicializador {

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
            System.out.println("✅ Tablas creadas o verificadas correctamente");

        } catch (SQLException e) {
            System.err.println("❌ Error al crear las tablas: " + e.getMessage());
        }
    }
}