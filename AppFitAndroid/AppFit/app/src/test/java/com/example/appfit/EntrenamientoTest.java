package com.example.appfit;

import org.junit.Test;

import com.example.appfit.dao.EntrenamientoDao;
import com.example.appfit.modelos.Entrenamiento;
import com.example.appfit.modelos.Usuario;
import com.example.appfit.dao.UsuarioDao;

import static org.junit.Assert.*;

public class EntrenamientoTest {

    @Test
    public void agregarEntrenamiento() {
        //Arrange
        Entrenamiento entrenamiento = new Entrenamiento(1);
        EntrenamientoDao entrenamientoDao = new EntrenamientoDao();

        //Act
        entrenamientoDao.agregarEntrenamiento(entrenamiento);

        //Assert
        assertTrue(entrenamientoDao.getListaEntrenamientos().contains(entrenamiento));
    }

    @Test
    public void eliminarUsuario() {
        //Arrange
        Entrenamiento entrenamiento = new Entrenamiento(1);
        EntrenamientoDao entrenamientoDao = new EntrenamientoDao();

        //Act
        entrenamientoDao.agregarEntrenamiento(entrenamiento);
        entrenamientoDao.eliminarEntrenamiento(entrenamiento);

        //Assert
        assertFalse(entrenamientoDao.getListaEntrenamientos().contains(entrenamiento));
    }

    @Test
    public void actualizarEntrenamiento() {
        //Arrange
        Entrenamiento entrenamiento = new Entrenamiento(1);
        EntrenamientoDao entrenamientoDao = new EntrenamientoDao();

        //Act
        entrenamientoDao.agregarEntrenamiento(entrenamiento);

        Entrenamiento entrenamientoActualizado = new Entrenamiento(2);
        entrenamientoDao.actualizaEntrenamieto(1, entrenamientoActualizado);

        //Assert
        assertEquals(2, entrenamientoActualizado.getId());
    }
}