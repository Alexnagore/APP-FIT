package com.example.appfit.dao;

/**
 *
 * @author jmeri
 */

import com.example.appfit.modelos.Ejercicio;
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
    public void actualizaEjercicios(Ejercicio ejercicio){
        listaEjercicios.set(listaEjercicios.indexOf(ejercicio), ejercicio);
    }
}
