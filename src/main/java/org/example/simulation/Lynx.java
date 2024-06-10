package org.example.simulation;

import java.util.ArrayList;

/**
 * Represents a Lynx in the simulation.
 * Extends the Animal class with additional behavior specific to Lynxes.
 */
public class Lynx extends Animal {

    // Tracks the number of turns since the Lynx last ate
    private int turnsSinceLastMeal;

    /**
     * Constructs a Lynx at the specified row and column.
     * @param row Initial row position.
     * @param col Initial column position.
     */
    public Lynx(int row, int col) {
        super(row, col);
        this.turnsSinceLastMeal = 0;
    }

    /**
     * Moves the Lynx to a new position on the board.
     * Updates the board and the Lynx's position if the move is valid.
     * @param board The simulation board.
     */
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
    /**
     * Gets the number of turns since the Lynx last ate.
     * @return The number of turns since the last meal.
     */
    public int getTurnsSinceLastMeal() {
        return turnsSinceLastMeal;
    }
    /**
     * Resets the counter for turns since the Lynx last ate.
     */
    public void resetTurnsSinceLastMeal() {
        turnsSinceLastMeal = 0;
    }
}
