package com.example.pruebafinal.business;

import com.example.pruebafinal.dao.EjercicioInterface;
import com.example.pruebafinal.modelos.Ejercicio;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

/**
 * Capa de negocio para gestionar ejercicios
 */
public class EjercicioManager {

    private final EjercicioInterface ejercicioRepository;
    private final ExecutorService executorService;

    /**
     * Constructor con inyección de dependencia
     * @param ejercicioRepository repositorio de ejercicios
     */
    public EjercicioManager(EjercicioInterface ejercicioRepository) {
        this.ejercicioRepository = ejercicioRepository;
        this.executorService = Executors.newCachedThreadPool();
    }

    /**
     * Agrega un nuevo ejercicio
     * @param ejercicio el ejercicio a agregar
     * @param callback función a ejecutar con el ID generado
     */
    public void agregarEjercicio(Ejercicio ejercicio, Consumer<String> callback) {
        executorService.execute(() -> {
            String objectId = ejercicioRepository.agregarEjercicio(ejercicio);
            if (callback != null) {
                callback.accept(objectId);
            }
        });
    }

    /**
     * Elimina un ejercicio por su ID
     * @param objectId identificador del ejercicio
     */
    public void eliminarEjercicio(String objectId) {
        executorService.execute(() -> ejercicioRepository.eliminarEjercicio(objectId));
    }

    /**
     * Obtiene la lista de ejercicios de forma asíncrona
     * @param callback función a ejecutar cuando se obtenga la lista
     */
    public void getListaEjercicios(Consumer<List<Ejercicio>> callback) {
        executorService.execute(() -> {
            List<Ejercicio> ejercicios = ejercicioRepository.getListaEjercicios();
            if (callback != null) {
                callback.accept(ejercicios);
            }
        });
    }

    /**
     * Obtiene un ejercicio por su ID de forma asíncrona
     * @param objectId identificador del ejercicio
     * @param callback función a ejecutar con el resultado
     */
    public void getEjercicio(String objectId, Consumer<Ejercicio> callback) {
        executorService.execute(() -> {
            Ejercicio ejercicio = ejercicioRepository.getEjercicio(objectId);
            if (callback != null) {
                callback.accept(ejercicio);
            }
        });
    }

    /**
     * Actualiza la información de un ejercicio
     * @param ejercicio ejercicio con información actualizada
     */
    public void actualizarEjercicio(Ejercicio ejercicio) {
        executorService.execute(() -> ejercicioRepository.actualizaEjercicio(ejercicio));
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