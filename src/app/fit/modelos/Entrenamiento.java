package app.fit.modelos;

import java.util.List;

public class Entrenamiento {
    
    private List<Ejercicio> ejercicios;
    
    public Entrenamiento (List<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }

    public List<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    @Override
    public String toString() {
        return "Entrenamiento{" + "ejercicios=" + ejercicios + '}';
    }
    
}
