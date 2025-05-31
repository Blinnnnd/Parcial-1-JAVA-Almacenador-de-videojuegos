
package dao;

import Model.VideoJuego;
import java.util.List;

/**
 *
 * @author Blinnnnd
 * 
 */

public interface VideoJuegoDAO {
    
    void guardar (VideoJuego juego);
    VideoJuego buscarPorId (int id);
    List <VideoJuego> listarTodos();
    void actualizar(VideoJuego juego);
    void eliminarPorID (int id);
    
}
