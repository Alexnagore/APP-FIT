package com.example.appfit.dao;

import com.example.appfit.modelos.Entrenamiento;
import com.example.appfit.modelos.Usuario;

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
        listaEntrenamientos.remove(entrenamiento);
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
    public void setListaEntrenamientos(List<Entrenamiento> listaEntrenamientos) {
        this.listaEntrenamientos = listaEntrenamientos;
    }

    @Override
    public void actualizaEntrenamieto(int id, Entrenamiento entrenamiento){
        for(Entrenamiento e : listaEntrenamientos){
            if(e.getId() == id){
                listaEntrenamientos.set(listaEntrenamientos.indexOf(e), entrenamiento);
            }
        }
    }
}
