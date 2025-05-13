package app.fit;

import app.fit.dao.*;
import app.fit.modelos.Ejercicio;
import app.fit.modelos.Entrenamiento;
import app.fit.vistas.CrearEntrenamientoVista;
import app.fit.vistas.CrearEjercicioVista;
import app.fit.vistas.EntrenamientosVista;
import app.fit.vistas.EjerciciosVista;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AppControlador {
    
    private static AppControlador instancia;
    
    private final APIRESTEjercicio ejercicios;
    private final APIRESTEntrenamiento entrenamientos;
    
    private EntrenamientosVista vistaEntrenamientos;
    private EjerciciosVista vistaEjercicios;
    
    private AppControlador(){
        ejercicios = new APIRESTEjercicio();
        entrenamientos = new APIRESTEntrenamiento();
       
        vistaEntrenamientos = new EntrenamientosVista();
        
        for (Entrenamiento entrenamiento: entrenamientos.getListaEntrenamientos()) {
            vistaEntrenamientos.getEntrenamientosComboBox().addItem(entrenamiento);
        }
        
        actualizarVistaEntrenamientos();
        
        vistaEntrenamientos.getAgregarEntrenamientoButton().addActionListener((ActionEvent e) -> {
            abrirVentanaCrearEntrenamiento();
            vistaEntrenamientos.setVisible(false);
        });
        
        vistaEntrenamientos.getEntrenamientosComboBox().addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                actualizarVistaEntrenamientos();
            }
        });
        
        vistaEntrenamientos.getEliminarEntrenamientoButton().addActionListener((ActionEvent e) -> {
            Entrenamiento entrenamientoSeleccionado = (Entrenamiento) vistaEntrenamientos.getEntrenamientosComboBox().getSelectedItem();
            entrenamientos.eliminarEntrenamiento(entrenamientoSeleccionado.getObjectId());
            actualizarVistaEntrenamientos();
        });
        
        vistaEntrenamientos.getEjercicioButton().addActionListener((ActionEvent e) -> {
            vistaEjercicios.setVisible(true);
            vistaEntrenamientos.setVisible(false);
        });
        
        vistaEntrenamientos.getSaveButton().addActionListener((ActionEvent e) -> {
            Entrenamiento entrenamientoSeleccionado = (Entrenamiento) vistaEntrenamientos.getEntrenamientosComboBox().getSelectedItem();
            entrenamientos.actualizaEntrenamientos(entrenamientoSeleccionado);
        });
        
        vistaEjercicios = new EjerciciosVista();
        
        for (Ejercicio ejercicio: ejercicios.getListaEjercicios()) {
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
    
    public static AppControlador getInstancia() {
        if (instancia == null) {
            instancia = new AppControlador();
        }
        return instancia;
    }
    
    private void actualizarVistaEntrenamientos() {
        Entrenamiento selectedEntrenamiento = (Entrenamiento) vistaEntrenamientos.getEntrenamientosComboBox().getSelectedItem();
        
        if (selectedEntrenamiento.getObjectId() == null) return;
        
        JPanel listaPanel = vistaEntrenamientos.getEjerciciosListPanel();
        listaPanel.removeAll();
        
        for (Ejercicio ejercicio : selectedEntrenamiento.getEjercicios()) {
            System.out.println("Pintando ejercicio: " + ejercicio.getNombre());
            Ejercicio ej = ejercicio;
            
            JPanel ejercicioPanel = vistaEntrenamientos.agregarEjercicioVisual(
                ej.getNombre(),
                e -> eliminarEjercicio(selectedEntrenamiento, ej),
                e -> subirEjercicio(selectedEntrenamiento, ej),
                e -> bajarEjercicio(selectedEntrenamiento, ej)
            );
            listaPanel.add(ejercicioPanel);
        }
        
        vistaEntrenamientos.ajustarAlturaScroll();
        
        listaPanel.revalidate();
        listaPanel.repaint();
    }
    
    private void abrirVentanaCrearEntrenamiento() {
        CrearEntrenamientoVista crearVista = new CrearEntrenamientoVista(ejercicios.getListaEjercicios());
        
        crearVista.getGuardarButton().addActionListener((ActionEvent e) -> {
            String nombreEntrenamiento = crearVista.getNombreEntrenamiento();
            List<JCheckBox> checkBoxes = crearVista.getEjerciciosSeleccionados();
     
            System.out.println(checkBoxes);
            
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
            
            vistaEntrenamientos.getEntrenamientosComboBox().addItem(nuevoEntrenamiento);
            
            crearVista.dispose();
            vistaEntrenamientos.setVisible(true);
        });
        
        crearVista.setVisible(true);
        
    }
    
        private void actualizarVistaEjercicios() {
        String selectedObjectName = (String) vistaEjercicios.getEjerciciosComboBox().getSelectedItem();
        
        if (selectedObjectName == null) return;
        
        Ejercicio seleccionado = null;
        for (Ejercicio ejercicio : ejercicios.getListaEjercicios()) {
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
            
            vistaEjercicios.getEjerciciosComboBox().addItem(nuevoEjercicio.getNombre());
            
            crearEjercicioVista.dispose();
            vistaEjercicios.setVisible(true);
        });
        
        crearEjercicioVista.setVisible(true);
        
    }
    
    public List<Ejercicio> listarEjercicios() {
        return ejercicios.getListaEjercicios();
    }


    public List<Entrenamiento> listarEntrenamientos() {
        return entrenamientos.getListaEntrenamientos();
    }
 
    private void eliminarEjercicio(Entrenamiento entrenamiento, Ejercicio ejercicio) {
        entrenamiento.eliminarEjercicio(ejercicio);
        actualizarVistaEntrenamientos();
    }
    
    private void subirEjercicio(Entrenamiento entrenamiento, Ejercicio ejercicio) {
        entrenamiento.subirEjercicio(ejercicio);
        actualizarVistaEntrenamientos();
    }
    
    private void bajarEjercicio(Entrenamiento entrenamiento, Ejercicio ejercicio) {
        entrenamiento.bajarEjercicio(ejercicio);
        actualizarVistaEntrenamientos();
    }
    
}
