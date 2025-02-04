import java.awt.Image;

public abstract class Pieces {
    
    // Private properties to math;
    private static final int INITIALPIECEWIDTH = 392;
    private static final int INITIALPICEHEIGTH = 60;
    private static final int GRIDSIZE = 96;
    
    // Properties of the positions;
    private int initialWidthPosition;
    private int initialHeightPosition;

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
            if (initialWidthPosition + (width * GRIDSIZE) < INITIALPIECEWIDTH) {
                throw new Exception("Invalid width value");
            }
        }
        else {
            if (initialWidthPosition + (width * GRIDSIZE) > INITIALPIECEWIDTH + 7 * GRIDSIZE) {
                throw new Exception("Invalid width value");
            }
        }
        
        // Throws the Exceptions if the height value makes the piece out of the board;
        if (height < 0) {
            if (initialHeightPosition + (height * GRIDSIZE) < INITIALPICEHEIGTH) {
                throw new Exception("Invalid height value");
            }
        }
        else {
            if (initialHeightPosition + (height * GRIDSIZE) > INITIALPICEHEIGTH + 7 * GRIDSIZE) {
                throw new Exception("Invalid height value");
            }
        }
        
        // Setting the other postions;
        initialWidthPosition += width * GRIDSIZE;
        initialHeightPosition += height * GRIDSIZE;
    }

    // The abstract method to get the image; 
    public abstract Image getImage();
}