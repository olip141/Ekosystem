package org.example.simulation;

import java.util.ArrayList;
import java.util.Iterator;

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
        Iterator<Lynx> lynxIterator = lynxes.iterator();
        while (lynxIterator.hasNext()) {
            Lynx lynx = lynxIterator.next();
            Iterator<Rabbit> rabbitIterator = rabbits.iterator();
            while (rabbitIterator.hasNext()) {
                Rabbit rabbit = rabbitIterator.next();
                if (Math.abs(lynx.getRow() - rabbit.getRow()) <= 1 && Math.abs(lynx.getCol() - rabbit.getCol()) <= 1) {
                    System.out.println("Lynx caught a rabbit!");
                    board.get(rabbit.getRow()).set(rabbit.getCol(), Animal.EMPTY);
                    rabbitIterator.remove();
                    lynx.resetTurnsSinceLastMeal();
                    break;
                }
            }
            Iterator<Mouse> mouseIterator = mice.iterator();
            while (mouseIterator.hasNext()) {
                Mouse mouse = mouseIterator.next();
                if (Math.abs(lynx.getRow() - mouse.getRow()) <= 1 && Math.abs(lynx.getCol() - mouse.getCol()) <= 1) {
                    System.out.println("Lynx caught a mouse!");
                    board.get(mouse.getRow()).set(mouse.getCol(), Animal.EMPTY);
                    mouseIterator.remove();
                    lynx.resetTurnsSinceLastMeal();
                    break;
                }
            }
        }

        // Check if a fox catches a rabbit or mouse
        Iterator<Fox> foxIterator = foxes.iterator();
        while (foxIterator.hasNext()) {
            Fox fox = foxIterator.next();
            Iterator<Rabbit> rabbitIterator = rabbits.iterator();
            while (rabbitIterator.hasNext()) {
                Rabbit rabbit = rabbitIterator.next();
                if (Math.abs(fox.getRow() - rabbit.getRow()) <= 1 && Math.abs(fox.getCol() - rabbit.getCol()) <= 1) {
                    System.out.println("Fox caught a rabbit!");
                    board.get(rabbit.getRow()).set(rabbit.getCol(), Animal.EMPTY);
                    rabbitIterator.remove();
                    fox.resetTurnsSinceLastMeal();
                    break;
                }
            }
            Iterator<Mouse> mouseIterator = mice.iterator();
            while (mouseIterator.hasNext()) {
                Mouse mouse = mouseIterator.next();
                if (Math.abs(fox.getRow() - mouse.getRow()) <= 1 && Math.abs(fox.getCol() - mouse.getCol()) <= 1) {
                    System.out.println("Fox caught a mouse!");
                    board.get(mouse.getRow()).set(mouse.getCol(), Animal.EMPTY);
                    mouseIterator.remove();
                    fox.resetTurnsSinceLastMeal();
                    break;
                }
            }
        }

        // Check if a rabbit finds a plant
        Iterator<Rabbit> rabbitIterator = rabbits.iterator();
        while (rabbitIterator.hasNext()) {
            Rabbit rabbit = rabbitIterator.next();
            Iterator<Plant> plantIterator = plantList.iterator();
            while (plantIterator.hasNext()) {
                Plant plant = plantIterator.next();
                if (Math.abs(rabbit.getRow() - plant.getRow()) <= 1 && Math.abs(rabbit.getCol() - plant.getCol()) <= 1) {
                    System.out.println("Rabbit found a plant!");
                    board.get(plant.getRow()).set(plant.getCol(), Animal.EMPTY);
                    plantIterator.remove();
                    rabbit.resetTurnsSinceLastPlant();
                    break;
                }
            }
        }

        // Check if a mouse finds a plant
        Iterator<Mouse> mouseIterator = mice.iterator();
        while (mouseIterator.hasNext()) {
            Mouse mouse = mouseIterator.next();
            Iterator<Plant> plantIterator = plantList.iterator();
            while (plantIterator.hasNext()) {
                Plant plant = plantIterator.next();
                if (Math.abs(mouse.getRow() - plant.getRow()) <= 1 && Math.abs(mouse.getCol() - plant.getCol()) <= 1) {
                    System.out.println("Mouse found a plant!");
                    board.get(plant.getRow()).set(plant.getCol(), Animal.EMPTY);
                    plantIterator.remove();
                    mouse.resetTurnsSinceLastPlant();
                    break;
                }
            }
        }

        // Handle lynx and fox fights
        lynxIterator = lynxes.iterator();
        while (lynxIterator.hasNext()) {
            Lynx lynx = lynxIterator.next();
            foxIterator = foxes.iterator();
            while (foxIterator.hasNext()) {
                Fox fox = foxIterator.next();
                if (Math.abs(lynx.getRow() - fox.getRow()) <= 1 && Math.abs(lynx.getCol() - fox.getCol()) <= 1) {
                    int randomNumber = (int) (Math.random() * 100);
                    if (randomNumber < 70) {
                        // Lynx wins the fight
                        System.out.println("Lynx wins a fight with fox!");
                        board.get(fox.getRow()).set(fox.getCol(), Animal.EMPTY);
                        foxIterator.remove();
                        lynx.resetTurnsSinceLastMeal();
                        break;
                    } else {
                        // Fox wins the fight
                        System.out.println("Fox wins a fight with lynx!");
                        board.get(lynx.getRow()).set(lynx.getCol(), Animal.EMPTY);
                        lynxIterator.remove();
                        fox.resetTurnsSinceLastMeal();
                        break;
                    }
                }
            }
        }

        // Check if a rabbit dies of hunger
        rabbitIterator = rabbits.iterator();
        while (rabbitIterator.hasNext()) {
            Rabbit rabbit = rabbitIterator.next();
            if (rabbit.getTurnsSinceLastPlant() >= 7) {
                System.out.println("Rabbit died of hunger!");
                board.get(rabbit.getRow()).set(rabbit.getCol(), Animal.EMPTY);
                rabbitIterator.remove();
                break;
            }
        }

        // Check if a mouse dies of hunger
        mouseIterator = mice.iterator();
        while (mouseIterator.hasNext()) {
            Mouse mouse = mouseIterator.next();
            if (mouse.getTurnsSinceLastPlant() >= 5) {
                System.out.println("Mouse died of hunger!");
                board.get(mouse.getRow()).set(mouse.getCol(), Animal.EMPTY);
                mouseIterator.remove();
                break;
            }
        }

        // Check if a lynx dies of hunger
        lynxIterator = lynxes.iterator();
        while (lynxIterator.hasNext()) {
            Lynx lynx = lynxIterator.next();
            if (lynx.getTurnsSinceLastMeal() >= 10) {
                System.out.println("Lynx died of hunger!");
                board.get(lynx.getRow()).set(lynx.getCol(), Animal.EMPTY);
                lynxIterator.remove();
                break;
            }
        }

        // Check if a fox dies of hunger
        foxIterator = foxes.iterator();
        while (foxIterator.hasNext()) {
            Fox fox = foxIterator.next();
            if (fox.getTurnsSinceLastMeal() >= 8) {
                System.out.println("Fox died of hunger!");
                board.get(fox.getRow()).set(fox.getCol(), Animal.EMPTY);
                foxIterator.remove();
                break;
            }
        }
    }
}
