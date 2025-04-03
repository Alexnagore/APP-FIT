package app.fit.dao;

import app.fit.modelos.Entrenamiento;
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
    public void eliminarEntrenamiento(String objectId){
        for (Entrenamiento entrenamiento : listaEntrenamientos) {
            if (entrenamiento.getObjectId().equals(objectId)) {
                listaEntrenamientos.remove(entrenamiento);
            }
        }
    }
    
    @Override
    public List<Entrenamiento> getListaEntrenamientos() {
        return listaEntrenamientos;
    }
    
    @Override
    public Entrenamiento getEntrenamiento(String objectId) {
        for (Entrenamiento entrenamiento : listaEntrenamientos) {
            if (entrenamiento.getObjectId().equals(objectId)) {
                return entrenamiento;
            }
        }
        return new Entrenamiento("-1");
    }
    
    @Override
    public void actualizaEntrenamientos(Entrenamiento entrenamiento){
        listaEntrenamientos.set(listaEntrenamientos.indexOf(entrenamiento), entrenamiento);
    }
}
