package app.fit.modelos;

public class EjercicioModelo {
    
    private final String descripcion;
    private final int puntuacion;
    private final int tiempo;
    private Localizacion puntoInicial;
    private Localizacion puntoFinal;
    private int numRepeticiones;

    public EjercicioModelo(String descripcion, int puntuacion, int tiempo, Localizacion puntoInicial, Localizacion puntoFinal) {
        this.descripcion = descripcion;
        this.puntuacion = puntuacion;
        this.tiempo = tiempo;
        this.puntoInicial = puntoInicial;
        this.puntoFinal = puntoFinal;
    }
    
    public EjercicioModelo(String descripcion, int puntuacion, int tiempo, int numRepeticiones) {
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

    public int getNumRepeticiones() {
        return numRepeticiones;
    }

    @Override
    public String toString() {
        return "Ejercicio{" + "descripcion=" + descripcion + ", puntuacion=" + puntuacion + ", tiempo=" + tiempo + ", puntoInicial=" + puntoInicial + ", puntoFinal=" + puntoFinal + ", numRepeticiones=" + numRepeticiones + '}';
    }
    
    
}
