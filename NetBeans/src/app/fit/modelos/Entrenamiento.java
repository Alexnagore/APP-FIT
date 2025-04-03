package app.fit.modelos;

import java.util.ArrayList;
import java.util.List;

public class Entrenamiento {
    
    private String idObjeto;
    private List<Ejercicio> ejercicios; 
    
    public Entrenamiento () {
        this.ejercicios = new ArrayList<>();
    }

    public Entrenamiento(String idObjeto) {
        this.idObjeto = idObjeto;
    }

    public List<Ejercicio> getEjercicios() {
        return ejercicios;
    }
    
    public void agregarEjercicio(Ejercicio ejercicio) {
        this.ejercicios.add(ejercicio);
    }
    
    public int getPuntuacion() {
        return calcularPuntuacion();
    }
    
    private int calcularPuntuacion() {
        var puntos = 0;
        for (Ejercicio ejercicio : ejercicios) {
            puntos += ejercicio.getPuntuacion();
        }
        return puntos;
    }

    @Override
    public String toString() {
        return "Entrenamiento{" + "ejercicios=" + ejercicios.toString() + '}';
    }

    public Object getObjectId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
