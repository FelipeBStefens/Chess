import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SuperButton extends JButton implements ActionListener{

    // All the buttons in a matrix;
    private static SuperButton[][] buttonsMatrix = new SuperButton[8][8];

    // Constants properties;
    private static final int LENGTH_BUTTON = 96;
    private static final Color GREEN_COLOR = new Color(129, 235, 103, 50);
    private static final Color RED_COLOR = new Color(235, 52, 52, 130);
    private static final Color GRAY_COLOR = new Color(101, 97, 102, 50);

    // Buttons to change the action;
    private int positionButtonWidth;
    private int positionButtonHeight;
    private boolean ableMove = false;
    private boolean isDisabled = false;
    private boolean isClicked = false;

    // The constructor of the class;
    public SuperButton(int positionButtonWidth, int positionButtonHeight){

        // activating and creating the button;
        addActionListener(this);
        this.positionButtonWidth = positionButtonWidth;
        this.positionButtonHeight = positionButtonHeight; 
        setBounds(positionButtonWidth, positionButtonHeight, LENGTH_BUTTON, LENGTH_BUTTON);

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

                if (isClicked) {
                    setBackground(GRAY_COLOR);
                }
                else if (ableMove) {
                    setBackground(RED_COLOR);
                }
                // To complete this part...
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

                if (isClicked) {
                    setBackground(GRAY_COLOR);
                }
                else if (ableMove) {
                    setBackground(RED_COLOR);
                }
                // To complete this part...
                else if (isDisabled) {
                    setVisible(false);
                }
                else {
                    resetColorButton(false);
                }
                
            }
        });
    }

    // The performance of the button once is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        
        SuperButton.onceClicked(GraphicalBoard.blackKing, this);
    }

    // Disable the buttons that aren't selected;
    private static void disableButtons() {

        // Analysing aall the buttons;
        for (SuperButton[] buttonArray : buttonsMatrix) {
            for (SuperButton button : buttonArray) {
                
                if (!button.ableMove && !button.isClicked) {
                    // Disable the button;
                    button.isDisabled = true;
                }
            }
        }
    }

    // Reseting the opaque and the area filled;
    private void resetColorButton(boolean value) {
        setOpaque(value);
        setContentAreaFilled(value);
    }

    // Selecting all the buttons in a matrix;
    public static void setButtonsMatrix(SuperButton[][] buttonsMatrix) {
        SuperButton.buttonsMatrix = buttonsMatrix;
    }

    // When the button is clicked by the User;
    private static void onceClicked(Pieces piece, SuperButton button) {

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
        // Setting the backgroud of the clicked buttn;
        button.setBackground(GRAY_COLOR);
        button.isClicked = true;
        
        // Analysing the possible buttons to click with the positions of the button and the piece; 
        if (button.positionButtonWidth - 1 == piece.initialWidthPosition && 
            button.positionButtonHeight + 3 == piece.initialHeightPosition) {

            // If the piece is a Pawn;
            if (piece instanceof Pawn) {

                if(((Pawn)piece).isWhite) {
        
                    if (line - 1 >= 0) {
                        buttonsMatrix[line - 1][column].resetColorButton(true);
                        buttonsMatrix[line - 1][column].setBackground(RED_COLOR);
                        buttonsMatrix[line - 1][column].ableMove = true;
                    }
                    
                }
                else {
        
                    if (line + 1 <= 7) {
                        buttonsMatrix[line + 1][column].resetColorButton(true);
                        buttonsMatrix[line + 1][column].setBackground(RED_COLOR);
                        buttonsMatrix[line + 1][column].ableMove = true;
                    } 
                }
                disableButtons();
            }
            // If the piece is a Tower;
            else if (piece instanceof Tower) {

                for (int i = -8; i < 8; i++) {

                    if (i != 0) {
                        if (line + i >= 0 && line + i <= 7) {
                            buttonsMatrix[line + i][column].resetColorButton(true);
                            buttonsMatrix[line + i][column].setBackground(RED_COLOR);
                            buttonsMatrix[line + i][column].ableMove = true;
                        }
                        if (column + i >= 0 && column + i <= 7) {
                            buttonsMatrix[line][column + i].resetColorButton(true);
                            buttonsMatrix[line][column + i].setBackground(RED_COLOR);
                            buttonsMatrix[line][column + i].ableMove = true;
                        }
                    }
                }
                disableButtons();
            }
            // If the piece is a Bishop;
            else if (piece instanceof Bishop) {

                for (int i = 1; i < 8; i++) { 

                    if (line - i >= 0 && column + i <= 7) {
                        buttonsMatrix[line - i][column + i].resetColorButton(true);
                        buttonsMatrix[line - i][column + i].setBackground(RED_COLOR);
                        buttonsMatrix[line - i][column + i].ableMove = true;
                    }
                    if (line - i >= 0 && column - i >= 0) {
                        buttonsMatrix[line - i][column - i].resetColorButton(true);
                        buttonsMatrix[line - i][column - i].setBackground(RED_COLOR);
                        buttonsMatrix[line - i][column - i].ableMove = true;
                    }
                    if (line + i <= 7 && column + i <= 7) {
                        buttonsMatrix[line + i][column + i].resetColorButton(true);
                        buttonsMatrix[line + i][column + i].setBackground(RED_COLOR);
                        buttonsMatrix[line + i][column + i].ableMove = true;
                    }
                    if (line + i <= 7 && column - i >= 0) {
                        buttonsMatrix[line + i][column - i].resetColorButton(true);
                        buttonsMatrix[line + i][column - i].setBackground(RED_COLOR);
                        buttonsMatrix[line + i][column - i].ableMove = true;
                    }
                }
                disableButtons();
            }
            // If the piece is a Horse;
            else if (piece instanceof Horse) {

                if (line + 2 <= 7) {
                    if (column + 1 <= 7) {
                        buttonsMatrix[line + 2][column + 1].resetColorButton(true);
                        buttonsMatrix[line + 2][column + 1].setBackground(RED_COLOR);
                        buttonsMatrix[line + 2][column + 1].ableMove = true;
                    }
                    if (column - 1 >= 0) {
                        buttonsMatrix[line + 2][column - 1].resetColorButton(true);
                        buttonsMatrix[line + 2][column - 1].setBackground(RED_COLOR);
                        buttonsMatrix[line + 2][column - 1].ableMove = true;
                    }
                }
                if (line - 2 >= 0) {
                    if (column + 1 <= 7) {
                        buttonsMatrix[line - 2][column + 1].resetColorButton(true);
                        buttonsMatrix[line - 2][column + 1].setBackground(RED_COLOR);
                        buttonsMatrix[line - 2][column + 1].ableMove = true;
                    }
                    if (column - 1 >= 0) {
                        buttonsMatrix[line - 2][column - 1].resetColorButton(true);
                        buttonsMatrix[line - 2][column - 1].setBackground(RED_COLOR);
                        buttonsMatrix[line - 2][column - 1].ableMove = true;
                    }
                }
                if (line + 1 <= 7) {
                    if (column + 2 <= 7) {
                        buttonsMatrix[line + 1][column + 2].resetColorButton(true);
                        buttonsMatrix[line + 1][column + 2].setBackground(RED_COLOR);
                        buttonsMatrix[line + 1][column + 2].ableMove = true;
                    }
                    if (column - 2 >= 0) {
                        buttonsMatrix[line + 1][column - 2].resetColorButton(true);
                        buttonsMatrix[line + 1][column - 2].setBackground(RED_COLOR);
                        buttonsMatrix[line + 1][column - 2].ableMove = true;
                    }
                }
                if (line - 1 >= 0) {
                    if (column + 2 <= 7) {
                        buttonsMatrix[line - 1][column + 2].resetColorButton(true);
                        buttonsMatrix[line - 1][column + 2].setBackground(RED_COLOR);
                        buttonsMatrix[line - 1][column + 2].ableMove = true;
                    }
                    if (column - 2 >= 0) {
                        buttonsMatrix[line - 1][column - 2].resetColorButton(true);
                        buttonsMatrix[line - 1][column - 2].setBackground(RED_COLOR);
                        buttonsMatrix[line - 1][column - 2].ableMove = true;
                    }
                }
                disableButtons();
            }
            // If the piece is a Queen;
            else if (piece instanceof Queen) {

                for (int i = -8; i < 8; i++) {

                    if (i != 0) {
                        if (line + i >= 0 && line + i <= 7) {
                            buttonsMatrix[line + i][column].resetColorButton(true);
                            buttonsMatrix[line + i][column].setBackground(RED_COLOR);
                            buttonsMatrix[line + i][column].ableMove = true;
                        }
                        if (column + i >= 0 && column + i <= 7) {
                            buttonsMatrix[line][column + i].resetColorButton(true);
                            buttonsMatrix[line][column + i].setBackground(RED_COLOR);
                            buttonsMatrix[line][column + i].ableMove = true;
                        }
                    }
                    if (i >= 1 && i < 8) {
                        if (line - i >= 0 && column + i <= 7) {
                            buttonsMatrix[line - i][column + i].resetColorButton(true);
                            buttonsMatrix[line - i][column + i].setBackground(RED_COLOR);
                            buttonsMatrix[line - i][column + i].ableMove = true;
                        }
                        if (line - i >= 0 && column - i >= 0) {
                            buttonsMatrix[line - i][column - i].resetColorButton(true);
                            buttonsMatrix[line - i][column - i].setBackground(RED_COLOR);
                            buttonsMatrix[line - i][column - i].ableMove = true;
                        }
                        if (line + i <= 7 && column + i <= 7) {
                            buttonsMatrix[line + i][column + i].resetColorButton(true);
                            buttonsMatrix[line + i][column + i].setBackground(RED_COLOR);
                            buttonsMatrix[line + i][column + i].ableMove = true;
                        }
                        if (line + i <= 7 && column - i >= 0) {
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
                    buttonsMatrix[line + 1][column].resetColorButton(true);
                    buttonsMatrix[line + 1][column].setBackground(RED_COLOR);
                    buttonsMatrix[line + 1][column].ableMove = true;

                    if (column + 1 <= 7) {
                        buttonsMatrix[line + 1][column + 1].resetColorButton(true);
                        buttonsMatrix[line + 1][column + 1].setBackground(RED_COLOR);
                        buttonsMatrix[line + 1][column + 1]. ableMove = true;
                    }
                    if (column - 1 >= 0) {
                        buttonsMatrix[line + 1][column - 1].resetColorButton(true);
                        buttonsMatrix[line + 1][column - 1].setBackground(RED_COLOR);
                        buttonsMatrix[line + 1][column - 1].ableMove = true;
                    }
                }
                if (line - 1 >= 0) {
                    buttonsMatrix[line - 1][column].resetColorButton(true);
                    buttonsMatrix[line - 1][column].setBackground(RED_COLOR);
                    buttonsMatrix[line - 1][column].ableMove = true;

                    if (column + 1 <= 7) {
                        buttonsMatrix[line - 1][column + 1].resetColorButton(true);
                        buttonsMatrix[line - 1][column + 1].setBackground(RED_COLOR);
                        buttonsMatrix[line - 1][column + 1].ableMove = true;
                    }
                    if (column - 1 >= 0) {
                        buttonsMatrix[line - 1][column - 1].resetColorButton(true);
                        buttonsMatrix[line - 1][column - 1].setBackground(RED_COLOR);
                        buttonsMatrix[line - 1][column - 1].ableMove = true;
                    }
                }
                if (column + 1 <= 7) {
                    buttonsMatrix[line][column + 1].resetColorButton(true);
                    buttonsMatrix[line][column + 1].setBackground(RED_COLOR);
                    buttonsMatrix[line][column + 1].ableMove = true;
                }
                if (column - 1 >= 0) {
                    buttonsMatrix[line][column - 1].resetColorButton(true);
                    buttonsMatrix[line][column - 1].setBackground(RED_COLOR);
                    buttonsMatrix[line][column - 1].ableMove = true;
                }
                disableButtons();
            }
        }
    }
    
}
