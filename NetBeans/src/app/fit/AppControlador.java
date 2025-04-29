/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.fit;

import app.fit.dao.*;
import app.fit.modelos.Ejercicio;
import app.fit.modelos.Entrenamiento;
import app.fit.modelos.Partida;
import app.fit.modelos.Usuario;
import app.fit.vistas.CrearEntrenamientoVista;
import app.fit.vistas.EntrenamientosVista;
import app.fit.vistas.EjerciciosVista;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

/**
 *
 * @author alumno
 */
public class AppControlador {
    private final APIRESTEjercicio ejercicios;
    private final APIRESTEntrenamiento entrenamientos;
    private final APIRESTUsuario inventarioUsuario;
    private List<Ejercicio> listaEjercicios;
    private List<Entrenamiento> listaEntrenamientos;
    private EntrenamientosVista vistaEntrenamientos;
    private EjerciciosVista vistaEjercicios;
    
    public AppControlador(){
        ejercicios = new APIRESTEjercicio();
        entrenamientos = new APIRESTEntrenamiento();
        inventarioUsuario = new APIRESTUsuario();
        listaEjercicios = ejercicios.getListaEjercicios();
        listaEntrenamientos = entrenamientos.getListaEntrenamientos();
       
        vistaEntrenamientos = new EntrenamientosVista();
        
        for (Entrenamiento entrenamiento: listaEntrenamientos) {
            vistaEntrenamientos.getEntrenamientosComboBox().addItem(entrenamiento.getObjectId());
        }
        
        vistaEntrenamientos.getAgregarEntrenamientoButton().addActionListener((ActionEvent e) -> {
            abrirVentanaCrearEntrenamiento();
            vistaEntrenamientos.setVisible(false);
        });
        
        vistaEntrenamientos.getEntrenamientosComboBox().addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                actualizarVistaEntrenameintos();
            }
        });
        
        vistaEntrenamientos.getEjercicioButton().addActionListener((ActionEvent e) -> {
            vistaEjercicios.setVisible(true);
            vistaEntrenamientos.setVisible(false);
        });
        
        vistaEntrenamientos.getUsuarioButton().addActionListener((ActionEvent e) -> {});
        
        
        vistaEjercicios = new EjerciciosVista();
        
        for (Ejercicio ejercicio: listaEjercicios) {
            vistaEjercicios.getEjerciciosComboBox().addItem(ejercicio.getObjectId());
        }
        
        vistaEjercicios.getVolverButton().addActionListener((ActionEvent e) -> {
            vistaEjercicios.setVisible(false);
            vistaEntrenamientos.setVisible(true);
        });
        
        vistaEntrenamientos.setVisible(true);

    }
    
    private void actualizarVistaEntrenameintos() {
        String selectedObjectId = (String) vistaEntrenamientos.getEntrenamientosComboBox().getSelectedItem();
        
        System.out.println(selectedObjectId);
        if (selectedObjectId == null) return;
        
        Entrenamiento seleccionado = null;
        for (Entrenamiento entrenamiento : listaEntrenamientos) {
            if (entrenamiento.getObjectId().equals(selectedObjectId)) {
                seleccionado = entrenamiento;
                break;
            }
        }
        
        if (seleccionado != null) {
            StringBuilder sb = new StringBuilder();
            for (Ejercicio ejercicio : seleccionado.getEjercicios()) {
                sb.append(ejercicio.toString()).append("\n");
                System.out.println(ejercicio.toString());
            }
            vistaEntrenamientos.getEjercicioArea().setText(sb.toString());
        } else {
            vistaEntrenamientos.getEjercicioArea().setText("Entrenamientos no encontrado");
        }
    }
    
    private void abrirVentanaCrearEntrenamiento() {
        CrearEntrenamientoVista crearVista = new CrearEntrenamientoVista(ejercicios.getListaEjercicios());
        
        crearVista.getGuardarButton().addActionListener((ActionEvent e) -> {
            String nombreEntrenamiento = crearVista.getNombreEntrenamiento();
            List<JCheckBox> checkBoxes = crearVista.getEjerciciosSeleccionados();
            
            if (nombreEntrenamiento.isEmpty()) {
                JOptionPane.showMessageDialog(crearVista, "Por favor, ingresa un nombre para el entrenamiento.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Entrenamiento nuevoEntrenamiento = new Entrenamiento();
            nuevoEntrenamiento.setNombre(nombreEntrenamiento);
            
            for (JCheckBox checkBox : checkBoxes) {
                if (checkBox.isSelected()) {
                    String ejercicioId = checkBox.getActionCommand();
                    Ejercicio ejercicioSeleccionado = ejercicios.getEjercicio(ejercicioId);
                    nuevoEntrenamiento.agregarEjercicio(ejercicioSeleccionado);
                }
            }
            
            String id = entrenamientos.agregarEntrenamiento(nuevoEntrenamiento);
            nuevoEntrenamiento.setObjectId(id);
            listaEntrenamientos.add(nuevoEntrenamiento);
            
            vistaEntrenamientos.getEntrenamientosComboBox().addItem(id);
            
            crearVista.dispose();
            vistaEntrenamientos.setVisible(true);
        });
        
        crearVista.setVisible(true);
        
    }
    
    private void abrirVentanaCrearEjercicio() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
