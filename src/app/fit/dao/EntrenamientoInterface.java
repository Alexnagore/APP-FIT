package app.fit.dao;

import app.fit.modelos.EntrenamientoModelo;
import java.util.List;

/**
 *
 * @author jmeri
 */
public interface EntrenamientoInterface {
    public void agregarEntrenamiento(EntrenamientoModelo entrenamiento);
    public List<EntrenamientoModelo> getListaEntrenamientos();
    public EntrenamientoModelo getEntrenamiento(EntrenamientoModelo entrenamiento);
    public void setListaEntrenamientos(List<EntrenamientoModelo> listaEntrenamientos);
    public void actualizaEntrenamientos(EntrenamientoModelo entrenamiento);
}
