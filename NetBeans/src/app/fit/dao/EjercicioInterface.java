package app.fit.dao;

import app.fit.modelos.Ejercicio;
import java.util.List;

/**
 *
 * @author jmeri
 */
public interface EjercicioInterface {
    
    public void agregarEjercicio(Ejercicio ejercicio);
    public void eliminarEjercicio(String objectId);
    public List<Ejercicio> getListaEjercicios();
    public Ejercicio getEjercicio(String objectId);
    public void actualizaEjercicio(Ejercicio ejercicio);
}
