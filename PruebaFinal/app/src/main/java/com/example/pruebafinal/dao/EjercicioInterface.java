package com.example.pruebafinal.dao;

import com.example.pruebafinal.modelos.Ejercicio;
import java.util.List;

/**
 *
 * @author jmeri
 */
public interface EjercicioInterface {
    public String agregarEjercicio(Ejercicio ejercicio);
    public void eliminarEjercicio(String objectId);
    public List<Ejercicio> getListaEjercicios();
    public Ejercicio getEjercicio(String objectId);
    public void actualizaEjercicio(Ejercicio ejercicio);
}