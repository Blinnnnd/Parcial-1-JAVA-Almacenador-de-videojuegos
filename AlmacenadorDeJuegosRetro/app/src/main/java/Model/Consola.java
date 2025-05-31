
package Model;

/**
 *
 * @author Blinnnnd
 * 
 */

public class Consola {
    private int id;
    private String titulo;
    private String fabricante;
    
    public Consola(){
        
    }
    
    public Consola(int id, String nombre, String fabricante){
        this.id = id;
        this.titulo = nombre;
        this.fabricante = fabricante;
    }
    
    //Getters 
    
    public int getId() {
        return id;
    }

    public String getNombre() {
        return titulo;
    }

    public String getFabricante() {
        return fabricante;
    }
    
    //Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.titulo = nombre;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    
    
}
