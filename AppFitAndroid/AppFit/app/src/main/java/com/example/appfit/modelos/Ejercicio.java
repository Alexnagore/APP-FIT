package com.example.appfit.modelos;

public class Ejercicio {
    
    private final String descripcion;
    private final int puntuacion;
    private final int tiempo;
    private Localizacion puntoInicial;
    private Localizacion puntoFinal;
    private int numRepeticiones;
    private int id;

    public Ejercicio(String descripcion, int puntuacion, int tiempo, Localizacion puntoInicial, Localizacion puntoFinal, int id) {
        this.descripcion = descripcion;
        this.puntuacion = puntuacion;
        this.tiempo = tiempo;
        this.puntoInicial = puntoInicial;
        this.puntoFinal = puntoFinal;
        this.id = id;
    }
    
    public Ejercicio(String descripcion, int puntuacion, int tiempo, int numRepeticiones, int id) {
        this.descripcion = descripcion;
        this.puntuacion = puntuacion;
        this.tiempo = tiempo;
        this.numRepeticiones = numRepeticiones;
        this.id = id;
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

    public int getId() {
        return id;
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
