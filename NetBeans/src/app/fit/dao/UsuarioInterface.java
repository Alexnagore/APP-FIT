/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package app.fit.dao;

import app.fit.modelos.Usuario;
import java.util.List;

/**
 *
 * @author jmeri
 */
public interface UsuarioInterface {
    public void agregarUsuario(Usuario usuario);
    public void eliminarUsuario(String objectId);
    public List<Usuario> getListaUsuarios();
    public Usuario getUsuario(String objectId);
    public void actualizaUsuario(Usuario usuario);
}
