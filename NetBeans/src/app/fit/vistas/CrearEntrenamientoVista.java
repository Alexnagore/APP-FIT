package app.fit.vistas;

import app.fit.modelos.Ejercicio;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class CrearEntrenamientoVista extends JFrame {
    private JTextField nombreEntrenamientoField;
    private JPanel ejerciciosPanel;
    private JButton guardarButton;
    
    public CrearEntrenamientoVista(List<Ejercicio> ejerciciosDisponibles) {
        setTitle("Nuevo Entrenamiento");
        setSize(400, 300);
        setLayout(new BorderLayout());
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel nombreLabel = new JLabel("Nombre del Entrenamiento");
        nombreEntrenamientoField = new JTextField(20);
        panel.add(nombreLabel);
        panel.add(nombreEntrenamientoField);
        
        ejerciciosPanel = new JPanel();
        ejerciciosPanel.setLayout(new BoxLayout(ejerciciosPanel, BoxLayout.Y_AXIS));
        
        for (Ejercicio ejercicio : ejerciciosDisponibles) {
            JCheckBox checkBox = new JCheckBox(ejercicio.toString());
            checkBox.setActionCommand(ejercicio.getObjectId());
            ejerciciosPanel.add(checkBox);
        }
        
        JScrollPane scrollPane = new JScrollPane(ejerciciosPanel);
        panel.add(new JLabel("Selecciona los ejercicios:"));
        panel.add(scrollPane);
        
        guardarButton = new JButton("Crear Entrenamiento");
        panel.add(guardarButton);
        
        add(panel, BorderLayout.CENTER);
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public String getNombreEntrenamiento() {
        return nombreEntrenamientoField.getText();
    }
    
    public List<JCheckBox> getEjerciciosSeleccionados() {
        List<JCheckBox> seleccionados = new ArrayList<>();
        
        for (Component comp : ejerciciosPanel.getComponents()) {
            if (comp instanceof JCheckBox) {
                JCheckBox checkBox = (JCheckBox) comp;
                if (checkBox.isSelected()) {
                    seleccionados.add(checkBox);
                }
            }
        }
        
        return seleccionados;
    }
    
    public JButton getGuardarButton() {
        return guardarButton;
    }
}
