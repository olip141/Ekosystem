package org.example.simulation;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class AnimalTest {

    @Test
    public void testAnimalInitialization() {
        Animal animal = new Animal(10, 20);
        assertEquals(10, animal.getRow());
        assertEquals(20, animal.getCol());
    }

    @Test
    public void testSetAndGetRow() {
        Animal animal = new Animal(0, 0);
        animal.setRow(5);
        assertEquals(5, animal.getRow());
    }

    @Test
    public void testSetAndGetCol() {
        Animal animal = new Animal(0, 0);
        animal.setCol(5);
        assertEquals(5, animal.getCol());
    }

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
        assertTrue(animal.isValidMove(board, 3, 3));
        assertFalse(animal.isValidMove(board, 5, 5)); // Out of bounds
        board.get(3).set(3, Animal.FOX);
        assertFalse(animal.isValidMove(board, 3, 3)); // Occupied by another animal
    }

    @Test
    public void testGetRandomDirection() {
        Animal animal = new Animal(0, 0);
        int direction = animal.getRandomDirection();
        assertTrue(direction >= -1 && direction <= 1);
    }
}