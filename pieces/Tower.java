package Chess.pieces;

import javax.swing.*;
import java.awt.*;

public class Tower extends Pieces {

    // The constructor of the class;
    public Tower(boolean isWhite, int initialWidthPosition, int initialHeightPosition) {

        // Using the constructor of the inherited class;
        super(initialWidthPosition, initialHeightPosition);

        // Set a value of the properties using the constructor and this; 
        this.isWhite = isWhite;
    }

    // Returning the Image of the piece using Override;
    @Override
    public Image getImage() {

        // Verifying if it's white or black;
        if (isWhite) {
            return new
                    ImageIcon(System.getProperty("user.dir") + "/Images/Pieces/WhitePieces/WhiteTower.png").getImage();
        }
        else {
            return new 
                ImageIcon(System.getProperty("user.dir") + "/Images/Pieces/BlackPieces/BlackTower.png").getImage();
        }
    }
}
