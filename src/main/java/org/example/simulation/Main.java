package org.example.simulation;

import java.util.ArrayList;

import static org.example.simulation.Animal.*;

public class Main {

    private static final int BOARD_SIZE = 10;
    private static final int NUM_RABBITS = 3;
    private static final int NUM_MICE = 3;
    private static final int NUM_LYNXES = 3;
    private static final int NUM_FOXES = 3;
    private static final int NUM_PLANTS = 10;
    private static final int NUM_TURNS = 20;

    public static void main(String[] args) {
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

    private static ArrayList<Rabbit> createRabbits(ArrayList<ArrayList<Character>> board) {
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

    private static ArrayList<Lynx> createLynxes(ArrayList<ArrayList<Character>> board) {
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

    private static ArrayList<Fox> createFoxes(ArrayList<ArrayList<Character>> board) {
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

    private static ArrayList<Plant> createPlant(ArrayList<ArrayList<Character>> board) {
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


    private static void moveAnimals(ArrayList<ArrayList<Character>> board, ArrayList<Rabbit> rabbits,ArrayList<Mouse> mice, ArrayList<Fox> foxes, ArrayList<Lynx> lynxes) {

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

    private static void handleCollisions(ArrayList<ArrayList<Character>> board, ArrayList<Lynx> lynxes, ArrayList<Rabbit> rabbits,ArrayList<Mouse> mice,
                                         ArrayList<Fox> foxes, ArrayList<Plant> plantList) {

        // Sprawdzenie czy ryś złapał zająca
        for (Lynx lynx : lynxes) {
            for (Rabbit rabbit : rabbits) {
                if (Math.abs(lynx.getRow() - rabbit.getRow()) <= 1 && Math.abs(lynx.getCol() - rabbit.getCol()) <= 1) {
                    System.out.println("Lynx caught a rabbit!");
                    board.get(rabbit.getRow()).set(rabbit.getCol(), Animal.EMPTY);
                    rabbits.remove(rabbit);
                    lynx.resetTurnsSinceLastMeal();
                    break;
                }
            }
            for (Mouse mouse : mice) {
                if (Math.abs(lynx.getRow() - mouse.getRow()) <= 1 && Math.abs(lynx.getCol() - mouse.getCol()) <= 1) {
                    System.out.println("Lynx caught a mouse!");
                    board.get(mouse.getRow()).set(mouse.getCol(), Animal.EMPTY);
                    mice.remove(mouse);
                    lynx.resetTurnsSinceLastMeal();
                    break;
                }
            }
        }

        // Sprawdzenie czy lis złapał zająca lub mysz
        for (Fox fox : foxes) {
            for (Rabbit rabbit : rabbits) {
                if (Math.abs(fox.getRow() - rabbit.getRow()) <= 1 && Math.abs(fox.getCol() - rabbit.getCol()) <= 1) {
                    System.out.println("Fox caught a rabbit!");
                    board.get(rabbit.getRow()).set(rabbit.getCol(), Animal.EMPTY);
                    rabbits.remove(rabbit);
                    fox.resetTurnsSinceLastMeal();
                    break;
                }
            }
            for (Mouse mouse : mice) {
                if (Math.abs(fox.getRow() - mouse.getRow()) <= 1 && Math.abs(fox.getCol() - mouse.getCol()) <= 1) {
                    System.out.println("Fox caught a mouse!");
                    board.get(mouse.getRow()).set(mouse.getCol(), Animal.EMPTY);
                    mice.remove(mouse);
                    fox.resetTurnsSinceLastMeal();
                    break;
                }
            }
        }

        // Sprawdzenie czy zając znalazł roślinę
        for (Rabbit rabbit : rabbits) {
            for (Plant plant : plantList) {
                if (Math.abs(rabbit.getRow() - plant.getRow()) <= 1 && Math.abs(rabbit.getCol() - plant.getCol()) <= 1) {
                    System.out.println("Rabbit found a plant!");
                    board.get(plant.getRow()).set(plant.getCol(), Animal.EMPTY);
                    plantList.remove(plant);
                    rabbit.resetTurnsSinceLastPlant();
                    break;
                }
            }
        }

        // Sprawdzenie czy mysz znalazła roślinę
        for (Mouse mouse : mice) {
            for (Plant plant : plantList) {
                if (Math.abs(mouse.getRow() - plant.getRow()) <= 1 && Math.abs(mouse.getCol() - plant.getCol()) <= 1) {
                    System.out.println("Mouse found a plant!");
                    board.get(plant.getRow()).set(plant.getCol(), Animal.EMPTY);
                    plantList.remove(plant);
                    mouse.resetTurnsSinceLastPlant();
                    break;
                }
            }
        }

        // Walka rysia z lisem
        for (Lynx lynx : lynxes) {
            for (Fox fox : foxes) {
                if (Math.abs(lynx.getRow() - fox.getRow()) <= 1 && Math.abs(lynx.getCol() - fox.getCol()) <= 1) {
                    // Losowa liczba z zakresu od 0 do 99
                    int randomNumber = (int) (Math.random() * 100);
                    //System.out.println(randomNumber);
                    // Jeśli losowa liczba jest mniejsza niż 70, ryś wygrywa
                    if (randomNumber < 70) {
                        // Ryś wygrywa
                        System.out.println("Lynx wins a fight with fox!");
                        board.get(fox.getRow()).set(fox.getCol(), Animal.EMPTY);
                        foxes.remove(fox);
                        lynx.resetTurnsSinceLastMeal();
                        break;
                    } else {
                        // Lis wygrywa walkę
                        System.out.println("Fox wins a fight with lynx!");
                        board.get(lynx.getRow()).set(lynx.getCol(), Animal.EMPTY);
                        lynxes.remove(lynx);
                        fox.resetTurnsSinceLastMeal();
                        break;
                    }
                }
            }
        }

        // Sprawdzenie czy zając umiera z głodu
        for (Rabbit rabbit : rabbits) {
            if (rabbit.getTurnsSinceLastPlant() >= 7) {
                System.out.println("Rabbit died of hunger!");
                board.get(rabbit.getRow()).set(rabbit.getCol(), Animal.EMPTY);
                rabbits.remove(rabbit);
                break;
            }
        }

        // Sprawdzenie czy mysz umiera z głodu
        for (Mouse mouse : mice) {
            if (mouse.getTurnsSinceLastPlant() >= 5) {
                System.out.println("Mouse died of hunger!");
                board.get(mouse.getRow()).set(mouse.getCol(), Animal.EMPTY);
                mice.remove(mouse);
                break;
            }
        }

        // Sprawdzenie czy ryś umiera z głodu
        for (Lynx lynx : lynxes) {
            if (lynx.getTurnsSinceLastMeal() >= 10) {
                System.out.println("Lynx died of hunger!");
                board.get(lynx.getRow()).set(lynx.getCol(), Animal.EMPTY);
                lynxes.remove(lynx);
                break;
            }
        }

        // Sprawdzenie czy lis umiera z głodu
        for (Fox fox : foxes) {
            if (fox.getTurnsSinceLastMeal() >= 8) {
                System.out.println("Fox died of hunger!");
                board.get(fox.getRow()).set(fox.getCol(), Animal.EMPTY);
                foxes.remove(fox);
                break;
            }
        }
    }
}
