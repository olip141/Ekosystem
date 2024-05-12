package org.example.simulation;

import java.util.ArrayList;

public class Lynx extends Animal {

    private int turnsSinceLastMeal;

    public Lynx(int row, int col) {
        super(row, col);
        this.turnsSinceLastMeal = 0;
    }

    @Override
    public void move(ArrayList<ArrayList<Character>> board) {
        int newRow = getRow() + getRandomDirection();
        int newCol = getCol() + getRandomDirection();
        if (isValidMove(board, newRow, newCol)) {
            board.get(newRow).set(newCol, LYNX);
            board.get(getRow()).set(getCol(), EMPTY);
            setRow(newRow);
            setCol(newCol);
        }
        turnsSinceLastMeal++;
    }

    public int getTurnsSinceLastMeal() {
        return turnsSinceLastMeal;
    }

    public void resetTurnsSinceLastMeal() {
        turnsSinceLastMeal = 0;
    }
}
