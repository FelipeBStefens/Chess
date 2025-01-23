import java.awt.*;
import javax.swing.*;

public class GraphicalBoard extends JFrame{
    
    // Constants to use in the program, that are numbers in pixels;
    private static final int SIZEBOARD = 768;
    private static final int GRIDSIZE = 96;
    private static final int INITIALPIECEWIDTH = 392;
    private static final int INITIALPICEHEIGTH = 60;

    // Instanciate the black pieces:
    // The King and Queen;
    public King blackKing = new King(false, INITIALPIECEWIDTH + 4 * GRIDSIZE, INITIALPICEHEIGTH);
    public Queen blackQueen = new Queen(false, INITIALPIECEWIDTH + 3 * GRIDSIZE, INITIALPICEHEIGTH);
    // The Bishops;
    public Bishop blackBishop1 = new Bishop(false, INITIALPIECEWIDTH + 2 * GRIDSIZE, INITIALPICEHEIGTH);
    public Bishop blackBishop2 = new Bishop(false, INITIALPIECEWIDTH + 5 * GRIDSIZE, INITIALPICEHEIGTH);
    // The Horses;
    public Horse blackHorse1 = new Horse(false, INITIALPIECEWIDTH + GRIDSIZE, INITIALPICEHEIGTH);
    public Horse blackHorse2 = new Horse(false, INITIALPIECEWIDTH + 6 * GRIDSIZE, INITIALPICEHEIGTH);
    // The Towers;
    public Tower blackTower1 = new Tower(false, INITIALPIECEWIDTH, INITIALPICEHEIGTH);
    public Tower blackTower2 = new Tower(false, INITIALPIECEWIDTH + 7 * GRIDSIZE, INITIALPICEHEIGTH);
    // The Pawns:
    public Pawn blackPawn1 = new Pawn(false, INITIALPIECEWIDTH, INITIALPICEHEIGTH + GRIDSIZE);
    public Pawn blackPawn2 = new Pawn(false, INITIALPIECEWIDTH + GRIDSIZE, INITIALPICEHEIGTH + GRIDSIZE);
    public Pawn blackPawn3 = new Pawn(false, INITIALPIECEWIDTH + 2 * GRIDSIZE, INITIALPICEHEIGTH + GRIDSIZE);
    public Pawn blackPawn4 = new Pawn(false, INITIALPIECEWIDTH + 3 * GRIDSIZE, INITIALPICEHEIGTH + GRIDSIZE);
    public Pawn blackPawn5 = new Pawn(false, INITIALPIECEWIDTH + 4 * GRIDSIZE, INITIALPICEHEIGTH + GRIDSIZE);
    public Pawn blackPawn6 = new Pawn(false, INITIALPIECEWIDTH + 5 * GRIDSIZE, INITIALPICEHEIGTH + GRIDSIZE);
    public Pawn blackPawn7 = new Pawn(false, INITIALPIECEWIDTH + 6 * GRIDSIZE, INITIALPICEHEIGTH + GRIDSIZE);
    public Pawn blackPawn8 = new Pawn(false, INITIALPIECEWIDTH + 7 * GRIDSIZE, INITIALPICEHEIGTH + GRIDSIZE);

    // Instanciate the white pieces:
    // The King and Queen;
    public King whiteKing = new King(true, INITIALPIECEWIDTH + 4 * GRIDSIZE, INITIALPICEHEIGTH + 7 * GRIDSIZE);
    public Queen whiteQueen = new Queen(true, INITIALPIECEWIDTH + 3 * GRIDSIZE, INITIALPICEHEIGTH + 7 * GRIDSIZE);
    // The Bishops;
    public Bishop whiteBishop1 = new Bishop(true, INITIALPIECEWIDTH + 2 * GRIDSIZE, INITIALPICEHEIGTH + 7 * GRIDSIZE);
    public Bishop whiteBishop2 = new Bishop(true, INITIALPIECEWIDTH + 5 * GRIDSIZE, INITIALPICEHEIGTH + 7 * GRIDSIZE);
    // The Horses;
    public Horse whiteHorse1 = new Horse(true, INITIALPIECEWIDTH + GRIDSIZE, INITIALPICEHEIGTH + 7 * GRIDSIZE);
    public Horse whiteHorse2 = new Horse(true, INITIALPIECEWIDTH + 6 * GRIDSIZE, INITIALPICEHEIGTH + 7 * GRIDSIZE);
    // The Towers;
    public Tower whiteTower1 = new Tower(true, INITIALPIECEWIDTH, INITIALPICEHEIGTH + 7 * GRIDSIZE);
    public Tower whiteTower2 = new Tower(true, INITIALPIECEWIDTH + 7 * GRIDSIZE, INITIALPICEHEIGTH + 7 * GRIDSIZE);
    // The Pawns;
    public Pawn whitePawn1 = new Pawn(true, INITIALPIECEWIDTH, INITIALPICEHEIGTH + 6 * GRIDSIZE);
    public Pawn whitePawn2 = new Pawn(true, INITIALPIECEWIDTH + GRIDSIZE, INITIALPICEHEIGTH + 6 * GRIDSIZE);
    public Pawn whitePawn3 = new Pawn(true, INITIALPIECEWIDTH + 2 * GRIDSIZE, INITIALPICEHEIGTH + 6 * GRIDSIZE);
    public Pawn whitePawn4 = new Pawn(true, INITIALPIECEWIDTH + 3 * GRIDSIZE, INITIALPICEHEIGTH + 6 * GRIDSIZE);
    public Pawn whitePawn5 = new Pawn(true, INITIALPIECEWIDTH + 4 * GRIDSIZE, INITIALPICEHEIGTH + 6 * GRIDSIZE);
    public Pawn whitePawn6 = new Pawn(true, INITIALPIECEWIDTH + 5 * GRIDSIZE, INITIALPICEHEIGTH + 6 * GRIDSIZE);
    public Pawn whitePawn7 = new Pawn(true, INITIALPIECEWIDTH + 6 * GRIDSIZE, INITIALPICEHEIGTH + 6 * GRIDSIZE);
    public Pawn whitePawn8 = new Pawn(true, INITIALPIECEWIDTH + 7 * GRIDSIZE, INITIALPICEHEIGTH + 6 * GRIDSIZE);

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

    // Move the piece on the board;
    public void movePieceGUI(Pieces piece, int gridWidth, int girdHeight) throws Exception{
        
        // Set the height and the width of the piece;
        piece.addSize(gridWidth, girdHeight);
        
        // Repaint teh board
        repaint();
    }

    // Painting the board using Graphics;
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
            
        // Put the backgroud of the board in black;
        graphics.setColor(Color.BLACK);
        graphics.fillRect((getSize().width - SIZEBOARD)/2, (getSize().height - SIZEBOARD)/2, SIZEBOARD, SIZEBOARD);
      
        // Select parts of the board and painting with white;
        graphics.setColor(Color.WHITE); 
        int numCols = getSize().width / GRIDSIZE;
        for (int row = 0; row < 8; row++) {
                
            if (row % 2 == 0) {
                for (int col = 0; col < numCols/2; col  += 2) {
                    
                    int x = (getSize().width - SIZEBOARD) / 2 + col * GRIDSIZE;
                    int y = (getSize().height - SIZEBOARD) / 2 + row * GRIDSIZE;
        
                    graphics.fillRect(x, y, GRIDSIZE, GRIDSIZE);
                }
            }
            else {
                for (int col = 1; col < numCols/2; col += 2) {
                    
                    int x = (getSize().width - SIZEBOARD) / 2 + col * GRIDSIZE;
                    int y = (getSize().height - SIZEBOARD) / 2 + row * GRIDSIZE;
        
                    graphics.fillRect(x, y, GRIDSIZE, GRIDSIZE);
                }
            }
        }

        // Drawing the black pieces:
        // The King and Queen;
        graphics.drawImage(blackKing.getImage(), blackKing.getPieceWidth(), blackKing.getPieceHeight(), this);
        graphics.drawImage(blackQueen.getImage(), blackQueen.getPieceWidth(), blackQueen.getPieceHeight(), this);
        // The Bishops;
        graphics.drawImage(blackBishop1.getImage(), blackBishop1.getPieceWidth(), blackBishop1.getPieceHeight(), this);
        graphics.drawImage(blackBishop2.getImage(), blackBishop2.getPieceWidth(), blackBishop2.getPieceHeight(), this);
        // The Horses;
        graphics.drawImage(blackHorse1.getImage(), blackHorse1.getPieceWidth(), blackHorse1.getPieceHeight(), this);
        graphics.drawImage(blackHorse2.getImage(), blackHorse2.getPieceWidth(), blackHorse2.getPieceHeight(), this);
        // The Towers;
        graphics.drawImage(blackTower1.getImage(), blackTower1.getPieceWidth(), blackTower1.getPieceHeight(), this);
        graphics.drawImage(blackTower2.getImage(), blackTower2.getPieceWidth(), blackTower2.getPieceHeight(), this);
        // The Pawns;
        graphics.drawImage(blackPawn1.getImage(), blackPawn1.getPieceWidth(), blackPawn1.getPieceHeight(), this);
        graphics.drawImage(blackPawn2.getImage(), blackPawn2.getPieceWidth(), blackPawn2.getPieceHeight(), this);
        graphics.drawImage(blackPawn3.getImage(), blackPawn3.getPieceWidth(), blackPawn3.getPieceHeight(), this);
        graphics.drawImage(blackPawn4.getImage(), blackPawn4.getPieceWidth(), blackPawn4.getPieceHeight(), this);
        graphics.drawImage(blackPawn5.getImage(), blackPawn5.getPieceWidth(), blackPawn5.getPieceHeight(), this);
        graphics.drawImage(blackPawn6.getImage(), blackPawn6.getPieceWidth(), blackPawn6.getPieceHeight(), this);
        graphics.drawImage(blackPawn7.getImage(), blackPawn7.getPieceWidth(), blackPawn7.getPieceHeight(), this);
        graphics.drawImage(blackPawn8.getImage(), blackPawn8.getPieceWidth(), blackPawn8.getPieceHeight(), this);
        
        // Drawing the white pieces:
        // The King and Queen;
        graphics.drawImage(whiteKing.getImage(), whiteKing.getPieceWidth(), whiteKing.getPieceHeight(), this);
        graphics.drawImage(whiteQueen.getImage(), whiteQueen.getPieceWidth(), whiteQueen.getPieceHeight(), this);
        // The Bishops;
        graphics.drawImage(whiteBishop1.getImage(), whiteBishop1.getPieceWidth(), whiteBishop1.getPieceHeight(), this);
        graphics.drawImage(whiteBishop2.getImage(), whiteBishop2.getPieceWidth(), whiteBishop2.getPieceHeight(), this);
        // The Horses;
        graphics.drawImage(whiteHorse1.getImage(), whiteHorse1.getPieceWidth(), whiteHorse1.getPieceHeight(), this);
        graphics.drawImage(whiteHorse2.getImage(), whiteHorse2.getPieceWidth(), whiteHorse2.getPieceHeight(), this);
        // The Towers;
        graphics.drawImage(whiteTower1.getImage(), whiteTower1.getPieceWidth(), whiteTower1.getPieceHeight(), this);
        graphics.drawImage(whiteTower2.getImage(), whiteTower2.getPieceWidth(), whiteTower2.getPieceHeight(), this);
        // The Pawns;
        graphics.drawImage(whitePawn1.getImage(), whitePawn1.getPieceWidth(), whitePawn1.getPieceHeight(), this);
        graphics.drawImage(whitePawn2.getImage(), whitePawn2.getPieceWidth(), whitePawn2.getPieceHeight(), this);
        graphics.drawImage(whitePawn3.getImage(), whitePawn3.getPieceWidth(), whitePawn3.getPieceHeight(), this);
        graphics.drawImage(whitePawn4.getImage(), whitePawn4.getPieceWidth(), whitePawn4.getPieceHeight(), this);
        graphics.drawImage(whitePawn5.getImage(), whitePawn5.getPieceWidth(), whitePawn5.getPieceHeight(), this);
        graphics.drawImage(whitePawn6.getImage(), whitePawn6.getPieceWidth(), whitePawn6.getPieceHeight(), this);
        graphics.drawImage(whitePawn7.getImage(), whitePawn7.getPieceWidth(), whitePawn7.getPieceHeight(), this);
        graphics.drawImage(whitePawn8.getImage(), whitePawn8.getPieceWidth(), whitePawn8.getPieceHeight(), this);
    }
}
