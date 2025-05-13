package app.fit.vistas;

import app.fit.modelos.Entrenamiento;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.*;


public class EntrenamientosVista extends JFrame {
    private final JComboBox<Entrenamiento> entrenamientosComboBox;
    private final JButton agregarEntrenamientoButton;
    private final JButton añadirEjercicioButton;
    private final JButton eliminarEntrenamientoButton;
    private final JPanel ejerciciosListPanel;
    private final JScrollPane ejerciciosScrollPane;
    private final JButton ejercicioButton;
    private final JButton saveButton;
    
    
    public EntrenamientosVista() {
        
        setTitle("Entrenemientos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel principal
        JPanel contenedorPrincipal = new JPanel();
        contenedorPrincipal.setLayout(new BoxLayout(contenedorPrincipal, BoxLayout.Y_AXIS));
        setContentPane(contenedorPrincipal);
        
        // Panel superior para selección de entrenamientos
        JPanel panelSuperior = new JPanel(new FlowLayout());
        entrenamientosComboBox = new JComboBox<>();
        agregarEntrenamientoButton = new JButton("Nuevo Entrenamiento");
        añadirEjercicioButton = new JButton("Añadir Ejercicio");
        eliminarEntrenamientoButton = new JButton("Eliminar Entrenamiento");
        
        panelSuperior.add(new JLabel("Entrenamiento:"));
        panelSuperior.add(entrenamientosComboBox);
        panelSuperior.add(agregarEntrenamientoButton);
        panelSuperior.add(añadirEjercicioButton);
        panelSuperior.add(eliminarEntrenamientoButton);

        contenedorPrincipal.add(panelSuperior);
        
        // Panel que contiene la lista de ejercicios
        ejerciciosListPanel = new JPanel();
        ejerciciosListPanel.setLayout(new BoxLayout(ejerciciosListPanel, BoxLayout.Y_AXIS));

        ejerciciosScrollPane = new JScrollPane(ejerciciosListPanel);
        ejerciciosScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ejerciciosScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        ejerciciosScrollPane.setBorder(BorderFactory.createTitledBorder("Ejercicios"));
        
        contenedorPrincipal.add(ejerciciosScrollPane);
        
        // Panel inferior para añadir ejercicios y usuarios
        JPanel panelInferior = new JPanel(new FlowLayout());
        ejercicioButton = new JButton("Mostrar Ejercicios");
        saveButton = new JButton("Guardar");

        panelInferior.add(ejercicioButton);
        panelInferior.add(saveButton);

        contenedorPrincipal.add(panelInferior);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public void ajustarAlturaScroll() {
        int alturaTotal = 0;
        for (Component comp : ejerciciosListPanel.getComponents()) {
            alturaTotal += comp.getPreferredSize().height;
        }

        alturaTotal += 10 * ejerciciosListPanel.getComponentCount(); // margen

        // Limita la altura máxima
        int alturaMaxima = 400;
        int alturaFinal = Math.min(alturaTotal, alturaMaxima);

        ejerciciosScrollPane.setPreferredSize(new Dimension(600, alturaFinal));
        ejerciciosScrollPane.revalidate();
        pack(); // actualiza tamaño de la ventana
    }
   
    public JComboBox<Entrenamiento> getEntrenamientosComboBox() {
        return entrenamientosComboBox;
    }

    public JPanel getEjerciciosListPanel() {
        return ejerciciosListPanel;
    }
    
    public JButton getAgregarEntrenamientoButton() {
        return agregarEntrenamientoButton;
    }

    public JButton getEjercicioButton() {
        return ejercicioButton;
    }
 
    public JButton getSaveButton() {
        return saveButton;
    }
    
    public JButton getAñadirEjercicioButton() {
        return añadirEjercicioButton;
    }

    public JButton getEliminarEntrenamientoButton() {
        return eliminarEntrenamientoButton;
    }
       
    public JPanel agregarEjercicioVisual(String nombreEjercicio,
                                        ActionListener eliminarListener,
                                        ActionListener subirListener,
                                        ActionListener bajarListener) {
        
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel(nombreEjercicio);
        JButton eliminarBtn = new JButton("X");
        JButton subirBtn = new JButton("🔼");
        JButton bajarBtn = new JButton("🔽");
        
        eliminarBtn.addActionListener(eliminarListener);
        subirBtn.addActionListener(subirListener);
        bajarBtn.addActionListener(bajarListener);

        panel.add(label);
        panel.add(subirBtn);
        panel.add(bajarBtn);
        panel.add(eliminarBtn);

        return panel;
    }
    
}
