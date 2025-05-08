package com.example.pruebafinal.modelos;

import java.util.ArrayList;
import java.util.List;

public class Partida {

    private String objectId;
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

    public String getObjectId() {
        return objectId;
    }

    public void addEntrenamiento (Entrenamiento entrenamiento) {
        this.entrenamientos.add(entrenamiento);
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setEntrenamientos(List<Entrenamiento> entrenamientos) {
        this.entrenamientos = entrenamientos;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    @Override
    public String toString() {
        return "Partida{" + usuario.toString() + ", entrenamientos=" + entrenamientos.toString() + '}';
    }


}
