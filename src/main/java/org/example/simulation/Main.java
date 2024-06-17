package org.example.simulation;

import java.util.ArrayList;

import static org.example.simulation.CreateEntities.NUM_TURNS;
import static org.example.simulation.CreateEntities.*;
import static org.example.simulation.Errors.userInputErrors;
import static org.example.simulation.HandleCollisions.handleCollisions;
import static org.example.simulation.PrintBoard.printBoard;
import static org.example.simulation.Move.moveAnimals;

/**
 * Main class to run the simulation.
 */
public class Main {

    /**
     * Entry point of the simulation application.
     */
    public static void main(String[] args) {

        // Validate user input
        userInput();
        userInputErrors();

        // Initialize the simulation board and entities
        ArrayList<ArrayList<Character>> board = createBoard();
        ArrayList<Rabbit> rabbits = createRabbits(board);
        ArrayList<Mouse> mice = createMice(board);
        ArrayList<Fox> foxes = createFoxes(board);
        ArrayList<Plant> plantList = createPlant(board);
        ArrayList<Lynx> lynxes = createLynxes(board);

        // Run the simulation for a predefined number of turns
        for (int turn = 1; turn <= NUM_TURNS; turn++) {
            System.out.println("Turn " + turn + ":");

            // Display the current state of the board
            printBoard(board);

            // Move all animals according to their movement rules
            moveAnimals(board, rabbits, mice, foxes, lynxes);

            // Handle any collisions or interactions between entities
            handleCollisions(board,lynxes, rabbits, mice,foxes ,plantList);
        }
    }
}
