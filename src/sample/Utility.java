package sample;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Utility {

    private static MediaPlayer mediaPlayer;

    public static Random random;

    public static void setDelay(int durationMs) {

        // delay between explosion fade and value set to null
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 123 nothing...
            }
        }, durationMs);
    }


    public static void playSFX(String soundFile) {

        Media sound = new Media(new File(soundFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    public static void randomizeSound() {

        // randomized the sound fx

        random = new Random();
        int audioChoice = random.nextInt(3);

        switch (audioChoice) {

            case 0:
                playSFX("src/assets/sound/explosion-1.wav");
                break;
            case 1:
                playSFX("src/assets/sound/explosion-2.wav");
                break;
            case 2:
                playSFX("src/assets/sound/explosion-3.wav");
                break;
            default:
                playSFX("src/assets/sound/lose-song.wav");
            // no default
        }


    }
}
