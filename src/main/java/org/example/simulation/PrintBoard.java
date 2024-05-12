package org.example.simulation;

import java.util.ArrayList;

import static org.example.simulation.Animal.*;

public class PrintBoard {
    public static void printBoard(ArrayList<ArrayList<Character>> board) {
        int numFoxes = 0;
        int numRabbits = 0;
        int numMice = 0;
        int numPlants = 0;
        int numLynxes = 0;

        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {
                System.out.print(board.get(i).get(j) + " ");
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
            System.out.println();
        }

        System.out.println("Number of lynxes: " + numLynxes);
        System.out.println("Number of foxes: " + numFoxes);
        System.out.println("Number of rabbits: " + numRabbits);
        System.out.println("Number of mice: " + numMice);
        System.out.println("Number of plants: " + numPlants);
    }
}
