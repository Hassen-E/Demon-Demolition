package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Game extends Application {

    public static int score;
    private boolean gameOver = false;

    @Override
    public void start(Stage stage) throws Exception {

    }

    public enum difficulty {
        // toYoungToDie, hurtMePlenty, nightmare
        EASY, MEDIUM, HARD
    }

    public void gameLoop() {
        // implement tick system -- speed changed by difficulty
    }


}
