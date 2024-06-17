package org.example.simulation;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class AnimalTest {

    // Tests initialization of the Animal object
    @Test
    public void testAnimalInitialization() {
        Animal animal = new Animal(10, 20);
        assertEquals(10, animal.getRow()); // Verifies the row is set correctly
        assertEquals(20, animal.getCol()); // Verifies the column is set correctly
    }

    // Tests setting and getting the row value
    @Test
    public void testSetAndGetRow() {
        Animal animal = new Animal(0, 0);
        animal.setRow(5);
        assertEquals(5, animal.getRow()); // Verifies the row is updated correctly
    }

    // Tests setting and getting the column value
    @Test
    public void testSetAndGetCol() {
        Animal animal = new Animal(0, 0);
        animal.setCol(5);
        assertEquals(5, animal.getCol()); // Verifies the column is updated correctly
    }

    // Tests the isValidMove method on a 5x5 board
    @Test
    public void testIsValidMove() {
        ArrayList<ArrayList<Character>> board = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ArrayList<Character> row = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                row.add(Animal.EMPTY);
            }
            board.add(row);
        }
        Animal animal = new Animal(2, 2);
        assertTrue(animal.isValidMove(board, 3, 3)); // Verifies the move to an empty space is valid
        assertFalse(animal.isValidMove(board, 5, 5)); // Verifies the move out of bounds is invalid
        board.get(3).set(3, Animal.FOX);
        assertFalse(animal.isValidMove(board, 3, 3)); // Verifies the move to an occupied space is invalid
    }

    // Tests generating a random direction
    @Test
    public void testGetRandomDirection() {
        Animal animal = new Animal(0, 0);
        int direction = animal.getRandomDirection();
        assertTrue(direction >= -1 && direction <= 1); // Verifies the generated direction is within the expected range
    }
}