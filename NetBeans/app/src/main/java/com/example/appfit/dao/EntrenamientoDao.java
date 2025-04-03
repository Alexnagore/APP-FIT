package com.example.appfit.dao;

import com.example.appfit.modelos.Entrenamiento;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jmeri
 */
public class EntrenamientoDao implements EntrenamientoInterface {
    private List<Entrenamiento> listaEntrenamientos;
    
    public EntrenamientoDao(){
       listaEntrenamientos = new ArrayList<>();
    }
    
    @Override
    public void agregarEntrenamiento(Entrenamiento entrenamiento){
        listaEntrenamientos.add(entrenamiento);
    }
    
    @Override
    public void eliminarEntrenamiento(Entrenamiento entrenamiento){
        listaEntrenamientos.remove(listaEntrenamientos.indexOf(entrenamiento));
    }
    
    @Override
    public List<Entrenamiento> getListaEntrenamientos() {
        return listaEntrenamientos;
    }
    
    @Override
    public Entrenamiento getEntrenamiento(Entrenamiento entrenamiento) {
        return listaEntrenamientos.get(listaEntrenamientos.indexOf(entrenamiento));
    }

    @Override
    public void setListaEntrenamientos(List<Entrenamiento> listaEjercicios) {
        this.listaEntrenamientos = listaEjercicios;
    }
    
    @Override
    public void actualizaEntrenamientos(Entrenamiento entrenamiento){
        listaEntrenamientos.set(listaEntrenamientos.indexOf(entrenamiento), entrenamiento);
    }
}
