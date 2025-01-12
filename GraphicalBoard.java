package Chess;
import java.awt.*;
import javax.swing.*;

public class GraphicalBoard extends JFrame{
    
    // Images of the black pieces;
    ImageIcon blackPawn = new ImageIcon("C:/Users/jstef/Desktop/Projetos Programação/JavaProjects/Chess/Images/Pieces/BlackPieces/BlackPawn.png");
    ImageIcon blackBishop = new ImageIcon("C:/Users/jstef/Desktop/Projetos Programação/JavaProjects/Chess/Images/Pieces/BlackPieces/BlackBishop.png");
    ImageIcon blackTower = new ImageIcon("C:/Users/jstef/Desktop/Projetos Programação/JavaProjects/Chess/Images/Pieces/BlackPieces/BlackTower.png");
    ImageIcon blackHorse = new ImageIcon("C:/Users/jstef/Desktop/Projetos Programação/JavaProjects/Chess/Images/Pieces/BlackPieces/BlackHorse.png");
    ImageIcon blackQueen = new ImageIcon("C:/Users/jstef/Desktop/Projetos Programação/JavaProjects/Chess/Images/Pieces/BlackPieces/BlackQueen.png");
    ImageIcon blackKing = new ImageIcon("C:/Users/jstef/Desktop/Projetos Programação/JavaProjects/Chess/Images/Pieces/BlackPieces/BlackKing.png");

    // Images of the white pieces;
    ImageIcon whitePawn = new ImageIcon("C:/Users/jstef/Desktop/Projetos Programação/JavaProjects/Chess/Images/Pieces/WhitePieces/WhitePawn.png");
    ImageIcon whiteBishop = new ImageIcon("C:/Users/jstef/Desktop/Projetos Programação/JavaProjects/Chess/Images/Pieces/WhitePieces/WhiteBishop.png");
    ImageIcon whiteTower = new ImageIcon("C:/Users/jstef/Desktop/Projetos Programação/JavaProjects/Chess/Images/Pieces/WhitePieces/WhiteTower.png");
    ImageIcon whiteHorse = new ImageIcon("C:/Users/jstef/Desktop/Projetos Programação/JavaProjects/Chess/Images/Pieces/WhitePieces/WhiteHorse.png");
    ImageIcon whiteQueen = new ImageIcon("C:/Users/jstef/Desktop/Projetos Programação/JavaProjects/Chess/Images/Pieces/WhitePieces/WhiteQueen.png");
    ImageIcon whiteKing = new ImageIcon("C:/Users/jstef/Desktop/Projetos Programação/JavaProjects/Chess/Images/Pieces/WhitePieces/WhiteKing.png");

    // Constants to use in the program, that are numbers in pixels;
    private static final int SIZEBOARD = 768;
    private static final int GRIDSIZE = 96;
    private static final int INITIALPIECEWIDTH = 392;
    private static final int INITIALPICEHEIGTH = 60;

    // The method to create the board;
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
        graphics.fillRect((getWidth() - SIZEBOARD)/2, (getHeight() - SIZEBOARD)/2, SIZEBOARD, SIZEBOARD);
      
        // Select parts of the board and painting with white;
        graphics.setColor(Color.WHITE); 
        int numCols = getWidth() / GRIDSIZE;
        for (int row = 0; row < 8; row++) {
                
            if (row % 2 == 0) {
                for (int col = 0; col < numCols/2; col  += 2) {
                    
                    int x = (getWidth() - SIZEBOARD) / 2 + col * GRIDSIZE;
                    int y = (getHeight() - SIZEBOARD) / 2 + row * GRIDSIZE;
        
                    graphics.fillRect(x, y, GRIDSIZE, GRIDSIZE);
                }
            }
            else {
                for (int col = 1; col < numCols/2; col += 2) {
                    
                    int x = (getWidth() - SIZEBOARD) / 2 + col * GRIDSIZE;
                    int y = (getHeight() - SIZEBOARD) / 2 + row * GRIDSIZE;
        
                    graphics.fillRect(x, y, GRIDSIZE, GRIDSIZE);
                }
            }
        }

        // Drawing the black pieces on the board;
        // The Towers:
        graphics.drawImage(blackTower.getImage(), INITIALPIECEWIDTH, INITIALPICEHEIGTH, this);
        graphics.drawImage(blackTower.getImage(), INITIALPIECEWIDTH + 7 * GRIDSIZE, INITIALPICEHEIGTH, this);
        // The Horses:
        graphics.drawImage(blackHorse.getImage(), INITIALPIECEWIDTH + GRIDSIZE, INITIALPICEHEIGTH, this);
        graphics.drawImage(blackHorse.getImage(), INITIALPIECEWIDTH + 6 * GRIDSIZE, INITIALPICEHEIGTH, this);
        // The Bishops:
        graphics.drawImage(blackBishop.getImage(), INITIALPIECEWIDTH + 2 * GRIDSIZE, INITIALPICEHEIGTH, this);
        graphics.drawImage(blackBishop.getImage(), INITIALPIECEWIDTH + 5 * GRIDSIZE, INITIALPICEHEIGTH, this);
        // The Queen and King:
        graphics.drawImage(blackQueen.getImage(), INITIALPIECEWIDTH + 3 * GRIDSIZE, INITIALPICEHEIGTH, this);
        graphics.drawImage(blackKing.getImage(), INITIALPIECEWIDTH + 4 * GRIDSIZE, INITIALPICEHEIGTH, this);
        // The Pawns:
        graphics.drawImage(blackPawn.getImage(), INITIALPIECEWIDTH, INITIALPICEHEIGTH + GRIDSIZE, this);
        graphics.drawImage(blackPawn.getImage(), INITIALPIECEWIDTH + GRIDSIZE, INITIALPICEHEIGTH + GRIDSIZE, this);
        graphics.drawImage(blackPawn.getImage(), INITIALPIECEWIDTH + 2 * GRIDSIZE, INITIALPICEHEIGTH + GRIDSIZE, this);
        graphics.drawImage(blackPawn.getImage(), INITIALPIECEWIDTH + 3 * GRIDSIZE, INITIALPICEHEIGTH + GRIDSIZE, this);
        graphics.drawImage(blackPawn.getImage(), INITIALPIECEWIDTH + 4 * GRIDSIZE, INITIALPICEHEIGTH + GRIDSIZE, this);
        graphics.drawImage(blackPawn.getImage(), INITIALPIECEWIDTH + 5 * GRIDSIZE, INITIALPICEHEIGTH + GRIDSIZE, this);
        graphics.drawImage(blackPawn.getImage(), INITIALPIECEWIDTH + 6 * GRIDSIZE, INITIALPICEHEIGTH + GRIDSIZE, this);
        graphics.drawImage(blackPawn.getImage(), INITIALPIECEWIDTH + 7 * GRIDSIZE, INITIALPICEHEIGTH + GRIDSIZE, this);

        // Drawing the white pieces on the board;
        // The Towers:
        graphics.drawImage(whiteTower.getImage(), INITIALPIECEWIDTH, INITIALPICEHEIGTH + 7 * GRIDSIZE, this);
        graphics.drawImage(whiteTower.getImage(), INITIALPIECEWIDTH + 7 * GRIDSIZE, INITIALPICEHEIGTH + 7 * GRIDSIZE, this);
        // The Horses:
        graphics.drawImage(whiteHorse.getImage(), INITIALPIECEWIDTH + GRIDSIZE, INITIALPICEHEIGTH + 7 * GRIDSIZE, this);
        graphics.drawImage(whiteHorse.getImage(), INITIALPIECEWIDTH + 6 * GRIDSIZE, INITIALPICEHEIGTH + 7 * GRIDSIZE, this);
        // The Bishops:
        graphics.drawImage(whiteBishop.getImage(), INITIALPIECEWIDTH + 2 * GRIDSIZE, INITIALPICEHEIGTH + 7 * GRIDSIZE, this);
        graphics.drawImage(whiteBishop.getImage(), INITIALPIECEWIDTH + 5 *  GRIDSIZE, INITIALPICEHEIGTH + 7 * GRIDSIZE, this);
        // The Queen and King:
        graphics.drawImage(whiteQueen.getImage(), INITIALPIECEWIDTH + 3 * GRIDSIZE, INITIALPICEHEIGTH + 7 * GRIDSIZE, this);
        graphics.drawImage(whiteKing.getImage(), INITIALPIECEWIDTH + 4 * GRIDSIZE, INITIALPICEHEIGTH + 7 * GRIDSIZE, this);
        // The Pawns:
        graphics.drawImage(whitePawn.getImage(), INITIALPIECEWIDTH , INITIALPICEHEIGTH + 6 * GRIDSIZE, this);
        graphics.drawImage(whitePawn.getImage(), INITIALPIECEWIDTH + GRIDSIZE, INITIALPICEHEIGTH + 6 * GRIDSIZE, this);
        graphics.drawImage(whitePawn.getImage(), INITIALPIECEWIDTH + 2 * GRIDSIZE, INITIALPICEHEIGTH + 6 * GRIDSIZE, this);
        graphics.drawImage(whitePawn.getImage(), INITIALPIECEWIDTH + 3 * GRIDSIZE, INITIALPICEHEIGTH + 6 * GRIDSIZE, this);
        graphics.drawImage(whitePawn.getImage(), INITIALPIECEWIDTH + 4 * GRIDSIZE, INITIALPICEHEIGTH + 6 * GRIDSIZE, this);
        graphics.drawImage(whitePawn.getImage(), INITIALPIECEWIDTH + 5 * GRIDSIZE, INITIALPICEHEIGTH + 6 * GRIDSIZE, this);
        graphics.drawImage(whitePawn.getImage(), INITIALPIECEWIDTH + 6 * GRIDSIZE, INITIALPICEHEIGTH + 6 * GRIDSIZE, this);
        graphics.drawImage(whitePawn.getImage(), INITIALPIECEWIDTH + 7 * GRIDSIZE, INITIALPICEHEIGTH + 6 * GRIDSIZE, this);
    }
}
