package app.fit.dao;

import app.fit.modelos.Ejercicio;
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
    public void actualizaEjercicios(Ejercicio ejercicio);
}
