package org.example.simulation;

/**
 * Represents a Plant in the simulation.
 * Provides basic position functionalities.
 */
public class Plant {
    private int row;
    private int col;

    /**
     * Constructs a Plant at the specified row and column.
     * @param row Initial row position.
     * @param col Initial column position.
     */
    public Plant(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Gets the current row position of the plant.
     * @return The current row position.
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets the current column position of the plant.
     * @return The current column position.
     */
    public int getCol() {
        return col;
    }
}
