package app.fit.modelos;

public class Sentadillas {
    private String descripcion;
    private int puntuacion;
    private int tiempo;
    private int numSentadillas;

    public Sentadillas (String descripcion, int puntuacion, int tiempo, int numSentadillas) {
        this.descripcion = descripcion;
        this.puntuacion = puntuacion;
        this.tiempo = tiempo;
        this.numSentadillas = numSentadillas;
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

    public int getNumSentadillas() {
        return numSentadillas;
    }
    
}
