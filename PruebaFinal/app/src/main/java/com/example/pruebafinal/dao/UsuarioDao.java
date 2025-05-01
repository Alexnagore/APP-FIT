/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.pruebafinal.dao;

import com.example.pruebafinal.modelos.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author jmeri
 */
public class UsuarioDao implements UsuarioInterface{
    private List<Usuario> listaUsuarios;
    
    public UsuarioDao(){
       listaUsuarios = new ArrayList<>();
    }
    
    @Override
    public void agregarUsuario(Usuario usuario){
        listaUsuarios.add(usuario);
    }
    
    @Override
    public void eliminarUsuario(String objectId){
        listaUsuarios.remove(listaUsuarios.indexOf(objectId));
    }

    @Override
    public void getListaUsuarios(Consumer<List<Usuario>> callback) {

    }

    //@Override
    //public List<Usuario> getListaUsuarios() {
    //    return listaUsuarios;
    //}
    
    @Override
    public Usuario getUsuario(String objectId) {
        return listaUsuarios.get(listaUsuarios.indexOf(objectId));
    }
    
    @Override
    public void actualizaUsuario(Usuario usuario){
        listaUsuarios.set(listaUsuarios.indexOf(usuario), usuario);
    }
}
