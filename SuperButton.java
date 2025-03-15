import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class SuperButton extends JButton implements ActionListener{

    // All the buttons in a matrix, and all the black and white pieces;
    private static SuperButton[][] buttonsMatrix = new SuperButton[8][8];
    public static ArrayList<Pieces> blackPieces = new ArrayList<Pieces>();
    public static ArrayList<Pieces> whitePieces = new ArrayList<Pieces>();

    // Getting the height and the width of the screen to define the length of the button;
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int LENGTH_BUTTON = (int) ((0.9 * screenSize.height) / 8);

    // Constants color properties;
    private static final Color GREEN_COLOR = new Color(129, 235, 103, 70);
    private static final Color RED_COLOR = new Color(235, 52, 52, 130);
    private static final Color GRAY_COLOR = new Color(101, 97, 102, 50);
    private static final int ERROR_MARGIN = 1;
    
    // Buttons to change the action;
    private int positionButtonWidth;
    private int positionButtonHeight;
    private boolean ableMove = false;
    private boolean isDisabled = false;
    private boolean isSelected = false;

    // Create a new Graphical Board to move the pieces and the rounds;
    public static GraphicalBoard board = new GraphicalBoard();
    private static SuperButton lastButton;
    private static Pieces lastPiece;
    private static boolean isWhiteTurn = true;

    // Definig the sound of the File and the clip;
    File movePieceSound = new File(System.getProperty("user.dir") + "\\Sounds\\ChessPieceSound.wav");
    Clip clipSound;

    // Selecting all the buttons in a matrix, and all the pieces in two arrays separated in black and white;
    public static void setButtonsPiecesMatrix(SuperButton[][] buttonsMatrix, ArrayList<Pieces> whitePieces, ArrayList<Pieces> blackPieces) {

        SuperButton.buttonsMatrix = buttonsMatrix;
        SuperButton.whitePieces = whitePieces;
        SuperButton.blackPieces = blackPieces;
    }

    // Reseting the opaque and the area filled;
    private void resetColorButton(boolean value) {

        setOpaque(value);
        setContentAreaFilled(value);
    }

    // Method to verify if there is a piece in the button and the color;
    private static Pieces getPiece(SuperButton button, boolean isWhite) {

        for (int i = 0; i < blackPieces.size(); i++) {

            if (Math.abs(button.positionButtonWidth - blackPieces.get(i).initialWidthPosition) <= ERROR_MARGIN && 
                Math.abs(button.positionButtonHeight - blackPieces.get(i).initialHeightPosition) <= ERROR_MARGIN) {
                
                if (!isWhite) {
                    return blackPieces.get(i);
                }
            }
        }   
        for (int i = 0; i < whitePieces.size(); i++) {

            if (Math.abs(button.positionButtonWidth - whitePieces.get(i).initialWidthPosition) <= ERROR_MARGIN && 
                Math.abs(button.positionButtonHeight - whitePieces.get(i).initialHeightPosition) <= ERROR_MARGIN) {
                
                if (isWhite) {
                    return whitePieces.get(i);
                }
            }
        }   
        return null;
    }

    // The constructor of the class;
    public SuperButton(int positionButtonWidth, int positionButtonHeight){

        // activating and creating the button;
        addActionListener(this);
        this.positionButtonWidth = positionButtonWidth;
        this.positionButtonHeight = positionButtonHeight; 
        setBounds(positionButtonWidth, positionButtonHeight, LENGTH_BUTTON, LENGTH_BUTTON);
        setRolloverEnabled(false);

        // Creating a mouse listener to capture the moviments of the mouse;
        addMouseListener(new MouseAdapter() {

            // When the mouse clicked something;
            @Override
            public void mouseClicked(MouseEvent e) {
                resetColorButton(true);
            }

            // When the mouse range enter in button's bounds;
            @Override
            public void mouseEntered(MouseEvent e) {

                if (isSelected) {
                    setBackground(GRAY_COLOR);
                }
                else if (ableMove) {
                    setBackground(RED_COLOR);
                }
                else if (isDisabled) {
                    setVisible(false);
                }
                else {
                    resetColorButton(true);
                    setBackground(GREEN_COLOR);
                }
            }

            // When the mouse range leave in button's bounds;
            @Override
            public void mouseExited(MouseEvent e) {

                if (isSelected) {
                    setBackground(GRAY_COLOR);
                }
                else if (ableMove) {
                    setBackground(RED_COLOR);
                }
                else if (isDisabled) {
                    setVisible(false);
                }
                else {
                    resetColorButton(false);
                }
                
            }
        });
    }

    // The performance of the button once is clicked;
    @Override
    public void actionPerformed(ActionEvent e) {

        // Try this part of the code; 
        try {
            // Getting the piece and selecting the round;
            Pieces piece = getPiece(this, isWhiteTurn);
            onceClicked(piece, this);
        }
        // Catching all the possible Exceptions that can happen;
        catch (Exception exception) {

            System.out.println(exception.getMessage());
            System.out.println("The Exception involves the buttons and the Graphical Board...");
        }finally{
            if(!verifyWin()){
                disableButtons();
                board.winWindow(!isWhiteTurn);
            }
        }
    }

    // When the button is clicked by the User;
    private static void onceClicked(Pieces piece, SuperButton button) throws Exception{

        // Lines and Columns of the matrix;
        int line = 0;
        int column = 0;

        // Find the clicked button in the matrix, the lines and the columns;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if (buttonsMatrix[j][i] == button) {
                    line = j;
                    column = i;
                    break;
                }
            }
        }

        // Move the piece if you click to another 'ableMove' button;
        if (button.ableMove && piece == null) {

            // Going to all buttons;
            for (SuperButton[] buttonArray : buttonsMatrix) {
                for (SuperButton allButton : buttonArray) {

                    allButton.ableMove = false;
                    allButton.isDisabled = false;
                    allButton.isSelected = false;
                    allButton.setVisible(true);
                    allButton.resetColorButton(false);
                    allButton.setBackground(null);
                }
            }

            // Catching the audio errors;
            try {

                // Open and cliping the audio;
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(button.movePieceSound);
                button.clipSound = AudioSystem.getClip();
                button.clipSound.open(audioStream);

                // Analysig all the positions;
                if (lastButton.positionButtonWidth >= button.positionButtonWidth) {

                    if (lastButton.positionButtonHeight >= button.positionButtonHeight) {

                        // Starting the audio;
                        button.clipSound.start();
                        // Moving in the Graphic Board;
                        GraphicalBoard.movePieceGUI(board, lastPiece, (int)((lastButton.positionButtonWidth - button.positionButtonWidth) / -Pieces.getGridSize()),
                            (int)((lastButton.positionButtonHeight - button.positionButtonHeight) / - Pieces.getGridSize()));
                    }
                    else {

                        // Starting the audio;
                        button.clipSound.start();
                        // Moving in the Graphic Board;
                        GraphicalBoard.movePieceGUI(board, lastPiece, (int)((lastButton.positionButtonWidth - button.positionButtonWidth) / -Pieces.getGridSize()),
                            (int)((button.positionButtonHeight - lastButton.positionButtonHeight) / Pieces.getGridSize()));
                    }
                }
                else {

                    if (lastButton.positionButtonHeight >= button.positionButtonHeight) {

                        // Starting the audio;
                        button.clipSound.start();
                        // Moving in the Graphic Board;
                        GraphicalBoard.movePieceGUI(board, lastPiece, (int)((button.positionButtonWidth - lastButton.positionButtonWidth) / Pieces.getGridSize()),
                            (int)((lastButton.positionButtonHeight - button.positionButtonHeight) / -Pieces.getGridSize()));
                    }
                    else {

                        // Starting the audio;
                        button.clipSound.start();
                        // Moving in the Graphic Board;
                        GraphicalBoard.movePieceGUI(board, lastPiece, (int)((button.positionButtonWidth - lastButton.positionButtonWidth) / Pieces.getGridSize()),
                            (int)((button.positionButtonHeight - lastButton.positionButtonHeight) / Pieces.getGridSize()));
                    }
                }
            } 
            catch (UnsupportedAudioFileException e) {

                System.out.println("There is an audio error...");
                throw new RuntimeException(e);
            }

            // Selected the last button that was clicked;
            eliminatePiece(lastPiece, lastPiece.isWhite);
            board.invertBoard(whitePieces, blackPieces);
            SuperButton.lastButton = button;
            isWhiteTurn = !isWhiteTurn;
        }              
        // Analysing the possible buttons to click with the positions of the button and the piece; 
        else if (Math.abs(button.positionButtonWidth - piece.initialWidthPosition) <= ERROR_MARGIN && 
            Math.abs(button.positionButtonHeight - piece.initialHeightPosition) <= ERROR_MARGIN) {

            // Setting if the button is selected or not and select the last button and the last piece that was clicked;
            button.isSelected = !button.isSelected;
            SuperButton.lastButton = button;
            SuperButton.lastPiece = piece;

            // If the button is selected;
            if (button.isSelected) {

                // Setting the backgroud of the clicked button;
                button.setBackground(GRAY_COLOR);

                // If the piece is a Pawn;
                if (piece instanceof Pawn) {

                    if (line - 1 >= 0 && !verifyPiece(buttonsMatrix[line - 1][column], true) && !verifyPiece(buttonsMatrix[line - 1][column], false)) {
                            
                        buttonsMatrix[line - 1][column].resetColorButton(true);
                        buttonsMatrix[line - 1][column].setBackground(RED_COLOR);
                        buttonsMatrix[line - 1][column].ableMove = true;

                        if (line == 6 && !verifyPiece(buttonsMatrix[line - 2][column], true) && !verifyPiece(buttonsMatrix[line - 2][column], false)) {
                                
                            buttonsMatrix[line - 2][column].resetColorButton(true);
                            buttonsMatrix[line - 2][column].setBackground(RED_COLOR);
                            buttonsMatrix[line - 2][column].ableMove = true;
                        }
                    }

                    if (line - 1 >= 0 && column - 1 >= 0 && verifyPiece(buttonsMatrix[line - 1][column - 1], !piece.isWhite)) {
                            
                        buttonsMatrix[line - 1][column - 1].resetColorButton(true);
                        buttonsMatrix[line - 1][column - 1].setBackground(RED_COLOR);
                        buttonsMatrix[line - 1][column - 1].ableMove = true;
                    }

                    if (line - 1 >= 0 && column + 1 <= 7 && verifyPiece(buttonsMatrix[line - 1][column + 1], !piece.isWhite)) {
                            
                        buttonsMatrix[line - 1][column + 1].resetColorButton(true);
                        buttonsMatrix[line - 1][column + 1].setBackground(RED_COLOR);
                        buttonsMatrix[line - 1][column + 1].ableMove = true;
                    }
                    disableButtons();
                }
                // If the piece is a Tower;
                else if (piece instanceof Tower) {
                    
                    for (int i = line - 1; i >= 0; i--) { 

                        if (!verifyPiece(buttonsMatrix[i][column], true) && !verifyPiece(buttonsMatrix[i][column], false)) {

                            buttonsMatrix[i][column].resetColorButton(true);
                            buttonsMatrix[i][column].setBackground(RED_COLOR);
                            buttonsMatrix[i][column].ableMove = true;
                        } 
                        else {
                            if (verifyPiece(buttonsMatrix[i][column], !piece.isWhite)) { 

                                buttonsMatrix[i][column].resetColorButton(true);
                                buttonsMatrix[i][column].setBackground(RED_COLOR);
                                buttonsMatrix[i][column].ableMove = true;
                            }
                            break;
                        }
                    }

                    for (int i = line + 1; i < 8; i++) { 

                        if (!verifyPiece(buttonsMatrix[i][column], true) && !verifyPiece(buttonsMatrix[i][column], false)) {

                            buttonsMatrix[i][column].resetColorButton(true);
                            buttonsMatrix[i][column].setBackground(RED_COLOR);
                            buttonsMatrix[i][column].ableMove = true;
                        } 
                        else {
                            if (verifyPiece(buttonsMatrix[i][column], !piece.isWhite)) { 

                                buttonsMatrix[i][column].resetColorButton(true);
                                buttonsMatrix[i][column].setBackground(RED_COLOR);
                                buttonsMatrix[i][column].ableMove = true;
                            }
                            break;
                        }
                    }

                    for (int i = column - 1; i >= 0; i--) { 

                        if (!verifyPiece(buttonsMatrix[line][i], true) && !verifyPiece(buttonsMatrix[line][i], false)) {

                            buttonsMatrix[line][i].resetColorButton(true);
                            buttonsMatrix[line][i].setBackground(RED_COLOR);
                            buttonsMatrix[line][i].ableMove = true;
                        } 
                        else {
                            if (verifyPiece(buttonsMatrix[line][i], !piece.isWhite)) { 

                                buttonsMatrix[line][i].resetColorButton(true);
                                buttonsMatrix[line][i].setBackground(RED_COLOR);
                                buttonsMatrix[line][i].ableMove = true;
                            }
                            break;
                        }
                    }

                    for (int i = column + 1; i < 8; i++) { 

                        if (!verifyPiece(buttonsMatrix[line][i], true) && !verifyPiece(buttonsMatrix[line][i], false)) {

                            buttonsMatrix[line][i].resetColorButton(true);
                            buttonsMatrix[line][i].setBackground(RED_COLOR);
                            buttonsMatrix[line][i].ableMove = true;
                        } 
                        else {
                            if (verifyPiece(buttonsMatrix[line][i], !piece.isWhite)) { 

                                buttonsMatrix[line][i].resetColorButton(true);
                                buttonsMatrix[line][i].setBackground(RED_COLOR);
                                buttonsMatrix[line][i].ableMove = true;
                            }
                            break;
                        }
                    }
                    disableButtons();
                }
                // If the piece is a Bishop;
                else if (piece instanceof Bishop) {

                    for (int i = 1; i < 8; i++) { 

                        if (line - i >= 0 && column + i <= 7) {
                            if (verifyPiece(buttonsMatrix[line - i][column + i], !piece.isWhite)) {

                                buttonsMatrix[line - i][column + i].resetColorButton(true);
                                buttonsMatrix[line - i][column + i].setBackground(RED_COLOR);
                                buttonsMatrix[line - i][column + i].ableMove = true;
                                break;
                            }
                            else if (verifyPiece(buttonsMatrix[line - i][column + i], piece.isWhite)) {
                                break;
                            }
                            else {

                                buttonsMatrix[line - i][column + i].resetColorButton(true);
                                buttonsMatrix[line - i][column + i].setBackground(RED_COLOR);
                                buttonsMatrix[line - i][column + i].ableMove = true;
                            }
                        }
                    }
                    for (int i = 1; i < 8; i++) {

                        if (line - i >= 0 && column - i >= 0) {
                            if (verifyPiece(buttonsMatrix[line - i][column - i], !piece.isWhite)) {

                                buttonsMatrix[line - i][column - i].resetColorButton(true);
                                buttonsMatrix[line - i][column - i].setBackground(RED_COLOR);
                                buttonsMatrix[line - i][column - i].ableMove = true;
                                break;
                            }
                            else if (verifyPiece(buttonsMatrix[line - i][column - i], piece.isWhite)) {
                                break;
                            }
                            else {

                                buttonsMatrix[line - i][column - i].resetColorButton(true);
                                buttonsMatrix[line - i][column - i].setBackground(RED_COLOR);
                                buttonsMatrix[line - i][column - i].ableMove = true;
                            }
                        }
                    }
                    for (int i = 1; i < 8; i++) {

                        if (line + i <= 7 && column + i <= 7) {
                            if (verifyPiece(buttonsMatrix[line + i][column + i], !piece.isWhite)) {

                                buttonsMatrix[line + i][column + i].resetColorButton(true);
                                buttonsMatrix[line + i][column + i].setBackground(RED_COLOR);
                                buttonsMatrix[line + i][column + i].ableMove = true;
                                break;
                            }
                            else if (verifyPiece(buttonsMatrix[line + i][column + i], piece.isWhite)) {
                                break;
                            }
                            else {

                                buttonsMatrix[line + i][column + i].resetColorButton(true);
                                buttonsMatrix[line + i][column + i].setBackground(RED_COLOR);
                                buttonsMatrix[line + i][column + i].ableMove = true;
                            }
                        }
                    }
                    for (int i = 1; i < 8; i++) {

                        if (line + i <= 7 && column - i >= 0) {
                            if (verifyPiece(buttonsMatrix[line + i][column - i], !piece.isWhite)) {

                                buttonsMatrix[line + i][column - i].resetColorButton(true);
                                buttonsMatrix[line + i][column - i].setBackground(RED_COLOR);
                                buttonsMatrix[line + i][column - i].ableMove = true;
                                break;
                            }
                            else if (verifyPiece(buttonsMatrix[line + i][column - i], piece.isWhite)) {
                                break;
                            }
                            else {

                                buttonsMatrix[line + i][column - i].resetColorButton(true);
                                buttonsMatrix[line + i][column - i].setBackground(RED_COLOR);
                                buttonsMatrix[line + i][column - i].ableMove = true;
                            }
                        }
                    }     
                    disableButtons();
                }
                // If the piece is a Horse;
                else if (piece instanceof Horse) {

                    if (line + 2 <= 7) {
                        if (column + 1 <= 7) {
                            if (!verifyPiece(buttonsMatrix[line + 2][column + 1], piece.isWhite)) {

                                buttonsMatrix[line + 2][column + 1].resetColorButton(true);
                                buttonsMatrix[line + 2][column + 1].setBackground(RED_COLOR);
                                buttonsMatrix[line + 2][column + 1].ableMove = true;
                            }
                        }
                        if (column - 1 >= 0) {
                            if (!verifyPiece(buttonsMatrix[line + 2][column - 1], piece.isWhite)) {

                                buttonsMatrix[line + 2][column - 1].resetColorButton(true);
                                buttonsMatrix[line + 2][column - 1].setBackground(RED_COLOR);
                                buttonsMatrix[line + 2][column - 1].ableMove = true;
                            }   
                        }
                    }
                    if (line - 2 >= 0) {
                        if (column + 1 <= 7) {
                            if (!verifyPiece(buttonsMatrix[line - 2][column + 1], piece.isWhite)) {

                                buttonsMatrix[line - 2][column + 1].resetColorButton(true);
                                buttonsMatrix[line - 2][column + 1].setBackground(RED_COLOR);
                                buttonsMatrix[line - 2][column + 1].ableMove = true;
                            }
                            
                        }
                        if (column - 1 >= 0) {
                            if (!verifyPiece(buttonsMatrix[line - 2][column - 1], piece.isWhite)) {

                                buttonsMatrix[line - 2][column - 1].resetColorButton(true);
                                buttonsMatrix[line - 2][column - 1].setBackground(RED_COLOR);
                                buttonsMatrix[line - 2][column - 1].ableMove = true;
                            }
                        }
                    }
                    if (line + 1 <= 7) {
                        if (column + 2 <= 7) {
                            if (!verifyPiece(buttonsMatrix[line + 1][column + 2], piece.isWhite)) {

                                buttonsMatrix[line + 1][column + 2].resetColorButton(true);
                                buttonsMatrix[line + 1][column + 2].setBackground(RED_COLOR);
                                buttonsMatrix[line + 1][column + 2].ableMove = true;
                            }
                        }
                        if (column - 2 >= 0) {
                            if (!verifyPiece(buttonsMatrix[line + 1][column - 2], piece.isWhite)) {

                                buttonsMatrix[line + 1][column - 2].resetColorButton(true);
                                buttonsMatrix[line + 1][column - 2].setBackground(RED_COLOR);
                                buttonsMatrix[line + 1][column - 2].ableMove = true;
                            }
                        }
                    }
                    if (line - 1 >= 0) {
                        if (column + 2 <= 7) {
                            if (!verifyPiece(buttonsMatrix[line - 1][column + 2], piece.isWhite)) {

                                buttonsMatrix[line - 1][column + 2].resetColorButton(true);
                                buttonsMatrix[line - 1][column + 2].setBackground(RED_COLOR);
                                buttonsMatrix[line - 1][column + 2].ableMove = true;
                            }
                        }
                        if (column - 2 >= 0) {
                            if (!verifyPiece(buttonsMatrix[line - 1][column - 2], piece.isWhite)) {

                                buttonsMatrix[line - 1][column - 2].resetColorButton(true);
                                buttonsMatrix[line - 1][column - 2].setBackground(RED_COLOR);
                                buttonsMatrix[line - 1][column - 2].ableMove = true;
                            }
                        }
                    }
                    disableButtons();
                }
                // If the piece is a Queen;
                else if (piece instanceof Queen) {

                    for (int i = line - 1; i >= 0; i--) { 

                        if (!verifyPiece(buttonsMatrix[i][column], true) && !verifyPiece(buttonsMatrix[i][column], false)) {

                            buttonsMatrix[i][column].resetColorButton(true);
                            buttonsMatrix[i][column].setBackground(RED_COLOR);
                            buttonsMatrix[i][column].ableMove = true;
                        } 
                        else {
                            if (verifyPiece(buttonsMatrix[i][column], !piece.isWhite)) { 

                                buttonsMatrix[i][column].resetColorButton(true);
                                buttonsMatrix[i][column].setBackground(RED_COLOR);
                                buttonsMatrix[i][column].ableMove = true;
                            }
                            break;
                        }
                    }
                    for (int i = line + 1; i < 8; i++) { 

                        if (!verifyPiece(buttonsMatrix[i][column], true) && !verifyPiece(buttonsMatrix[i][column], false)) {

                            buttonsMatrix[i][column].resetColorButton(true);
                            buttonsMatrix[i][column].setBackground(RED_COLOR);
                            buttonsMatrix[i][column].ableMove = true;
                        } 
                        else {
                            if (verifyPiece(buttonsMatrix[i][column], !piece.isWhite)) { 

                                buttonsMatrix[i][column].resetColorButton(true);
                                buttonsMatrix[i][column].setBackground(RED_COLOR);
                                buttonsMatrix[i][column].ableMove = true;
                            }
                            break;
                        }
                    }
                    for (int i = column - 1; i >= 0; i--) { 

                        if (!verifyPiece(buttonsMatrix[line][i], true) && !verifyPiece(buttonsMatrix[line][i], false)) {

                            buttonsMatrix[line][i].resetColorButton(true);
                            buttonsMatrix[line][i].setBackground(RED_COLOR);
                            buttonsMatrix[line][i].ableMove = true;
                        } 
                        else {
                            if (verifyPiece(buttonsMatrix[line][i], !piece.isWhite)) { 

                                buttonsMatrix[line][i].resetColorButton(true);
                                buttonsMatrix[line][i].setBackground(RED_COLOR);
                                buttonsMatrix[line][i].ableMove = true;
                            }
                            break;
                        }
                    }
                    for (int i = column + 1; i < 8; i++) { 

                        if (!verifyPiece(buttonsMatrix[line][i], true) && !verifyPiece(buttonsMatrix[line][i], false)) {

                            buttonsMatrix[line][i].resetColorButton(true);
                            buttonsMatrix[line][i].setBackground(RED_COLOR);
                            buttonsMatrix[line][i].ableMove = true;
                        } 
                        else {
                            if (verifyPiece(buttonsMatrix[line][i], !piece.isWhite)) { 

                                buttonsMatrix[line][i].resetColorButton(true);
                                buttonsMatrix[line][i].setBackground(RED_COLOR);
                                buttonsMatrix[line][i].ableMove = true;
                            }
                            break;
                        }
                    }
                    for (int i = 1; i < 8; i++) { 

                        if (line - i >= 0 && column + i <= 7) {
                            if (verifyPiece(buttonsMatrix[line - i][column + i], !piece.isWhite)) {

                                buttonsMatrix[line - i][column + i].resetColorButton(true);
                                buttonsMatrix[line - i][column + i].setBackground(RED_COLOR);
                                buttonsMatrix[line - i][column + i].ableMove = true;
                                break;
                            }
                            else if (verifyPiece(buttonsMatrix[line - i][column + i], piece.isWhite)) {
                                break;
                            }
                            else {

                                buttonsMatrix[line - i][column + i].resetColorButton(true);
                                buttonsMatrix[line - i][column + i].setBackground(RED_COLOR);
                                buttonsMatrix[line - i][column + i].ableMove = true;
                            }
                        }
                    }
                    for (int i = 1; i < 8; i++) {

                        if (line - i >= 0 && column - i >= 0) {
                            if (verifyPiece(buttonsMatrix[line - i][column - i], !piece.isWhite)) {

                                buttonsMatrix[line - i][column - i].resetColorButton(true);
                                buttonsMatrix[line - i][column - i].setBackground(RED_COLOR);
                                buttonsMatrix[line - i][column - i].ableMove = true;
                                break;
                            }
                            else if (verifyPiece(buttonsMatrix[line - i][column - i], piece.isWhite)) {
                                break;
                            }
                            else {

                                buttonsMatrix[line - i][column - i].resetColorButton(true);
                                buttonsMatrix[line - i][column - i].setBackground(RED_COLOR);
                                buttonsMatrix[line - i][column - i].ableMove = true;
                            }
                        }
                    }
                    for (int i = 1; i < 8; i++) {

                        if (line + i <= 7 && column + i <= 7) {
                            if (verifyPiece(buttonsMatrix[line + i][column + i], !piece.isWhite)) {

                                buttonsMatrix[line + i][column + i].resetColorButton(true);
                                buttonsMatrix[line + i][column + i].setBackground(RED_COLOR);
                                buttonsMatrix[line + i][column + i].ableMove = true;
                                break;
                            }
                            else if (verifyPiece(buttonsMatrix[line + i][column + i], piece.isWhite)) {
                                break;
                            }
                            else {

                                buttonsMatrix[line + i][column + i].resetColorButton(true);
                                buttonsMatrix[line + i][column + i].setBackground(RED_COLOR);
                                buttonsMatrix[line + i][column + i].ableMove = true;
                            }
                        }
                    }
                    for (int i = 1; i < 8; i++) {

                        if (line + i <= 7 && column - i >= 0) {
                            if (verifyPiece(buttonsMatrix[line + i][column - i], !piece.isWhite)) {

                                buttonsMatrix[line + i][column - i].resetColorButton(true);
                                buttonsMatrix[line + i][column - i].setBackground(RED_COLOR);
                                buttonsMatrix[line + i][column - i].ableMove = true;
                                break;
                            }
                            else if (verifyPiece(buttonsMatrix[line + i][column - i], piece.isWhite)) {
                                break;
                            }
                            else {

                                buttonsMatrix[line + i][column - i].resetColorButton(true);
                                buttonsMatrix[line + i][column - i].setBackground(RED_COLOR);
                                buttonsMatrix[line + i][column - i].ableMove = true;
                            }
                        }
                    }     
                    disableButtons();
                }
                // If the piece is a King;
                else if (piece instanceof King) {

                    if (line + 1 <= 7) {

                        if (!verifyPiece(buttonsMatrix[line + 1][column], piece.isWhite)) {

                            buttonsMatrix[line + 1][column].resetColorButton(true);
                            buttonsMatrix[line + 1][column].setBackground(RED_COLOR);
                            buttonsMatrix[line + 1][column].ableMove = true;
                        }
                        
                        if (column + 1 <= 7) {
                            if (!verifyPiece(buttonsMatrix[line + 1][column + 1], piece.isWhite)) {

                                buttonsMatrix[line + 1][column + 1].resetColorButton(true);
                                buttonsMatrix[line + 1][column + 1].setBackground(RED_COLOR);
                                buttonsMatrix[line + 1][column + 1]. ableMove = true;
                            } 
                        }
                        if (column - 1 >= 0) {
                            if (!verifyPiece(buttonsMatrix[line + 1][column - 1], piece.isWhite)) {

                                buttonsMatrix[line + 1][column - 1].resetColorButton(true);
                                buttonsMatrix[line + 1][column - 1].setBackground(RED_COLOR);
                                buttonsMatrix[line + 1][column - 1].ableMove = true;
                            }
                        }
                    }
                    if (line - 1 >= 0) {

                        if (!verifyPiece(buttonsMatrix[line - 1][column], piece.isWhite)) {

                            buttonsMatrix[line - 1][column].resetColorButton(true);
                            buttonsMatrix[line - 1][column].setBackground(RED_COLOR);
                            buttonsMatrix[line - 1][column].ableMove = true;
                        }

                        if (column + 1 <= 7) {
                            if (!verifyPiece(buttonsMatrix[line - 1][column + 1], piece.isWhite)) {

                                buttonsMatrix[line - 1][column + 1].resetColorButton(true);
                                buttonsMatrix[line - 1][column + 1].setBackground(RED_COLOR);
                                buttonsMatrix[line - 1][column + 1].ableMove = true;
                            }
                        }
                        if (column - 1 >= 0) {
                            if (!verifyPiece(buttonsMatrix[line - 1][column - 1], piece.isWhite)) {

                                buttonsMatrix[line - 1][column - 1].resetColorButton(true);
                                buttonsMatrix[line - 1][column - 1].setBackground(RED_COLOR);
                                buttonsMatrix[line - 1][column - 1].ableMove = true;
                            }
                        }
                    }
                    if (column + 1 <= 7) {

                        if (!verifyPiece(buttonsMatrix[line][column + 1], piece.isWhite)) {
                            
                            buttonsMatrix[line][column + 1].resetColorButton(true);
                            buttonsMatrix[line][column + 1].setBackground(RED_COLOR);
                            buttonsMatrix[line][column + 1].ableMove = true;
                        }
                    }
                    if (column - 1 >= 0) {
                        if (!verifyPiece(buttonsMatrix[line][column - 1], piece.isWhite)) {

                            buttonsMatrix[line][column - 1].resetColorButton(true);
                            buttonsMatrix[line][column - 1].setBackground(RED_COLOR);
                            buttonsMatrix[line][column - 1].ableMove = true;
                        }
                    }
                    disableButtons();
                }
            }
            // If the button isn't seleted;
            else {

                // Going to all buttons;
                for (SuperButton[] buttonArray : buttonsMatrix) {
                    for (SuperButton allButton : buttonArray) {

                        allButton.ableMove = false;
                        allButton.isDisabled = false;
                        allButton.isSelected = false;
                        allButton.setVisible(true);
                        allButton.resetColorButton(false);
                        allButton.setBackground(null);
                    }
                }
            } 
        }   
    }
    
    // Disable the buttons that aren't selected;
    private static void disableButtons() {

        // Analysing all the buttons;
        for (SuperButton[] buttonArray : buttonsMatrix) {
            for (SuperButton button : buttonArray) {
                
                if (!button.ableMove && !button.isSelected) {
                    // Disable the button;
                    button.isDisabled = true;
                }
            }
        }
    }

    // Method to eliminate the Pieces;
    private static void eliminatePiece(Pieces piece, boolean isWhite) {

        if (isWhite) {
            for (int i = 0; i < blackPieces.size(); i++) {
                if (Math.abs(piece.initialHeightPosition - blackPieces.get(i).initialHeightPosition) <= ERROR_MARGIN && 
                    Math.abs(piece.initialWidthPosition - blackPieces.get(i).initialWidthPosition) <= ERROR_MARGIN) {
                    
                    blackPieces.remove(i);
                    break;
                }
            }
        }
        else {
            for (int i = 0; i < whitePieces.size(); i++) {
                if (Math.abs(piece.initialHeightPosition - whitePieces.get(i).initialHeightPosition) <= ERROR_MARGIN && 
                    Math.abs(piece.initialWidthPosition - whitePieces.get(i).initialWidthPosition) <= ERROR_MARGIN) {
                    
                    whitePieces.remove(i);
                    break;
                }
            } 
        }
        board.revalidate();
        board.repaint();
    }

    // Method to verify if there is a piece in the button and the color;
    private static boolean verifyPiece(SuperButton button, boolean isWhite) {

        for (int i = 0; i < blackPieces.size(); i++) {

            if (Math.abs(button.positionButtonWidth - blackPieces.get(i).initialWidthPosition) <= ERROR_MARGIN && 
                Math.abs(button.positionButtonHeight - blackPieces.get(i).initialHeightPosition) <= ERROR_MARGIN) {
                
                if (!isWhite) {
                    return true;
                }
                else {
                    return false;
                }
            }
        }   
        for (int i = 0; i < whitePieces.size(); i++) {

            if (Math.abs(button.positionButtonWidth - whitePieces.get(i).initialWidthPosition) <= ERROR_MARGIN && 
                Math.abs(button.positionButtonHeight - whitePieces.get(i).initialHeightPosition) <= ERROR_MARGIN) {
                
                if (isWhite) {
                    return true;
                }
                else {
                    return false;
                }
            }
        }   
        return false;
    }

    public static boolean verifyWin() {

        boolean hasKing = false;
        boolean whiteBlackTurn = !isWhiteTurn;

        if (whiteBlackTurn) {
            for (Pieces piece : blackPieces) {
                if (piece instanceof King) {
                    hasKing = true;
                    return hasKing;
                }
            }
        } else {
            for (Pieces piece : whitePieces) {
                if (piece instanceof King) {
                    hasKing = true;
                    return hasKing;
                }
            }
        }
        return hasKing;
    }

}
