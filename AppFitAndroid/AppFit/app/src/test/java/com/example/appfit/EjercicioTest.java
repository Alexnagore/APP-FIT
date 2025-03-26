package com.example.appfit;

import org.junit.Test;

import com.example.appfit.dao.EjercicioDao;
import com.example.appfit.modelos.Ejercicio;
import com.example.appfit.modelos.Localizacion;

import static org.junit.Assert.*;
public class EjercicioTest {

    Localizacion puntoInicial = new Localizacion(13.2,16.2,152.2);
    Localizacion puntoFinal = new Localizacion(19.2,20.2,158.2);

    @Test
    public void agregarEjercicio() {
        //Arrange
        Ejercicio ejercicio = new Ejercicio("ej3", 3000, 20, puntoInicial,puntoFinal, 3);
        EjercicioDao ejercicioDao = new EjercicioDao();

        //Act
        ejercicioDao.agregarEjercicio(ejercicio);

        //Assert
        assertTrue(ejercicioDao.getListaEjercicios().contains(ejercicio));
    }

    @Test
    public void eliminarEjerccicio() {
        //Arrange
        Ejercicio ejercicio = new Ejercicio("ej3", 3000, 20, puntoInicial,puntoFinal, 3);
        EjercicioDao ejercicioDao = new EjercicioDao();

        //Act
        ejercicioDao.agregarEjercicio(ejercicio);
        ejercicioDao.eliminarEjercicio(ejercicio);

        //Assert
        assertFalse(ejercicioDao.getListaEjercicios().contains(ejercicio));
    }

    @Test
    public void actualizarEjercicio() {
        //Arrange
        Ejercicio ejercicio = new Ejercicio("ej3", 3000, 20, puntoInicial,puntoFinal, 3);
        EjercicioDao ejercicioDao = new EjercicioDao();

        //Act
        ejercicioDao.agregarEjercicio(ejercicio);

        Ejercicio ejercicioActualizado = new Ejercicio("ej2", 3000, 20, puntoInicial,puntoFinal, 2);
        ejercicioDao.actualizarEjercicio(3, ejercicioActualizado);

        //Assert
        assertEquals(2, ejercicioActualizado.getId());
    }
}