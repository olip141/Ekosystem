package org.example.simulation;

import java.util.ArrayList;

public class Cat extends Animal {
    private int turnsSinceLastMouse;

    public Cat(int row, int col) {
        super(row, col);
        this.turnsSinceLastMouse = 0;
    }

    @Override
    public void move(ArrayList<ArrayList<Character>> board) {
        int newRow = getRow() + getRandomDirection();
        int newCol = getCol() + getRandomDirection();
        if (isValidMove(board, newRow, newCol)) {
            board.get(newRow).set(newCol, CAT);
            board.get(getRow()).set(getCol(), EMPTY);
            setRow(newRow);
            setCol(newCol);
        }
        turnsSinceLastMouse++;
    }

    public int getTurnsSinceLastMouse() {
        return turnsSinceLastMouse;
    }

    public void resetTurnsSinceLastMouse() {
        turnsSinceLastMouse = 0;
    }
}
