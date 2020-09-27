package com.kodilla.ticTacToe;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Random;


public class GameViewManager {

    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private BoardSubScene boardScene;
    private BOARD chosenBoard;
    private Stage menuStage;

    private static final int GAME_WIDTH = 600;
    private static final int GAME_HEIGHT = 800;
    private final static String BACKGROUND_IMAGE = "background.png";

    private InfoLabel pointsLabel;
    private TicTacToeButton buttonFinishGame;

    private int positionX;
    private int positionY;
    private int randomPositionX;
    private int randomPositionY;

    private int sameSign;
    private int possibleMoves;
    private int points = 0;

    private char[][] board;

    private ImageView sign;

    private boolean endGame = false;
    private boolean isYourTurn;

    private final Random random = new Random();


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

    private void createLabelPoints(int points) {
        pointsLabel = new InfoLabel("POINTS: " + points);
        pointsLabel.setLayoutX(110);
        pointsLabel.setLayoutY(25);
        gamePane.getChildren().add(pointsLabel);
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

    private void initializeTable() {
        for (int row = 0; row < chosenBoard.getSize(); row++) {
            for (int col = 0; col < chosenBoard.getSize(); col++) {
                board[row][col] = '-';
            }
        }
    }

    private void playerMove() {

        boardScene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                positionX = (int) ((event.getSceneX() - 50) / (500 / chosenBoard.getSize()));
                positionY = (int) ((event.getSceneY() - 150) / (500 / chosenBoard.getSize()));

                if (board[positionY][positionX] == '-') {
                    loadImageInBoard(positionX, positionY);
                    board[positionY][positionX] = 'x';
                    isYourTurn = false;
                    possibleMoves--;
                    checkCol(positionX, 'x');
                    checkRow(positionY, 'x');
                    checkDiagonal1(positionX, positionY, 'x');
                    checkDiagonal2(positionX, positionY, 'x');
                }
                while ((!isYourTurn) && (possibleMoves != 0) && (!endGame)) {
                    computerMove();
                }

                if (endGame || possibleMoves == 0) {
                    if (possibleMoves == 0 && !endGame) {
                        whoWon("DRAW");
                    }else {
                        if (!isYourTurn) {
                            whoWon("YOU WINNER");
                        } else {
                            whoWon("YOU LOOS");
                        }
                    }
                }
            }
        });
    }

    public void whoWon(String text) {

        InfoLabel winnerLabel = new InfoLabel(text);
        winnerLabel.setLayoutX(110);
        winnerLabel.setLayoutY(380);
        gamePane.getChildren().add(winnerLabel);

        boardScene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                newGame();
            }
        });
    }

    private void computerMove() {
        randomPositionX = random.nextInt(chosenBoard.getSize());
        randomPositionY = random.nextInt(chosenBoard.getSize());
        if (board[randomPositionY][randomPositionX] == '-') {
            loadImageInBoard(randomPositionX, randomPositionY);
            board[randomPositionY][randomPositionX] = 'o';
            isYourTurn = true;
            possibleMoves--;
            checkCol(randomPositionX, 'o');
            checkRow(randomPositionY, 'o');
            checkDiagonal1(randomPositionX, randomPositionY, 'o');
            checkDiagonal2(randomPositionX, randomPositionY, 'o');
        }
    }

    public void newGame() {
        createNewGame(menuStage, chosenBoard);
        endGame = false;
    }

    private void checkSameSign(char xOrO) {
        if (sameSign == 3) {
            if (xOrO =='x')
            points++;
            endGame = true;
        }
    }

    private void checkCol(int col, char xOrO) {
        sameSign = 0;
        for (int i = 0; i < chosenBoard.getSize(); i++) {
            if ((board[i][col] == xOrO)) {
                sameSign++;
                checkSameSign(xOrO);
            } else {
                sameSign = 0;
            }
        }
    }

    private void checkRow(int row, char xOrO) {
        sameSign = 0;
        for (int i = 0; i < chosenBoard.getSize(); i++) {
            if ((board[row][i] == xOrO)) {
                sameSign++;
                checkSameSign(xOrO);
            } else {
                sameSign = 0;
            }
        }
    }

    private void checkDiagonal1(int row, int col, char xOrO) {
        sameSign = 0;
        while ((row != 0) && (col != 0)) {
            row--;
            col--;
        }
        int j = col - 1;
        for (int i = row; i < chosenBoard.getSize(); i++) {
            j++;
            if (i == chosenBoard.getSize() || j == chosenBoard.getSize()) {
                break;
            }
            if ((board[j][i] == xOrO)) {
                sameSign++;
                checkSameSign(xOrO);
            } else {
                sameSign = 0;
            }
        }
    }

    private void checkDiagonal2(int row, int col, char xOrO) {
        sameSign = 0;
        while (row != 0 && col != (chosenBoard.getSize() - 1)) {
            row--;
            col++;
        }

        int j = col + 1;
        for (int i = row; i < chosenBoard.getSize(); i++) {
            j--;
            if (i == chosenBoard.getSize() || j == -1) {
                break;
            }
            if ((board[j][i] == xOrO)) {
                sameSign++;
                checkSameSign(xOrO);
            } else {
                sameSign = 0;
            }
        }
    }

    private void loadImageInBoard(int cellX, int cellY) {
        if (isYourTurn) {
            sign = new ImageView("cross.png");
        } else {
            sign = new ImageView("circle.png");
        }

        sign.setLayoutX(cellX * (500 / chosenBoard.getSize()));
        sign.setLayoutY(cellY * (500 / chosenBoard.getSize()));
        sign.setFitWidth(500 / chosenBoard.getSize());
        sign.setFitHeight(500 / chosenBoard.getSize());
        boardScene.getPane().getChildren().add(sign);
    }

    public void createNewGame(Stage menuStage, BOARD chosenBoard) {
        this.menuStage = menuStage;
        this.chosenBoard = chosenBoard;
        menuStage.hide();
        gameStage.show();
        crateBackground();
        createLabelPoints(points);
        creteButtonFinish();
        board = new char[chosenBoard.getSize()][chosenBoard.getSize()];
        isYourTurn = true;
        possibleMoves = chosenBoard.getSize() * chosenBoard.getSize();
        initializeTable();
        createChoiceBoardSubScene();
        playerMove();
    }

    private void crateBackground() {
        Image backgroundImage = new Image(BACKGROUND_IMAGE, 1000, 1000, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        gamePane.setBackground(new Background(background));

    }
}
