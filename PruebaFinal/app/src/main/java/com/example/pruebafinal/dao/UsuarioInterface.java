/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.pruebafinal.dao;

import com.example.pruebafinal.modelos.Usuario;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author jmeri
 */
public interface UsuarioInterface {
    public void agregarUsuario(Usuario usuario);
    public void eliminarUsuario(String objectId);
    public void getListaUsuarios(Consumer<List<Usuario>> callback);
    public Usuario getUsuario(String objectId);
    public void actualizaUsuario(Usuario usuario);
}
