package com.example.pruebafinal.modelos;

import java.util.ArrayList;
import java.util.List;

public class Entrenamiento {

    private String objectId;
    private String nombre;
    private List<Ejercicio> ejercicios;

    public Entrenamiento () {
        this.ejercicios = new ArrayList<>();
    }

    public Entrenamiento(String idObjeto) {
        this.objectId = idObjeto;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public List<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }

    public void agregarEjercicio(Ejercicio ejercicio) {
        this.ejercicios.add(ejercicio);
    }

    public void eliminarEjercicio(Ejercicio ejercicio) {
        this.ejercicios.remove(ejercicio);
    }

    public int getPuntuacion() {
        return calcularPuntuacion();
    }

    private int calcularPuntuacion() {
        return ejercicios.stream()
                .mapToInt(Ejercicio::getPuntuacion)
                .sum();
    }

    @Override
    public String toString() {
        return "Entrenamiento{" + "ejercicios=" + ejercicios.toString() + '}';
    }

    public void setNombre(String nombreEntrenamiento) {
        this.nombre = nombreEntrenamiento;
    }

    public String getNombre() {
        return nombre;
    }



}
