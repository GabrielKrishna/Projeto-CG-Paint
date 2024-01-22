import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrawingPanel extends JPanel {

    public int primitiveType = 0;
    private int startX, startY; // Armazenar as coordenadas iniciais do clique
    private int centerX, centerY; // Armazenar as coordenadas do centro
    private int radius; // Armazenar o raio

    private JComboBox<String> primitiveComboBox;
    private JComboBox<String> algorithmComboBox;
    private JComboBox<String> colorComboBox;

    // Construtor modificado para receber JComboBox

    public DrawingPanel(JComboBox<String> primitiveComboBox, JComboBox<String> algorithmComboBox, JComboBox<String> colorComboBox) {
            this.primitiveComboBox = primitiveComboBox;
            this.algorithmComboBox = algorithmComboBox;
            this.colorComboBox = colorComboBox;
        setPreferredSize(new Dimension(600, 400));
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (primitiveType) {
                    case 0: //Linhas
                        handleLineClick(e);
                        break;
                    case 1: //Poligonos
                        handleLineClick(e);
                        break;
                    case 2: //Circunferências
                        handleCircleClick(e);
                        break;
                }
            }

            private void handleLineClick(MouseEvent e) {
                if (startX == 0 && startY == 0) {
                    // Se as coordenadas iniciais ainda não foram definidas
                    startX = e.getX();
                    startY = e.getY();
                } else {
                    // Coordenadas finais no segundo clique
                    int endX = e.getX();
                    int endY = e.getY();

                    // Desenha a linha usando o algoritmo selecionado
                    drawSelectedPrimitive(startX, startY, endX, endY);

                    // Zera as coordenadas iniciais para o próximo clique
                    startX = 0;
                    startY = 0;
                }
            }

            private void handleCircleClick(MouseEvent e) {
                if (centerX == 0 && centerY == 0) {
                    // Se as coordenadas do centro ainda não foram definidas
                    centerX = e.getX();
                    centerY = e.getY();
                    radius = 250;

                    // Desenha a circunferência com as coordenadas e o raio obtidos
                    drawSelectedPrimitive(radius);

                    // Limpar as coordenadas do centro para a próxima circunferência
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
    private void requestRadius() {
        // Pede ao usuário para inserir o raio usando um diálogo de entrada
        String input = JOptionPane.showInputDialog("Digite o raio da circunferência:");
        try {
            radius = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            // Tratamento de erro se o usuário inserir algo que não seja um número
            JOptionPane.showMessageDialog(this, "Por favor, insira um valor válido para o raio.", "Erro", JOptionPane.ERROR_MESSAGE);
            requestRadius(); // Tenta novamente
        }
    }
    private void drawSelectedPrimitive(int... args) {
        Graphics g = getGraphics();

        int primitiveType = primitiveComboBox.getSelectedIndex();
        int algType = algorithmComboBox.getSelectedIndex();

        switch (primitiveType) {
            case 0: //Linhas
                // Chama o método de desenho de linhas com o algoritmo selecionado
                switch (algType) {
                    case 0:
                        LineAlgorithms.algAnalitic(g, args[0], args[1], args[2], args[3]);
                        System.out.println("0");
                        break;
                    case 1:
                        LineAlgorithms.algDDA(g, args[0], args[1], args[2], args[3]); //DDA
                        System.out.println("1");
                        break;
                    case 2:
                        LineAlgorithms.algBres(g, args[0], args[1], args[2], args[3]); //Bresenham
                        System.out.println("2");
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

                        break;
                    case 1:

                        System.out.println("8");
                        break;
                    case 2:
                        CircleAlgorithms.algBres(g, args[0], centerX, centerY);
                        System.out.println("9");
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