package org.example.simulation;

import java.util.ArrayList;

public class Mouse extends Animal {
    private int turnsSinceLastCheese;

    public Mouse(int row, int col) {
        super(row, col);
        turnsSinceLastCheese = 0;
    }

    @Override
    public void move(ArrayList<ArrayList<Character>> board) {
        int newRow = getRow() + getRandomDirection();
        int newCol = getCol() + getRandomDirection();
        if (isValidMove(board, newRow, newCol)) {
            board.get(newRow).set(newCol, MOUSE);
            board.get(getRow()).set(getCol(), EMPTY);
            setRow(newRow);
            setCol(newCol);
        }
    }

    public int getTurnsSinceLastCheese() {
        return turnsSinceLastCheese;
    }

    public void resetTurnsSinceLastCheese() {
        turnsSinceLastCheese = 0;
    }

    public void incrementTurnsSinceLastCheese() {
        turnsSinceLastCheese++;
    }
}
