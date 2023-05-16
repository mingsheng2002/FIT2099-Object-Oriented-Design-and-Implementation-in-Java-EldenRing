package game.controllers;

import game.actors.archetypes.*;
import game.resets.Resettable;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A Archetype manager class that allows users to select the archetype starting class.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 */
public class ArchetypeManager {

    /**
     * An instance of ArchetypeManager
     */
    private static ArchetypeManager instance =null;

    /**
     * Private constructor of ArchetypeManager
     */
    private ArchetypeManager(){}

    /**
     * An ArrayList of valid selections that represent the available options for the archetype starting class
     */
    private static ArrayList<Integer> validSelections;

    /**
     * Factory method use to instantiate ArchetypeManager if instance is null and add valid integer for the archetype starting class.
     * @return instance an instance of ArchetypeManager
     */
    public static ArchetypeManager getInstance(){
        if (instance ==null){
            instance =  new ArchetypeManager();
            validSelections = new ArrayList<>();
            validSelections.add(1);
            validSelections.add(2);
            validSelections.add(3);
            validSelections.add(4);
        }
        return instance;
    }

    /**
     * This method obtain user input to select a archetype and returns the corresponding archetype before the game start.
     * @return archetype an archetype that player selected
     * @see ArchetypeManager#menuItem()
     * @see ArchetypeManager#chooseArchetype(int)
     * @see Archetype
     */
    public Archetype run() {
        int choice = menuItem();
        Archetype archetype = chooseArchetype(choice);
        return archetype;
    }

    /**
     * This method display the available archetypes to the user for selection and returns user's choice.
     * @return choice an int that representing user's choice
     */
    public int menuItem(){
        Scanner sel = new Scanner(System.in);
        int choice = 0;

        try{
            System.out.println("1) Samurai");
            System.out.println("2) Bandit");
            System.out.println("3) Wretch");
            System.out.println("4) Astrologer");
            System.out.print("Choose your starting class: ");

            choice = Integer.parseInt(sel.nextLine());
            System.out.println("Your choice: " + choice);

            if (!validSelections.contains(choice)) {
                throw new InputMismatchException();
            }
        }
        catch (Exception e) {
            System.out.println("Enter either 1, 2, 3 or 4 to start the game");
        }
        return choice;
    }

    /**
     * This method create an archetype based on the user'choice (parameter) and return the created archetype.
     * @param choice int that representing user's choice
     * @return archetype an archetype that player selected
     * @see Archetype
     * @see ArchetypeManager#menuItem()
     * @see Samurai
     * @see Bandit
     * @see Wretch
     */
    public Archetype chooseArchetype(int choice){
        Archetype archetype = null;

        // Keep looping until a valid selection is made
        while (! validSelections.contains(choice)) {
            choice = menuItem();
        }

        switch (choice) {
            case 1:
                archetype = new Samurai();
                break;
            case 2:
                archetype = new Bandit();
                break;
            case 3:
                archetype = new Wretch();
                break;
            case 4:
                archetype = new Astrologer();
                break;
        }
        return archetype;
    }
}