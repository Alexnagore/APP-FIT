package app.fit.vistas;

import app.fit.modelos.Ejercicio;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class CrearEntrenamientoVista extends JFrame {
    private final JTextField nombreEntrenamientoField;
    private final JPanel ejerciciosPanel;
    private final JButton guardarButton;
    private List<String> ordenSeleccion = new ArrayList<>();
    
    public CrearEntrenamientoVista(List<Ejercicio> ejerciciosDisponibles) {
        setTitle("Nuevo Entrenamiento");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(400, 300));
        setLayout(new BorderLayout(10, 10));
        
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel nombrePanel = new JPanel(new BorderLayout(5,5));
        JLabel nombreLabel = new JLabel("Nombre del Entrenamiento");
        nombreEntrenamientoField = new JTextField();
        nombreEntrenamientoField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        nombrePanel.add(nombreLabel, BorderLayout.NORTH);
        nombrePanel.add(nombreEntrenamientoField, BorderLayout.CENTER);
        panelPrincipal.add(nombrePanel);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JLabel ejerciciosLabel = new JLabel("Selecciona los ejercicios:");
        panelPrincipal.add(ejerciciosLabel);

        ejerciciosPanel = new JPanel();
        ejerciciosPanel.setLayout(new BoxLayout(ejerciciosPanel, BoxLayout.Y_AXIS));
        ejerciciosPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        ejerciciosPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        
        for (Ejercicio ejercicio : ejerciciosDisponibles) {
            JCheckBox checkBox = new JCheckBox(ejercicio.toString());
            checkBox.setAlignmentX(Component.LEFT_ALIGNMENT); 
            checkBox.setActionCommand(ejercicio.getObjectId());
            
            checkBox.addItemListener(e -> {
                String id = checkBox.getActionCommand();
                if (checkBox.isSelected()) {
                    ordenSeleccion.add(id);
                } else {
                    ordenSeleccion.remove(id);
                }

                System.out.println("Orden de selección: " + ordenSeleccion);
            });

            ejerciciosPanel.add(checkBox);
        }
        
        JScrollPane scrollPane = new JScrollPane(ejerciciosPanel);
        scrollPane.setPreferredSize(new Dimension(350, 150));
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        panelPrincipal.add(scrollPane);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 15)));
        
        
        guardarButton = new JButton("Crear Entrenamiento");
        guardarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelPrincipal.add(guardarButton);

        add(panelPrincipal, BorderLayout.CENTER);
        pack();
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
                    System.out.println("Ejercicio " + checkBox.getName());
                }
            }
        }
        
        return seleccionados;
    }
    
    public List<String> getOrdenEjercicios() {
        return ordenSeleccion;
    }
    
    public JButton getGuardarButton() {
        return guardarButton;
    }
}
