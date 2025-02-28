import java.awt.Image;

public abstract class Pieces {
    
    // Private properties to math;
    private static final int INITIAL_PIECE_WIDTH = 392;
    private static final int INITIAL_PIECE_HEIGHT = 60;
    private static final int GRID_SIZE = 96;
    
    // Properties of the positions;
    public int initialWidthPosition;
    public int initialHeightPosition;

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