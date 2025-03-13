/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package app.fit.dao;

import app.fit.modelos.UsuarioModelo;
import java.util.List;

/**
 *
 * @author jmeri
 */
public interface UsuarioInterface {
    public void agregarUsuario(UsuarioModelo usuario);
    public List<UsuarioModelo> getListaUsuarios();
    public void setListaUsuarios(List<UsuarioModelo> listaUsuarios);
    public void actualizaUsuarios(UsuarioModelo usuario);
}
