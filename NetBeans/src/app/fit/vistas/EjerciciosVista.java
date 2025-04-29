/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.fit.vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
public class EjerciciosVista extends JFrame {
    private JComboBox<String> ejerciciosComboBox;
    private JButton agregarEjercicioButton;
    private JTextArea ejercicioArea;
    private JButton volverButton;
    
    public EjerciciosVista() {
        setTitle("Ejercicios");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior para selección de entrenamientos
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new FlowLayout());
        ejerciciosComboBox = new JComboBox<>();
        agregarEjercicioButton = new JButton("Nuevo Ejercicio");
        panelSuperior.add(new JLabel("Ejercicio:"));
        panelSuperior.add(ejerciciosComboBox);
        panelSuperior.add(agregarEjercicioButton);
        add(panelSuperior, BorderLayout.NORTH);
        
        // Área de texto para mostrar los ejercicios
        ejercicioArea = new JTextArea();
        ejercicioArea.setEditable(false);
        add(new JScrollPane(ejercicioArea), BorderLayout.CENTER);
        
        // Panel inferior para añadir ejercicios y usuarios
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new FlowLayout());
        // Botón para mostrar proveedores
        volverButton = new JButton("Volver");
        panelInferior.add(volverButton, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }
    
    public JComboBox<String> getEjerciciosComboBox() {
        return ejerciciosComboBox;
    }

    public JTextArea getEjercicioArea() {
        return ejercicioArea;
    }
    
    public JButton getAgregarEjercicioButton() {
        return agregarEjercicioButton;
    }

    public JButton getVolverButton() {
        return volverButton;
    }

}
