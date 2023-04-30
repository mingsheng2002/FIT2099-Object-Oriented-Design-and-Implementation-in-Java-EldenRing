package game.controllers;

import game.actors.playerarchetypes.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ArchetypeManager {

    private static ArchetypeManager instance =null;
    private Player player;

    private ArchetypeManager(){}

    public static ArchetypeManager getInstance(){
        if (instance ==null){
            instance =  new ArchetypeManager();
        }
        return instance;
    }

    public Archetype run() {
        int choice = menuItem();
        Archetype archetype = chooseArchetype(choice);
        return archetype;
    }

    public int menuItem(){
        Scanner sel = new Scanner(System.in);
        int choice = 0;

        try{
            System.out.println("1) Samurai");
            System.out.println("2) Bandit");
            System.out.println("3) Wretch");
            System.out.print("Choose your starting class: ");

            choice = Integer.parseInt(sel.nextLine());
            System.out.println("Your choice: " + choice);

            if (!(choice > 0 && choice < 4)) {
                throw new InputMismatchException();
            }
        }
        catch (Exception e) {
            System.out.println("Enter either 1, 2 or 3 to start the game");
        }
        return choice;
    }

    public Archetype chooseArchetype(int choice){
        Archetype archetype = null;
        int selection = choice;

        // Keep looping until a valid selection is made
        while (selection != 1 && selection != 2 && selection != 3) {
            selection = menuItem();
        }

        switch (selection) {
            case 1:
                archetype = new Samurai();
                break;
            case 2:
                archetype = new Bandit();
                break;
            case 3:
                archetype = new Wretch();
                break;
        }

        return archetype;
    }
}