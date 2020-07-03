package sample;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Random;

import static sample.Main.demonCount;
import static sample.Main.getScore;

public class Enemy {

    static Node cacodemon;
    static Node goblin;
    static Node lostsoul;

    public static void demonSpawnOrder(Pane pane) {

        Random random = new Random();

        int demonId = random.nextInt(3); // corresponds with amount of demons  to start counting at 0

        // TODO: 5/8/20 use function to preform action in switch v
        switch (demonId) {
            case 0:
                cacodemon = Enemy.renderDemon("assets/moving-Cacodemon.gif", "assets/explosion.gif");
                Enemy.spawnLocation(cacodemon); //spawns demon in random position - May be bugged
                pane.getChildren().add(cacodemon);
                Main.setDemonCount(Main.getDemonCount() + 1);
                break;
            case 1:
                goblin = Enemy.renderDemon("assets/pizza.gif", "assets/explosion.gif");
                Enemy.spawnLocation(goblin); //spawns demon in random position - May be bugged
                pane.getChildren().add(goblin);
                Main.setDemonCount(Main.getDemonCount() + 1);
                break;

            case 2:
                lostsoul = Enemy.renderDemon("assets/lost-soul.gif", "assets/explosion.gif");
                Enemy.spawnLocation(lostsoul); //spawns demon in random position - May be bugged
                pane.getChildren().add(lostsoul);
                Main.setDemonCount(Main.getDemonCount() + 1);
                break;
            default:
                System.out.println("error @ demonSpawnOrder");
                System.exit(-1);
        }
    }


    // image to other image on click
    public static Node renderDemon(String startImg, String endImg) {


        // enemy animation
        ImageView enemyGraphic = new ImageView(new Image(startImg));
        enemyGraphic.setPreserveRatio(true);
        enemyGraphic.setFitHeight(120);

        // action when enemy clicked - sets image + mouse event
        enemyGraphic.setOnMouseClicked((MouseEvent mouseEvent) -> {

            enemyGraphic.setImage(new Image(endImg));

            Utility.randomizeSound(); // random explosion sound 3 separate
            explosionFade(enemyGraphic); // quickly fade explosion after click

            // delays 300Ms before deleting the node so we can see the Explosion
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(300), ev -> {
                Main.gamePane.getChildren().remove(enemyGraphic);// test

            }));
            timeline.setCycleCount(1);
            timeline.play();

            if(mouseEvent.getClickCount() == 1){
                Main.setScore(Main.getScore() + 75);
                Main.setDemonCount(Main.getDemonCount() - 1);
            }


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
        int enemySpawnX = random.nextInt(Main.SCENE_WIDTH - 100); // 100 is to compensate for size of demon
        int enemySpawnY = random.nextInt(Main.SCENE_HEIGHT - 100);

        // set position of spawn
        demon.setLayoutX(enemySpawnX);
        demon.setLayoutY(enemySpawnY);
    }

}
