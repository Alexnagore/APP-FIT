package com.example.pruebafinal.modelos;

import android.annotation.SuppressLint;

public class Ejercicio {
    private String objectId;
    private String nombre;
    private int puntuacion;
    private int tiempo;
    private Localizacion puntoInicial;
    private Localizacion puntoFinal;
    private int numRepeticiones;

    public Ejercicio(String objectId) {
        this.objectId = objectId;
    }

    public Ejercicio(String descripcion, int puntuacion, int tiempo, Localizacion puntoInicial, Localizacion puntoFinal) {
        this.nombre = descripcion;
        this.puntuacion = puntuacion;
        this.tiempo = tiempo;
        this.puntoInicial = puntoInicial;
        this.puntoFinal = puntoFinal;
    }

    public Ejercicio(String objectId, String descripcion, int puntuacion, int tiempo, Localizacion puntoInicial, Localizacion puntoFinal) {
        this.objectId = objectId;
        this.nombre = descripcion;
        this.puntuacion = puntuacion;
        this.tiempo = tiempo;
        this.puntoInicial = puntoInicial;
        this.puntoFinal = puntoFinal;
    }

    public Ejercicio(String descripcion, int puntuacion, int tiempo, int numRepeticiones) {
        this.nombre = descripcion;
        this.puntuacion = puntuacion;
        this.tiempo = tiempo;
        this.numRepeticiones = numRepeticiones;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public Localizacion getPuntoInicial() {
        return puntoInicial;
    }

    public void setPuntoInicial(Localizacion puntoInicial) {
        this.puntoInicial = puntoInicial;
    }

    public Localizacion getPuntoFinal() {
        return puntoFinal;
    }

    public void setPuntoFinal(Localizacion puntoFinal) {
        this.puntoFinal = puntoFinal;
    }

    public int getNumRepeticiones() {
        return numRepeticiones;
    }

    public void setNumRepeticiones(int numRepeticiones) {
        this.numRepeticiones = numRepeticiones;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public String toString() {
        return String.format("Ejercicio: %s\n" +
                        "-----------------------\n" +
                        "Repeticiones: %d\n" +
                        "Tiempo: %d\n" +
                        "Puntuacion: %d\n" +
                        "Ubicación Inicial: %s\n" +
                        "Ubicación Final: %s\n" +
                        "________________________\n",
                nombre,
                numRepeticiones,
                tiempo,
                puntuacion,
                (puntoInicial != null ? puntoInicial.toString() : "No especificado"),
                (puntoFinal != null ? puntoFinal.toString() : "No especificado")
        );
    }


}
