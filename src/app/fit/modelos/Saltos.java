package app.fit.modelos;

public class Saltos {
    private String descripcion;
    private int puntuacion;
    private int tiempo;
    private int numSaltos;

    public Saltos (String descripcion, int puntuacion, int tiempo, int numSaltos) {
        this.descripcion = descripcion;
        this.puntuacion = puntuacion;
        this.tiempo = tiempo;
        this.numSaltos = numSaltos;
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

    public int getNumSaltos() {
        return numSaltos;
    }
    
}
