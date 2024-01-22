import javax.swing.*;
import java.awt.*;

public class PaintFrame extends JFrame {

    private JComboBox<String> primitiveComboBox;
    private JComboBox<String> algorithmComboBox;
    private JComboBox<String> colorComboBox;

    public PaintFrame() {
        setTitle("Mini Paint");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

        primitiveComboBox = new JComboBox<>(new String[]{"Linhas", "Poligonos", "Circunferencias"});
        primitiveComboBox.addActionListener(e -> updateAlgorithmComboBox());

        algorithmComboBox = new JComboBox<>();
        updateAlgorithmComboBox();
        algorithmComboBox.addActionListener(e -> {
            clearDrawing();
        });

        String[] colors = {"Vermelho", "Verde", "Azul"};
        colorComboBox = new JComboBox<>(colors);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Desenho", createDrawingPanel());

        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Primitivas:"));
        controlPanel.add(primitiveComboBox);
        controlPanel.add(new JLabel("Algoritmo:"));
        controlPanel.add(algorithmComboBox);
        controlPanel.add(new JLabel("Cores"));
        controlPanel.add(colorComboBox);

        getContentPane().add(controlPanel, BorderLayout.NORTH);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateAlgorithmComboBox() {
        int primitiveType = primitiveComboBox.getSelectedIndex();
        String[] algType;
        switch (primitiveType) {
            case 0: //Linhas
                algType = new String[]{"Analitico", "DDA", "Bresenham"};
                break;
            case 1: //Polígonos
                algType= new String[]{"Varredura", "BoundaryFill", "Analise Geometrica"};
                break;
            case 2: //Circunferência
                algType= new String[]{"Parametrica", "Incremental com Simetria", "Bresenham"};
                break;
            default:
                algType = new String[]{"Analitico", "DDA", "Bresenham"};
                break;
        };
        algorithmComboBox.setModel(new DefaultComboBoxModel<>(algType));
    }

    private DrawingPanel createDrawingPanel() {
        return new DrawingPanel(primitiveComboBox, algorithmComboBox, colorComboBox);
    }

    private void clearDrawing() {
        repaint();
    }
}