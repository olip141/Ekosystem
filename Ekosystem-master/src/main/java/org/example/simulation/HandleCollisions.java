package org.example.simulation;

import java.util.ArrayList;

/**
 * Handles collisions and interactions between different entities on the simulation board.
 */
public class HandleCollisions {

    /**
     * Processes all collisions and interactions between animals and plants on the board.
     * @param board     The simulation board.
     * @param lynxes    List of lynxes to check for collisions.
     * @param rabbits   List of rabbits to check for collisions.
     * @param mice      List of mice to check for collisions.
     * @param foxes     List of foxes to check for collisions.
     * @param plantList List of plants to check for collisions.
     */
    static void handleCollisions(ArrayList<ArrayList<Character>> board, ArrayList<Lynx> lynxes, ArrayList<Rabbit> rabbits, ArrayList<Mouse> mice,
                                 ArrayList<Fox> foxes, ArrayList<Plant> plantList) {

        // Check if a lynx catches a rabbit or mouse
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

        // Check if a fox catches a rabbit or mouse
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

        // Check if a rabbit finds a plant
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

        // Check if a mouse finds a plant
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

        // Handle lynx and fox fights
        for (Lynx lynx : lynxes) {
            for (Fox fox : foxes) {
                if (Math.abs(lynx.getRow() - fox.getRow()) <= 1 && Math.abs(lynx.getCol() - fox.getCol()) <= 1) {
                    int randomNumber = (int) (Math.random() * 100);
                    if (randomNumber < 70) {
                        // Lynx wins the fight
                        System.out.println("Lynx wins a fight with fox!");
                        board.get(fox.getRow()).set(fox.getCol(), Animal.EMPTY);
                        foxes.remove(fox);
                        lynx.resetTurnsSinceLastMeal();
                        break;
                    } else {
                        // Fox wins the fight
                        System.out.println("Fox wins a fight with lynx!");
                        board.get(lynx.getRow()).set(lynx.getCol(), Animal.EMPTY);
                        lynxes.remove(lynx);
                        fox.resetTurnsSinceLastMeal();
                        break;
                    }
                }
            }
        }

        // Check if a rabbit dies of hunger
        for (Rabbit rabbit : rabbits) {
            if (rabbit.getTurnsSinceLastPlant() >= 7) {
                System.out.println("Rabbit died of hunger!");
                board.get(rabbit.getRow()).set(rabbit.getCol(), Animal.EMPTY);
                rabbits.remove(rabbit);
                break;
            }
        }

        // Check if a mouse dies of hunger
        for (Mouse mouse : mice) {
            if (mouse.getTurnsSinceLastPlant() >= 5) {
                System.out.println("Mouse died of hunger!");
                board.get(mouse.getRow()).set(mouse.getCol(), Animal.EMPTY);
                mice.remove(mouse);
                break;
            }
        }

        // Check if a lynx dies of hunger
        for (Lynx lynx : lynxes) {
            if (lynx.getTurnsSinceLastMeal() >= 10) {
                System.out.println("Lynx died of hunger!");
                board.get(lynx.getRow()).set(lynx.getCol(), Animal.EMPTY);
                lynxes.remove(lynx);
                break;
            }
        }

        // Check if a fox dies of hunger
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