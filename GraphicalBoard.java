package Chess;
import java.awt.*;
import javax.swing.*;

public class GraphicalBoard extends JFrame{
    
    
    public void openBoard() {

        // Creating a new Window;
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);

        // Putting an Icon on the Window;
        ImageIcon imageIcon = new ImageIcon("C:/Users/jstef/Desktop/Projetos Programação/JavaProjects/Chess/Images/ChessIcon.jpg");
        setIconImage(imageIcon.getImage());

        // Putting a new background to the Window;
        ImageIcon backgroundImage = new ImageIcon("C:/Users/jstef/Desktop/Projetos Programação/JavaProjects/Chess/Images/Background.jpg");
        JLabel background = new JLabel("", backgroundImage, JLabel.CENTER);
        background.setBounds(0, 0, 1700, 1133);
        add(background);

        // Set the Window visible;
        setVisible(true);
    }

    // Painting the board using Graphics;
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        // Put the backgroud of the board in black;
        graphics.setColor(Color.BLACK);
        graphics.fillRect((getWidth() - 768)/2, (getHeight() - 768)/2, 768, 768);
  
        // Select parts of the board and painting with white;
        graphics.setColor(Color.WHITE);
        int gridSize = 96; 
        int numCols = getWidth() / gridSize;
        for (int row = 0; row < 8; row++) {
            
            if (row % 2 == 0) {
                for (int col = 0; col < numCols/2; col  += 2) {
                
                    int x = (getWidth() - 768) / 2 + col * gridSize;
                    int y = (getHeight() - 768) / 2 + row * gridSize;
    
                    graphics.fillRect(x, y, gridSize, gridSize);
                }
            }
            else {
                for (int col = 1; col < numCols/2; col += 2) {
                
                    int x = (getWidth() - 768) / 2 + col * gridSize;
                    int y = (getHeight() - 768) / 2 + row * gridSize;
    
                    graphics.fillRect(x, y, gridSize, gridSize);
                }
            }
        }

    }
}
