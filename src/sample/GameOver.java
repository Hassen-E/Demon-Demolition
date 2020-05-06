package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;

import static sample.Game.gameLoop;



public class GameOver {

    public MediaPlayer loseMediaPlayer;

    public Game game;

    public Scene getGameOverScene(Stage mainStage) {

        Media loseSong = new Media(new File("src/assets/sound/lose-song.wav").toURI().toString());
        loseMediaPlayer = new MediaPlayer(loseSong);
        loseMediaPlayer.play();

        VBox layout = new VBox(100);
//        layout.setPadding(new Insets(10, 10, 10, 10));

        // game over title
        Label gameOverLabel = new Label("GAME OVER!");
        gameOverLabel.getStyleClass().add("title");


        // score + score number they are seperate so score can be different color (red)
        Label scoreText = new Label("Your score was: ");
        scoreText.getStyleClass().add("score");

        Label scoreNumber = new Label(Main.getScore() + "");
        scoreNumber.getStyleClass().add("scoreNumber");

        // Retry button
        Image retryImage = new Image("assets/retry-button.png");
        ImageView retryButton = new ImageView(retryImage);

        retryButton.setPreserveRatio(true);
        retryButton.setFitWidth(250);

        // Play button action
        retryButton.setOnMouseClicked((MouseEvent e) -> {

            // button click sound
            Utility.playSFX("src/assets/sound/menu-ding.wav");

            // change scene to game volcano
//            game = new Game();
//            mainStage.setScene(game.getGameScene(mainStage));
//
//            gameLoop.play();

        });


        HBox scoreLayout = new HBox(scoreText, scoreNumber);
        scoreLayout.setAlignment(Pos.CENTER);


        layout.getChildren().addAll(gameOverLabel, scoreLayout, retryButton);
        layout.setAlignment(Pos.CENTER);

        layout.getStylesheets().add("sample/style.css");

        return new Scene(layout, Main.SCENE_WIDTH, Main.SCENE_HEIGHT);
    }
}

