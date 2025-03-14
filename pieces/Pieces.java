package Chess.pieces;

import java.awt.*;

public abstract class Pieces {

    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    // Private properties to math;
    private static final int INITIAL_PIECE_WIDTH = (screenSize.width - (int)(0.9 * screenSize.height))/2;
    private static final int INITIAL_PIECE_HEIGHT = (screenSize.height - (int)(0.9 * screenSize.height))/2;
    private static final int GRID_SIZE = (int) (((int)(0.9 * screenSize.height))/8);
    
    // Properties of the positions and color;
    public int initialWidthPosition;
    public int initialHeightPosition;
    public boolean isWhite;

    // The constructor of the class;
    public Pieces(int initialWidthPosition, int initialHeightPosition) {
        
        this.initialWidthPosition = initialWidthPosition;
        this.initialHeightPosition = initialHeightPosition;
    }

    // Get method to get the width 
    public int getPieceWidth() {
        return initialWidthPosition;
    }

    // Get method to get the heigth;
    public int getPieceHeight() {
        return initialHeightPosition;
    }

    public static int getGridSize(){
        return GRID_SIZE;
    }

    // Set the size of the width and the heigth, used to move graphically the piece;
    public void addSize(int width, int height) throws Exception {
        
        // Throws the Exception if the values are invalid;
        if (width < -7 || width > 7 || height < -7 || height > 7) {
            throw new Exception("Invalid width or heigth value");
        }

        // Throws the Exceptions if the width value makes the piece out of the board;
        if (width < 0) {
            if (initialWidthPosition + (width * GRID_SIZE) < INITIAL_PIECE_WIDTH) {
                throw new Exception("Invalid width value");
            }
        }
        else {
            if (initialWidthPosition + (width * GRID_SIZE) > INITIAL_PIECE_WIDTH + 7 * GRID_SIZE) {
                throw new Exception("Invalid width value");
            }
        }
        
        // Throws the Exceptions if the height value makes the piece out of the board;
        if (height < 0) {
            if (initialHeightPosition + (height * GRID_SIZE) < INITIAL_PIECE_HEIGHT) {
                throw new Exception("Invalid height value");
            }
        }
        else {
            if (initialHeightPosition + (height * GRID_SIZE) > INITIAL_PIECE_HEIGHT + 7 * GRID_SIZE) {
                throw new Exception("Invalid height value");
            }
        }
        
        // Setting the other postions;
        initialWidthPosition += width * GRID_SIZE;
        initialHeightPosition += height * GRID_SIZE;
    }

    // The abstract method to get the image; 
    public abstract Image getImage();
}