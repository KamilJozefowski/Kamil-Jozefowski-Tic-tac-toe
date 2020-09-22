package com.kodilla.ticTacToe;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class GameViewManager {

    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private BoardSubScene boardScene;
    private BOARD chosenBoard;

    private static final int GAME_WIDTH = 600;
    private static final int GAME_HEIGHT = 800;

    private Stage menuStage;

    private final static String BACKGROUND_IMAGE = "background.png";

    private InfoLabel pointsLabel;
    private TicTacToeButton buttonFinishGame;


    public GameViewManager() {
        initializeStage();
    }

    private void initializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
        gameStage = new Stage();
        gameStage.initStyle(StageStyle.UNDECORATED);
        gameStage.setScene(gameScene);

    }

    private void createLabelPoints() {
        pointsLabel = new InfoLabel("POINTS: 00");
        pointsLabel.setLayoutX(110);
        pointsLabel.setLayoutY(25);
        gamePane.getChildren().add(pointsLabel);
    }

    private void createBoardButton() {
        for (int i = 0; i != (chosenBoard.getSize()*chosenBoard.getSize()); i++ ){
            CircleCrossButton field = new CircleCrossButton(500 / chosenBoard.getSize());
            boardScene.getFlowPane().getChildren().add(field);
        }
    }

    private void creteButtonFinish() {
        buttonFinishGame = new TicTacToeButton("FINISH");
        buttonFinishGame.setLayoutX(205);
        buttonFinishGame.setLayoutY(724);

        gamePane.getChildren().add(buttonFinishGame);
        buttonFinishGame.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                gameStage.hide();
                menuStage.show();
            }
        });
    }

    private void createChoiceBoardSubScene() {
        boardScene = new BoardSubScene(chosenBoard.getSize());
        boardScene.setLayoutX(50);
        boardScene.setLayoutY(150);
        gamePane.getChildren().add(boardScene);
    }


    public void createNewGame(Stage menuStage, BOARD chosenBoard) {
        this.menuStage = menuStage;
        this.chosenBoard = chosenBoard;
        menuStage.hide();
        gameStage.show();
        crateBackground();
        createLabelPoints();
        creteButtonFinish();
        createChoiceBoardSubScene();
        createBoardButton();
    }


    private void crateBackground() {
        Image backgroundImage = new Image("background.png", 1000, 1000, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        gamePane.setBackground(new Background(background));

    }
}
