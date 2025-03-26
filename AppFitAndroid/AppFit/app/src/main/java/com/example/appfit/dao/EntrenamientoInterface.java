package com.example.appfit.dao;

import com.example.appfit.modelos.Entrenamiento;
import java.util.List;

/**
 *
 * @author jmeri
 */
public interface EntrenamientoInterface {
    public void agregarEntrenamiento(Entrenamiento entrenamiento);
    public void eliminarEntrenamiento(Entrenamiento entrenamiento);
    public List<Entrenamiento> getListaEntrenamientos();
    public Entrenamiento getEntrenamiento(Entrenamiento entrenamiento);
    public void setListaEntrenamientos(List<Entrenamiento> listaEntrenamientos);
    public void actualizaEntrenamieto(int id, Entrenamiento entrenamiento);
}
