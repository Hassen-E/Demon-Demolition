package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

import static sample.Game.gameLoop;
import static sample.Main.*;


public class Menu extends Stage {

    Main main;
    Scene menuScene;
    Media sound;
    public static Game.STATE gameState;
    MediaPlayer menuMediaPlayer;
    Game game;


    // default constructor
    public Scene getMenuScene(Stage mainStage, Scene gameScene) {


        // Menu -> move to new class once working
        Label title = new Label("Demon Demolition");
        title.setWrapText(true);
        title.getStyleClass().add("title");

        Image buttonImage = new Image("assets/playButton.png");
        ImageView playButton = new ImageView(buttonImage);

        playButton.setPreserveRatio(true);
        playButton.setFitWidth(280);

        // menu audio
        sound = new Media(new File("src/assets/sound/menu-music.mp3").toURI().toString());
        MediaPlayer menuMediaPlayer = new MediaPlayer(sound);
        menuMediaPlayer.play();

        game = new Game();

        // Play button action
        playButton.setOnMouseClicked((MouseEvent e) -> {

            // stop menu music
            menuMediaPlayer.stop();

            // function to start game

            game.startGame(mainStage);

        });

        // menu page layout
        VBox layout = new VBox(300); // spacing between nodes
        layout.getStyleClass().add("menu");
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(title, playButton);



        menuScene = new Scene(layout, SCENE_WIDTH, SCENE_HEIGHT);
        menuScene.getStylesheets().add("sample/style.css");

        return menuScene;
    }



}
