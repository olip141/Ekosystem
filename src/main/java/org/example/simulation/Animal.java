package org.example.simulation;

import java.util.ArrayList;

/**
 * Represent an animal in the simulation
 * Provides basic movement and position functionalities
 */
public class Animal {
    protected static final char EMPTY = '.';
    protected static final char FOX = 'F';
    protected static final char RABBIT = 'R';
    protected static final char MOUSE = 'M';
    protected static final char PLANT = 'P';
    protected static final char LYNX = 'L';

    // Curent position of the animal on the board
    private int row;
    private int col;

    /**
     * Constructs an Animal at the specified row and column.
     * @param row Initial row position.
     * @param col Initial column position.
     */
    public Animal(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Moves the animal to a new position on the board.
     * This method should be overridden by subclasses to define specific movement behavior.
     * @param board The simulation board.
     */
    public void move(ArrayList<ArrayList<Character>> board) {}

    /**
     * Generates a random direction (-1, 0, or 1) for movement.
     * @return A random integer in the range [-1, 1].
     */
    protected int getRandomDirection() {
        return (int) (Math.random() * 3) - 1;
    }

    /**
     * Checks if the proposed move to a new position is valid.
     * A move is valid if it is within the bounds of the board and the target cell is empty.
     * @param board  The simulation board.
     * @param newRow The proposed new row.
     * @param newCol The proposed new column.
     * @return True if the move is valid, otherwise false.
     */
    protected boolean isValidMove(ArrayList<ArrayList<Character>> board, int newRow, int newCol) {
        return newRow >= 0 && newRow < board.size() && newCol >= 0 && newCol < board.get(0).size()
                && board.get(newRow).get(newCol) == EMPTY;
    }

    /**
     * Gets the current row position of the animal.
     * @return The current row position.
     */
    public int getRow() {
        return row;
    }

    /**
     * Sets a new row position for the animal.
     * @param row The new row position.
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Gets the current column position of the animal.
     * @return The current column position.
     */
    public int getCol() {
        return col;
    }

    /**
     * Sets a new column position for the animal.
     * @param col The new column position.
     */
    public void setCol(int col) {
        this.col = col;
    }
}
