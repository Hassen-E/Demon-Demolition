package sample;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import static sample.Game.gameLoop;

/** Demon Demolition
 * @author Hassen E
 * @version 1.1 - organized code, fix scene bugs
 */
public class Main extends Application {

    public static final int SCENE_WIDTH = 800;
    public static final int SCENE_HEIGHT = 600;
    public static int score = 0;
    public static boolean gameOver = false;
    public static double spawnSpeed = .9;
    public static int demonCount = 0; // number of demons on map

    public static Pane gamePane;

    private static MediaPlayer mediaPlayer;
    private static MediaPlayer menuMediaPlayer;


    public Enemy enemy = new Enemy();
    public Stage primaryStage;
    public Menu menu;
    public Timeline gameLoop;
    public Game game;
    public Game.STATE state;

    public Scene gameScene;
    public Scene menuScene;


    // TODO: 5/1/20 add difficulty to UI



    // TODO: 5/1/20 fix: demons spawn before start button is pressed
    @Override
    public void start(Stage primaryStage) throws Exception {


        menu = new Menu();
        menuScene = menu.getMenuScene(primaryStage, gameScene);
        primaryStage.setScene(menuScene);

        game = new Game();

        // TODO: add score to GUI Gameover screen
        primaryStage.setOnCloseRequest(e -> System.out.println("score: " + score));

        primaryStage.setTitle("DOOM!");
//        primaryStage.setResizable(false);
        primaryStage.show();
    }



    // Getters/ setters
    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        Main.score = score;
    }

    public static double getSpawnSpeed() {
        return spawnSpeed;
    }

    public static void setSpawnSpeed(int spawnSpeed) {
        Main.spawnSpeed = spawnSpeed;
    }

    public static int getDemonCount() {
        return demonCount;
    }

    public static void setDemonCount(int demonCount) {
        Main.demonCount = demonCount;
    }

    public static int getSceneWidth() {
        return SCENE_WIDTH;
    }

    public static int getSceneHeight() {
        return SCENE_HEIGHT;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
