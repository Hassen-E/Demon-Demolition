package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

import static sample.Main.*;

public class Game {

    Enemy enemy;
    Scene gameScene;
    MediaPlayer gameMediaPlayer;
    GameOver gameOver;
    public static Timeline gameLoop;
    public STATE state = STATE.GAME;


    public enum STATE {
        MENU,
        GAME,
        GAMEOVER
    }


    public Scene getGameScene(Stage mainStage) {
        
        // start game music
        // TODO: 5/5/20 loop file to replay song after finished
        Media sound = new Media(new File("src/assets/sound/game-music.mp3").toURI().toString());
        gameMediaPlayer = new MediaPlayer(sound);
        gameMediaPlayer.play();

        gamePane = new Pane();
        gamePane.getStyleClass().add("game");

        setGameLoop(gamePane, mainStage);

        gameScene = new Scene(gamePane, SCENE_WIDTH, SCENE_HEIGHT);
        gameScene.getStylesheets().add("sample/style.css");

        return gameScene;
    }

    public void setGameLoop(Pane gamePaneArg, Stage mainStage) {


          gameLoop = new Timeline(new KeyFrame(Duration.seconds(.6), event -> {

              state = STATE.GAME;

              Enemy.demonSpawnOrder(gamePaneArg);

              System.out.println("DC: " + demonCount);

              if (demonCount >= 4) {

                  gameMediaPlayer.stop();

                  // action when too many demons
                  state = STATE.GAMEOVER;

                  GameOver gameOver = new GameOver();
                  System.out.println(state);
                  mainStage.setScene(gameOver.getGameOverScene(mainStage));
                  gameLoop.stop();
              }
          }));
          gameLoop.setCycleCount(Timeline.INDEFINITE);
    }

    void cleanup() {
        // stop animations reset model ect.
    }

    void startGame(Stage stage) {
        // initialisation from start method goes here

        // button click sound
        Utility.playSFX("src/assets/sound/menu-ding.wav");

        // change scene to game volcano
        stage.setScene(getGameScene(stage));

        setGameLoop(gamePane, stage);
        gameLoop.play();
        stage.show();
    }

    void restart(Stage stage) {
        cleanup();
        startGame(stage);
    }
}
