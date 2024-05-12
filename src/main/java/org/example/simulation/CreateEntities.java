package org.example.simulation;

import java.util.ArrayList;

import static org.example.simulation.Animal.*;
import static org.example.simulation.Animal.PLANT;

public class CreateEntities {

    private static final int BOARD_SIZE = 50;
    private static final int NUM_RABBITS = 40;
    private static final int NUM_MICE = 50;
    private static final int NUM_LYNXES = 8;
    private static final int NUM_FOXES = 10;
    private static final int NUM_PLANTS = 100;
    static final int NUM_TURNS = 20;

    static ArrayList<ArrayList<Character>> createBoard() {
        ArrayList<ArrayList<Character>> board = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            ArrayList<Character> row = new ArrayList<>();
            for (int j = 0; j < BOARD_SIZE; j++) {
                row.add(Animal.EMPTY);
            }
            board.add(row);
        }
        return board;
    }

    static ArrayList<Rabbit> createRabbits(ArrayList<ArrayList<Character>> board) {
        ArrayList<Rabbit> rabbits = new ArrayList<>();
        for (int i = 0; i < NUM_RABBITS; i++) {
            int row, col;
            do {
                row = (int) (Math.random() * BOARD_SIZE);
                col = (int) (Math.random() * BOARD_SIZE);
            } while (board.get(row).get(col) != Animal.EMPTY);
            Rabbit rabbit = new Rabbit(row, col);
            rabbits.add(rabbit);
            board.get(row).set(col, RABBIT);
        }
        return rabbits;
    }

    static ArrayList<Mouse> createMice(ArrayList<ArrayList<Character>> board) {
        ArrayList<Mouse> mice = new ArrayList<>();
        for (int i = 0; i < NUM_MICE; i++) {
            int row, col;
            do {
                row = (int) (Math.random() * BOARD_SIZE);
                col = (int) (Math.random() * BOARD_SIZE);
            } while (board.get(row).get(col) != Animal.EMPTY);
            Mouse mouse = new Mouse(row, col);
            mice.add(mouse);
            board.get(row).set(col, MOUSE);
        }
        return mice;
    }

    static ArrayList<Lynx> createLynxes(ArrayList<ArrayList<Character>> board) {
        ArrayList<Lynx> lynxes = new ArrayList<>();
        for (int i = 0; i < NUM_LYNXES; i++) {
            int row, col;
            do {
                row = (int) (Math.random() * BOARD_SIZE);
                col = (int) (Math.random() * BOARD_SIZE);
            } while (board.get(row).get(col) != EMPTY);
            Lynx lynx = new Lynx(row, col);
            lynxes.add(lynx);
            board.get(row).set(col, LYNX);
        }
        return lynxes;
    }

    static ArrayList<Fox> createFoxes(ArrayList<ArrayList<Character>> board) {
        ArrayList<Fox> foxes = new ArrayList<>();
        for (int i = 0; i < NUM_FOXES; i++) {
            int row, col;
            do {
                row = (int) (Math.random() * BOARD_SIZE);
                col = (int) (Math.random() * BOARD_SIZE);
            } while (board.get(row).get(col) != EMPTY);
            Fox fox = new Fox(row, col);
            foxes.add(fox);
            board.get(row).set(col, FOX);
        }
        return foxes;
    }

    static ArrayList<Plant> createPlant(ArrayList<ArrayList<Character>> board) {
        ArrayList<Plant> plantList = new ArrayList<>();
        for (int i = 0; i < NUM_PLANTS; i++) {
            int row, col;
            do {
                row = (int) (Math.random() * BOARD_SIZE);
                col = (int) (Math.random() * BOARD_SIZE);
            } while (board.get(row).get(col) != EMPTY);
            Plant plant = new Plant(row, col);
            plantList.add(plant);
            board.get(row).set(col, PLANT);
        }
        return plantList;
    }
}
