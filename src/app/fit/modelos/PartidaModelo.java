package app.fit.modelos;

import java.util.List;

public class PartidaModelo {
    
    private List<EntrenamientoModelo> entrenamientos;
    
    public PartidaModelo (List<EntrenamientoModelo> entrenamientos) {
        this.entrenamientos = entrenamientos;
    }

    public List<EntrenamientoModelo> getEntrenamientos() {
        return entrenamientos;
    }
    
    public void addEntrenamiento (EntrenamientoModelo entrenamiento) {
        this.entrenamientos.add(entrenamiento);
    }

    @Override
    public String toString() {
        return "Partida{" + "entrenamientos=" + entrenamientos + '}';
    }
    
    
}
