
package Model;

/**
 *
 * @author Blinnnnd
 * 
 */

public class VideoJuego {
    private int id;
    private String nombre;
    private int añodelanzamiento;
    private Consola consola;
    
    public VideoJuego (){
        
    }
    
    public VideoJuego (int id, String nombre, int añodelanzamiento, Consola consola){
        this.id = id;
        this.nombre = nombre;
        this.añodelanzamiento = añodelanzamiento;
        this.consola = consola;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAñodelanzamiento() {
        return añodelanzamiento;
    }

    public void setAñodelanzamiento(int añodelanzamiento) {
        this.añodelanzamiento = añodelanzamiento;
    }

    public Consola getConsola() {
        return consola;
    }

    public void setConsola(Consola consola) {
        this.consola = consola;
    }
    
    
}
