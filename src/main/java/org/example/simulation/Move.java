package org.example.simulation;

import java.util.ArrayList;

public class Move {

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
