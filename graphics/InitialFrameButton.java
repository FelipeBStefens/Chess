package Chess.graphics;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class InitialFrameButton extends JButton implements ActionListener {

    JFrame frame;

    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public InitialFrameButton(JFrame frame) throws IOException, FontFormatException {
        super("Start");

        this.frame = frame;

        Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(System.getProperty("user.dir")+ "\\Fonts\\DancingScript-Bold.ttf"));
        customFont = customFont.deriveFont(Font.PLAIN, 130);

        addActionListener(this);
        setBounds(screenSize.width - 500, 60, 400, 150);
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        setFont(customFont);
        setFocusPainted(false);
        setOpaque(true);
        setVisible(true);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(Color.DARK_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e){
                setBackground(Color.BLACK);
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        InitialFrame.clipIs.stop();

        frame.dispose();
        try {
            SuperButton.board.openBoard();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        Thread soundThread = new Thread(() -> {
            try {
                String path = System.getProperty("user.dir") + "\\Sounds\\";

                Clip clipSt = InitialFrame.createClip(path + "SoundTrack.wav");
                Clip clipGr = InitialFrame.createClip(path + "GoodResult.wav");
                Clip clipVs = InitialFrame.createClip(path + "VictorySound.wav");

                // Fixing the Volume
                FloatControl volumeControlSt = (FloatControl) clipSt.getControl(FloatControl.Type.MASTER_GAIN);
                FloatControl volumeControlGr = (FloatControl) clipGr.getControl(FloatControl.Type.MASTER_GAIN);
                FloatControl volumeControlVs = (FloatControl) clipVs.getControl(FloatControl.Type.MASTER_GAIN);
                float volume = -25.0f;
                volumeControlSt.setValue(volume);
                volumeControlGr.setValue(volume);
                volumeControlVs.setValue(volume);

                clipSt.loop(Clip.LOOP_CONTINUOUSLY);
                clipSt.start();

                while (!GraphicalBoard.endGame) {
                    Thread.sleep(100);
                }

                clipSt.stop();
                clipGr.start();
                clipVs.start();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Start
        soundThread.start();
    }

    @Override
    public void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 50, 50);
    }

}
