package org.example.simulation;

import java.util.ArrayList;

public class HandleCollisions {

    static void handleCollisions(ArrayList<ArrayList<Character>> board, ArrayList<Lynx> lynxes, ArrayList<Rabbit> rabbits, ArrayList<Mouse> mice,
                                 ArrayList<Fox> foxes, ArrayList<Plant> plantList) {

        // Sprawdzenie czy ryś złapał zająca lub mysz
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