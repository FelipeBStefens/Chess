package Chess;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception{

        SuperButton.board.openBoard();
        try {
            File soundFile = new File(System.getProperty("user.dir") + "\\Sounds\\SoundTrack.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float volume = -25.0f;
            volumeControl.setValue(volume);

            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();

            Thread.sleep(Long.MAX_VALUE);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }


    }
}
