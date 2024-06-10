package org.example.simulation;

import java.util.ArrayList;

import static org.example.simulation.Animal.*;

/**
 * Provides functionality to print the simulation board and count the entities.
 */
public class PrintBoard {

    /**
     * Prints the simulation board and counts the number of each type of entity.
     * @param board The simulation board.
     */
    public static void printBoard(ArrayList<ArrayList<Character>> board) {
        int numFoxes = 0;
        int numRabbits = 0;
        int numMice = 0;
        int numPlants = 0;
        int numLynxes = 0;

        // Iterate over each cell in the board
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {
                System.out.print(board.get(i).get(j) + " ");
                // Print the current cell
                if (board.get(i).get(j) == FOX) {
                    numFoxes++;
                } else if (board.get(i).get(j) == RABBIT) {
                    numRabbits++;
                } else if (board.get(i).get(j) == PLANT) {
                    numPlants++;
                } else if (board.get(i).get(j) == LYNX) {
                    numLynxes++;
                } else if (board.get(i).get(j) == MOUSE) {
                    numMice++;
                }
            }
            // Move to the next line after each row
            System.out.println();
        }

        // Print the counts of each type of entity
        System.out.println("Number of lynxes: " + numLynxes);
        System.out.println("Number of foxes: " + numFoxes);
        System.out.println("Number of rabbits: " + numRabbits);
        System.out.println("Number of mice: " + numMice);
        System.out.println("Number of plants: " + numPlants);
    }
}
