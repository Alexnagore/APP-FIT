package app.fit.modelos;

public class Flexiones {
    private String descripcion;
    private int puntuacion;
    private int tiempo;
    private int numFlexiones;

    public Flexiones (String descripcion, int puntuacion, int tiempo, int numFlexiones) {
        this.descripcion = descripcion;
        this.puntuacion = puntuacion;
        this.tiempo = tiempo;
        this.numFlexiones = numFlexiones;
    }
    
    public int getPuntuacion() {
        return puntuacion;
    }

    public int getTiempo() {
        return tiempo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getNumFlexiones() {
        return numFlexiones;
    }
    
}
