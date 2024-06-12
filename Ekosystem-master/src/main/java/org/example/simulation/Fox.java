package org.example.simulation;

import java.util.ArrayList;

/**
 * Represents a Fox in the simulation.
 * Extends the Animal class with additional behavior specific to Foxes.
 */
public class Fox extends Animal {

    private int turnsSinceLastMeal;

    /**
     * Constructs a Fox at the specified row and column.
     * @param row Initial row position.
     * @param col Initial column position.
     */
    public Fox(int row, int col) {
        super(row, col);
        this.turnsSinceLastMeal = 0;
    }

    /**
     * Moves the Fox to a new position on the board.
     * Updates the board and the Fox's position if the move is valid.
     * @param board The simulation board.
     */
    @Override
    public void move(ArrayList<ArrayList<Character>> board) {
        int newRow = getRow() + getRandomDirection();
        int newCol = getCol() + getRandomDirection();
        if (isValidMove(board, newRow, newCol)) {
            board.get(newRow).set(newCol, FOX);
            board.get(getRow()).set(getCol(), EMPTY);
            setRow(newRow);
            setCol(newCol);
        }
        turnsSinceLastMeal++;
    }

    /**
     * Gets the number of turns since the Fox last ate.
     *
     * @return The number of turns since the last meal.
     */
    public int getTurnsSinceLastMeal() {
        return turnsSinceLastMeal;
    }

    /**
     * Resets the counter for turns since the Fox last ate.
     */
    public void resetTurnsSinceLastMeal() {
        turnsSinceLastMeal = 0;
    }
}
