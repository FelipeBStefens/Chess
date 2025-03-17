package Chess.graphics;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class InitialFrame extends JFrame {

    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static String path = System.getProperty("user.dir") + "\\Sounds\\";
    public static Clip clipIs;

    static {
        try {
            clipIs = createClip(path + "InitialST.wav");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void initialWindow() throws Exception {

        FloatControl volumeControlSt = (FloatControl) clipIs.getControl(FloatControl.Type.MASTER_GAIN);
        volumeControlSt.setValue(-25.0f);
        clipIs.open();
        clipIs.start();
        clipIs.loop(Clip.LOOP_CONTINUOUSLY);

        Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(System.getProperty("user.dir")+ "\\Fonts\\DancingScript-Bold.ttf"));
        customFont = customFont.deriveFont(Font.PLAIN, 130);

        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(screenSize.width, screenSize.height);
        setFont(customFont);

        ImageIcon imageIcon = new ImageIcon(System.getProperty("user.dir") + "/Images/ChessIcon.jpg");
        setIconImage(imageIcon.getImage());

        ImageIcon backgroundImage = new ImageIcon(System.getProperty("user.dir") + "/Images/ChessInitialImage.jpg");
        JLabel background = new JLabel("", backgroundImage, JLabel.CENTER);
        background.setBounds(0, 0, screenSize.width, screenSize.height);

        add(background);

        JLabel textArea = new JLabel("Chess");
        textArea.setBounds(100, 60, 400, 150);
        textArea.setForeground(Color.WHITE);
        textArea.setBackground(Color.BLACK);
        textArea.setFont(customFont);
        textArea.setOpaque(true);
        textArea.setBackground(null);
        textArea.setOpaque(false);
        textArea.setHorizontalAlignment(SwingConstants.CENTER);
        textArea.setVerticalAlignment(SwingConstants.CENTER);

        background.add(textArea);

        InitialFrameButton but = new InitialFrameButton(this);

        add(but);

        setVisible(true);
    }

    public static Clip createClip(String path) throws Exception {
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(path));
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        return clip;
    }
}
