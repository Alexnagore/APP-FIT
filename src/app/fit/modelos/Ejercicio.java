package app.fit.modelos;

public class Ejercicio {
    
    private String descripcion;
    private int puntuacion;
    private int tiempo;
    private Localizacion puntoInicial;
    private Localizacion puntoFinal;
    private int numRepeticiones;

    public Ejercicio(String descripcion, int puntuacion, int tiempo, Localizacion puntoInicial, Localizacion puntoFinal) {
        this.descripcion = descripcion;
        this.puntuacion = puntuacion;
        this.tiempo = tiempo;
        this.puntoInicial = puntoInicial;
        this.puntoFinal = puntoFinal;
    }
    
    public Ejercicio(String descripcion, int puntuacion, int tiempo, int numRepeticiones) {
        this.descripcion = descripcion;
        this.puntuacion = puntuacion;
        this.tiempo = tiempo;
        this.numRepeticiones = numRepeticiones;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public int getTiempo() {
        return tiempo;
    }

    public Localizacion getPuntoInicial() {
        return puntoInicial;
    }

    public Localizacion getPuntoFinal() {
        return puntoFinal;
    }
 
}
