package app.fit.vistas;

import app.fit.modelos.Ejercicio;
import java.awt.*;
import javax.swing.*;

public class CrearEjercicioVista extends JFrame {
    private JTextField nombreEjercicioField;
    private JTextField puntuacionField;
    private JTextField tiempoField;
    private JTextField puntoInicialField;
    private JTextField puntoFinalField;
    private JTextField repeticionesField;
    private final JButton guardarButton;
    
    public CrearEjercicioVista() {
        setTitle("Nuevo Ejercicio");
        setSize(400, 300);
        setLayout(new BorderLayout());
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel nombreLabel = new JLabel("Nombre del Ejercicio");
        nombreEjercicioField = new JTextField(20);
        panel.add(nombreLabel);
        panel.add(nombreEjercicioField);
        
        JPanel ejercicioPanel = new JPanel();
        ejercicioPanel.setLayout(new BoxLayout(ejercicioPanel, BoxLayout.Y_AXIS));
        
        JLabel puntuacionLabel = new JLabel("Puntuacion:");
        puntuacionField = new JTextField(5);
        JLabel tiempoLabel = new JLabel("Tiempo (segundos):");
        tiempoField = new JTextField(3);
        JLabel repeticionesLabel = new JLabel("Repeticiones:");
        repeticionesField = new JTextField(2);
        ejercicioPanel.add(puntuacionLabel);
        ejercicioPanel.add(puntuacionField);
        ejercicioPanel.add(tiempoLabel);
        ejercicioPanel.add(tiempoField);
        ejercicioPanel.add(repeticionesLabel);
        ejercicioPanel.add(repeticionesField);
        
        panel.add(ejercicioPanel);
       
        guardarButton = new JButton("Crear Ejercicio");
        panel.add(guardarButton);
        
        add(panel, BorderLayout.CENTER);
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public String getNombreEjercicio() {
        return nombreEjercicioField.getText();
    }
    
    public Ejercicio getNuevoEjercicio() {
        return new Ejercicio(getNombreEjercicio(), Integer.parseInt(puntuacionField.getText()), Integer.parseInt(tiempoField.getText()), Integer.parseInt(repeticionesField.getText()));
    }
    
    public JButton getGuardarButton() {
        return guardarButton;
    }
}
