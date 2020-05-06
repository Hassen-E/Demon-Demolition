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

        System.out.println("in game scene");

        setGameLoop(gamePane, mainStage);

        gameScene = new Scene(gamePane, SCENE_WIDTH, SCENE_HEIGHT);
        gameScene.getStylesheets().add("sample/style.css");

        return gameScene;
    }

    public void setGameLoop(Pane gamePaneArg, Stage mainStage) {

        System.out.println("outside " + state);

          gameLoop = new Timeline(new KeyFrame(Duration.seconds(.7), event -> {

              state = STATE.GAME;

              System.out.println("inside: " + state);


              System.out.println("in game loop");
              Enemy.demonSpawnOrder(gamePaneArg);

              System.out.println("demon count: " + demonCount);

              if (demonCount >= 4) {

                  gameMediaPlayer.stop();

                  // action when to many demons
                  state = STATE.GAMEOVER;
                  System.out.println(state);
                  gameLoop.stop();
                  gameOver = new GameOver();
                  mainStage.setScene(gameOver.getGameOverScene(mainStage));

              } else {
                  System.out.println("loop again");
              }

          }));
          gameLoop.setCycleCount(Timeline.INDEFINITE);
    }
}
