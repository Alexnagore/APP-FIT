package app.fit.modelos;

import java.util.List;

public class EntrenamientoModelo {
    
    private List<EjercicioModelo> ejercicios;
    
    public EntrenamientoModelo (List<EjercicioModelo> ejercicios) {
        this.ejercicios = ejercicios;
    }

    public List<EjercicioModelo> getEjercicios() {
        return ejercicios;
    }
    
    public void agregarEjercicio(EjercicioModelo ejercicio) {
        this.ejercicios.add(ejercicio);
    }

    @Override
    public String toString() {
        return "Entrenamiento{" + "ejercicios=" + ejercicios + '}';
    }
    
}
