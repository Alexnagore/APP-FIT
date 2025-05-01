package com.example.pruebafinal.modelos;

public class Usuario implements java.io.Serializable {

    private String objectId;
    private String nombre;
    private int puntuacion;
    private int entrenamientosCompletados;

    public Usuario() {
    }

    public Usuario(String nombre) {
        this.nombre = nombre;
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
    public int getPuntuacion() {
        return puntuacion;
    }

    public void aumentarPuntuacion(int puntos) {
        this.puntuacion += puntos;
    }

    public int getEntrenamientosCompletados() {
        return entrenamientosCompletados;
    }

    public void incrementarEntrenamientosCompletados() {
        this.entrenamientosCompletados += 1;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public void setEntrenamientosCompletados(int entrenamientosCompletados) {
        this.entrenamientosCompletados = entrenamientosCompletados;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + " puntuacion=" + puntuacion + ", entrenamientosCompletados=" + entrenamientosCompletados + '}';
    }


}
