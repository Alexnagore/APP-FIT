/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.appfit.dao;

import com.example.appfit.modelos.Usuario;
import java.util.List;

/**
 *
 * @author jmeri
 */
public interface UsuarioInterface {
    public void agregarUsuario(Usuario usuario);
    public void eliminarUsuario(Usuario usuario);
    public List<Usuario> getListaUsuarios();
    public Usuario getUsuario(Usuario usuario);
    public void setListaUsuarios(List<Usuario> listaUsuarios);
    public void actualizaUsuarios(int id, Usuario usuario);
}
