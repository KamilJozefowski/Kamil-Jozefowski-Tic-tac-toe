package com.kodilla.ticTacToe;

public enum BOARD {

    BOARD3x3("3x3.png", 3),
    BOARD4x4("4x4.png", 4),
    BOARD5x5("5x5.png", 5),
    BOARD6x6("6x6.png", 6);

    private final int size;


    private String urlBoard;

    private BOARD(String urlBoard, int size) {
        this.urlBoard = urlBoard;
        this.size = size;
    }

    public String getUrlBoard() {
        return this.urlBoard;
    }

    public int getSize() {
        return size;
    }


}
