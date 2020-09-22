package com.kodilla.ticTacToe;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;

public class ViewManager {

    private static final int WIDTH = 1024;
    private static final int HEIGHT = 768;
    private final AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    private final static int MENU_BUTTON_START_X = 100;
    private final static int MENU_BUTTON_START_Y = 150;

    private TicTacToeSubScene creditsSubScene;
    private TicTacToeSubScene helpSubScene;
    private TicTacToeSubScene scoresSubScene;
    private TicTacToeSubScene gameChooserScene;

    private TicTacToeSubScene sceneToHidden = null;

    List<TicTacToeButton> menuButtons;

    List<ChoiceBoard> boardList;
    private BOARD chosenBoard;

    public ViewManager() {
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        mainStage.initStyle(StageStyle.UNDECORATED);
        createSubScenes();
        createBackground();
        creatorButtons();
        createLogo();

    }

    private void showSubScene(TicTacToeSubScene subScene) {
        if (sceneToHidden != null) {
            sceneToHidden.moveSubScene();
        }
        subScene.moveSubScene();
        sceneToHidden = subScene;
    }

    private void createSubScenes() {
        creditsSubScene = new TicTacToeSubScene();
        mainPane.getChildren().add(creditsSubScene);

        helpSubScene = new TicTacToeSubScene();
        mainPane.getChildren().add(helpSubScene);

        scoresSubScene = new TicTacToeSubScene();
        mainPane.getChildren().add(scoresSubScene);

        createChoiceBoardSubScene();
    }

    private void createChoiceBoardSubScene() {
        gameChooserScene = new TicTacToeSubScene();
        mainPane.getChildren().add(gameChooserScene);

        InfoLabel chooseBoardLabel = new InfoLabel("CHOOSE YOUR BOARD");
        chooseBoardLabel.setLayoutX(110);
        chooseBoardLabel.setLayoutY(25);
        gameChooserScene.getAnchorPane().getChildren().add(chooseBoardLabel);
        gameChooserScene.getAnchorPane().getChildren().add(createBoardToChoose());
        gameChooserScene.getAnchorPane().getChildren().add(createButtonToStart());

    }

    private HBox createBoardToChoose(){
        HBox box = new HBox(20);
        boardList = new ArrayList<>();
        for (BOARD board : BOARD.values()){
            ChoiceBoard boardToPick = new ChoiceBoard(board);
            boardList.add(boardToPick);
            box.getChildren().add(boardToPick);

            boardToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    for(ChoiceBoard board : boardList){
                        board.setCircleChosen(false);
                    }
                    boardToPick.setCircleChosen(true);
                    chosenBoard = boardToPick.getBoard();
                }
            });
        }

        box.setLayoutX(300 - (118*2));
        box.setLayoutY(100);
        return box;

    }

    private TicTacToeButton createButtonToStart(){
        TicTacToeButton startButton = new TicTacToeButton("START");
        startButton.setLayoutX(350);
        startButton.setLayoutY(300);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (chosenBoard != null){
                    //sizeBoard = chosenBoard.getSize();
                    GameViewManager gameManager = new GameViewManager();
                    gameManager.createNewGame(mainStage, chosenBoard);
                }
            }
        });
        return startButton;
    }

    public Stage getMainStage() {
        return mainStage;
    }

    private void addMenuButton(TicTacToeButton button) {
        button.setLayoutX(MENU_BUTTON_START_X);
        button.setLayoutY(MENU_BUTTON_START_Y + (menuButtons.size() * 100));
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }

    public void creatorButtons() {
        createStartButton();
        createScoresButton();
        createHelpButton();
        createCreditsButton();
        createExitButton();
    }

    public void createStartButton() {
        TicTacToeButton startButton = new TicTacToeButton("PLAY");
        addMenuButton(startButton);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(gameChooserScene);
            }
        });
    }

    public void createScoresButton() {
        TicTacToeButton scoresButton = new TicTacToeButton("SCORES");
        addMenuButton(scoresButton);

        scoresButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(scoresSubScene);
            }
        });
    }

    public void createHelpButton() {
        TicTacToeButton helpButton = new TicTacToeButton("HELP");
        addMenuButton(helpButton);

        helpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(helpSubScene);
            }
        });
    }

    public void createCreditsButton() {
        TicTacToeButton creditsButton = new TicTacToeButton("CREDITS");
        addMenuButton(creditsButton);

        creditsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(creditsSubScene);
            }
        });
    }

    public void createExitButton() {
        TicTacToeButton exitButton = new TicTacToeButton("EXIT");
        addMenuButton(exitButton);

        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainStage.close();
            }
        });
    }


    public void createBackground() {
        Image backgroundImage = new Image("background.png", 1000, 1000, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(background));
    }

    private void createLogo() {
        ImageView logo = new ImageView("logo.png");
        logo.setLayoutX(400);
        logo.setLayoutY(40);

        logo.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logo.setEffect(new DropShadow());
            }
        });

        logo.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logo.setEffect(null);
            }
        });

        mainPane.getChildren().add(logo);
    }

}
