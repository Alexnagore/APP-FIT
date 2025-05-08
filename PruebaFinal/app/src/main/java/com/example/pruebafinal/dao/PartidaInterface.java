package com.example.pruebafinal.dao;

import com.example.pruebafinal.modelos.Partida;
import java.util.List;

/**
 *
 * @author jmeri
 */
public interface PartidaInterface {
    
    public void agregarPartida(Partida partida);
    public void eliminarPartida(String objectId);
    public List<Partida> getListaPartidas();
    public Partida getPartida(String objectId);
    public void actualizaPartida(Partida partida);
}
