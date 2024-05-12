package org.example.simulation;

import java.util.Random;

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

