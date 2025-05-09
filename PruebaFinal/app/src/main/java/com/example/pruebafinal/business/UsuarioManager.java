package com.example.pruebafinal.business;

import com.example.pruebafinal.dao.UsuarioInterface;
import com.example.pruebafinal.modelos.Usuario;

import java.util.List;
import java.util.function.Consumer;

/**
 * Capa de negocio para gestionar usuarios
 */
public class UsuarioManager {

    private final UsuarioInterface usuarioRepository;

    /**
     * Constructor con inyección de dependencia
     * @param usuarioRepository repositorio de usuarios
     */
    public UsuarioManager(UsuarioInterface usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Agrega un nuevo usuario
     * @param usuario el usuario a agregar
     */
    public void agregarUsuario(Usuario usuario) {
        usuarioRepository.agregarUsuario(usuario);
    }

    /**
     * Elimina un usuario por su ID
     * @param objectId identificador del usuario
     */
    public void eliminarUsuario(String objectId) {
        usuarioRepository.eliminarUsuario(objectId);
    }

    /**
     * Obtiene la lista de usuarios de forma asíncrona
     * @param callback función a ejecutar cuando se obtenga la lista
     */
    public void getListaUsuarios(Consumer<List<Usuario>> callback) {
        usuarioRepository.getListaUsuarios(callback);
    }

    /**
     * Obtiene un usuario por su ID
     * @param objectId identificador del usuario
     * @return el usuario encontrado o null
     */
    public Usuario getUsuario(String objectId) {
        return usuarioRepository.getUsuario(objectId);
    }

    /**
     * Actualiza la información de un usuario
     * @param usuario usuario con información actualizada
     */
    public void actualizarUsuario(Usuario usuario) {
        usuarioRepository.actualizaUsuario(usuario);
    }

    /**
     * Busca un usuario por nombre
     * @param nombre nombre del usuario a buscar
     * @param callback función a ejecutar con el resultado
     */
    public void buscarUsuarioPorNombre(String nombre, Consumer<Usuario> callback) {
        getListaUsuarios(usuarios -> {
            for (Usuario u : usuarios) {
                if (u.getNombre().equals(nombre)) {
                    callback.accept(u);
                    return;
                }
            }
            callback.accept(null);
        });
    }
}