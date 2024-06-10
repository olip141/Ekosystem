package org.example.simulation;

import java.util.ArrayList;

/**
 * Represents a Rabbit in the simulation.
 * Extends the Animal class with additional behavior specific to Rabbits.
 */
public class Rabbit extends Animal {
    private int turnsSinceLastPlant;

    /**
     * Constructs a Rabbit at the specified row and column.
     * @param row Initial row position.
     * @param col Initial column position.
     */
    public Rabbit(int row, int col) {
        super(row, col);
        turnsSinceLastPlant = 0;
    }

    /**
     * Moves the Rabbit to a new position on the board.
     * Updates the board and the Rabbit's position if the move is valid.
     * @param board The simulation board.
     */
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

    /**
     * Gets the number of turns since the Rabbit last ate a plant.
     * @return The number of turns since the last plant meal.
     */
    public int getTurnsSinceLastPlant() {
        return turnsSinceLastPlant;
    }

    /**
     * Resets the counter for turns since the Rabbit last ate a plant.
     */
    public void resetTurnsSinceLastPlant() {
        turnsSinceLastPlant = 0;
    }
}
