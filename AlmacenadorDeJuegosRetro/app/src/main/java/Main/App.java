
package Main;

import Model.Consola;
import Model.VideoJuego;
import Util.DBInicializador;
import java.util.List;
import dao.impl.ConsolaDAOimpl;
import dao.impl.VideoJuegoDAOimpl;
import java.util.Scanner;

/**
 *
 * @author Blinnnnd
 * 
 */

public class App {
    private static final ConsolaDAOimpl consolaDAO = new ConsolaDAOimpl();
    private static final VideoJuegoDAOimpl videojuegoDAO = new VideoJuegoDAOimpl();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        DBInicializador.crearTablas();
        int opcion;

        do {
            mostrarMenu();
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> agregarConsola();
                case 2 -> listarConsolas();
                case 3 -> eliminarConsola();
                case 4 -> buscarConsolaPorId();
                case 5 -> actualizarConsola();
                case 6 -> agregarVideojuego();
                case 7 -> listarVideojuegos();
                case 8 -> eliminarVideojuego();
                case 9 -> buscarVideojuegoPorId();
                case 10 -> actualizarVideojuego();
                case 0 -> System.out.println("👋 Saliendo del programa...");
                default -> System.out.println("❌ Opción inválida.");
            }
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
            System.out.println("""
            ╔════════════════════════════════════════════════╗
            ║               << MENÚ PRINCIPAL >>             ║
            ╠════════════════════════════════════════════════╣
            ║                    CONSOLAS                    ║
            ╠════════════════════════════════════════════════╣
            ║ 1. Agregar consola                             ║
            ║ 2. Listar consolas                             ║
            ║ 3. Eliminar consola                            ║
            ║ 4. Buscar consola por ID                       ║
            ║ 5. Actualizar consola                          ║
            ╠════════════════════════════════════════════════╣
            ║                 VIDEOJUEGOS                    ║
            ╠════════════════════════════════════════════════╣
            ║ 6. Agregar videojuego                          ║
            ║ 7. Listar videojuegos                          ║
            ║ 8. Eliminar videojuego                         ║
            ║ 9. Buscar videojuego por ID                    ║
            ║ 10. Actualizar videojuego                      ║
            ╠════════════════════════════════════════════════╣
            ║ 0. Salir                                       ║
            ╚════════════════════════════════════════════════╝
            """);
        System.out.print("Selecciona una opción: ");
    }

    // --- Consola ---
    private static void agregarConsola() {
        System.out.print("Título de la consola: ");
        String titulo = sc.nextLine();
        System.out.print("Fabricante: ");
        String fabricante = sc.nextLine();
        consolaDAO.guardar(new Consola(0, titulo, fabricante));
        System.out.println("✅ Consola guardada.");
    }

    private static void listarConsolas() {
        List<Consola> lista = consolaDAO.ListarTodas();

        System.out.println("\n╔═══════════════════════════╗");
        System.out.println("║     LISTA DE CONSOLAS     ║");
        System.out.println("╚═══════════════════════════╝");

        for (Consola c : lista) {
            System.out.printf(" %-4s | %-20s | %-10s %n", c.getId(), c.getTitulo(), c.getFabricante());
        }

//        System.out.println("═════════════════════════════\n");
    }

    private static void eliminarConsola() {
        listarConsolas();
        System.out.print("ID a eliminar (SOLO PODRA ELIMINAR LAS CONOSOLAS QUE NO TENGAN VIDEOJUEGOS ASOCIADOS): ");
        int id = Integer.parseInt(sc.nextLine());
        consolaDAO.eliminar(id);
        System.out.println("🗑️ Consola eliminada.");
    }

    private static void buscarConsolaPorId() {
        System.out.print("ID de consola: ");
        int id = Integer.parseInt(sc.nextLine());
        Consola c = consolaDAO.buscarPorId(id);
        if (c != null) {
            System.out.println("Encontrada: " + c.getTitulo() + " - " + c.getFabricante());
        } else {
            System.out.println("❌ Consola no encontrada.");
        }
    }

    private static void actualizarConsola() {
        listarConsolas();
        System.out.print("ID a actualizar: ");
        int id = Integer.parseInt(sc.nextLine());
        Consola consola = consolaDAO.buscarPorId(id);
        if (consola != null) {
            System.out.print("Nuevo título: ");
            consola.setTitulo(sc.nextLine());
            System.out.print("Nuevo fabricante: ");
            consola.setFabricante(sc.nextLine());
            consolaDAO.actualizar(consola);
            System.out.println("✅ Consola actualizada.");
        } else {
            System.out.println("❌ Consola no encontrada.");
        }
    }

    // Video Juego
    private static void agregarVideojuego() {
        System.out.print("Nombre del videojuego: ");
        String nombre = sc.nextLine();
        System.out.print("Año de lanzamiento: ");
        int año = Integer.parseInt(sc.nextLine());

        listarConsolas();
        System.out.print("ID de consola: ");
        int idConsola = Integer.parseInt(sc.nextLine());
        Consola consola = consolaDAO.buscarPorId(idConsola);

        if (consola != null) {
            videojuegoDAO.guardar(new VideoJuego(0, nombre, año, consola));
            System.out.println("🎮 Videojuego guardado.");
        } else {
            System.out.println("❌ Consola no válida.");
        }
    }

    private static void listarVideojuegos() {
        List<VideoJuego> lista = videojuegoDAO.listarTodos();

        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║             LISTA DE VIDEOJUEGOS               ║");
        System.out.println("╠════════════════════════════════════════════════╣");
        System.out.println("║ ID   | Nombre          | Año    | Consola      ║");
        System.out.println("╠════════════════════════════════════════════════╣");

        for (VideoJuego vj : lista) {
            System.out.printf("║ %-4s | %-15s | %-6s | %-12s ║%n",
                    vj.getId(), vj.getNombre(), vj.getAñodelanzamiento(), vj.getConsola().getTitulo());
        }

        System.out.println("╚════════════════════════════════════════════════╝\n");
    }

    private static void eliminarVideojuego() {
        listarVideojuegos();
        System.out.print("ID a eliminar: ");
        int id = Integer.parseInt(sc.nextLine());
        videojuegoDAO.eliminarPorID(id);
        System.out.println("🗑️ Videojuego eliminado.");
    }

    private static void buscarVideojuegoPorId() {
        System.out.print("ID del videojuego: ");
        int id = Integer.parseInt(sc.nextLine());
        VideoJuego vj = videojuegoDAO.buscarPorId(id);
        if (vj != null) {
            System.out.println("🎮 " + vj.getNombre() + " - " + vj.getAñodelanzamiento() + " - " +
                    vj.getConsola().getTitulo());
        } else {
            System.out.println("❌ Videojuego no encontrado.");
        }
    }

    private static void actualizarVideojuego() {
        listarVideojuegos();
        System.out.print("ID a actualizar: ");
        int id = Integer.parseInt(sc.nextLine());
        VideoJuego vj = videojuegoDAO.buscarPorId(id);
        if (vj != null) {
            System.out.print("Nuevo nombre: ");
            vj.setNombre(sc.nextLine());
            System.out.print("Nuevo año de lanzamiento: ");
            vj.setAñodelanzamiento(Integer.parseInt(sc.nextLine()));

            listarConsolas();
            System.out.print("Nuevo ID de consola: ");
            int idConsola = Integer.parseInt(sc.nextLine());
            Consola nuevaConsola = consolaDAO.buscarPorId(idConsola);
            if (nuevaConsola != null) {
                vj.setConsola(nuevaConsola);
                videojuegoDAO.actualizar(vj);
                System.out.println("✅ Videojuego actualizado.");
            } else {
                System.out.println("❌ Consola no encontrada.");
            }
        } else {
            System.out.println("❌ Videojuego no encontrado.");
        }
    }
}

