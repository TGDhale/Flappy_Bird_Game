import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.net.URL;

public class Sound {
    private Clip clip;

    public Sound(String fileName) {
        try {
            URL url = getClass().getResource(fileName);
            AudioInputStream audio = AudioSystem.getAudioInputStream(
                    new BufferedInputStream(url.openStream()));
            clip = AudioSystem.getClip();
            clip.open(audio);
        } catch (Exception e) {
            System.out.println("Could not load sound: " + fileName);
            e.printStackTrace();
        }
    }

    // play once (restarts if already playing)
    public void play() {
        if (clip == null) return;
        if (clip.isRunning()) clip.stop();
        clip.setFramePosition(0);
        clip.start();
    }

    // play continuously (for background music)
    public void loop() {
        if (clip == null) return;
        clip.setFramePosition(0);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        if (clip != null) clip.stop();
    }
}