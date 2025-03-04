import java.awt.Image;
import javax.swing.ImageIcon;

public class Horse extends Pieces {

    // The constructor of the class;
    public Horse(boolean isWhite, int initialWidthPosition, int initialHeightPosition) {

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
                ImageIcon("C:/Users/jstef/Desktop/Projetos Programação/JavaProjects/Chess/Images/Pieces/WhitePieces/WhiteHorse.png").getImage();
        }
        else {
            return new 
                ImageIcon("C:/Users/jstef/Desktop/Projetos Programação/JavaProjects/Chess/Images/Pieces/BlackPieces/BlackHorse.png").getImage();
        }
    }
}
