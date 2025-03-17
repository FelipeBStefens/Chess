package graphics;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PromotionButtons {
    
    static ImageIcon whiteQueenIcon = new ImageIcon(System.getProperty("user.dir") + "/Images/Pieces/WhitePieces/WhiteQueen.png");
    static ImageIcon whiteTowerIcon = new ImageIcon(System.getProperty("user.dir") + "/Images/Pieces/WhitePieces/WhiteTower.png");
    static ImageIcon whiteBishopIcon = new ImageIcon(System.getProperty("user.dir") + "/Images/Pieces/WhitePieces/WhiteBishop.png");
    static ImageIcon whiteHorseIcon = new ImageIcon(System.getProperty("user.dir") + "/Images/Pieces/WhitePieces/WhiteHorse.png");
    static ImageIcon blackQueenIcon = new ImageIcon(System.getProperty("user.dir") + "/Images/Pieces/BlackPieces/BlackQueen.png");
    static ImageIcon blackTowerIcon = new ImageIcon(System.getProperty("user.dir") + "/Images/Pieces/BlackPieces/BlackTower.png");
    static ImageIcon blackBishopIcon = new ImageIcon(System.getProperty("user.dir") + "/Images/Pieces/BlackPieces/BlackBishop.png");
    static ImageIcon blackHorseIcon = new ImageIcon(System.getProperty("user.dir") + "/Images/Pieces/BlackPieces/BlackHorse.png");
    static String promotedPiece = "";

    public static String promotionButtons(JFrame frame, boolean isWhite) {
        
        JButton queenButton = new JButton(isWhite ? whiteQueenIcon : blackQueenIcon);
        JButton towerButton = new JButton(isWhite ? whiteTowerIcon : blackTowerIcon);
        JButton bishopButton = new JButton(isWhite ? whiteBishopIcon : blackBishopIcon);
        JButton horseButton = new JButton(isWhite ? whiteHorseIcon : blackHorseIcon);
        
        JButton[] buttons = {queenButton, towerButton, bishopButton, horseButton};
        
        ActionListener listener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                JButton source = (JButton) e.getSource();

                if (source == queenButton) {
                    promotedPiece = "Queen";
                } 
                else if (source == towerButton) {
                    promotedPiece = "Tower";
                } 
                else if (source == bishopButton) {
                    promotedPiece = "Bishop";
                } 
                else if (source == horseButton) {
                    promotedPiece = "Horse";
                }

                JOptionPane.getRootFrame().dispose();
            }
        };

        for (JButton button : buttons) {
            button.addActionListener(listener);
        }

        JOptionPane.showOptionDialog(null, "Choose the piece to promote:",
                "Pawn Promotion", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, buttons, buttons[0]);

        return promotedPiece;
    }
}