package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class Animal {
    protected int x;
    protected int y;

    public Animal(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

class Mouse extends Animal {
    private int turnsSinceLastCheese = 0;

    public Mouse(int x, int y) {
        super(x, y);
    }

    @Override
    public void move() {
        Random rand = new Random();
        int direction = rand.nextInt(8); // 0 - up, 1 - down, 2 - left, 3 - right, 4 - up-right, 5 - down-right, 6 - down-left, 7 - up-left

        switch(direction) {
            case 0:
                if (y > 0) y--;
                break;
            case 1:
                if (y < 9) y++;
                break;
            case 2:
                if (x > 0) x--;
                break;
            case 3:
                if (x < 9) x++;
                break;
            case 4:
                if (y > 0 && x < 9) {
                    y--;
                    x++;
                }
                break;
            case 5:
                if (y < 9 && x < 9) {
                    y++;
                    x++;
                }
                break;
            case 6:
                if (y < 9 && x > 0) {
                    y++;
                    x--;
                }
                break;
            case 7:
                if (y > 0 && x > 0) {
                    y--;
                    x--;
                }
                break;
        }

        turnsSinceLastCheese++;
    }

    public int getTurnsSinceLastCheese() {
        return turnsSinceLastCheese;
    }

    public void resetTurnsSinceLastCheese() {
        turnsSinceLastCheese = 0;
    }
}

class Cat extends Animal {
    private int turnsSinceLastMouse = 0;

    public Cat(int x, int y) {
        super(x, y);
    }

    @Override
    public void move() {
        Random rand = new Random();
        int direction = rand.nextInt(8); // 0 - up, 1 - down, 2 - left, 3 - right, 4 - up-right, 5 - down-right, 6 - down-left, 7 - up-left

        switch(direction) {
            case 0:
                if (y > 0) y--;
                break;
            case 1:
                if (y < 9) y++;
                break;
            case 2:
                if (x > 0) x--;
                break;
            case 3:
                if (x < 9) x++;
                break;
            case 4:
                if (y > 0 && x < 9) {
                    y--;
                    x++;
                }
                break;
            case 5:
                if (y < 9 && x < 9) {
                    y++;
                    x++;
                }
                break;
            case 6:
                if (y < 9 && x > 0) {
                    y++;
                    x--;
                }
                break;
            case 7:
                if (y > 0 && x > 0) {
                    y--;
                    x--;
                }
                break;
        }

        turnsSinceLastMouse++;
    }

    public int getTurnsSinceLastMouse() {
        return turnsSinceLastMouse;
    }

    public void resetTurnsSinceLastMouse() {
        turnsSinceLastMouse = 0;
    }
}

class Cheese extends Animal {
    public Cheese(int x, int y) {
        super(x, y);
    }
}

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
        ArrayList<Cheese> cheeses = new ArrayList<>();
        for (int i = 0; i < numCheese; i++) {
            int x, y;
            do {
                x = new Random().nextInt(cols);
                y = new Random().nextInt(rows);
            } while (occupiedPositions.contains(x + "," + y));
            occupiedPositions.add(x + "," + y);
            cheeses.add(new Cheese(x, y));
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
            for (Cheese cheese : cheeses) {
                board.get(cheese.getY()).set(cheese.getX(), 'S'); // S represents the cheese
            }

            // sprawdzenie czy mysz obok sera
            for (Mouse mouse : mice) {
                for (Cheese cheese : cheeses) {
                    int distance = Math.abs(mouse.getX() - cheese.getX()) + Math.abs(mouse.getY() - cheese.getY());
                    if (distance <= 1) {
                        mouse.resetTurnsSinceLastCheese();
                        occupiedPositions.remove(cheese.getX() + "," + cheese.getY());
                        cheeses.remove(cheese);
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
            System.out.println("Cheese count: " + cheeses.size());
            System.out.println();
        }
    }
}
