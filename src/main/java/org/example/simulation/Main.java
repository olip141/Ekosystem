package org.example.simulation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        int rows = 30;
        int cols = 30;
        int numTurns = 20;
        int numMice = 115;
        int numCats = 15;
        int numCheese = 200;

        ArrayList<ArrayList<Character>> board = new ArrayList<>();
        Set<String> occupiedPositions = new HashSet<>();

        // myszy na polach
        ArrayList<Mouse> mice = new ArrayList<>();
        for (int i = 0; i < numMice; i++) {
            int x, y;
            do {
                x = new Random().nextInt(cols);
                y = new Random().nextInt(rows);
            } while (occupiedPositions.contains(x + "," + y));
            occupiedPositions.add(x + "," + y);
            mice.add(new Mouse(x, y));
        }

        // koty na polach
        ArrayList<Cat> cats = new ArrayList<>();
        for (int i = 0; i < numCats; i++) {
            int x, y;
            do {
                x = new Random().nextInt(cols);
                y = new Random().nextInt(rows);
            } while (occupiedPositions.contains(x + "," + y));
            occupiedPositions.add(x + "," + y);
            cats.add(new Cat(x, y));
        }

        // ser na polach
        ArrayList<Plant> plants = new ArrayList<>();
        for (int i = 0; i < numCheese; i++) {
            int x, y;
            do {
                x = new Random().nextInt(cols);
                y = new Random().nextInt(rows);
            } while (occupiedPositions.contains(x + "," + y));
            occupiedPositions.add(x + "," + y);
            plants.add(new Plant(x, y));
        }

        // plansza
        for (int i = 0; i < rows; i++) {
            ArrayList<Character> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                row.add('.');
            }
            board.add(row);
        }

        for (int turn = 1; turn <= numTurns; turn++) {
            for (ArrayList<Character> row : board) {
                for (int j = 0; j < row.size(); j++) {
                    row.set(j, '.');
                }
            }

            // myszy na planszy
            for (Mouse mouse : mice) {
                board.get(mouse.getY()).set(mouse.getX(), 'M'); // M represents the mouse
                mouse.move();
            }

            // koty na planszy
            for (Cat cat : cats) {
                board.get(cat.getY()).set(cat.getX(), 'C'); // C represents the cat
                cat.move();
            }

            // ser na polach
            for (Plant plant : plants) {
                board.get(plant.getY()).set(plant.getX(), 'S'); // S represents the cheese
            }

            // sprawdzenie czy mysz obok sera
            for (Mouse mouse : mice) {
                for (Plant plant : plants) {
                    int distance = Math.abs(mouse.getX() - plant.getX()) + Math.abs(mouse.getY() - plant.getY());
                    if (distance <= 1) {
                        mouse.resetTurnsSinceLastCheese();
                        occupiedPositions.remove(plant.getX() + "," + plant.getY());
                        plants.remove(plant);
                        break;
                    }
                }
            }

            // sprawdzenie czy kot obok myszy
            for (Cat cat : cats) {
                for (Mouse mouse : mice) {
                    int distance = Math.abs(cat.getX() - mouse.getX()) + Math.abs(cat.getY() - mouse.getY());
                    if (distance <= 1) {
                        cat.resetTurnsSinceLastMouse();
                        occupiedPositions.remove(mouse.getX() + "," + mouse.getY());
                        mice.remove(mouse);
                        break;
                    }
                }
            }

            // sprawdzenie czy mysz dead z głodu
            ArrayList<Mouse> miceToRemove = new ArrayList<>();
            for (Mouse mouse : mice) {
                if (mouse.getTurnsSinceLastCheese() >= 8) {
                    miceToRemove.add(mouse);
                }
            }
            for (Mouse mouse : miceToRemove) {
                occupiedPositions.remove(mouse.getX() + "," + mouse.getY());
                mice.remove(mouse);
            }

            // sprawdzenie czy kot dead z głodu
            ArrayList<Cat> catsToRemove = new ArrayList<>();
            for (Cat cat : cats) {
                if (cat.getTurnsSinceLastMouse() >= 8) {
                    catsToRemove.add(cat);
                }
            }
            for (Cat cat : catsToRemove) {
                occupiedPositions.remove(cat.getX() + "," + cat.getY());
                cats.remove(cat);
            }

            // display planszy
            System.out.println("Turn " + turn + ":");
            for (ArrayList<Character> row : board) {
                for (char c : row) {
                    System.out.print(c + " ");
                }
                System.out.println();
            }

            // display liczników pokazuje turę +
            System.out.println("Mice count: " + mice.size());
            System.out.println("Cats count: " + cats.size());
            System.out.println("Cheese count: " + plants.size());
            System.out.println();
        }
    }
}
