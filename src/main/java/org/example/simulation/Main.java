package org.example.simulation;

import java.util.ArrayList;

import static org.example.simulation.Animal.*;

public class Main {
    private static final int BOARD_SIZE = 10;
    private static final int NUM_MICE = 3;
    private static final int NUM_CATS = 3;
    private static final int NUM_CHEESE = 10;
    private static final int NUM_TURNS = 20;

    public static void main(String[] args) {
        ArrayList<ArrayList<Character>> board = createBoard();
        ArrayList<Mouse> mice = createMice(board);
        ArrayList<Cat> cats = createCats(board);
        ArrayList<Cheese> cheeseList = createCheese(board);

        for (int turn = 1; turn <= NUM_TURNS; turn++) {
            System.out.println("Turn " + turn + ":");

            printBoard(board);

            moveAnimals(board, mice, cats);

            handleCollisions(board, mice, cats, cheeseList);
        }
    }

    private static ArrayList<ArrayList<Character>> createBoard() {
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

    private static ArrayList<Mouse> createMice(ArrayList<ArrayList<Character>> board) {
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

    private static ArrayList<Cat> createCats(ArrayList<ArrayList<Character>> board) {
        ArrayList<Cat> cats = new ArrayList<>();
        for (int i = 0; i < NUM_CATS; i++) {
            int row, col;
            do {
                row = (int) (Math.random() * BOARD_SIZE);
                col = (int) (Math.random() * BOARD_SIZE);
            } while (board.get(row).get(col) != Animal.EMPTY);
            Cat cat = new Cat(row, col);
            cats.add(cat);
            board.get(row).set(col, CAT);
        }
        return cats;
    }

    private static ArrayList<Cheese> createCheese(ArrayList<ArrayList<Character>> board) {
        ArrayList<Cheese> cheeseList = new ArrayList<>();
        for (int i = 0; i < NUM_CHEESE; i++) {
            int row, col;
            do {
                row = (int) (Math.random() * BOARD_SIZE);
                col = (int) (Math.random() * BOARD_SIZE);
            } while (board.get(row).get(col) != Animal.EMPTY);
            Cheese cheese = new Cheese(row, col);
            cheeseList.add(cheese);
            board.get(row).set(col, CHEESE);
        }
        return cheeseList;
    }

    public static void printBoard(ArrayList<ArrayList<Character>> board) {
        int numCats = 0;
        int numMice = 0;
        int numCheeses = 0;

        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {
                System.out.print(board.get(i).get(j) + " ");
                if (board.get(i).get(j) == CAT) {
                    numCats++;
                } else if (board.get(i).get(j) == MOUSE) {
                    numMice++;
                } else if (board.get(i).get(j) == CHEESE) {
                    numCheeses++;
                }
            }
            System.out.println();
        }

        System.out.println("Number of cats: " + numCats);
        System.out.println("Number of mice: " + numMice);
        System.out.println("Number of cheeses: " + numCheeses);
    }


    private static void moveAnimals(ArrayList<ArrayList<Character>> board, ArrayList<Mouse> mice, ArrayList<Cat> cats) {
        for (Mouse mouse : mice) {
            mouse.move(board);
            mouse.incrementTurnsSinceLastCheese();
        }
        for (Cat cat : cats) {
            cat.move(board);
        }
    }

    private static void handleCollisions(ArrayList<ArrayList<Character>> board, ArrayList<Mouse> mice,
                                         ArrayList<Cat> cats, ArrayList<Cheese> cheeseList) {
        // Sprawdzenie czy kot złapał mysz
        for (Cat cat : cats) {
            for (Mouse mouse : mice) {
                if (Math.abs(cat.getRow() - mouse.getRow()) <= 1 && Math.abs(cat.getCol() - mouse.getCol()) <= 1) {
                    System.out.println("Cat caught a mouse!");
                    board.get(mouse.getRow()).set(mouse.getCol(), Animal.EMPTY);
                    mice.remove(mouse);
                    cat.resetTurnsSinceLastMouse();
                    break;
                }
            }
        }

        // Sprawdzenie czy mysz znalazła ser
        for (Mouse mouse : mice) {
            for (Cheese cheese : cheeseList) {
                if (Math.abs(mouse.getRow() - cheese.getRow()) <= 1 && Math.abs(mouse.getCol() - cheese.getCol()) <= 1) {
                    System.out.println("Mouse found cheese!");
                    board.get(cheese.getRow()).set(cheese.getCol(), Animal.EMPTY);
                    cheeseList.remove(cheese);
                    mouse.resetTurnsSinceLastCheese();
                    break;
                }
            }
        }

        // Sprawdzenie czy mysz umiera z głodu
        for (Mouse mouse : mice) {
            if (mouse.getTurnsSinceLastCheese() >= 8) {
                System.out.println("Mouse died of hunger!");
                board.get(mouse.getRow()).set(mouse.getCol(), Animal.EMPTY);
                mice.remove(mouse);
                break;
            }
        }

        // Sprawdzenie czy kot umiera z głodu
        for (Cat cat : cats) {
            if (cat.getTurnsSinceLastMouse() >= 8) {
                System.out.println("Cat died of hunger!");
                board.get(cat.getRow()).set(cat.getCol(), Animal.EMPTY);
                cats.remove(cat);
                break;
            }
        }
    }
}
