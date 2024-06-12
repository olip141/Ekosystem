package org.example.simulation;

import java.util.ArrayList;

/**
 * Provides functionality to move all animals on the simulation board.
 */
public class Move {

    /**
     * Moves all animals on the board by invoking their respective move methods.
     * @param board   The simulation board.
     * @param rabbits List of rabbits to be moved.
     * @param mice    List of mice to be moved.
     * @param foxes   List of foxes to be moved.
     * @param lynxes  List of lynxes to be moved.
     */
    static void moveAnimals(ArrayList<ArrayList<Character>> board, ArrayList<Rabbit> rabbits, ArrayList<Mouse> mice, ArrayList<Fox> foxes, ArrayList<Lynx> lynxes) {

        for(Lynx lynx : lynxes) {
            lynx.move(board);
        }
        for (Fox fox : foxes) {
            fox.move(board);
        }
        for (Rabbit rabbit : rabbits) {
            rabbit.move(board);
        }
        for (Mouse mouse : mice) {
            mouse.move(board);
        }

    }
}
