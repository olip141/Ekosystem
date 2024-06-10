package org.example.simulation;

import java.util.ArrayList;

import static org.example.simulation.CreateEntities.NUM_TURNS;
import static org.example.simulation.CreateEntities.*;
import static org.example.simulation.Errors.userInputErrors;
import static org.example.simulation.HandleCollisions.handleCollisions;
import static org.example.simulation.PrintBoard.printBoard;
import static org.example.simulation.Move.moveAnimals;

public class Main {

    public static void main(String[] args) {

        userInput();
        userInputErrors();

        ArrayList<ArrayList<Character>> board = createBoard();
        ArrayList<Rabbit> rabbits = createRabbits(board);
        ArrayList<Mouse> mice = createMice(board);
        ArrayList<Fox> foxes = createFoxes(board);
        ArrayList<Plant> plantList = createPlant(board);
        ArrayList<Lynx> lynxes = createLynxes(board);

        for (int turn = 1; turn <= NUM_TURNS; turn++) {
            System.out.println("Turn " + turn + ":");

            printBoard(board);

            moveAnimals(board, rabbits, mice, foxes, lynxes);

            handleCollisions(board,lynxes, rabbits, mice,foxes ,plantList);
        }

    }
}
