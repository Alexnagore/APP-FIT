/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.fit;

import app.fit.dao.*;
import app.fit.modelos.Ejercicio;
import app.fit.modelos.Entrenamiento;
import app.fit.modelos.Localizacion;
import app.fit.modelos.Partida;
import app.fit.modelos.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumno
 */
public class AppControlador {
    private final APIRESTEjercicio ejercicios;
    private final APIRESTEntrenamiento entrenamientos;
    private final UsuarioDao inventarioUsuario;
    private final Partida partida;
    //implementación de vistas si es necesaris
/*
    private TiendaVista vista;
    private InventarioLibrosVista inventarioVista;
    private DirectorioProveedoresVista listaProveedoresVista;
    private ProveedorVista proveedorVista;
*/
    
    public AppControlador(){
        ejercicios = new APIRESTEjercicio();
        entrenamientos = new APIRESTEntrenamiento();
        inventarioUsuario = new UsuarioDao();
        
        Localizacion puntoInicial = new Localizacion(13.2,16.2,152.2);
        Localizacion puntoFinal = new Localizacion(19.2,20.2,158.2);
        // Inicializar con algunos libros
        Ejercicio ej1 = new Ejercicio("ej1", 1000, 13, puntoInicial,puntoFinal);
        Ejercicio ej2 = new Ejercicio("ej2", 2000, 50, puntoInicial,puntoFinal);
        Ejercicio ej3 = new Ejercicio("ej3", 3000, 20, puntoInicial,puntoFinal);

        ejercicios.agregarEjercicio(ej1);
        ejercicios.agregarEjercicio(ej2);
        ejercicios.agregarEjercicio(ej3);
        
        ej3.setObjectId("xbkqm8JV4g");
        ej3.setDescripcion("Desc cambiada");
        ejercicios.actualizaEjercicio(ej3);
        
        ejercicios.eliminarEjercicio("OB1dCc1g89");
        
        Entrenamiento en1 = new Entrenamiento();
        en1.agregarEjercicio(ej1);
        en1.agregarEjercicio(ej2);
        en1.agregarEjercicio(ej3);

        Entrenamiento en2 = new Entrenamiento();
        en2.agregarEjercicio(ej3);
        en2.agregarEjercicio(ej2);

        entrenamientos.agregarEntrenamiento(en1);
        entrenamientos.agregarEntrenamiento(en2);
        
        en1.eliminarEjercicio(ej1);
        
        en1.setObjectId("ydbSBz2XUv");
        
        entrenamientos.actualizaEntrenamientos(en1);
        
        Usuario usuario1 = new Usuario("Martin", "Orenes", "mo@hotmail.com", "cont");
        Usuario usuario2 = new Usuario("Javier", "Merino", "jm@hotmail.com", "contr");
        Usuario usuario3 = new Usuario("Alex", "Nagore", "an@hotmail.com", "contra");
        
        inventarioUsuario.agregarUsuario(usuario1);
        inventarioUsuario.agregarUsuario(usuario2);
        inventarioUsuario.agregarUsuario(usuario3);
        
        partida = new Partida(usuario1);
        partida.addEntrenamiento(en2);
        
        completarPartida(partida);
    
        
        probarMetodosDAO();
    }
    
   
    private void completarPartida(Partida partida){
        
        for(Entrenamiento entrenamiento: partida.getEntrenamientos()){
            partida.getUsuario().aumentarPuntuacion(entrenamiento.getPuntuacion());
            partida.getUsuario().incrementarEntrenamientosCompletados();
        }
    }
    
    
    
    private void probarMetodosDAO() {
        System.out.println("---- Listado de Ejercicios ----");
        ArrayList<Ejercicio> listaEjercicios = new ArrayList<Ejercicio>();
        listaEjercicios = this.ejercicios.getListaEjercicios();
        for (Ejercicio e : listaEjercicios) {
            System.out.println(e);
        }

        System.out.println("---- Listado de Entrenamientos ----");
        ArrayList<Entrenamiento> listaEntrenamientos =new ArrayList<Entrenamiento>();
        listaEntrenamientos = this.entrenamientos.getListaEntrenamientos();
        for (Entrenamiento entrenamiento : listaEntrenamientos) {
            System.out.println(entrenamiento);
        }

        System.out.println("---- Listado de Usuarios ----");
        for (Usuario u : listarUsuarios()) {
            System.out.println(u);
        }
        //System.out.println("Obtener Usuario en índice 2: " + obtenerUsuario(2));
    }
    
    
    public List<Ejercicio> listarEjercicios() {
        return ejercicios.getListaEjercicios();
    }


    /*public Ejercicio obtenerEjercicio(int indice) {
        return ejercicios.getEjercicio(indice);
    }*/


    public List<Entrenamiento> listarEntrenamientos() {
        return entrenamientos.getListaEntrenamientos();
    }

    /*
    public Entrenamiento obtenerEntrenamiento(int indice) {
        return entrenamientos.getEntrenamiento(indice);
    }
    */
 
    public List<Usuario> listarUsuarios() {
        return inventarioUsuario.getListaUsuarios();
    }

/*
    public Usuario obtenerUsuario(int indice) {
        return inventarioUsuario.getUsuario(indice);
    }
*/
}
