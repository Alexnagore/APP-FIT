package com.example.pruebafinal.business;

import com.example.pruebafinal.dao.PartidaInterface;
import com.example.pruebafinal.modelos.Partida;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

/**
 * Capa de negocio para gestionar partidas
 */
public class PartidaManager {

    private final PartidaInterface partidaRepository;
    private final ExecutorService executorService;

    /**
     * Constructor con inyección de dependencia
     * @param partidaRepository repositorio de partidas
     */
    public PartidaManager(PartidaInterface partidaRepository) {
        this.partidaRepository = partidaRepository;
        this.executorService = Executors.newCachedThreadPool();
    }

    /**
     * Agrega una nueva partida
     * @param partida la partida a agregar
     */
    public void agregarPartida(Partida partida) {
        executorService.execute(() -> partidaRepository.agregarPartida(partida));
    }

    /**
     * Elimina una partida por su ID
     * @param objectId identificador de la partida
     */
    public void eliminarPartida(String objectId) {
        executorService.execute(() -> partidaRepository.eliminarPartida(objectId));
    }

    /**
     * Obtiene la lista de partidas de forma asíncrona
     * @param callback función a ejecutar cuando se obtenga la lista
     */
    public void getListaPartidas(Consumer<List<Partida>> callback) {
        executorService.execute(() -> {
            List<Partida> partidas = partidaRepository.getListaPartidas();
            if (callback != null) {
                callback.accept(partidas);
            }
        });
    }

    /**
     * Obtiene una partida por su ID de forma asíncrona
     * @param objectId identificador de la partida
     * @param callback función a ejecutar con el resultado
     */
    public void getPartida(String objectId, Consumer<Partida> callback) {
        executorService.execute(() -> {
            Partida partida = partidaRepository.getPartida(objectId);
            if (callback != null) {
                callback.accept(partida);
            }
        });
    }

    /**
     * Actualiza la información de una partida
     * @param partida partida con información actualizada
     */
    public void actualizarPartida(Partida partida) {
        executorService.execute(() -> partidaRepository.actualizaPartida(partida));
    }

    /**
     * Obtiene partidas asociadas a un usuario específico
     * @param usuarioId ID del usuario
     * @param callback función a ejecutar con la lista filtrada
     */
    public void getPartidasPorUsuario(String usuarioId, Consumer<List<Partida>> callback) {
        getListaPartidas(partidas -> {
            List<Partida> partidasUsuario = new java.util.ArrayList<>();
            for (Partida p : partidas) {
                if (p.getUsuario() != null &&
                        p.getUsuario().getObjectId() != null &&
                        p.getUsuario().getObjectId().equals(usuarioId)) {
                    partidasUsuario.add(p);
                }
            }
            callback.accept(partidasUsuario);
        });
    }

    /**
     * Libera recursos cuando ya no sean necesarios
     */
    public void shutdown() {
        if (!executorService.isShutdown()) {
            executorService.shutdown();
        }
    }
}