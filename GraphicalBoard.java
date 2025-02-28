import java.awt.*;
import javax.swing.*;

public class GraphicalBoard extends JFrame{
    
    // Constants to use in the program, that are numbers in pixels;
    private static final int SIZE_BOARD = 768;
    private static final int GRID_SIZE = 96;
    private static final int INITIAL_PIECE_WIDTH = 392;
    private static final int INITIAL_PIECE_HEIGHT = 60;
    private static final int INITIAL_BUTTON_WIDTH = INITIAL_PIECE_WIDTH + 1;
    private static final int INITIAL_BUTTON_HEIGHT = INITIAL_PIECE_HEIGHT - 3;

    // Instanciate the black pieces:
    // The King and Queen;
    public static King blackKing = new King(false, INITIAL_PIECE_WIDTH + 4 * GRID_SIZE, INITIAL_PIECE_HEIGHT);
    public static Queen blackQueen = new Queen(false, INITIAL_PIECE_WIDTH + 3 * GRID_SIZE, INITIAL_PIECE_HEIGHT);
    // The Bishops;
    public static Bishop blackBishop1 = new Bishop(false, INITIAL_PIECE_WIDTH + 2 * GRID_SIZE, INITIAL_PIECE_HEIGHT);
    public static Bishop blackBishop2 = new Bishop(false, INITIAL_PIECE_WIDTH + 5 * GRID_SIZE, INITIAL_PIECE_HEIGHT);
    // The Horses;
    public static Horse blackHorse1 = new Horse(false, INITIAL_PIECE_WIDTH + GRID_SIZE, INITIAL_PIECE_HEIGHT);
    public static Horse blackHorse2 = new Horse(false, INITIAL_PIECE_WIDTH + 6 * GRID_SIZE, INITIAL_PIECE_HEIGHT);
    // The Towers;
    public static Tower blackTower1 = new Tower(false, INITIAL_PIECE_WIDTH, INITIAL_PIECE_HEIGHT);
    public static Tower blackTower2 = new Tower(false, INITIAL_PIECE_WIDTH + 7 * GRID_SIZE, INITIAL_PIECE_HEIGHT);
    // The Pawns:
    public static Pawn blackPawn1 = new Pawn(false, INITIAL_PIECE_WIDTH, INITIAL_PIECE_HEIGHT + GRID_SIZE);
    public static Pawn blackPawn2 = new Pawn(false, INITIAL_PIECE_WIDTH + GRID_SIZE, INITIAL_PIECE_HEIGHT + GRID_SIZE);
    public static Pawn blackPawn3 = new Pawn(false, INITIAL_PIECE_WIDTH + 2 * GRID_SIZE, INITIAL_PIECE_HEIGHT + GRID_SIZE);
    public static Pawn blackPawn4 = new Pawn(false, INITIAL_PIECE_WIDTH + 3 * GRID_SIZE, INITIAL_PIECE_HEIGHT + GRID_SIZE);
    public static Pawn blackPawn5 = new Pawn(false, INITIAL_PIECE_WIDTH + 4 * GRID_SIZE, INITIAL_PIECE_HEIGHT + GRID_SIZE);
    public static Pawn blackPawn6 = new Pawn(false, INITIAL_PIECE_WIDTH + 5 * GRID_SIZE, INITIAL_PIECE_HEIGHT + GRID_SIZE);
    public static Pawn blackPawn7 = new Pawn(false, INITIAL_PIECE_WIDTH + 6 * GRID_SIZE, INITIAL_PIECE_HEIGHT + GRID_SIZE);
    public static Pawn blackPawn8 = new Pawn(false, INITIAL_PIECE_WIDTH + 7 * GRID_SIZE, INITIAL_PIECE_HEIGHT + GRID_SIZE);

    // Instanciate the white pieces:
    // The King and Queen;
    public static King whiteKing = new King(true, INITIAL_PIECE_WIDTH + 4 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 7 * GRID_SIZE);
    public static Queen whiteQueen = new Queen(true, INITIAL_PIECE_WIDTH + 3 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 7 * GRID_SIZE);
    // The Bishops;
    public static Bishop whiteBishop1 = new Bishop(true, INITIAL_PIECE_WIDTH + 2 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 7 * GRID_SIZE);
    public static Bishop whiteBishop2 = new Bishop(true, INITIAL_PIECE_WIDTH + 5 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 7 * GRID_SIZE);
    // The Horses;
    public static Horse whiteHorse1 = new Horse(true, INITIAL_PIECE_WIDTH + GRID_SIZE, INITIAL_PIECE_HEIGHT + 7 * GRID_SIZE);
    public static Horse whiteHorse2 = new Horse(true, INITIAL_PIECE_WIDTH + 6 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 7 * GRID_SIZE);
    // The Towers;
    public static Tower whiteTower1 = new Tower(true, INITIAL_PIECE_WIDTH, INITIAL_PIECE_HEIGHT + 7 * GRID_SIZE);
    public static Tower whiteTower2 = new Tower(true, INITIAL_PIECE_WIDTH + 7 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 7 * GRID_SIZE);
    // The Pawns;
    public static Pawn whitePawn1 = new Pawn(true, INITIAL_PIECE_WIDTH, INITIAL_PIECE_HEIGHT + 6 * GRID_SIZE);
    public static Pawn whitePawn2 = new Pawn(true, INITIAL_PIECE_WIDTH + GRID_SIZE, INITIAL_PIECE_HEIGHT + 6 * GRID_SIZE);
    public static Pawn whitePawn3 = new Pawn(true, INITIAL_PIECE_WIDTH + 2 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 6 * GRID_SIZE);
    public static Pawn whitePawn4 = new Pawn(true, INITIAL_PIECE_WIDTH + 3 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 6 * GRID_SIZE);
    public static Pawn whitePawn5 = new Pawn(true, INITIAL_PIECE_WIDTH + 4 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 6 * GRID_SIZE);
    public static Pawn whitePawn6 = new Pawn(true, INITIAL_PIECE_WIDTH + 5 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 6 * GRID_SIZE);
    public static Pawn whitePawn7 = new Pawn(true, INITIAL_PIECE_WIDTH + 6 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 6 * GRID_SIZE);
    public static Pawn whitePawn8 = new Pawn(true, INITIAL_PIECE_WIDTH + 7 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 6 * GRID_SIZE); 

    // Instanciate the buttons;
    // Line 1;
    public SuperButton button1x1 = new SuperButton(INITIAL_BUTTON_WIDTH, INITIAL_BUTTON_HEIGHT);
    public SuperButton button1x2 = new SuperButton(INITIAL_BUTTON_WIDTH + GRID_SIZE, INITIAL_BUTTON_HEIGHT);
    public SuperButton button1x3 = new SuperButton(INITIAL_BUTTON_WIDTH + 2 * GRID_SIZE, INITIAL_BUTTON_HEIGHT);
    public SuperButton button1x4 = new SuperButton(INITIAL_BUTTON_WIDTH + 3 * GRID_SIZE, INITIAL_BUTTON_HEIGHT);
    public SuperButton button1x5 = new SuperButton(INITIAL_BUTTON_WIDTH + 4 * GRID_SIZE, INITIAL_BUTTON_HEIGHT);
    public SuperButton button1x6 = new SuperButton(INITIAL_BUTTON_WIDTH + 5 * GRID_SIZE, INITIAL_BUTTON_HEIGHT);
    public SuperButton button1x7 = new SuperButton(INITIAL_BUTTON_WIDTH + 6 * GRID_SIZE, INITIAL_BUTTON_HEIGHT);
    public SuperButton button1x8 = new SuperButton(INITIAL_BUTTON_WIDTH + 7 * GRID_SIZE, INITIAL_BUTTON_HEIGHT);
    // Line 2;
    public SuperButton button2x1 = new SuperButton(INITIAL_BUTTON_WIDTH, INITIAL_BUTTON_HEIGHT + GRID_SIZE);
    public SuperButton button2x2 = new SuperButton(INITIAL_BUTTON_WIDTH + GRID_SIZE, INITIAL_BUTTON_HEIGHT + GRID_SIZE);
    public SuperButton button2x3 = new SuperButton(INITIAL_BUTTON_WIDTH + 2 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + GRID_SIZE);
    public SuperButton button2x4 = new SuperButton(INITIAL_BUTTON_WIDTH + 3 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + GRID_SIZE);
    public SuperButton button2x5 = new SuperButton(INITIAL_BUTTON_WIDTH + 4 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + GRID_SIZE);
    public SuperButton button2x6 = new SuperButton(INITIAL_BUTTON_WIDTH + 5 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + GRID_SIZE);
    public SuperButton button2x7 = new SuperButton(INITIAL_BUTTON_WIDTH + 6 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + GRID_SIZE);
    public SuperButton button2x8 = new SuperButton(INITIAL_BUTTON_WIDTH + 7 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + GRID_SIZE);
    // Line 3;
    public SuperButton button3x1 = new SuperButton(INITIAL_BUTTON_WIDTH, INITIAL_BUTTON_HEIGHT + 2 * GRID_SIZE);
    public SuperButton button3x2 = new SuperButton(INITIAL_BUTTON_WIDTH + GRID_SIZE, INITIAL_BUTTON_HEIGHT + 2 * GRID_SIZE);
    public SuperButton button3x3 = new SuperButton(INITIAL_BUTTON_WIDTH + 2 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + 2 * GRID_SIZE);
    public SuperButton button3x4 = new SuperButton(INITIAL_BUTTON_WIDTH + 3 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + 2 * GRID_SIZE);
    public SuperButton button3x5 = new SuperButton(INITIAL_BUTTON_WIDTH + 4 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + 2 * GRID_SIZE);
    public SuperButton button3x6 = new SuperButton(INITIAL_BUTTON_WIDTH + 5 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + 2 * GRID_SIZE);
    public SuperButton button3x7 = new SuperButton(INITIAL_BUTTON_WIDTH + 6 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + 2 * GRID_SIZE);
    public SuperButton button3x8 = new SuperButton(INITIAL_BUTTON_WIDTH + 7 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + 2 * GRID_SIZE);
    // Line 4;
    public SuperButton button4x1 = new SuperButton(INITIAL_BUTTON_WIDTH, INITIAL_BUTTON_HEIGHT + 3 * GRID_SIZE);
    public SuperButton button4x2 = new SuperButton(INITIAL_BUTTON_WIDTH + GRID_SIZE, INITIAL_BUTTON_HEIGHT + 3 * GRID_SIZE);
    public SuperButton button4x3 = new SuperButton(INITIAL_BUTTON_WIDTH + 2 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + 3 * GRID_SIZE);
    public SuperButton button4x4 = new SuperButton(INITIAL_BUTTON_WIDTH + 3 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + 3 * GRID_SIZE);
    public SuperButton button4x5 = new SuperButton(INITIAL_BUTTON_WIDTH + 4 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + 3 * GRID_SIZE);
    public SuperButton button4x6 = new SuperButton(INITIAL_BUTTON_WIDTH + 5 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + 3 *GRID_SIZE);
    public SuperButton button4x7 = new SuperButton(INITIAL_BUTTON_WIDTH + 6 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + 3 * GRID_SIZE);
    public SuperButton button4x8 = new SuperButton(INITIAL_BUTTON_WIDTH + 7 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + 3 * GRID_SIZE);
    // Line 5
    public SuperButton button5x1 = new SuperButton(INITIAL_BUTTON_WIDTH, INITIAL_BUTTON_HEIGHT + 4 * GRID_SIZE);
    public SuperButton button5x2 = new SuperButton(INITIAL_BUTTON_WIDTH + GRID_SIZE, INITIAL_BUTTON_HEIGHT + 4 * GRID_SIZE);
    public SuperButton button5x3 = new SuperButton(INITIAL_BUTTON_WIDTH + 2 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + 4 * GRID_SIZE);
    public SuperButton button5x4 = new SuperButton(INITIAL_BUTTON_WIDTH + 3 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + 4 * GRID_SIZE);
    public SuperButton button5x5 = new SuperButton(INITIAL_BUTTON_WIDTH + 4 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + 4 * GRID_SIZE);
    public SuperButton button5x6 = new SuperButton(INITIAL_BUTTON_WIDTH + 5 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + 4 * GRID_SIZE);
    public SuperButton button5x7 = new SuperButton(INITIAL_BUTTON_WIDTH + 6 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + 4 * GRID_SIZE);
    public SuperButton button5x8 = new SuperButton(INITIAL_BUTTON_WIDTH + 7 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + 4 * GRID_SIZE);
    // Line 6
    public SuperButton button6x1 = new SuperButton(INITIAL_BUTTON_WIDTH, INITIAL_BUTTON_HEIGHT + 5 * GRID_SIZE);
    public SuperButton button6x2 = new SuperButton(INITIAL_BUTTON_WIDTH + GRID_SIZE, INITIAL_BUTTON_HEIGHT + 5 * GRID_SIZE);
    public SuperButton button6x3 = new SuperButton(INITIAL_BUTTON_WIDTH + 2 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + 5 * GRID_SIZE);
    public SuperButton button6x4 = new SuperButton(INITIAL_BUTTON_WIDTH + 3 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + 5 * GRID_SIZE);
    public SuperButton button6x5 = new SuperButton(INITIAL_BUTTON_WIDTH + 4 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + 5 * GRID_SIZE);
    public SuperButton button6x6 = new SuperButton(INITIAL_BUTTON_WIDTH + 5 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + 5 * GRID_SIZE);
    public SuperButton button6x7 = new SuperButton(INITIAL_BUTTON_WIDTH + 6 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + 5 * GRID_SIZE);
    public SuperButton button6x8 = new SuperButton(INITIAL_BUTTON_WIDTH + 7 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + 5 * GRID_SIZE);
    // Line 7
    public SuperButton button7x1 = new SuperButton(INITIAL_BUTTON_WIDTH, INITIAL_BUTTON_HEIGHT + 6 * GRID_SIZE);
    public SuperButton button7x2 = new SuperButton(INITIAL_BUTTON_WIDTH + GRID_SIZE, INITIAL_BUTTON_HEIGHT + 6 * GRID_SIZE);
    public SuperButton button7x3 = new SuperButton(INITIAL_BUTTON_WIDTH + 2 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + 6 * GRID_SIZE);
    public SuperButton button7x4 = new SuperButton(INITIAL_BUTTON_WIDTH + 3 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + 6 * GRID_SIZE);
    public SuperButton button7x5 = new SuperButton(INITIAL_BUTTON_WIDTH + 4 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + 6 * GRID_SIZE);
    public SuperButton button7x6 = new SuperButton(INITIAL_BUTTON_WIDTH + 5 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + 6 * GRID_SIZE);
    public SuperButton button7x7 = new SuperButton(INITIAL_BUTTON_WIDTH + 6 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + 6 * GRID_SIZE);
    public SuperButton button7x8 = new SuperButton(INITIAL_BUTTON_WIDTH + 7 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + 6 * GRID_SIZE);
    // Line 8;
    public SuperButton button8x1 = new SuperButton(INITIAL_BUTTON_WIDTH, INITIAL_BUTTON_HEIGHT + 7 * GRID_SIZE);
    public SuperButton button8x2 = new SuperButton(INITIAL_BUTTON_WIDTH + GRID_SIZE, INITIAL_BUTTON_HEIGHT + 7 * GRID_SIZE);
    public SuperButton button8x3 = new SuperButton(INITIAL_BUTTON_WIDTH + 2 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + 7 * GRID_SIZE);
    public SuperButton button8x4 = new SuperButton(INITIAL_BUTTON_WIDTH + 3 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + 7 * GRID_SIZE);
    public SuperButton button8x5 = new SuperButton(INITIAL_BUTTON_WIDTH + 4 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + 7 * GRID_SIZE);
    public SuperButton button8x6 = new SuperButton(INITIAL_BUTTON_WIDTH + 5 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + 7 * GRID_SIZE);
    public SuperButton button8x7 = new SuperButton(INITIAL_BUTTON_WIDTH + 6 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + 7 * GRID_SIZE);
    public SuperButton button8x8 = new SuperButton(INITIAL_BUTTON_WIDTH + 7 * GRID_SIZE, INITIAL_BUTTON_HEIGHT + 7 * GRID_SIZE);

    // The matriz of all the buttons of the Window;
    private SuperButton[][] buttonsMatrix = {{button1x1,button1x2,button1x3,button1x4,button1x5,button1x6,button1x7,button1x8},
                                            {button2x1,button2x2,button2x3,button2x4,button2x5,button2x6,button2x7,button2x8},
                                            {button3x1,button3x2,button3x3,button3x4,button3x5,button3x6,button3x7,button3x8},
                                            {button4x1,button4x2,button4x3,button4x4,button4x5,button4x6,button4x7,button4x8},
                                            {button5x1,button5x2,button5x3,button5x4,button5x5,button5x6,button5x7,button5x8},
                                            {button6x1,button6x2,button6x3,button6x4,button6x5,button6x6,button6x7,button6x8},
                                            {button7x1,button7x2,button7x3,button7x4,button7x5,button7x6,button7x7,button7x8},
                                            {button8x1,button8x2,button8x3,button8x4,button8x5,button8x6,button8x7,button8x8}};

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

        // Adding the buttons on the Window;
        for (SuperButton[] arrayLine : buttonsMatrix) {
            for (SuperButton button : arrayLine) {
                add(button);
                setComponentZOrder(button, 1);
            }
        }
        SuperButton.setButtonsMatrix(buttonsMatrix);

        // Putting a new background to the Window;
        ImageIcon backgroundImage = new ImageIcon("C:/Users/jstef/Desktop/Projetos Programação/JavaProjects/Chess/Images/Background.jpg");
        JLabel background = new JLabel("", backgroundImage, JLabel.CENTER);
        background.setBounds(0, 0, 1700, 1133);
        add(background);
        setComponentZOrder(background, 0);

        // Set the Window visible;
        setVisible(true);
    }

    // Move the piece on the board;
    public void movePieceGUI(Pieces piece, int gridWidth, int gridHeight) throws Exception{
        
        // Set the height and the width of the piece;
        piece.addSize(gridWidth, gridHeight);
        
        // Repaint teh board
        repaint();
    }

    // Painting the board using Graphics;
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
            
        // Put the backgroud of the board in black;
        graphics.setColor(Color.BLACK);
        graphics.fillRect((getSize().width - SIZE_BOARD)/2, (getSize().height - SIZE_BOARD)/2, SIZE_BOARD, SIZE_BOARD);
      
        // Select parts of the board and painting with white;
        graphics.setColor(Color.WHITE); 
        int numCols = getSize().width / GRID_SIZE;
        for (int row = 0; row < 8; row++) {
                
            if (row % 2 == 0) {
                for (int col = 0; col < numCols/2; col  += 2) {
                    
                    int x = (getSize().width - SIZE_BOARD) / 2 + col * GRID_SIZE;
                    int y = (getSize().height - SIZE_BOARD) / 2 + row * GRID_SIZE;
        
                    graphics.fillRect(x, y, GRID_SIZE, GRID_SIZE);
                }
            }
            else {
                for (int col = 1; col < numCols/2; col += 2) {
                    
                    int x = (getSize().width - SIZE_BOARD) / 2 + col * GRID_SIZE;
                    int y = (getSize().height - SIZE_BOARD) / 2 + row * GRID_SIZE;
        
                    graphics.fillRect(x, y, GRID_SIZE, GRID_SIZE);
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