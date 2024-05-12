package org.example.simulation;

import java.util.ArrayList;

public class Rabbit extends Animal {
    private int turnsSinceLastPlant;

    public Rabbit(int row, int col) {
        super(row, col);
        turnsSinceLastPlant = 0;
    }

    @Override
    public void move(ArrayList<ArrayList<Character>> board) {
        int newRow = getRow() + getRandomDirection();
        int newCol = getCol() + getRandomDirection();
        if (isValidMove(board, newRow, newCol)) {
            board.get(newRow).set(newCol, RABBIT);
            board.get(getRow()).set(getCol(), EMPTY);
            setRow(newRow);
            setCol(newCol);
        }
        turnsSinceLastPlant++;
    }

    public int getTurnsSinceLastPlant() {
        return turnsSinceLastPlant;
    }

    public void resetTurnsSinceLastPlant() {
        turnsSinceLastPlant = 0;
    }
}
