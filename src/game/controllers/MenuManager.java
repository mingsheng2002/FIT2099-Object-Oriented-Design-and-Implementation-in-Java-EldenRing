package game.controllers;

import game.actors.Player;
import game.actors.players.Bandit;
import game.actors.players.Samurai;
import game.actors.players.Wretch;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuManager {

    private static MenuManager instance =null;

    private MenuManager(){}

    public static MenuManager getInstance(){
        if (instance ==null){
            instance =  new MenuManager();
        }
        return instance;
    }

    public Player run(){
        int choice = menuItem();
        return createPlayer(choice);

    }

    public int menuItem(){
        Scanner sel = new Scanner(System.in);
        int choice = 0;

        try{
            System.out.println("1) Samurai");
            System.out.println("2) Bandit");
            System.out.println("3) Wretch");
            System.out.print("Select one class of player: ");


            choice = Integer.parseInt(sel.nextLine());
            System.out.println("Your choice: " + choice);

            if (!(choice>0 && choice<4) ){
                throw new InputMismatchException();
            }
        } catch (NumberFormatException ex) {
            System.out.println("Your choice must be an integer.");
        } catch (InputMismatchException ex) {
            System.out.println("Your choice must be an integer between 1 and 3.");
        }

        return choice;

    }

    public Player createPlayer(int choice){
        Player player = null;
        int selection = choice;

        // Keep looping until a valid selection is made
        while (selection != 1 && selection != 2 && selection != 3) {
            selection = menuItem();
        }

        switch (selection) {
            case 1:
                player = new Samurai();
                break;
            case 2:
                player = new Bandit();
                break;
            case 3:
                player = new Wretch();
                break;
        }

        return player;
    }



    }


