package org.example.simulation;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static org.example.simulation.Animal.*;
import static org.example.simulation.Animal.PLANT;

/**
 * Responsible for creating initial entities and board for the simulation.
 */
public class CreateEntities {

    // Parameters for simulation setup
    public static int BOARD_SIZE;
    private static int NUM_RABBITS;
    private static int NUM_MICE;
    private static int NUM_LYNXES;
    private static int NUM_FOXES;
    private static int NUM_PLANTS;
    static int NUM_TURNS;
    public static int sum;

    /**
     * Handles user input to set up the simulation parameters.
     */
    public static void userInput() {
        Scanner scanner = new Scanner(System.in);

        // Get user input for simulation parameters
        BOARD_SIZE = getInput(scanner, "Enter board size (e.g. type 10 to create 10x10 board): ");
        NUM_TURNS = getInput(scanner, "Enter number of turns: ");
        NUM_RABBITS = getInput(scanner, "Enter number of rabbits: ");
        NUM_MICE = getInput(scanner, "Enter number of mice: ");
        NUM_LYNXES = getInput(scanner, "Enter number of lynxes: ");
        NUM_FOXES = getInput(scanner, "Enter number of foxes: ");
        NUM_PLANTS = getInput(scanner, "Enter number of plants: ");

        // Calculate total number of entities
        ArrayList<Integer> values = new ArrayList<>();
        values.add(NUM_RABBITS);
        values.add(NUM_MICE);
        values.add(NUM_LYNXES);
        values.add(NUM_FOXES);
        values.add(NUM_PLANTS);

        for (Integer value : values) {
            sum += value;
        }
    }

    /**
     * Obtains user input from the console.
     * @param scanner the scanner object to read input
     * @param prompt  the message prompting the user for input
     * @return the integer input provided by the user
     */
    static int getInput(Scanner scanner, String prompt) {
        int input = -1;
        while (input < 0) {
            System.out.print(prompt);
            try {
                input = scanner.nextInt();
                if (input < 0) {
                    System.out.println("Input must be a non-negative integer. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a non-negative integer.");
                scanner.next(); // Clear the invalid input
            }
        }
        return input;
    }

    /**
     * Creates the simulation board.
     * @return The initialized simulation board.
     */
    static ArrayList<ArrayList<Character>> createBoard() {
        ArrayList<ArrayList<Character>> board = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            ArrayList<Character> row = new ArrayList<>();
            for (int j = 0; j < BOARD_SIZE; j++) {
                row.add(EMPTY);
            }
            board.add(row);
        }
        return board;
    }

    /**
     * Creates rabbits and initializes their positions on the board.
     * @param board The simulation board.
     * @return List of created rabbits.
     */
    static ArrayList<Rabbit> createRabbits(ArrayList<ArrayList<Character>> board) {
        ArrayList<Rabbit> rabbits = new ArrayList<>();
        for (int i = 0; i < NUM_RABBITS; i++) {
            int row, col;
            do {
                row = (int) (Math.random() * BOARD_SIZE);
                col = (int) (Math.random() * BOARD_SIZE);
            } while (board.get(row).get(col) != EMPTY);
            Rabbit rabbit = new Rabbit(row, col);
            rabbits.add(rabbit);
            board.get(row).set(col, RABBIT);
        }
        return rabbits;
    }

    /**
     * Creates mice and initializes their positions on the board.
     *
     * @param board The simulation board.
     * @return List of created mice.
     */
    static ArrayList<Mouse> createMice(ArrayList<ArrayList<Character>> board) {
        ArrayList<Mouse> mice = new ArrayList<>();
        for (int i = 0; i < NUM_MICE; i++) {
            int row, col;
            do {
                row = (int) (Math.random() * BOARD_SIZE);
                col = (int) (Math.random() * BOARD_SIZE);
            } while (board.get(row).get(col) != EMPTY);
            Mouse mouse = new Mouse(row, col);
            mice.add(mouse);
            board.get(row).set(col, MOUSE);
        }
        return mice;
    }

    /**
     * Creates lynxes and initializes their positions on the board.
     * @param board The simulation board.
     * @return List of created lynxes.
     */
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

    /**
     * Creates foxes and initializes their positions on the board.
     * @param board The simulation board.
     * @return List of created foxes.
     */
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

    /**
     * Creates plants and initializes their positions on the board.
     * @param board The simulation board.
     * @return List of created plants.
     */
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
