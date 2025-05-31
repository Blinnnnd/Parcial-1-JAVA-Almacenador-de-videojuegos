
package dao;

import Model.Consola;
import java.util.List;

/**
 *
 * @author Blinnnnd
 * 
 */

public interface ConsolaDAO {
    
    void guardar(Consola consola);
    Consola buscarPorId(int id);
    List <Consola> ListarTodas();
    void actualizar(Consola consola);
    void eliminar(int id);
    
}
