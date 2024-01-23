import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrawingPanel extends JPanel {

    public int primitiveType = 0;
    private int startX, startY, endX, endY;
    public int centerX, centerY;
    private int radius;

    private JComboBox<String> primitiveComboBox;
    private JComboBox<String> algorithmComboBox;
    private JComboBox<String> colorComboBox;


    public DrawingPanel(JComboBox<String> primitiveComboBox, JComboBox<String> algorithmComboBox, JComboBox<String> colorComboBox) {
        this.primitiveComboBox = primitiveComboBox;
        this.algorithmComboBox = algorithmComboBox;
        this.colorComboBox = colorComboBox;
        setPreferredSize(new Dimension(1600, 900));
        setBackground(Color.WHITE);


        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (primitiveType) {
                    case 0: //Linhas
                        handleLineClick(e);
                        break;
                    case 1: //Poligonos
                        handlePoligonClick(e);
                        break;
                    case 2: //Circunferências
                        handleCircleClick(e);
                        break;
                }
            }

            private void handleLineClick(MouseEvent e) {
                if (startX == 0 && startY == 0) {
                    startX = e.getX();
                    startY = e.getY();
                } else {
                    endX = e.getX();
                    endY = e.getY();

                    drawSelectedPrimitive(getSelectedColor(), startX, startY, endX, endY);

                    startX = 0;
                    startY = 0;
                }
            }

            private void handlePoligonClick(MouseEvent e) {
                if (startX == 0 && startY == 0) {
                    startX = e.getX();
                    startY = e.getY();
                } else {
                    endX = e.getX();
                    endY = e.getY();

                    drawSelectedPrimitive(getSelectedColor(), startX, startY, endX, endY);

                    startX = 0;
                    startY = 0;
                }
            }

            private void handleCircleClick(MouseEvent e) {
                if (centerX == 0 && centerY == 0) {
                    centerX = e.getX();
                    centerY = e.getY();

                    String raioStr = JOptionPane.showInputDialog(DrawingPanel.this, "Raio do círculo:");

                    try {
                        if (raioStr != null) {
                            radius = (int) Double.parseDouble(raioStr);
                        } else {
                            return;
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(DrawingPanel.this, "Insira um valor válido.");
                        return;
                    }


                    drawSelectedPrimitive(getSelectedColor(), radius, centerX, centerY);

                    centerX = 0;
                    centerY = 0;
                }
            }
        });
    }

    private Color getSelectedColor() {
        String selectedColor = (String) colorComboBox.getSelectedItem();
        switch (selectedColor) {
            case "Vermelho":
                return Color.RED;
            case "Verde":
                return Color.GREEN;
            case "Azul":
                return Color.BLUE;
            default:
                return Color.RED;
        }
    }

    private void drawSelectedPrimitive(Color color, int... args) {
        Graphics g = getGraphics();
        g.setColor(color);

        int primitiveType = primitiveComboBox.getSelectedIndex();
        int algType = algorithmComboBox.getSelectedIndex();

        switch (primitiveType) {
            case 0:
                switch (algType) {
                    case 0:
                        LineAlgorithms.algAnalitic(g, args[0], args[1], args[2], args[3]);
                        break;
                    case 1:
                        LineAlgorithms.algDDA(g, args[0], args[1], args[2], args[3]);
                        break;
                    case 2:
                        LineAlgorithms.algBres(g, args[0], args[1], args[2], args[3]);
                        break;
                    default:
                        System.out.println("default");
                        break;
                }
                break;

            case 1:
                switch (algType) { //Poligonos
                    case 0:
                        System.out.println("4");//Varredura
                        break;
                    case 1:
                        System.out.println("5");//BoundaryFill
                        break;
                    case 2:
                        System.out.println("6");//Analise Geometrica
                        break;
                    default:
                        System.out.println("default");
                        break;
                }
                break;

            case 2: //Circunferências
                switch (algType) {
                    case 0:
                        CircleAlgorithms.algParam(g, args[0], args[1], args[2]);
                        break;
                    case 1:
                        CircleAlgorithms.algIncSem(g, args[0], args[1], args[2]);
                        break;
                    case 2:
                        CircleAlgorithms.algBres(g, args[0], args[1], args[2]);
                        break;
                    default:
                        System.out.println("default");
                        break;
                }
                break;

            default:
                System.out.println("default");
                break;

        }
    }
}