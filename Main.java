package Chess;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {

        SuperButton.board.openBoard();

        Thread soundThread = new Thread(() -> {
            try {
                String path = System.getProperty("user.dir") + "\\Sounds\\";

                Clip clipSt = createClip(path + "SoundTrack.wav");
                Clip clipGr = createClip(path + "GoodResult.wav");
                Clip clipVs = createClip(path + "VictorySound.wav");

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

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Start
        soundThread.start();
    }

    // Método auxiliar pra criar um Clip
    public static Clip createClip(String path) throws Exception {
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(path));
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        return clip;
    }
}
