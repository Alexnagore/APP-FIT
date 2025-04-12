package app.fit.dao;

/**
 *
 * @author jmeri
 */

import app.fit.modelos.Ejercicio;
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
    public void eliminarEjercicio(String objectId){
        for (Ejercicio ejercicio : listaEjercicios) {
            if (ejercicio.getObjectId().equals(objectId)) {
                listaEjercicios.remove(ejercicio);
            }
        }
    }
    
    @Override
    public List<Ejercicio> getListaEjercicios() {
        return listaEjercicios;
    }
    
    @Override
    public Ejercicio getEjercicio(String objectId) {
        for (Ejercicio ejercicio : listaEjercicios) {
            if (ejercicio.getObjectId().equals(objectId)) {
                return ejercicio;
            }
        }
        return new Ejercicio("-1");
    }
    
    @Override
    public void actualizaEjercicio(Ejercicio ejercicio){
        listaEjercicios.set(listaEjercicios.indexOf(ejercicio), ejercicio);
    }
}
