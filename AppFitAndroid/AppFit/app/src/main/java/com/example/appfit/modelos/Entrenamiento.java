package com.example.appfit.modelos;

import java.util.ArrayList;
import java.util.List;

public class Entrenamiento {
    
    private List<Ejercicio> ejercicios;
    private int id;
    
    public Entrenamiento (int id) {
        this.id = id;
        this.ejercicios = new ArrayList<>();
    }

    public List<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public int getId() { return id; }

    public void agregarEjercicio(Ejercicio ejercicio) {
        this.ejercicios.add(ejercicio);
    }
    
    public int getPuntuacion() {
        return calcularPuntuacion();
    }
    
    private int calcularPuntuacion() {
        int puntos = 0;
        for (Ejercicio ejercicio : ejercicios) {
            puntos += ejercicio.getPuntuacion();
        }
        return puntos;
    }

    @Override
    public String toString() {
        return "Entrenamiento{" + "ejercicios=" + ejercicios.toString() + '}';
    }
    
}
