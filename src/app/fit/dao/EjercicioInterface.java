package app.fit.dao;

import app.fit.modelos.EjercicioModelo;
import java.util.List;

/**
 *
 * @author jmeri
 */
public interface EjercicioInterface {
    
    public void agregarEjercicio(EjercicioModelo ejercicio);
    public List<EjercicioModelo> getListaEjercicios();
    public void setListaEjercicios(List<EjercicioModelo> listaEjercicios);
    public void actualizaEjercicios(EjercicioModelo ejercicio);
}
