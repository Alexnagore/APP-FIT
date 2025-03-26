package com.example.appfit.dao;

/**
 *
 * @author jmeri
 */

import com.example.appfit.modelos.Ejercicio;
import com.example.appfit.modelos.Entrenamiento;

import java.util.ArrayList;
import java.util.List;

public class EjercicioDao implements EjercicioInterface {
    
    private List<Ejercicio> listaEjercicios;
    
    public EjercicioDao(){
       listaEjercicios = new ArrayList<>();
    }
    
    @Override
    public void agregarEjercicio(Ejercicio ejercicio){
        listaEjercicios.add(ejercicio);
    }
    
    @Override
    public void eliminarEjercicio(Ejercicio ejercicio){
        listaEjercicios.remove(listaEjercicios.indexOf(ejercicio));
    }
    
    @Override
    public List<Ejercicio> getListaEjercicios() {
        return listaEjercicios;
    }
    
    @Override
    public Ejercicio getEjercicio(Ejercicio ejercicio) {
        return listaEjercicios.get(listaEjercicios.indexOf(ejercicio));
    }

    @Override
    public void setListaEjercicios(List<Ejercicio> listaEjercicios) {
        this.listaEjercicios = listaEjercicios;
    }

    @Override
    public void actualizarEjercicio(int id, Ejercicio ejercicio){
        for(Ejercicio e : listaEjercicios){
            if(e.getId() == id){
                listaEjercicios.set(listaEjercicios.indexOf(e), ejercicio);
            }
        }
    }
}
