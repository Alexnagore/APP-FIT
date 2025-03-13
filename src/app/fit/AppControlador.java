/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.fit;

import app.fit.dao.*;
import app.fit.modelos.EjercicioModelo;
import app.fit.modelos.EntrenamientoModelo;
import app.fit.modelos.Localizacion;
import app.fit.modelos.PartidaModelo;
import app.fit.modelos.UsuarioModelo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumno
 */
public class AppControlador {
    private EjercicioDao ejercicios;
    private EntrenamientoDao entrenamientos;
    private UsuarioDao inventarioUsuario;
    private PartidaModelo partida;
    //implementación de vistas si es necesaris
/*
    private TiendaVista vista;
    private InventarioLibrosVista inventarioVista;
    private DirectorioProveedoresVista listaProveedoresVista;
    private ProveedorVista proveedorVista;
*/
    
    public AppControlador(){
        ejercicios = new EjercicioDao();
        entrenamientos = new EntrenamientoDao();
        inventarioUsuario = new UsuarioDao();
        
        Localizacion puntoInicial = new Localizacion(13.2,16.2,152.2);
        Localizacion puntoFinal = new Localizacion(19.2,20.2,158.2);
        // Inicializar con algunos libros
        EjercicioModelo ej1 = new EjercicioModelo("ej1", 1000, 13, puntoInicial,puntoFinal);
        EjercicioModelo ej2 = new EjercicioModelo("ej2", 2000, 50, puntoInicial,puntoFinal);
        EjercicioModelo ej3 = new EjercicioModelo("ej3", 3000, 20, puntoInicial,puntoFinal);

        ejercicios.agregarEjercicio(ej1);
        ejercicios.agregarEjercicio(ej2);
        ejercicios.agregarEjercicio(ej3);
        

        // Inicializar con algunos proveedores
        EntrenamientoModelo en1 = new EntrenamientoModelo();
        en1.agregarEjercicio(ej1);
        en1.agregarEjercicio(ej2);

        EntrenamientoModelo en2 = new EntrenamientoModelo();
        en2.agregarEjercicio(ej3);
        en2.agregarEjercicio(ej2);

        entrenamientos.agregarEntrenamiento(en1);
        entrenamientos.agregarEntrenamiento(en2);
        
        UsuarioModelo usuario1 = new UsuarioModelo("Martin", "Orenes", "mo@hotmail.com", "cont");
        UsuarioModelo usuario2 = new UsuarioModelo("Javier", "Merino", "jm@hotmail.com", "contr");
        UsuarioModelo usuario3 = new UsuarioModelo("Alex", "Nagore", "an@hotmail.com", "contra");
        
        inventarioUsuario.agregarUsuario(usuario1);
        inventarioUsuario.agregarUsuario(usuario2);
        inventarioUsuario.agregarUsuario(usuario3);
        
        partida = new PartidaModelo(usuario1);
        partida.addEntrenamiento(en2);
        
        completarPartida(partida);
    
        
        probarMetodosDAO();
    }
    
   
    private void completarPartida(PartidaModelo partida){
        
        for(EntrenamientoModelo entrenamiento: partida.getEntrenamientos()){
            partida.getUsuario().aumentarPuntuacion(entrenamiento.getPuntuacion());
            partida.getUsuario().incrementarEntrenamientosCompletados();
        }
    }
    
    
    
    private void probarMetodosDAO() {
        System.out.println("---- Listado de Ejercicios ----");
        for (EjercicioModelo e : listarEjercicios()) {
            System.out.println(e);
        }
       // System.out.println("Obtener Ejercicio en índice 1: " + obtenerEjercicio(1));

        System.out.println("---- Listado de Entrenamientos ----");
        for (EntrenamientoModelo en : listarEntrenamientos()) {
            System.out.println(en);
        }
        //System.out.println("Obtener Entrenamiento en índice 0: " + obtenerEntrenamiento(0));

        System.out.println("---- Listado de Usuarios ----");
        for (UsuarioModelo u : listarUsuarios()) {
            System.out.println(u);
        }
        //System.out.println("Obtener Usuario en índice 2: " + obtenerUsuario(2));
    }
    
    
    public List<EjercicioModelo> listarEjercicios() {
        return ejercicios.getListaEjercicios();
    }


    /*public EjercicioModelo obtenerEjercicio(int indice) {
        return ejercicios.getEjercicio(indice);
    }*/


    public List<EntrenamientoModelo> listarEntrenamientos() {
        return entrenamientos.getListaEntrenamientos();
    }

    /*
    public EntrenamientoModelo obtenerEntrenamiento(int indice) {
        return entrenamientos.getEntrenamiento(indice);
    }
    */
 
    public List<UsuarioModelo> listarUsuarios() {
        return inventarioUsuario.getListaUsuarios();
    }

/*
    public UsuarioModelo obtenerUsuario(int indice) {
        return inventarioUsuario.getUsuario(indice);
    }
*/
}
