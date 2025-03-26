package com.example.appfit;

import org.junit.Test;
import com.example.appfit.modelos.Usuario;
import com.example.appfit.dao.UsuarioDao;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UsuarioTest {

    @Test
    public void agregarUsuario() {
        //Arrange
        Usuario usuario = new Usuario("Pablo", "Merino", "jmerinopinedo@gmail.com", "1234", 1);
        UsuarioDao usuarioDao = new UsuarioDao();

        //Act
        usuarioDao.agregarUsuario(usuario);

        //Assert
        assertTrue(usuarioDao.getListaUsuarios().contains(usuario));
    }

    @Test
    public void eliminarUsuario() {
        //Arrange
        Usuario usuario = new Usuario("Pablo", "Merino", "jmerinopinedo@gmail.com", "1234", 1);
        UsuarioDao usuarioDao = new UsuarioDao();

        //Act
        usuarioDao.agregarUsuario(usuario);
        usuarioDao.eliminarUsuario(usuario);

        //Assert
        assertFalse(usuarioDao.getListaUsuarios().contains(usuario));
    }

    @Test
    public void actualizarUsuario() {
        //Arrange
        Usuario usuario = new Usuario("Pablo", "Merino", "jmerinopinedo@gmail.com", "1234", 1);
        UsuarioDao usuarioDao = new UsuarioDao();

        //Act
        usuarioDao.agregarUsuario(usuario);

        Usuario usuarioActualizado = new Usuario("Pablo", "Merino", "jmerinopinedo@gmail.com", "12345", 2);
        usuarioDao.actualizaUsuarios(1, usuarioActualizado);

        //Assert
        assertEquals(2, usuarioActualizado.getId());
    }
}