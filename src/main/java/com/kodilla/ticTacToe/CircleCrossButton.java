package com.kodilla.ticTacToe;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class CircleCrossButton extends Button {

    private final String BUTTON_FREE_STYLE = "-fx-background-color: transparent;";
    private final String BUTTON_CIRCLE_STYLE = "-fx-background-color: transparent;-fx-background-size: contain; -fx-background-position: center; -fx-background-repeat: no-repeat; -fx-background-image: url('circle.png');";
    private final String BUTTON_CROSS_STYLE = "-fx-background-color: transparent;-fx-background-size: contain; -fx-background-position: center; -fx-background-repeat: no-repeat; -fx-background-image: url('cross.png');";

    private boolean yourTurn = true;

    public CircleCrossButton(int sizeButton) {
        setPrefHeight(sizeButton);
        setPrefWidth(sizeButton);
        setStyle(BUTTON_FREE_STYLE);
        initializeButtonListeners();

    }

    private void initializeButtonListeners() {

        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    if (yourTurn){
                        setStyle(BUTTON_CROSS_STYLE);
                        yourTurn = false;
                    }else {
                        setStyle(BUTTON_CIRCLE_STYLE);
                        yourTurn = true;
                    }

                }
            }
        });

    }

}
