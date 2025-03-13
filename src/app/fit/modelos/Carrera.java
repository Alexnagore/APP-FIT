/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.fit.modelos;

/**
 *
 * @author alumno
 */
public class Carrera {
    
    private String descripcion;
    private int puntuacion;
    private int tiempo;
    private Localizacion puntoInicial;
    private Localizacion puntoFinal;

    public Carrera(String descripcion, int puntuacion, int tiempo, Localizacion puntoInicial, Localizacion puntoFinal) {
        this.descripcion = descripcion;
        this.puntuacion = puntuacion;
        this.tiempo = tiempo;
        this.puntoInicial = puntoInicial;
        this.puntoFinal = puntoFinal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public int getTiempo() {
        return tiempo;
    }

    public Localizacion getPuntoInicial() {
        return puntoInicial;
    }

    public Localizacion getPuntoFinal() {
        return puntoFinal;
    }
 
}
