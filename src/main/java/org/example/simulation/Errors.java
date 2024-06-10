package org.example.simulation;
import static org.example.simulation.CreateEntities.BOARD_SIZE;
import static org.example.simulation.CreateEntities.sum;

/**
 * Handles errors related to user input in the simulation.
 */
public class Errors {

    /**
     * Checks for errors in user input, such as exceeding the board size.
     */
    static void userInputErrors(){

        if(sum > BOARD_SIZE*BOARD_SIZE){
            System.out.println("There is not enough space on the board!");
            System.out.println("Closing program...");
            System.exit(0);
        }
    }
}
