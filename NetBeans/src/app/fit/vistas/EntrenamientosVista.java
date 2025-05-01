/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.fit.vistas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author User
 */
public class EntrenamientosVista extends JFrame {
    private final JComboBox<String> entrenamientosComboBox;
    private final JButton agregarEntrenamientoButton;
    private final JTextArea ejercicioArea;
    private final JButton ejercicioButton;
    private final JButton usuarioButton;
    
    public EntrenamientosVista() {
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;
        
        setTitle("Entrenemientos");
        setSize(600, screenHeight-40);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior para selección de entrenamientos
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new FlowLayout());
        entrenamientosComboBox = new JComboBox<>();
        agregarEntrenamientoButton = new JButton("Nuevo Entrenamiento");
        panelSuperior.add(new JLabel("Entrenamiento:"));
        panelSuperior.add(entrenamientosComboBox);
        panelSuperior.add(agregarEntrenamientoButton);
        add(panelSuperior, BorderLayout.NORTH);
        
        // Área de texto para mostrar los ejercicios
        ejercicioArea = new JTextArea();
        ejercicioArea.setEditable(false);
        add(new JScrollPane(ejercicioArea), BorderLayout.CENTER);
        
        // Panel inferior para añadir ejercicios y usuarios
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new FlowLayout());
        // Botón para mostrar proveedores
        ejercicioButton = new JButton("Mostrar Ejercicios");
        panelInferior.add(ejercicioButton, BorderLayout.CENTER);
        usuarioButton = new JButton("Mostrar Usuarios");
        panelInferior.add(usuarioButton, BorderLayout.SOUTH); 
        add(panelInferior, BorderLayout.SOUTH);
    }
    
    public JComboBox<String> getEntrenamientosComboBox() {
        return entrenamientosComboBox;
    }

    public JTextArea getEjercicioArea() {
        return ejercicioArea;
    }
    
    public JButton getAgregarEntrenamientoButton() {
        return agregarEntrenamientoButton;
    }

    public JButton getEjercicioButton() {
        return ejercicioButton;
    }
 
    public JButton getUsuarioButton() {
        return usuarioButton;
    }
}
