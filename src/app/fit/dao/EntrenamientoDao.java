package app.fit.dao;

import app.fit.modelos.EntrenamientoModelo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jmeri
 */
public class EntrenamientoDao implements EntrenamientoInterface {
    private List<EntrenamientoModelo> listaEntrenamientos;
    
    public EntrenamientoDao(){
       listaEntrenamientos = new ArrayList<>();
    }
    
    @Override
    public void agregarEntrenamiento(EntrenamientoModelo entrenamiento){
        listaEntrenamientos.add(entrenamiento);
    }
    
    @Override
    public List<EntrenamientoModelo> getListaEntrenamientos() {
        return listaEntrenamientos;
    }
    
    @Override
    public EntrenamientoModelo getEntrenamiento(EntrenamientoModelo entrenamiento) {
        return listaEntrenamientos.get(listaEntrenamientos.indexOf(entrenamiento));
    }

    @Override
    public void setListaEntrenamientos(List<EntrenamientoModelo> listaEjercicios) {
        this.listaEntrenamientos = listaEjercicios;
    }
    
    @Override
    public void actualizaEntrenamientos(EntrenamientoModelo entrenamiento){
        listaEntrenamientos.set(listaEntrenamientos.indexOf(entrenamiento), entrenamiento);
    }
}
