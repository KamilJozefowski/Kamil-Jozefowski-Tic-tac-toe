package com.kodilla.ticTacToe;

import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class BoardSubScene extends SubScene {
    private final String BACKGROUND_IMAGE3x3 = "3x3B.png";
    private final String BACKGROUND_IMAGE4x4 = "4x4B.png";
    private final String BACKGROUND_IMAGE5x5 = "5x5B.png";
    private final String BACKGROUND_IMAGE6x6 = "6x6B.png";
    private String BACKGROUND_IMAGE;

    private final int boardSize;


    public BoardSubScene(int boardSize) {
        super(new Pane(), 500, 500);
        this.boardSize = boardSize;
        prefHeight(500);
        prefWidth(500);

        setBackgroundImage(boardSize);

        BackgroundImage backgroundImage = new BackgroundImage(new Image(BACKGROUND_IMAGE, 500, 500, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        Pane root2 = (Pane) this.getRoot();

        root2.setBackground(new Background(backgroundImage));
    }

    public void setBackgroundImage(int boardSize) {
        switch (boardSize) {
            case 3 -> BACKGROUND_IMAGE = BACKGROUND_IMAGE3x3;
            case 4 -> BACKGROUND_IMAGE = BACKGROUND_IMAGE4x4;
            case 5 -> BACKGROUND_IMAGE = BACKGROUND_IMAGE5x5;
            case 6 -> BACKGROUND_IMAGE = BACKGROUND_IMAGE6x6;
            default -> System.out.println("ERROR");
        }
    }

    public Pane getPane() {
        return (Pane) this.getRoot();
    }
}
