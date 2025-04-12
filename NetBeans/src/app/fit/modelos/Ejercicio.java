package app.fit.modelos;

public class Ejercicio {
    private String objectId;
    private String descripcion;
    private int puntuacion;
    private int tiempo;
    private Localizacion puntoInicial;
    private Localizacion puntoFinal;
    private int numRepeticiones;

    public Ejercicio(String objectId) {
        this.objectId = objectId;
    }

    public Ejercicio(String descripcion, int puntuacion, int tiempo, Localizacion puntoInicial, Localizacion puntoFinal) {
        this.descripcion = descripcion;
        this.puntuacion = puntuacion;
        this.tiempo = tiempo;
        this.puntoInicial = puntoInicial;
        this.puntoFinal = puntoFinal;
    }
    
    public Ejercicio(String objectId, String descripcion, int puntuacion, int tiempo, Localizacion puntoInicial, Localizacion puntoFinal) {
        this.objectId = objectId;
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
    
    public String getObjectId() {
        return objectId;
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

    public int getNumRepeticiones() {
        return numRepeticiones;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    

    @Override
    public String toString() {
        return "Ejercicio{" + "descripcion=" + descripcion + ", puntuacion=" + puntuacion + ", tiempo=" + tiempo + ", puntoInicial=" + puntoInicial + ", puntoFinal=" + puntoFinal + ", numRepeticiones=" + numRepeticiones + '}';
    }
    
    
}
