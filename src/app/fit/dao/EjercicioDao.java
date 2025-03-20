package app.fit.dao;

/**
 *
 * @author jmeri
 */

import app.fit.modelos.EjercicioModelo;
import java.util.ArrayList;
import java.util.List;

public class EjercicioDao implements EjercicioInterface {
    
    private List<EjercicioModelo> listaEjercicios;
    
    public EjercicioDao(){
       listaEjercicios = new ArrayList<>();
    }
    
    @Override
    public void agregarEjercicio(EjercicioModelo ejercicio){
        listaEjercicios.add(ejercicio);
    }
    
    @Override
    public void eliminarEjercicio(EjercicioModelo ejercicio){
        listaEjercicios.remove(listaEjercicios.indexOf(ejercicio));
    }
    
    @Override
    public List<EjercicioModelo> getListaEjercicios() {
        return listaEjercicios;
    }
    
    @Override
    public EjercicioModelo getEjercicio(EjercicioModelo ejercicio) {
        return listaEjercicios.get(listaEjercicios.indexOf(ejercicio));
    }

    @Override
    public void setListaEjercicios(List<EjercicioModelo> listaEjercicios) {
        this.listaEjercicios = listaEjercicios;
    }
    
    @Override
    public void actualizaEjercicios(EjercicioModelo ejercicio){
        listaEjercicios.set(listaEjercicios.indexOf(ejercicio), ejercicio);
    }
}
