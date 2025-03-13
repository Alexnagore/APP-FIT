package app.fit.modelos;

import java.util.ArrayList;
import java.util.List;

public class PartidaModelo {
    
    private UsuarioModelo usuario;
    private List<EntrenamientoModelo> entrenamientos;
    
    public PartidaModelo (UsuarioModelo usuario) {
        this.usuario = usuario;
        this.entrenamientos = new ArrayList();
    }

    public List<EntrenamientoModelo> getEntrenamientos() {
        return entrenamientos;
    }
    
    public UsuarioModelo getUsuario() {
        return this.usuario;
    }
    
    public void addEntrenamiento (EntrenamientoModelo entrenamiento) {
        this.entrenamientos.add(entrenamiento);
    }

    @Override
    public String toString() {
        return "Partida{" + usuario.toString() + ", entrenamientos=" + entrenamientos.toString() + '}';
    }
    
    
}
