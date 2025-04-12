package app.fit.dao;

import app.fit.modelos.Entrenamiento;
import java.util.List;

/**
 *
 * @author jmeri
 */
public interface EntrenamientoInterface {
    public String agregarEntrenamiento(Entrenamiento entrenamiento);
    public void eliminarEntrenamiento(String objectId);
    public List<Entrenamiento> getListaEntrenamientos();
    public Entrenamiento getEntrenamiento(String objectId);
    public void actualizaEntrenamientos(Entrenamiento entrenamiento);
}
