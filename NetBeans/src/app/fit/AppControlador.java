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
import app.fit.vistas.CrearEjercicioVista;
import app.fit.vistas.EntrenamientosVista;
import app.fit.vistas.EjerciciosVista;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author alumno
 */
public class AppControlador {
    private final APIRESTEjercicio ejercicios;
    private final APIRESTEntrenamiento entrenamientos;
    private final APIRESTUsuario inventarioUsuario;
    private final List<Ejercicio> listaEjercicios;
    private final List<Entrenamiento> listaEntrenamientos;
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
            vistaEntrenamientos.getEntrenamientosComboBox().addItem(entrenamiento.getNombre());
        }
        
        actualizarVistaEntrenameintos();
        
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
            vistaEjercicios.getEjerciciosComboBox().addItem(ejercicio.getNombre());
        }
        
        actualizarVistaEjercicios();
        
        vistaEjercicios.getAgregarEjercicioButton().addActionListener((ActionEvent e) -> {
            abrirVentanaCrearEjercicio();
        });
        
        vistaEjercicios.getEjerciciosComboBox().addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                actualizarVistaEjercicios();
            }
        });
        
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
            if (entrenamiento.getNombre().equals(selectedObjectId)) {
                seleccionado = entrenamiento;
                break;
            }
        }
        
        JPanel listaPanel = vistaEntrenamientos.getEjerciciosListPanel();
        listaPanel.removeAll();
        
        if (seleccionado != null) {
            for (Ejercicio ejercicio : seleccionado.getEjercicios()) {
                listaPanel.add(vistaEntrenamientos.agregarEjercicioVisual(ejercicio.getNombre()));
            }
        } else {
            listaPanel.add(new JLabel("Entrenamiento no encontrado"));
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
            
            vistaEntrenamientos.getEntrenamientosComboBox().addItem(nuevoEntrenamiento.getNombre());
            
            crearVista.dispose();
            vistaEntrenamientos.setVisible(true);
        });
        
        crearVista.setVisible(true);
        
    }
    
        private void actualizarVistaEjercicios() {
        String selectedObjectName = (String) vistaEjercicios.getEjerciciosComboBox().getSelectedItem();
        
        System.out.println(selectedObjectName);
        if (selectedObjectName == null) return;
        
        Ejercicio seleccionado = null;
        for (Ejercicio ejercicio : listaEjercicios) {
            if (ejercicio.getNombre().equals(selectedObjectName)) {
                seleccionado = ejercicio;
                break;
            }
        }
        
        if (seleccionado != null) {
            vistaEjercicios.getEjercicioArea().setText(seleccionado.toString());
        } else {
            vistaEjercicios.getEjercicioArea().setText("Ejercicio no encontrado");
        }
    }
    
    private void abrirVentanaCrearEjercicio() {
        CrearEjercicioVista crearEjercicioVista = new CrearEjercicioVista();
        
        crearEjercicioVista.getGuardarButton().addActionListener((ActionEvent e) -> {
            String nombreEjercicio = crearEjercicioVista.getNombreEjercicio();
            
            if (nombreEjercicio.isEmpty()) {
                JOptionPane.showMessageDialog(crearEjercicioVista, "Por favor, ingresa un nombre para el entrenamiento.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Ejercicio nuevoEjercicio = crearEjercicioVista.getNuevoEjercicio();
                       
            String id = ejercicios.agregarEjercicio(nuevoEjercicio);
            nuevoEjercicio.setObjectId(id);
            listaEjercicios.add(nuevoEjercicio);
            
            vistaEjercicios.getEjerciciosComboBox().addItem(nuevoEjercicio.getNombre());
            
            crearEjercicioVista.dispose();
            vistaEjercicios.setVisible(true);
        });
        
        crearEjercicioVista.setVisible(true);
        
    }
   
    private void completarPartida(Partida partida){
        
        for(Entrenamiento entrenamiento: partida.getEntrenamientos()){
            partida.getUsuario().aumentarPuntuacion(entrenamiento.getPuntuacion());
            partida.getUsuario().incrementarEntrenamientosCompletados();
        }
    }
    
    public List<Ejercicio> listarEjercicios() {
        return ejercicios.getListaEjercicios();
    }


    public List<Entrenamiento> listarEntrenamientos() {
        return entrenamientos.getListaEntrenamientos();
    }
 
    public List<Usuario> listarUsuarios() {
        return inventarioUsuario.getListaUsuarios();
    }
    
}
