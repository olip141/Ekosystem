package org.example.simulation;

import java.util.ArrayList;

public class Animal {
    protected static final char EMPTY = '.';
    protected static final char FOX = 'C';
    protected static final char RABBIT = 'R';
    protected static final char MOUSE = 'M';
    protected static final char PLANT = 'P';
    protected static final char LYNX = 'L';

    private int row;
    private int col;

    public Animal(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void move(ArrayList<ArrayList<Character>> board) {}

    protected int getRandomDirection() {
        return (int) (Math.random() * 3) - 1;
    }

    protected boolean isValidMove(ArrayList<ArrayList<Character>> board, int newRow, int newCol) {
        return newRow >= 0 && newRow < board.size() && newCol >= 0 && newCol < board.get(0).size()
                && board.get(newRow).get(newCol) == EMPTY;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
