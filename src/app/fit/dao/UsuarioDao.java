/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.fit.dao;

import app.fit.modelos.UsuarioModelo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jmeri
 */
public class UsuarioDao implements UsuarioInterface{
    private List<UsuarioModelo> listaUsuarios;
    
    public UsuarioDao(){
       listaUsuarios = new ArrayList<>();
    }
    
    @Override
    public void agregarUsuario(UsuarioModelo usuario){
        listaUsuarios.add(usuario);
    }
    
    @Override
    public List<UsuarioModelo> getListaUsuarios() {
        return listaUsuarios;
    }

    @Override
    public void setListaUsuarios(List<UsuarioModelo> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    
    @Override
    public void actualizaUsuarios(UsuarioModelo usuario){
        listaUsuarios.set(listaUsuarios.indexOf(usuario), usuario);
    }
}
