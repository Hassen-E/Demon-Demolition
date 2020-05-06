package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.Random;

/** Demon Demolition
 * @author Hassen E
 * @version 1.0
 */
public class Main extends Application {

    public static int sceneWidth = 800;
    public static int sceneHeight = 600;
    public static int score = 0;
    public static boolean gameOver = false;
    public static Pane gameBoard;
    private static MediaPlayer mediaPlayer;
    private static MediaPlayer menuMediaPlayer;


    // difficulty settings
    public static int spawnSpeed;
    public static int demonCount = 0; // number of demons on map

    public Stage window;

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        Main.score = score;
    }

    public static int getSpawnSpeed() {
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

    public Scene gameScene;
    public Scene menuScene;



    public static int getSceneWidth() {
        return sceneWidth;
    }

    public static int getSceneHeight() {
        return sceneHeight;
    }

    // TODO: 5/1/20 add difficulty to ui
    private enum DIFFICULTY {
        EASY,
        MEDIUM,
        HARD
    }

    private enum STATE {
        MENU,
        GAME,
        GAMEOVER
    }

    private STATE state = STATE.MENU;

    public void demonSpawnOrder() {

        Node cacodemon;
        Node goblin;
        Node lostsoul;

        Random random = new Random();

        int demonNumber = random.nextInt(3); // corresponds with amount of demons  to start counting at 0
        System.out.println("Demon number: " + demonNumber);

        switch (demonNumber) {
            case 0:
                cacodemon = Enemy.renderDemon("assets/moving-Cacodemon.gif", "assets/explosion.gif");
                Enemy.spawnLocation(cacodemon); //spawns demon in random position - May be bugged
                gameBoard.getChildren().add(cacodemon);
                break;
            case 1:
                goblin = Enemy.renderDemon("assets/flying-goblin.gif", "assets/explosion.gif");
                Enemy.spawnLocation(goblin); //spawns demon in random position - May be bugged
                gameBoard.getChildren().add(goblin);
                break;

            case 2:
                lostsoul = Enemy.renderDemon("assets/lost-soul.gif", "assets/explosion.gif");
                Enemy.spawnLocation(lostsoul); //spawns demon in random position - May be bugged
                gameBoard.getChildren().add(lostsoul);
                break;
            default:
                System.out.println("error @ demonSpawnOrder");
                System.exit(-1);
        }
    }

    // TODO: 5/1/20 fix: demons spawn before start button is pressed
    @Override
    public void start(Stage primaryStage) throws Exception {


//      if (state == STATE.GAME) {
        Timeline gameLoop = new Timeline(new KeyFrame(Duration.seconds(.5), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                demonSpawnOrder();
                demonCount++;
                System.out.println("demon count: " + demonCount);
                if (demonCount >= 10) {
                    System.exit(2);
                }

            }
        }));
        gameLoop.setCycleCount(100); // change to 1!

        // Menu -> move to new class once working

        Label title = new Label("Demon Demolition");
        title.setWrapText(true);
        title.getStyleClass().add("title");

        Image buttonImage = new Image("assets/playButton.png");
        ImageView playButton = new ImageView(buttonImage);

        playButton.setPreserveRatio(true);
        playButton.setFitWidth(280);

        // start button action
        playButton.setOnMouseClicked((MouseEvent e) -> {
            Utility.playSFX("src/assets/sound/menu-ding.wav");
            state = STATE.GAME;
            // play music on click event
            primaryStage.setScene(gameScene);
            gameLoop.play();

            // game audio
            Media sound = new Media(new File("src/assets/sound/game-music.mp3").toURI().toString());
            mediaPlayer = new MediaPlayer(sound);
            menuMediaPlayer.pause();
            mediaPlayer.play();
            // TODO: 5/5/20 loop file to replay song after finished 
        });

        // menu page layout
        VBox layout = new VBox(300); // spacing between nodes
        layout.getStyleClass().add("menu");
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(title, playButton);

        // menu audio
        Media sound = new Media(new File("src/assets/sound/menu-music.mp3").toURI().toString());
        menuMediaPlayer = new MediaPlayer(sound);
        menuMediaPlayer.play();

        menuScene = new Scene(layout, sceneWidth, sceneHeight);
        menuScene.getStylesheets().add("sample/style.css");


        gameBoard = new Pane();

        // difficulty


        // Gameover



        // JFX boilerplate
        gameScene = new Scene(gameBoard, sceneWidth, sceneHeight);
        gameScene.getStylesheets().add("sample/style.css");
        gameBoard.getStyleClass().add("game");

        primaryStage.setOnCloseRequest(e -> System.out.println("score: " + score)); // TODO: add score to GUI Gameover screen
        primaryStage.setTitle("DOOM!");
        primaryStage.setResizable(false);
        primaryStage.setScene(menuScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
