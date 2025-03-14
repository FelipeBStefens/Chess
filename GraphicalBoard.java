package Chess;

import Chess.pieces.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;

public class GraphicalBoard extends JFrame{

    // Getting the height and width of the screen;
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static boolean endGame = false;

    // Constants to use in the program, that are numbers in pixels;
    public static final int SIZE_BOARD = (int) (0.9 * screenSize.height);
    private static final int GRID_SIZE = (int) (SIZE_BOARD/8);
    private static final int INITIAL_PIECE_WIDTH = (screenSize.width - SIZE_BOARD)/2;
    private static final int INITIAL_PIECE_HEIGHT = ((screenSize.height - SIZE_BOARD)/2);

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

    // The array of the black pieces;
    private Pieces[] blackPiecesArray = {blackKing, blackQueen, blackBishop1, blackBishop2, blackHorse1, blackHorse2,
                                        blackTower1, blackTower2, blackPawn1, blackPawn2, blackPawn3, blackPawn4,
                                        blackPawn5, blackPawn6, blackPawn7, blackPawn8};

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

    // The array of the white pieces;
    private Pieces[] whitePiecesArray = {whiteKing, whiteQueen, whiteBishop1, whiteBishop2, whiteHorse1, whiteHorse2, 
                                        whiteTower1, whiteTower2, whitePawn1, whitePawn2, whitePawn3, whitePawn4,
                                        whitePawn5, whitePawn6, whitePawn7, whitePawn8};

    // Instanciate the buttons;
    // Line 1;
    public SuperButton button1x1 = new SuperButton(INITIAL_PIECE_WIDTH, INITIAL_PIECE_HEIGHT);
    public SuperButton button1x2 = new SuperButton(INITIAL_PIECE_WIDTH + GRID_SIZE, INITIAL_PIECE_HEIGHT);
    public SuperButton button1x3 = new SuperButton(INITIAL_PIECE_WIDTH + 2 * GRID_SIZE, INITIAL_PIECE_HEIGHT);
    public SuperButton button1x4 = new SuperButton(INITIAL_PIECE_WIDTH + 3 * GRID_SIZE, INITIAL_PIECE_HEIGHT);
    public SuperButton button1x5 = new SuperButton(INITIAL_PIECE_WIDTH + 4 * GRID_SIZE, INITIAL_PIECE_HEIGHT);
    public SuperButton button1x6 = new SuperButton(INITIAL_PIECE_WIDTH + 5 * GRID_SIZE, INITIAL_PIECE_HEIGHT);
    public SuperButton button1x7 = new SuperButton(INITIAL_PIECE_WIDTH + 6 * GRID_SIZE, INITIAL_PIECE_HEIGHT);
    public SuperButton button1x8 = new SuperButton(INITIAL_PIECE_WIDTH + 7 * GRID_SIZE, INITIAL_PIECE_HEIGHT);
    // Line 2;
    public SuperButton button2x1 = new SuperButton(INITIAL_PIECE_WIDTH, INITIAL_PIECE_HEIGHT + GRID_SIZE);
    public SuperButton button2x2 = new SuperButton(INITIAL_PIECE_WIDTH + GRID_SIZE, INITIAL_PIECE_HEIGHT + GRID_SIZE);
    public SuperButton button2x3 = new SuperButton(INITIAL_PIECE_WIDTH + 2 * GRID_SIZE, INITIAL_PIECE_HEIGHT + GRID_SIZE);
    public SuperButton button2x4 = new SuperButton(INITIAL_PIECE_WIDTH + 3 * GRID_SIZE, INITIAL_PIECE_HEIGHT + GRID_SIZE);
    public SuperButton button2x5 = new SuperButton(INITIAL_PIECE_WIDTH + 4 * GRID_SIZE, INITIAL_PIECE_HEIGHT + GRID_SIZE);
    public SuperButton button2x6 = new SuperButton(INITIAL_PIECE_WIDTH + 5 * GRID_SIZE, INITIAL_PIECE_HEIGHT + GRID_SIZE);
    public SuperButton button2x7 = new SuperButton(INITIAL_PIECE_WIDTH + 6 * GRID_SIZE, INITIAL_PIECE_HEIGHT + GRID_SIZE);
    public SuperButton button2x8 = new SuperButton(INITIAL_PIECE_WIDTH + 7 * GRID_SIZE, INITIAL_PIECE_HEIGHT + GRID_SIZE);
    // Line 3;
    public SuperButton button3x1 = new SuperButton(INITIAL_PIECE_WIDTH, INITIAL_PIECE_HEIGHT + 2 * GRID_SIZE);
    public SuperButton button3x2 = new SuperButton(INITIAL_PIECE_WIDTH + GRID_SIZE, INITIAL_PIECE_HEIGHT + 2 * GRID_SIZE);
    public SuperButton button3x3 = new SuperButton(INITIAL_PIECE_WIDTH + 2 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 2 * GRID_SIZE);
    public SuperButton button3x4 = new SuperButton(INITIAL_PIECE_WIDTH + 3 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 2 * GRID_SIZE);
    public SuperButton button3x5 = new SuperButton(INITIAL_PIECE_WIDTH + 4 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 2 * GRID_SIZE);
    public SuperButton button3x6 = new SuperButton(INITIAL_PIECE_WIDTH + 5 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 2 * GRID_SIZE);
    public SuperButton button3x7 = new SuperButton(INITIAL_PIECE_WIDTH + 6 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 2 * GRID_SIZE);
    public SuperButton button3x8 = new SuperButton(INITIAL_PIECE_WIDTH + 7 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 2 * GRID_SIZE);
    // Line 4;
    public SuperButton button4x1 = new SuperButton(INITIAL_PIECE_WIDTH, INITIAL_PIECE_HEIGHT + 3 * GRID_SIZE);
    public SuperButton button4x2 = new SuperButton(INITIAL_PIECE_WIDTH + GRID_SIZE, INITIAL_PIECE_HEIGHT + 3 * GRID_SIZE);
    public SuperButton button4x3 = new SuperButton(INITIAL_PIECE_WIDTH + 2 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 3 * GRID_SIZE);
    public SuperButton button4x4 = new SuperButton(INITIAL_PIECE_WIDTH + 3 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 3 * GRID_SIZE);
    public SuperButton button4x5 = new SuperButton(INITIAL_PIECE_WIDTH + 4 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 3 * GRID_SIZE);
    public SuperButton button4x6 = new SuperButton(INITIAL_PIECE_WIDTH + 5 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 3 *GRID_SIZE);
    public SuperButton button4x7 = new SuperButton(INITIAL_PIECE_WIDTH + 6 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 3 * GRID_SIZE);
    public SuperButton button4x8 = new SuperButton(INITIAL_PIECE_WIDTH + 7 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 3 * GRID_SIZE);
    // Line 5
    public SuperButton button5x1 = new SuperButton(INITIAL_PIECE_WIDTH, INITIAL_PIECE_HEIGHT + 4 * GRID_SIZE);
    public SuperButton button5x2 = new SuperButton(INITIAL_PIECE_WIDTH + GRID_SIZE, INITIAL_PIECE_HEIGHT + 4 * GRID_SIZE);
    public SuperButton button5x3 = new SuperButton(INITIAL_PIECE_WIDTH + 2 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 4 * GRID_SIZE);
    public SuperButton button5x4 = new SuperButton(INITIAL_PIECE_WIDTH + 3 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 4 * GRID_SIZE);
    public SuperButton button5x5 = new SuperButton(INITIAL_PIECE_WIDTH + 4 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 4 * GRID_SIZE);
    public SuperButton button5x6 = new SuperButton(INITIAL_PIECE_WIDTH + 5 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 4 * GRID_SIZE);
    public SuperButton button5x7 = new SuperButton(INITIAL_PIECE_WIDTH + 6 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 4 * GRID_SIZE);
    public SuperButton button5x8 = new SuperButton(INITIAL_PIECE_WIDTH + 7 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 4 * GRID_SIZE);
    // Line 6
    public SuperButton button6x1 = new SuperButton(INITIAL_PIECE_WIDTH, INITIAL_PIECE_HEIGHT + 5 * GRID_SIZE);
    public SuperButton button6x2 = new SuperButton(INITIAL_PIECE_WIDTH + GRID_SIZE, INITIAL_PIECE_HEIGHT + 5 * GRID_SIZE);
    public SuperButton button6x3 = new SuperButton(INITIAL_PIECE_WIDTH + 2 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 5 * GRID_SIZE);
    public SuperButton button6x4 = new SuperButton(INITIAL_PIECE_WIDTH + 3 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 5 * GRID_SIZE);
    public SuperButton button6x5 = new SuperButton(INITIAL_PIECE_WIDTH + 4 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 5 * GRID_SIZE);
    public SuperButton button6x6 = new SuperButton(INITIAL_PIECE_WIDTH + 5 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 5 * GRID_SIZE);
    public SuperButton button6x7 = new SuperButton(INITIAL_PIECE_WIDTH + 6 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 5 * GRID_SIZE);
    public SuperButton button6x8 = new SuperButton(INITIAL_PIECE_WIDTH + 7 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 5 * GRID_SIZE);
    // Line 7
    public SuperButton button7x1 = new SuperButton(INITIAL_PIECE_WIDTH, INITIAL_PIECE_HEIGHT + 6 * GRID_SIZE);
    public SuperButton button7x2 = new SuperButton(INITIAL_PIECE_WIDTH + GRID_SIZE, INITIAL_PIECE_HEIGHT + 6 * GRID_SIZE);
    public SuperButton button7x3 = new SuperButton(INITIAL_PIECE_WIDTH + 2 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 6 * GRID_SIZE);
    public SuperButton button7x4 = new SuperButton(INITIAL_PIECE_WIDTH + 3 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 6 * GRID_SIZE);
    public SuperButton button7x5 = new SuperButton(INITIAL_PIECE_WIDTH + 4 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 6 * GRID_SIZE);
    public SuperButton button7x6 = new SuperButton(INITIAL_PIECE_WIDTH + 5 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 6 * GRID_SIZE);
    public SuperButton button7x7 = new SuperButton(INITIAL_PIECE_WIDTH + 6 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 6 * GRID_SIZE);
    public SuperButton button7x8 = new SuperButton(INITIAL_PIECE_WIDTH + 7 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 6 * GRID_SIZE);
    // Line 8;
    public SuperButton button8x1 = new SuperButton(INITIAL_PIECE_WIDTH, INITIAL_PIECE_HEIGHT + 7 * GRID_SIZE);
    public SuperButton button8x2 = new SuperButton(INITIAL_PIECE_WIDTH + GRID_SIZE, INITIAL_PIECE_HEIGHT + 7 * GRID_SIZE);
    public SuperButton button8x3 = new SuperButton(INITIAL_PIECE_WIDTH + 2 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 7 * GRID_SIZE);
    public SuperButton button8x4 = new SuperButton(INITIAL_PIECE_WIDTH + 3 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 7 * GRID_SIZE);
    public SuperButton button8x5 = new SuperButton(INITIAL_PIECE_WIDTH + 4 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 7 * GRID_SIZE);
    public SuperButton button8x6 = new SuperButton(INITIAL_PIECE_WIDTH + 5 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 7 * GRID_SIZE);
    public SuperButton button8x7 = new SuperButton(INITIAL_PIECE_WIDTH + 6 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 7 * GRID_SIZE);
    public SuperButton button8x8 = new SuperButton(INITIAL_PIECE_WIDTH + 7 * GRID_SIZE, INITIAL_PIECE_HEIGHT + 7 * GRID_SIZE);

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
    public void openBoard() throws Exception{

        // Creating a new Window;
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(screenSize.width, screenSize.height);
        
        // Putting an Icon on the Window;
        ImageIcon imageIcon = new ImageIcon(System.getProperty("user.dir")+"/Images/ChessIcon.jpg");
        setIconImage(imageIcon.getImage());

        // Adding the buttons on the Window;
        for (SuperButton[] arrayLine : buttonsMatrix) {
            for (SuperButton button : arrayLine) {
                add(button);
                setComponentZOrder(button, 1);
            }
        }

        // Transforming the arrays in lists and setting with the buttons matrix;
        ArrayList<Pieces> blackPieces = new ArrayList<Pieces>(Arrays.asList(blackPiecesArray));
        ArrayList<Pieces> whitePieces = new ArrayList<Pieces>(Arrays.asList(whitePiecesArray));
        SuperButton.setButtonsPiecesMatrix(buttonsMatrix, whitePieces, blackPieces);

        // Putting a new background to the Window;
        ImageIcon backgroundImage = new ImageIcon(System.getProperty("user.dir") + "/Images/Background.jpg");
        JLabel background = new JLabel("", backgroundImage, JLabel.CENTER);
        background.setBounds(0, 0, 1700, 1133);
        add(background);
        setComponentZOrder(background, 0);

        // Set the Window visible;
        setVisible(true);
    }

    // Move the piece on the board;
    public static void movePieceGUI(GraphicalBoard board, Pieces piece, int gridWidth, int gridHeight) throws Exception{
        
        // Set the height and the width of the piece;
        piece.addSize(gridWidth, gridHeight);
        
        // Repaint the board;
        board.revalidate();
        board.repaint();
    }

    // Painting the board using Graphics;
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
            
        // Put the backgroud of the board in black;
        graphics.setColor(Color.BLACK);
        graphics.fillRect((screenSize.width - SIZE_BOARD)/2, (screenSize.height - SIZE_BOARD)/2, SIZE_BOARD, SIZE_BOARD);
      
        // Select parts of the board and painting with white;
        graphics.setColor(Color.WHITE); 
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                
                // Griding the board with white houses;
                if ((row + col) % 2 == 0) { 
                    int x = (screenSize.width - SIZE_BOARD) / 2 + col * GRID_SIZE;
                    int y = (screenSize.height - SIZE_BOARD) / 2 + row * GRID_SIZE;
                    graphics.fillRect(x, y, GRID_SIZE, GRID_SIZE);
                }
            }
        }


        // Drawing all the black pieces that are alive in the game;
        for (int i = 0; i < SuperButton.blackPieces.size(); i++) {

            graphics.drawImage(SuperButton.blackPieces.get(i).getImage(), SuperButton.blackPieces.get(i).getPieceWidth(), 
            SuperButton.blackPieces.get(i).getPieceHeight(), GRID_SIZE, GRID_SIZE,this);
        }

        // Drawing all the white pieces that are alive in the game;
        for (int i = 0; i < SuperButton.whitePieces.size(); i++) {

            graphics.drawImage(SuperButton.whitePieces.get(i).getImage(), SuperButton.whitePieces.get(i).getPieceWidth(), 
            SuperButton.whitePieces.get(i).getPieceHeight(),GRID_SIZE, GRID_SIZE, this);
        }
    }

    public void winWindow(boolean winnerColor){
        endGame = true;

        if(winnerColor){

            Object[] options = {"OK"};

            int result = JOptionPane.showOptionDialog(
                    null,
                    "Checkmate, The white team wins",
                    "Win Window",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (result == JOptionPane.CLOSED_OPTION) {
                dispose();
            } else {
                dispose();
            }

        }else {
            Object[] options = {"OK"};

            int result = JOptionPane.showOptionDialog(
                    null,
                    "Checkmate, The black team wins",
                    "Win Window",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (result == JOptionPane.CLOSED_OPTION) {
                dispose();
            } else {
                dispose();
            }

        }
    }

}