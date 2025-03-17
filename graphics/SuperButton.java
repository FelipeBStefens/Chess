package Chess.graphics;

import Chess.pieces.*;

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
    private static boolean whiteCanRook = true;
    private static boolean blackCanRook = true; 

    // Create a new Graphical Board to move the pieces and the rounds;
    public static GraphicalBoard board = new GraphicalBoard();
    private static SuperButton lastButton;
    private static Pieces lastPiece;
    private static boolean isWhiteTurn = true;
    private static Tower towerRook;

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

        // Creating a mouse listener to capture the movements of the mouse;
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
        if (button.ableMove) {

            // Catching the audio errors;
            try {

                // Open and cliping the audio;
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(button.movePieceSound);
                button.clipSound = AudioSystem.getClip();
                button.clipSound.open(audioStream);

                // Analysig all the positions;
                if (lastPiece instanceof King && lastPiece.isWhite) {
                   
                    if (Math.abs(button.positionButtonWidth - buttonsMatrix[7][7].positionButtonWidth) <= ERROR_MARGIN && 
                        Math.abs(button.positionButtonHeight - buttonsMatrix[7][7].positionButtonHeight) <= ERROR_MARGIN) {

                        // Starting the audio;
                        button.clipSound.start();
                        // Moving in the Graphic Board;
                        GraphicalBoard.movePieceGUI(board, lastPiece, 2, 0);
                        GraphicalBoard.movePieceGUI(board, towerRook, -2, 0);
                        towerRook = null;
                        whiteCanRook = false;
                    }
                    else if (Math.abs(button.positionButtonWidth - buttonsMatrix[7][0].positionButtonWidth) <= ERROR_MARGIN && 
                        Math.abs(button.positionButtonHeight - buttonsMatrix[7][0].positionButtonHeight) <= ERROR_MARGIN) {
                        
                        // Starting the audio;
                        button.clipSound.start();
                        // Moving in the Graphic Board;
                        GraphicalBoard.movePieceGUI(board, lastPiece, -3, 0);
                        GraphicalBoard.movePieceGUI(board, towerRook, 2, 0);
                        towerRook = null;
                        whiteCanRook = false;
                    }
                }
                else if (lastPiece instanceof King && !lastPiece.isWhite) {
          
                    if (Math.abs(button.positionButtonWidth - buttonsMatrix[7][7].positionButtonWidth) <= ERROR_MARGIN && 
                        Math.abs(button.positionButtonHeight - buttonsMatrix[7][7].positionButtonHeight) <= ERROR_MARGIN) {

                        // Starting the audio;
                        button.clipSound.start();
                        // Moving in the Graphic Board;
                        GraphicalBoard.movePieceGUI(board, lastPiece, 3, 0);
                        GraphicalBoard.movePieceGUI(board, towerRook, -2, 0);
                        towerRook = null;
                        blackCanRook = false;
                    }
                    else if (Math.abs(button.positionButtonWidth - buttonsMatrix[7][0].positionButtonWidth) <= ERROR_MARGIN && 
                        Math.abs(button.positionButtonHeight - buttonsMatrix[7][0].positionButtonHeight) <= ERROR_MARGIN) {

                        // Starting the audio;
                        button.clipSound.start();
                        // Moving in the Graphic Board;
                        GraphicalBoard.movePieceGUI(board, lastPiece, -2, 0);
                        GraphicalBoard.movePieceGUI(board, towerRook, 2, 0);
                        towerRook = null;
                        blackCanRook = false;
                    }
                }
                if (lastButton.positionButtonWidth >= button.positionButtonWidth && piece == null) {

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
                else if (lastButton.positionButtonWidth <= button.positionButtonWidth && piece == null){

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

            eliminatePiece(lastPiece, lastPiece.isWhite);

            Queen newQueen = new Queen(isWhiteTurn, lastPiece.initialWidthPosition, lastPiece.initialHeightPosition);
            Tower newTower = new Tower(isWhiteTurn, lastPiece.initialWidthPosition, lastPiece.initialHeightPosition);
            Bishop newBishop = new Bishop(isWhiteTurn, lastPiece.initialWidthPosition, lastPiece.initialHeightPosition);
            Horse newHorse = new Horse(isWhiteTurn, lastPiece.initialWidthPosition, lastPiece.initialHeightPosition);
            // Promoting the pieces; 
            if (lastPiece instanceof Pawn && Math.abs(lastPiece.initialHeightPosition - (screenSize.height - (int)(0.9 * screenSize.height))/2) <= ERROR_MARGIN && lastPiece != null) {
                
                String promotionString = PromotionButtons.promotionButtons(board, lastPiece.isWhite);
                if (promotionString.equals("Queen")) {

                    if (isWhiteTurn) {
                        whitePieces.remove(lastPiece);
                        whitePieces.add(newQueen);
                    }
                    else {
                        blackPieces.remove(lastPiece);
                        blackPieces.add(newQueen);
                    }
                }
                else if (promotionString.equals("Tower")) {

                    if (isWhiteTurn) {
                        whitePieces.remove(lastPiece);
                        whitePieces.add(newTower);
                    }
                    else {
                        blackPieces.remove(lastPiece);
                        blackPieces.add(newTower);
                    }
                }
                else if (promotionString.equals("Bishop")) {
                    
                    if (isWhiteTurn) {
                        whitePieces.remove(lastPiece);
                        whitePieces.add(newBishop);
                    }
                    else {
                        blackPieces.remove(lastPiece);
                        blackPieces.add(newBishop);
                    }
                }
                else if (promotionString.equals("Horse")) {
                    
                    if (isWhiteTurn) {
                        whitePieces.remove(lastPiece);
                        whitePieces.add(newHorse);
                    }
                    else {
                        blackPieces.remove(lastPiece);
                        blackPieces.add(newHorse);
                    }
                }
                board.revalidate();
                board.repaint();
            }

            // Selected the last button that was clicked;
            board.invertBoard(whitePieces, blackPieces);
            SuperButton.lastButton = button;
            isWhiteTurn = !isWhiteTurn;
            
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
                        SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line - 1][column - 1]);
                    }

                    if (line - 1 >= 0 && column + 1 <= 7 && verifyPiece(buttonsMatrix[line - 1][column + 1], !piece.isWhite)) {
                            
                        buttonsMatrix[line - 1][column + 1].resetColorButton(true);
                        buttonsMatrix[line - 1][column + 1].setBackground(RED_COLOR);
                        buttonsMatrix[line - 1][column + 1].ableMove = true;
                        SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line - 1][column + 1]);
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
                            SuperButton.verifyRook(piece.isWhite, buttonsMatrix[i][column]);
                        } 
                        else {
                            if (verifyPiece(buttonsMatrix[i][column], !piece.isWhite)) { 

                                buttonsMatrix[i][column].resetColorButton(true);
                                buttonsMatrix[i][column].setBackground(RED_COLOR);
                                buttonsMatrix[i][column].ableMove = true;
                                SuperButton.verifyRook(piece.isWhite, buttonsMatrix[i][column]);
                            }
                            break;
                        }
                    }

                    for (int i = line + 1; i < 8; i++) { 

                        if (!verifyPiece(buttonsMatrix[i][column], true) && !verifyPiece(buttonsMatrix[i][column], false)) {

                            buttonsMatrix[i][column].resetColorButton(true);
                            buttonsMatrix[i][column].setBackground(RED_COLOR);
                            buttonsMatrix[i][column].ableMove = true;
                            SuperButton.verifyRook(piece.isWhite, buttonsMatrix[i][column]);
                        } 
                        else {
                            if (verifyPiece(buttonsMatrix[i][column], !piece.isWhite)) { 

                                buttonsMatrix[i][column].resetColorButton(true);
                                buttonsMatrix[i][column].setBackground(RED_COLOR);
                                buttonsMatrix[i][column].ableMove = true;
                                SuperButton.verifyRook(piece.isWhite, buttonsMatrix[i][column]);
                            }
                            break;
                        }
                    }

                    for (int i = column - 1; i >= 0; i--) { 

                        if (!verifyPiece(buttonsMatrix[line][i], true) && !verifyPiece(buttonsMatrix[line][i], false)) {

                            buttonsMatrix[line][i].resetColorButton(true);
                            buttonsMatrix[line][i].setBackground(RED_COLOR);
                            buttonsMatrix[line][i].ableMove = true;
                            SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line][i]);
                        } 
                        else {
                            if (verifyPiece(buttonsMatrix[line][i], !piece.isWhite)) { 

                                buttonsMatrix[line][i].resetColorButton(true);
                                buttonsMatrix[line][i].setBackground(RED_COLOR);
                                buttonsMatrix[line][i].ableMove = true;
                                SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line][i]);
                            }
                            break;
                        }
                    }

                    for (int i = column + 1; i < 8; i++) { 

                        if (!verifyPiece(buttonsMatrix[line][i], true) && !verifyPiece(buttonsMatrix[line][i], false)) {

                            buttonsMatrix[line][i].resetColorButton(true);
                            buttonsMatrix[line][i].setBackground(RED_COLOR);
                            buttonsMatrix[line][i].ableMove = true;
                            SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line][i]);
                        } 
                        else {
                            if (verifyPiece(buttonsMatrix[line][i], !piece.isWhite)) { 

                                buttonsMatrix[line][i].resetColorButton(true);
                                buttonsMatrix[line][i].setBackground(RED_COLOR);
                                buttonsMatrix[line][i].ableMove = true;
                                SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line][i]);
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
                                SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line - i][column + i]);
                                break;
                            }
                            else if (verifyPiece(buttonsMatrix[line - i][column + i], piece.isWhite)) {
                                break;
                            }
                            else {

                                buttonsMatrix[line - i][column + i].resetColorButton(true);
                                buttonsMatrix[line - i][column + i].setBackground(RED_COLOR);
                                buttonsMatrix[line - i][column + i].ableMove = true;
                                SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line - i][column + i]);
                            }
                        }
                    }
                    for (int i = 1; i < 8; i++) {

                        if (line - i >= 0 && column - i >= 0) {
                            if (verifyPiece(buttonsMatrix[line - i][column - i], !piece.isWhite)) {

                                buttonsMatrix[line - i][column - i].resetColorButton(true);
                                buttonsMatrix[line - i][column - i].setBackground(RED_COLOR);
                                buttonsMatrix[line - i][column - i].ableMove = true;
                                SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line - i][column - i]);
                                break;
                            }
                            else if (verifyPiece(buttonsMatrix[line - i][column - i], piece.isWhite)) {
                                break;
                            }
                            else {

                                buttonsMatrix[line - i][column - i].resetColorButton(true);
                                buttonsMatrix[line - i][column - i].setBackground(RED_COLOR);
                                buttonsMatrix[line - i][column - i].ableMove = true;
                                SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line - i][column - i]);
                            }
                        }
                    }
                    for (int i = 1; i < 8; i++) {

                        if (line + i <= 7 && column + i <= 7) {
                            if (verifyPiece(buttonsMatrix[line + i][column + i], !piece.isWhite)) {

                                buttonsMatrix[line + i][column + i].resetColorButton(true);
                                buttonsMatrix[line + i][column + i].setBackground(RED_COLOR);
                                buttonsMatrix[line + i][column + i].ableMove = true;
                                SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line + i][column + i]);
                                break;
                            }
                            else if (verifyPiece(buttonsMatrix[line + i][column + i], piece.isWhite)) {
                                break;
                            }
                            else {

                                buttonsMatrix[line + i][column + i].resetColorButton(true);
                                buttonsMatrix[line + i][column + i].setBackground(RED_COLOR);
                                buttonsMatrix[line + i][column + i].ableMove = true;
                                SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line + i][column + i]);
                            }
                        }
                    }
                    for (int i = 1; i < 8; i++) {

                        if (line + i <= 7 && column - i >= 0) {
                            if (verifyPiece(buttonsMatrix[line + i][column - i], !piece.isWhite)) {

                                buttonsMatrix[line + i][column - i].resetColorButton(true);
                                buttonsMatrix[line + i][column - i].setBackground(RED_COLOR);
                                buttonsMatrix[line + i][column - i].ableMove = true;
                                SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line + i][column - i]);
                                break;
                            }
                            else if (verifyPiece(buttonsMatrix[line + i][column - i], piece.isWhite)) {
                                break;
                            }
                            else {

                                buttonsMatrix[line + i][column - i].resetColorButton(true);
                                buttonsMatrix[line + i][column - i].setBackground(RED_COLOR);
                                buttonsMatrix[line + i][column - i].ableMove = true;
                                SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line + i][column - i]);
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
                                SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line + 2][column + 1]);
                            }
                        }
                        if (column - 1 >= 0) {
                            if (!verifyPiece(buttonsMatrix[line + 2][column - 1], piece.isWhite)) {

                                buttonsMatrix[line + 2][column - 1].resetColorButton(true);
                                buttonsMatrix[line + 2][column - 1].setBackground(RED_COLOR);
                                buttonsMatrix[line + 2][column - 1].ableMove = true;
                                SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line + 2][column - 1]);
                            }   
                        }
                    }
                    if (line - 2 >= 0) {
                        if (column + 1 <= 7) {
                            if (!verifyPiece(buttonsMatrix[line - 2][column + 1], piece.isWhite)) {

                                buttonsMatrix[line - 2][column + 1].resetColorButton(true);
                                buttonsMatrix[line - 2][column + 1].setBackground(RED_COLOR);
                                buttonsMatrix[line - 2][column + 1].ableMove = true;
                                SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line - 2][column + 1]);
                            }
                            
                        }
                        if (column - 1 >= 0) {
                            if (!verifyPiece(buttonsMatrix[line - 2][column - 1], piece.isWhite)) {

                                buttonsMatrix[line - 2][column - 1].resetColorButton(true);
                                buttonsMatrix[line - 2][column - 1].setBackground(RED_COLOR);
                                buttonsMatrix[line - 2][column - 1].ableMove = true;
                                SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line - 2][column - 1]);
                            }
                        }
                    }
                    if (line + 1 <= 7) {
                        if (column + 2 <= 7) {
                            if (!verifyPiece(buttonsMatrix[line + 1][column + 2], piece.isWhite)) {

                                buttonsMatrix[line + 1][column + 2].resetColorButton(true);
                                buttonsMatrix[line + 1][column + 2].setBackground(RED_COLOR);
                                buttonsMatrix[line + 1][column + 2].ableMove = true;
                                SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line + 1][column + 2]);
                            }
                        }
                        if (column - 2 >= 0) {
                            if (!verifyPiece(buttonsMatrix[line + 1][column - 2], piece.isWhite)) {

                                buttonsMatrix[line + 1][column - 2].resetColorButton(true);
                                buttonsMatrix[line + 1][column - 2].setBackground(RED_COLOR);
                                buttonsMatrix[line + 1][column - 2].ableMove = true;
                                SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line + 1][column - 2]);
                            }
                        }
                    }
                    if (line - 1 >= 0) {
                        if (column + 2 <= 7) {
                            if (!verifyPiece(buttonsMatrix[line - 1][column + 2], piece.isWhite)) {

                                buttonsMatrix[line - 1][column + 2].resetColorButton(true);
                                buttonsMatrix[line - 1][column + 2].setBackground(RED_COLOR);
                                buttonsMatrix[line - 1][column + 2].ableMove = true;
                                SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line - 1][column + 2]);
                            }
                        }
                        if (column - 2 >= 0) {
                            if (!verifyPiece(buttonsMatrix[line - 1][column - 2], piece.isWhite)) {

                                buttonsMatrix[line - 1][column - 2].resetColorButton(true);
                                buttonsMatrix[line - 1][column - 2].setBackground(RED_COLOR);
                                buttonsMatrix[line - 1][column - 2].ableMove = true;
                                SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line - 1][column - 2]);
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
                            SuperButton.verifyRook(piece.isWhite, buttonsMatrix[i][column]);
                        } 
                        else {
                            if (verifyPiece(buttonsMatrix[i][column], !piece.isWhite)) { 

                                buttonsMatrix[i][column].resetColorButton(true);
                                buttonsMatrix[i][column].setBackground(RED_COLOR);
                                buttonsMatrix[i][column].ableMove = true;
                                SuperButton.verifyRook(piece.isWhite, buttonsMatrix[i][column]);
                            }
                            break;
                        }
                    }
                    for (int i = line + 1; i < 8; i++) { 

                        if (!verifyPiece(buttonsMatrix[i][column], true) && !verifyPiece(buttonsMatrix[i][column], false)) {

                            buttonsMatrix[i][column].resetColorButton(true);
                            buttonsMatrix[i][column].setBackground(RED_COLOR);
                            buttonsMatrix[i][column].ableMove = true;
                            SuperButton.verifyRook(piece.isWhite, buttonsMatrix[i][column]);
                        } 
                        else {
                            if (verifyPiece(buttonsMatrix[i][column], !piece.isWhite)) { 

                                buttonsMatrix[i][column].resetColorButton(true);
                                buttonsMatrix[i][column].setBackground(RED_COLOR);
                                buttonsMatrix[i][column].ableMove = true;
                                SuperButton.verifyRook(piece.isWhite, buttonsMatrix[i][column]);
                            }
                            break;
                        }
                    }
                    for (int i = column - 1; i >= 0; i--) { 

                        if (!verifyPiece(buttonsMatrix[line][i], true) && !verifyPiece(buttonsMatrix[line][i], false)) {

                            buttonsMatrix[line][i].resetColorButton(true);
                            buttonsMatrix[line][i].setBackground(RED_COLOR);
                            buttonsMatrix[line][i].ableMove = true;
                            SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line][i]);
                        } 
                        else {
                            if (verifyPiece(buttonsMatrix[line][i], !piece.isWhite)) { 

                                buttonsMatrix[line][i].resetColorButton(true);
                                buttonsMatrix[line][i].setBackground(RED_COLOR);
                                buttonsMatrix[line][i].ableMove = true;
                                SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line][i]);
                            }
                            break;
                        }
                    }
                    for (int i = column + 1; i < 8; i++) { 

                        if (!verifyPiece(buttonsMatrix[line][i], true) && !verifyPiece(buttonsMatrix[line][i], false)) {

                            buttonsMatrix[line][i].resetColorButton(true);
                            buttonsMatrix[line][i].setBackground(RED_COLOR);
                            buttonsMatrix[line][i].ableMove = true;
                            SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line][i]);
                        } 
                        else {
                            if (verifyPiece(buttonsMatrix[line][i], !piece.isWhite)) { 

                                buttonsMatrix[line][i].resetColorButton(true);
                                buttonsMatrix[line][i].setBackground(RED_COLOR);
                                buttonsMatrix[line][i].ableMove = true;
                                SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line][i]);
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
                                SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line - i][column + i]);
                                break;
                            }
                            else if (verifyPiece(buttonsMatrix[line - i][column + i], piece.isWhite)) {
                                break;
                            }
                            else {

                                buttonsMatrix[line - i][column + i].resetColorButton(true);
                                buttonsMatrix[line - i][column + i].setBackground(RED_COLOR);
                                buttonsMatrix[line - i][column + i].ableMove = true;
                                SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line - i][column + i]);
                            }
                        }
                    }
                    for (int i = 1; i < 8; i++) {

                        if (line - i >= 0 && column - i >= 0) {
                            if (verifyPiece(buttonsMatrix[line - i][column - i], !piece.isWhite)) {

                                buttonsMatrix[line - i][column - i].resetColorButton(true);
                                buttonsMatrix[line - i][column - i].setBackground(RED_COLOR);
                                buttonsMatrix[line - i][column - i].ableMove = true;
                                SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line - i][column - i]);
                                break;
                            }
                            else if (verifyPiece(buttonsMatrix[line - i][column - i], piece.isWhite)) {
                                break;
                            }
                            else {

                                buttonsMatrix[line - i][column - i].resetColorButton(true);
                                buttonsMatrix[line - i][column - i].setBackground(RED_COLOR);
                                buttonsMatrix[line - i][column - i].ableMove = true;
                                SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line - i][column - i]);
                            }
                        }
                    }
                    for (int i = 1; i < 8; i++) {

                        if (line + i <= 7 && column + i <= 7) {
                            if (verifyPiece(buttonsMatrix[line + i][column + i], !piece.isWhite)) {

                                buttonsMatrix[line + i][column + i].resetColorButton(true);
                                buttonsMatrix[line + i][column + i].setBackground(RED_COLOR);
                                buttonsMatrix[line + i][column + i].ableMove = true;
                                SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line + i][column + i]);
                                break;
                            }
                            else if (verifyPiece(buttonsMatrix[line + i][column + i], piece.isWhite)) {
                                break;
                            }
                            else {

                                buttonsMatrix[line + i][column + i].resetColorButton(true);
                                buttonsMatrix[line + i][column + i].setBackground(RED_COLOR);
                                buttonsMatrix[line + i][column + i].ableMove = true;
                                SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line + i][column + i]);
                            }
                        }
                    }
                    for (int i = 1; i < 8; i++) {

                        if (line + i <= 7 && column - i >= 0) {
                            if (verifyPiece(buttonsMatrix[line + i][column - i], !piece.isWhite)) {

                                buttonsMatrix[line + i][column - i].resetColorButton(true);
                                buttonsMatrix[line + i][column - i].setBackground(RED_COLOR);
                                buttonsMatrix[line + i][column - i].ableMove = true;
                                SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line + i][column - i]);
                                break;
                            }
                            else if (verifyPiece(buttonsMatrix[line + i][column - i], piece.isWhite)) {
                                break;
                            }
                            else {

                                buttonsMatrix[line + i][column - i].resetColorButton(true);
                                buttonsMatrix[line + i][column - i].setBackground(RED_COLOR);
                                buttonsMatrix[line + i][column - i].ableMove = true;
                                SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line + i][column - i]);
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
                            SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line + 1][column]);
                        }
                        
                        if (column + 1 <= 7) {
                            if (!verifyPiece(buttonsMatrix[line + 1][column + 1], piece.isWhite)) {

                                buttonsMatrix[line + 1][column + 1].resetColorButton(true);
                                buttonsMatrix[line + 1][column + 1].setBackground(RED_COLOR);
                                buttonsMatrix[line + 1][column + 1]. ableMove = true;
                                SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line + 1][column + 1]);
                            } 
                        }
                        if (column - 1 >= 0) {
                            if (!verifyPiece(buttonsMatrix[line + 1][column - 1], piece.isWhite)) {

                                buttonsMatrix[line + 1][column - 1].resetColorButton(true);
                                buttonsMatrix[line + 1][column - 1].setBackground(RED_COLOR);
                                buttonsMatrix[line + 1][column - 1].ableMove = true;
                                SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line + 1][column - 1]);
                            }
                        }
                    }
                    if (line - 1 >= 0) {

                        if (!verifyPiece(buttonsMatrix[line - 1][column], piece.isWhite)) {

                            buttonsMatrix[line - 1][column].resetColorButton(true);
                            buttonsMatrix[line - 1][column].setBackground(RED_COLOR);
                            buttonsMatrix[line - 1][column].ableMove = true;
                            SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line - 1][column]);
                        }

                        if (column + 1 <= 7) {
                            if (!verifyPiece(buttonsMatrix[line - 1][column + 1], piece.isWhite)) {

                                buttonsMatrix[line - 1][column + 1].resetColorButton(true);
                                buttonsMatrix[line - 1][column + 1].setBackground(RED_COLOR);
                                buttonsMatrix[line - 1][column + 1].ableMove = true;
                                SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line - 1][column + 1]);
                            }
                        }
                        if (column - 1 >= 0) {
                            if (!verifyPiece(buttonsMatrix[line - 1][column - 1], piece.isWhite)) {

                                buttonsMatrix[line - 1][column - 1].resetColorButton(true);
                                buttonsMatrix[line - 1][column - 1].setBackground(RED_COLOR);
                                buttonsMatrix[line - 1][column - 1].ableMove = true;
                                SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line - 1][column - 1]);
                            }
                        }
                    }
                    if (column + 1 <= 7) {

                        if (!verifyPiece(buttonsMatrix[line][column + 1], piece.isWhite)) {
                            
                            buttonsMatrix[line][column + 1].resetColorButton(true);
                            buttonsMatrix[line][column + 1].setBackground(RED_COLOR);
                            buttonsMatrix[line][column + 1].ableMove = true;
                            SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line][column + 1]);
                        }
                    }
                    if (column - 1 >= 0) {
                        if (!verifyPiece(buttonsMatrix[line][column - 1], piece.isWhite)) {

                            buttonsMatrix[line][column - 1].resetColorButton(true);
                            buttonsMatrix[line][column - 1].setBackground(RED_COLOR);
                            buttonsMatrix[line][column - 1].ableMove = true;
                            SuperButton.verifyRook(piece.isWhite, buttonsMatrix[line][column - 1]);
                        }
                    }

                    boolean isTower = false;
                    
                    if (Math.abs((screenSize.width - (int)(0.9 * screenSize.height))/2 + (int)((int)(0.9 * screenSize.height)/2) - piece.initialWidthPosition) <= ERROR_MARGIN && 
                        Math.abs((screenSize.height - (int)(0.9 * screenSize.height))/2 + 7 * (int)((int)(0.9 * screenSize.height)/8) - piece.initialHeightPosition) <= ERROR_MARGIN &&
                        piece.isWhite && whiteCanRook) {

                        if (!verifyPiece(buttonsMatrix[line][column + 1], true) && !verifyPiece(buttonsMatrix[line][column + 1], false) && !verifyPiece(buttonsMatrix[line][column + 2], true) && !verifyPiece(buttonsMatrix[line][column + 2], false)  
                            && verifyPiece(buttonsMatrix[line][column + 3], piece.isWhite)) {
                            
                            for (int i = 0; i < whitePieces.size(); i++) {
                                if (Math.abs(buttonsMatrix[line][column + 3].positionButtonWidth - whitePieces.get(i).initialWidthPosition) <= ERROR_MARGIN &&
                                    Math.abs(buttonsMatrix[line][column + 3].positionButtonHeight - whitePieces.get(i).initialHeightPosition) <= ERROR_MARGIN &&
                                    whitePieces.get(i) instanceof Tower) {
                                    
                                    towerRook = (Tower)whitePieces.get(i);
                                    isTower = true;
                                    break;
                                }
                            }
                            if (isTower) {
                                
                                buttonsMatrix[line][column + 3].resetColorButton(true);
                                buttonsMatrix[line][column + 3].setBackground(RED_COLOR);
                                buttonsMatrix[line][column + 3].ableMove = true;
                            }
                        }
                        if (!verifyPiece(buttonsMatrix[line][column - 1], true) && !verifyPiece(buttonsMatrix[line][column - 1], false) && !verifyPiece(buttonsMatrix[line][column - 2], true) && !verifyPiece(buttonsMatrix[line][column - 2], false)  
                            && !verifyPiece(buttonsMatrix[line][column - 3], true) && !verifyPiece(buttonsMatrix[line][column - 3], false) && verifyPiece(buttonsMatrix[line][column - 4], piece.isWhite)) {
                            
                            for (int i = 0; i < whitePieces.size(); i++) {
                                if (Math.abs(buttonsMatrix[line][column - 4].positionButtonWidth - whitePieces.get(i).initialWidthPosition) <= ERROR_MARGIN &&
                                    Math.abs(buttonsMatrix[line][column - 4].positionButtonHeight - whitePieces.get(i).initialHeightPosition) <= ERROR_MARGIN &&
                                    whitePieces.get(i) instanceof Tower) {

                                    towerRook = (Tower)whitePieces.get(i);
                                    isTower = true;
                                    break;
                                }
                            }
                            if (isTower) {
                                
                                buttonsMatrix[line][column - 4].resetColorButton(true);
                                buttonsMatrix[line][column - 4].setBackground(RED_COLOR);
                                buttonsMatrix[line][column - 4].ableMove = true;
                            }
                        }
                    }
                    else if (Math.abs((screenSize.width - (int)(0.9 * screenSize.height))/2 + 3 * (int)((int)(0.9 * screenSize.height)/8) - piece.initialWidthPosition) <= ERROR_MARGIN && 
                        Math.abs((screenSize.height - (int)(0.9 * screenSize.height))/2 + 7 * (int)((int)(0.9 * screenSize.height)/8) - piece.initialHeightPosition) <= ERROR_MARGIN &&
                        !piece.isWhite && blackCanRook) {
                       
                        if (!verifyPiece(buttonsMatrix[line][column - 1], true) && !verifyPiece(buttonsMatrix[line][column - 1], false) && !verifyPiece(buttonsMatrix[line][column - 2], true) && !verifyPiece(buttonsMatrix[line][column - 2], false)  
                            && verifyPiece(buttonsMatrix[line][column - 3], piece.isWhite)) {
                            
                            for (int i = 0; i < blackPieces.size(); i++) {
                                if (Math.abs(buttonsMatrix[line][column - 3].positionButtonWidth - blackPieces.get(i).initialWidthPosition) <= ERROR_MARGIN &&
                                    Math.abs(buttonsMatrix[line][column - 3].positionButtonHeight - blackPieces.get(i).initialHeightPosition) <= ERROR_MARGIN &&
                                    blackPieces.get(i) instanceof Tower) {

                                    towerRook = (Tower)blackPieces.get(i);
                                    isTower = true;
                                    break;
                                }
                            }
                            if (isTower) {
                                
                                buttonsMatrix[line][column - 3].resetColorButton(true);
                                buttonsMatrix[line][column - 3].setBackground(RED_COLOR);
                                buttonsMatrix[line][column - 3].ableMove = true;
                            }
                        }
                        if (!verifyPiece(buttonsMatrix[line][column + 1], true) && !verifyPiece(buttonsMatrix[line][column + 1], false) && !verifyPiece(buttonsMatrix[line][column + 2], true) && !verifyPiece(buttonsMatrix[line][column + 2], false)  
                            && !verifyPiece(buttonsMatrix[line][column + 3], true) && !verifyPiece(buttonsMatrix[line][column + 3], false) && verifyPiece(buttonsMatrix[line][column + 4], piece.isWhite)) {
                            
                            for (int i = 0; i < blackPieces.size(); i++) {
                                if (Math.abs(buttonsMatrix[line][column + 4].positionButtonWidth - blackPieces.get(i).initialWidthPosition) <= ERROR_MARGIN &&
                                    Math.abs(buttonsMatrix[line][column + 4].positionButtonHeight - blackPieces.get(i).initialHeightPosition) <= ERROR_MARGIN &&
                                    blackPieces.get(i) instanceof Tower) {

                                    towerRook = (Tower)blackPieces.get(i);
                                    isTower = true;
                                    break;
                                }
                            }
                            if (isTower) {
                                
                                buttonsMatrix[line][column + 4].resetColorButton(true);
                                buttonsMatrix[line][column + 4].setBackground(RED_COLOR);
                                buttonsMatrix[line][column + 4].ableMove = true;
                            }
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

    public static void verifyRook(boolean rookWhite, SuperButton button) {

        if (whiteCanRook || blackCanRook) {
            if (!rookWhite) {

                King whiteKing = null;
                for (Pieces king : whitePieces) {
                    if (king instanceof King) {
                        whiteKing = (King)king;
                        break;
                    }
                }
                if (Math.abs(button.positionButtonWidth - whiteKing.initialWidthPosition) <= ERROR_MARGIN && 
                    Math.abs(button.positionButtonHeight - whiteKing.initialHeightPosition) <= ERROR_MARGIN) {
                    
                    whiteCanRook = false;
                }
            }
            else {

                King blackKing = null;
                for (Pieces king : blackPieces) {
                    if (king instanceof King) {
                        blackKing = (King)king;
                        break;
                    }
                }
                if (Math.abs(button.positionButtonWidth - blackKing.initialWidthPosition) <= ERROR_MARGIN && 
                    Math.abs(button.positionButtonHeight - blackKing.initialHeightPosition) <= ERROR_MARGIN) {
                    
                    blackCanRook = false;
                }
            }
        }

    }
}