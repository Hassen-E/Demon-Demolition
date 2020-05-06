package sample;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Enemy {



    // image to other image on click
    public static Node renderDemon(String startImg, String endImg) {


        // enemy animation
        ImageView enemyGraphic = new ImageView(new Image(startImg));
        enemyGraphic.setPreserveRatio(true);
        enemyGraphic.setFitHeight(120);

        // action when enemy clicked - sets image + mouse event
        enemyGraphic.setOnMouseClicked((MouseEvent e) -> {

            enemyGraphic.setImage(new Image(endImg));

            Utility.randomizeSound(); // random explosion sound 3 separate
            explosionFade(enemyGraphic); // quickly fade explosion after click

            // delays 300Ms before deleting the node so we can see the Explosion
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(300), ev -> {
                Main.gameBoard.getChildren().remove(enemyGraphic);
            }));
            timeline.setCycleCount(1);
            timeline.play();

            // add one point
            Main.setScore(+1);
            Main.demonCount --;
        });

        return enemyGraphic;
    }

    public static void explosionFade(Node graphic) {

        // quickly fade explosion after click
        FadeTransition ft = new FadeTransition(Duration.millis(400), graphic);
        ft.setFromValue(1.0);
        ft.setToValue(1);
        ft.setCycleCount(0);
        ft.setAutoReverse(true);
        ft.play();
    }


    public static void spawnLocation(Node demon) {

        // size of canvas
        Random random = new Random();
        /* May cause bug if demon is not size 100! */
        int enemySpawnX = random.nextInt(Main.sceneWidth - 100); // 100 is to compensate for size of demon
        int enemySpawnY = random.nextInt(Main.sceneHeight - 100);

        // set position of spawn
        demon.setLayoutX(enemySpawnX);
        demon.setLayoutY(enemySpawnY);
    }

    public void demonLifeSpan() {
        // depending on difficulty demon will stay on screen:
        // easy: 4s, med: 2s, hard: 1s


    }

    // There are to way for an enemy to "die"
    // 1) shrink before player can click them - ^ see above
    // 2) clicked by player -- addressed in method: die()

    public void die() {


    }
}
