package com.kodilla.ticTacToe;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class TicTacToeSubScene extends SubScene {

    private final String FONT_PATH = "file:resources/kenvector_future.ttf";
    private final String BACKGROUND_IMAGE = "yellow_panel.png";

    private boolean isHidden;


    public TicTacToeSubScene() {
        super(new AnchorPane(), 600, 400);
        prefHeight(400);
        prefWidth(600);

        BackgroundImage backgroundImage = new BackgroundImage(new Image(BACKGROUND_IMAGE,600, 400,false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        AnchorPane root2 = (AnchorPane) this.getRoot();

        root2.setBackground(new Background(backgroundImage));

        setLayoutX(1024);
        setLayoutY(180);
    }

    public void moveSubScene(){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);
        if (isHidden == false) {
            transition.setToX(-676);
            isHidden = true;
        }else {
            transition.setToX(0);
            isHidden = false;
        }

        transition.play();
    }

    public AnchorPane getAnchorPane(){
        return (AnchorPane) this.getRoot();
    }
}
