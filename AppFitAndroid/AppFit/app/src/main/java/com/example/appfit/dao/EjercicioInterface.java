package com.example.appfit.dao;

import com.example.appfit.modelos.Ejercicio;
import java.util.List;

/**
 *
 * @author jmeri
 */
public interface EjercicioInterface {
    
    public void agregarEjercicio(Ejercicio ejercicio);
    public void eliminarEjercicio(Ejercicio ejercicio);
    public List<Ejercicio> getListaEjercicios();
    public Ejercicio getEjercicio(Ejercicio ejercicio);
    public void setListaEjercicios(List<Ejercicio> listaEjercicios);
    public void actualizarEjercicio(int id, Ejercicio ejercicio);
}
