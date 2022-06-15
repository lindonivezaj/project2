package project2;
import java.util.Scanner;

public class project1 {

    String action = "";
    boolean valid1 = false;
    Scanner s = new Scanner(System.in);
    Player player = new Player();
    int turns = 0;
    public static void main(String[] args) {
        project1 game = new project1();

        game.outsideHouse();
        System.out.println("Total # of turns: " + game.getTotalTurns());
    }

    public void addTurn() {
        turns++;
    }

    public int getTotalTurns() {
        return turns;
    }

    public void outsideHouse() {
        boolean valid = false;

        do {
            System.out.println("You are locked outside your house. You need to get to the nearest safe house before the zombies eat you.");
            showNavigation();
            showCommands();

            action = s.next();

            if (action.equals("1")) {
                zombieEncounter();
                addTurn();
                valid = true;
            }

            else if (action.equals("2")) {
                outsideHouse();
                addTurn();
                valid = true;
            }

            else if (action.equals("3")) {
                safeRoute();
                addTurn();
                valid = true;
            }

            else if (action.equals("4")) {
                zombieEncounter();
                addTurn();
                valid = true;
            }

            else if (action.equals("5")) {
                System.out.println("There's no one to attack. Keep yourself together!");
                addTurn();
            }

            else if (action.equals("6")) {
                System.out.println("Are you out of your mind? You dashed away for nothing.");

                needToSleep();
                outsideHouse();
                addTurn();
                valid = true;
            }

            else if (action.equals("7")) {
                showCommands();
                showNavigation();
            }

            else if (action.equals("8")) {
                player.sleepPlayer();
                System.out.println("You've regained health. Your health is " + player.getHealth());
                addTurn();
                valid = true;
                outsideHouse();
            }
        } while (valid == false);
    }

    public void safeRoute() {
        boolean valid = false;

        do {
            System.out.println("There's no zombie around. What do you want to do next?");
            showCommands();
            showNavigation();
            action = s.next();

            if (action.equals("1")) {
                System.out.println("Dead end. You return to previous position.");
                safeRoute();
                addTurn();
                valid = true;
            }

            else if (action.equals("2")) {
                zombieEncounter();
                addTurn();
                valid = true;
            }

            else if (action.equals("3")) {
                oneStepLeft();
                addTurn();
                valid = true;
            }

            else if (action.equals("4")) {
                outsideHouse();
                addTurn();
                valid = true;
            }

            else if (action.equals("5")) {
                System.out.println("There's no one to attack. Keep yourself together!");
                addTurn();
            }

            else if (action.equals("6")) {
                System.out.println("Are you out of your mind? You dashed away for nothing.");
                needToSleep();
                outsideHouse();
                addTurn();
                valid = true;
            }

            else if (action.equals("7")) {
                showCommands();
                showNavigation();
            }

            else if (action.equals("8")) {
                player.sleepPlayer();
                System.out.println("You've regained health. Your health is " + player.getHealth());
                addTurn();
                valid = true;
                safeRoute();
            }
        } while (valid == false);
    }

    public void oneStepLeft() {
        boolean valid = false;

        do {
            System.out.println("You arrived at the safehouse. The caretaker greets you 'Hello'. What do you reply? You must reply with the appropriate word so that she will let you in.");

            action = s.next();

            if (action.equalsIgnoreCase("hi")) {
                gameEnding();
                addTurn();
                valid = true;
            }

            else {
                addTurn();
                System.out.println("Thats not the correct response. The caretaker takes you back to your house.");
                outsideHouse();
                valid = true;
            }
        } while (valid == false);


    }

    public void gameEnding() {
        System.out.println("Congratulations! You've finished the game!");

    }

    public void needToSleep() {
        boolean valid = false;
        do {
            System.out.println("You are wounded. You need to sleep to continue.");
            showNavigation();
            showCommands();
            action = s.next();

            if (action.equals("8")) {
                System.out.println("You've slept. You regained health.");
                valid = true;
            }
        } while (valid == false);
    }

    public void zombieEncounter() {
        boolean valid = false;
        do {
            System.out.println("There is a zombie here! What do you do?!");
            showCommands();
            action = s.next();

            if (action.equals("5")) {
                player.enemyDamage();
                System.out.println("You fought back but got wounded by 5 points. Your health is " + player.getHealth());
                valid = true;
                addTurn();
                needToSleep();
                safeRoute();
            }

            else if (action.equals("6")) {
                player.dashDamage();
                System.out.println("You dashed away. But you got tired. Your health is " + player.getHealth());
                needToSleep();
                valid = true;
                addTurn();
                outsideHouse();
            }

            else if (action.equals("7")) {
                showNavigation();
                showCommands();
            }

            else if (action.equals("8")) {
                System.out.println("Really?! You can't sleep! There's a zombie!");
                valid = false;
                addTurn();
            }
        } while (valid == false);
    }

    public void showNavigation() {
        System.out.println("(1) Go North");
        System.out.println("(2) Go South");
        System.out.println("(3) Go West");
        System.out.println("(4) Go East");
    }

    public void showCommands() {
        System.out.println("(5) Attack");
        System.out.println("(6) Dash away and return to previous position(inflicts damage to self by 1 point)");
        System.out.println("(7) Help");
        System.out.println("(8) Sleep");
    }
}

class Player {
    int health = 10;

    public void enemyDamage() {
        health -= 5;
    }

    public void dashDamage() {
        health--;
    }

    public int getHealth() {
        return health;
    }

    public void sleepPlayer() {
        if (health != 10) {
            health++;
        }
    }
}
