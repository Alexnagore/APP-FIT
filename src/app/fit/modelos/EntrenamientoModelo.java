package app.fit.modelos;

import java.util.ArrayList;
import java.util.List;

public class EntrenamientoModelo {
    
    private List<EjercicioModelo> ejercicios;
    
    
    public EntrenamientoModelo () {
        this.ejercicios = new ArrayList<>();
    }

    public List<EjercicioModelo> getEjercicios() {
        return ejercicios;
    }
    
    public void agregarEjercicio(EjercicioModelo ejercicio) {
        this.ejercicios.add(ejercicio);
    }

    @Override
    public String toString() {
        return "Entrenamiento{" + "ejercicios=" + ejercicios.toString() + '}';
    }
    
}
