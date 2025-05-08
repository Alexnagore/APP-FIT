package app.fit.vistas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class EntrenamientosVista extends JFrame {
    private final JComboBox<String> entrenamientosComboBox;
    private final JButton agregarEntrenamientoButton;
    private final JButton añadirEjercicioButton;
    private final JButton eliminarEntrenamientoButton;
    private final JPanel ejerciciosListPanel;
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
        añadirEjercicioButton = new JButton("Añadir Ejercicio");
        eliminarEntrenamientoButton = new JButton("Eliminar Entrenamiento");
        panelSuperior.add(new JLabel("Entrenamiento:"));
        panelSuperior.add(entrenamientosComboBox);
        panelSuperior.add(agregarEntrenamientoButton);
        panelSuperior.add(añadirEjercicioButton);
        panelSuperior.add(eliminarEntrenamientoButton);
        add(panelSuperior, BorderLayout.NORTH);
        
        // Panel que contiene la lista de ejercicios
        ejerciciosListPanel = new JPanel();
        ejerciciosListPanel.setLayout(new BoxLayout(ejerciciosListPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(ejerciciosListPanel);
        add(scrollPane, BorderLayout.CENTER);
        
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

    public JPanel getEjerciciosListPanel() {
        return ejerciciosListPanel;
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
    
    public JPanel agregarEjercicioVisual(String nombreEjercicio) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel(nombreEjercicio);
        JButton eliminarBtn = new JButton("X");
        JButton subirBtn = new JButton("🔼");
        JButton bajarBtn = new JButton("🔽");

        // Acción de eliminar
        eliminarBtn.addActionListener(e -> {
            ejerciciosListPanel.remove(panel);
            ejerciciosListPanel.revalidate();
            ejerciciosListPanel.repaint();
        });

        // Acción de subir
        subirBtn.addActionListener(e -> {
            int index = getComponentIndex(panel);
            if (index > 0) {
                ejerciciosListPanel.remove(panel);
                ejerciciosListPanel.add(panel, index - 1);
                ejerciciosListPanel.revalidate();
                ejerciciosListPanel.repaint();
            }
        });

        // Acción de bajar
        bajarBtn.addActionListener(e -> {
            int index = getComponentIndex(panel);
            if (index < ejerciciosListPanel.getComponentCount() - 1) {
                ejerciciosListPanel.remove(panel);
                ejerciciosListPanel.add(panel, index + 1);
                ejerciciosListPanel.revalidate();
                ejerciciosListPanel.repaint();
            }
        });

        panel.add(label);
        panel.add(subirBtn);
        panel.add(bajarBtn);
        panel.add(eliminarBtn);

        return panel;
    }
    
    private int getComponentIndex(Component comp) {
        Component[] comps = ejerciciosListPanel.getComponents();
        for (int i = 0; i < comps.length; i++) {
            if (comp == comps[i]) return i;
        }
        return -1;
    }
}
