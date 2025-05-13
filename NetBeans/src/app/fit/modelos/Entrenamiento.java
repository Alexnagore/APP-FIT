package app.fit.modelos;

import java.util.*;

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
        return nombre;
    }

    public void setNombre(String nombreEntrenamiento) {
        this.nombre = nombreEntrenamiento;
    }

    public String getNombre() {
        return nombre;
    }
    
    public void subirEjercicio(Ejercicio ejercicio) {
        int index = ejercicios.indexOf(ejercicio);
        if(index > 0) {
            Collections.swap(ejercicios, index, index -1);
        }
    }
    
    public void bajarEjercicio(Ejercicio ejercicio) {
        int index = ejercicios.indexOf(ejercicio);
        if(index > 0) {
            Collections.swap(ejercicios, index, index +1);
        }
    }
}
