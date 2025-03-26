/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.appfit.dao;

import com.example.appfit.modelos.Usuario;
import java.util.ArrayList;
import java.util.List;

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
    public void eliminarUsuario(Usuario usuario){
        listaUsuarios.remove(usuario);
    }
    
    @Override
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }
    
    @Override
    public Usuario getUsuario(Usuario usuario) {
        return listaUsuarios.get(listaUsuarios.indexOf(usuario));
    }

    @Override
    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    
    @Override
    public void actualizaUsuarios(int id, Usuario usuario){
        for(Usuario u : listaUsuarios){
            if(u.getId() == id){
                listaUsuarios.set(listaUsuarios.indexOf(u), usuario);
            }
        }
    }
}
