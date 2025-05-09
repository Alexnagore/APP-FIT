package com.example.pruebafinal.business;

import com.example.pruebafinal.dao.EntrenamientoInterface;
import com.example.pruebafinal.modelos.Entrenamiento;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

/**
 * Capa de negocio para gestionar entrenamientos
 */
public class EntrenamientoManager {

    private final EntrenamientoInterface entrenamientoRepository;
    private final ExecutorService executorService;

    /**
     * Constructor con inyección de dependencia
     * @param entrenamientoRepository repositorio de entrenamientos
     */
    public EntrenamientoManager(EntrenamientoInterface entrenamientoRepository) {
        this.entrenamientoRepository = entrenamientoRepository;
        this.executorService = Executors.newCachedThreadPool();
    }

    /**
     * Agrega un nuevo entrenamiento
     * @param entrenamiento el entrenamiento a agregar
     * @param callback función a ejecutar con el ID generado
     */
    public void agregarEntrenamiento(Entrenamiento entrenamiento, Consumer<String> callback) {
        executorService.execute(() -> {
            String objectId = entrenamientoRepository.agregarEntrenamiento(entrenamiento);
            if (callback != null) {
                callback.accept(objectId);
            }
        });
    }

    /**
     * Elimina un entrenamiento por su ID
     * @param objectId identificador del entrenamiento
     */
    public void eliminarEntrenamiento(String objectId) {
        executorService.execute(() -> entrenamientoRepository.eliminarEntrenamiento(objectId));
    }

    /**
     * Obtiene la lista de entrenamientos de forma asíncrona
     * @param callback función a ejecutar cuando se obtenga la lista
     */
    public void getListaEntrenamientos(Consumer<List<Entrenamiento>> callback) {
        executorService.execute(() -> {
            List<Entrenamiento> entrenamientos = entrenamientoRepository.getListaEntrenamientos();
            if (callback != null) {
                callback.accept(entrenamientos);
            }
        });
    }

    /**
     * Obtiene un entrenamiento por su ID de forma asíncrona
     * @param objectId identificador del entrenamiento
     * @param callback función a ejecutar con el resultado
     */
    public void getEntrenamiento(String objectId, Consumer<Entrenamiento> callback) {
        executorService.execute(() -> {
            Entrenamiento entrenamiento = entrenamientoRepository.getEntrenamiento(objectId);
            if (callback != null) {
                callback.accept(entrenamiento);
            }
        });
    }

    /**
     * Actualiza la información de un entrenamiento
     * @param entrenamiento entrenamiento con información actualizada
     */
    public void actualizarEntrenamiento(Entrenamiento entrenamiento) {
        executorService.execute(() -> entrenamientoRepository.actualizaEntrenamientos(entrenamiento));
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