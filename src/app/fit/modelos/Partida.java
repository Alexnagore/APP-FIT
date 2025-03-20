package app.fit.modelos;

import java.util.ArrayList;
import java.util.List;

public class Partida {
    
    private Usuario usuario;
    private List<Entrenamiento> entrenamientos;
    
    public Partida (Usuario usuario) {
        this.usuario = usuario;
        this.entrenamientos = new ArrayList();
    }

    public List<Entrenamiento> getEntrenamientos() {
        return entrenamientos;
    }
    
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void addEntrenamiento (Entrenamiento entrenamiento) {
        this.entrenamientos.add(entrenamiento);
    }

    @Override
    public String toString() {
        return "Partida{" + usuario.toString() + ", entrenamientos=" + entrenamientos.toString() + '}';
    }
    
    
}
