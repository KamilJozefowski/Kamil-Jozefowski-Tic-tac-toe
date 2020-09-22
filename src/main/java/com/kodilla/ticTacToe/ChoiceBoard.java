package com.kodilla.ticTacToe;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class ChoiceBoard extends VBox {

    private ImageView circleImage;
    private ImageView boardImage;

    private String circleNotChosen = "grey_circle.png";
    private String circleChosen = "yellow_boxTick.png";

    private BOARD board;

    private boolean isCircleChosen;

    public ChoiceBoard(BOARD board) {
        circleImage = new ImageView(circleNotChosen);
        boardImage = new ImageView(board.getUrlBoard());
        this.board = board;
        isCircleChosen = false;
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.getChildren().add(circleImage);
        this.getChildren().add(boardImage);
    }

    public BOARD getBoard() {
        return board;
    }

    public boolean getIsCircleChosen() {
        return isCircleChosen;
    }

    public void setCircleChosen(boolean isCircleChosen) {
        this.isCircleChosen = isCircleChosen;
        String imageToSet = this.isCircleChosen ? circleChosen : circleNotChosen;
        circleImage.setImage(new Image(imageToSet));
    }
}
