package lastpencil;

import java.util.Scanner;


public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Integer pencils = 0;
    static int whoisTurn = -1; //first = 1, second = 2
    private static final String FIRST = "John";
    private static final String SECOND = "Jack";

    public static void main(String[] args) {
        System.out.println("How many pencils would you like to use:");
        setupPencils();
        System.out.println("Who will be the first (John, Jack):");
        setupFirstPlayer();

        while (pencils > 0) {
            System.out.println("|".repeat(pencils) + "\n" + (whoisTurn == 1 ? FIRST : SECOND) + "'s turn!");
            pencil();
        }
        System.out.println((whoisTurn == 1 ? FIRST : SECOND) + " won!");
    }

    private static void setupPencils() {
        try {
            pencils = Integer.parseInt(scanner.nextLine());
            if (pencils == 0) {
                System.out.println("The number of pencils should be positive");
                setupPencils();
            } else if (pencils < 0) {
                throw new IllegalAccessException();
            }
        } catch (NumberFormatException | IllegalAccessException ignored) {
            System.out.println("The number of pencils should be numeric");
            setupPencils();
        }
    }

    private static void setupFirstPlayer() {
        String input = scanner.nextLine();
        if (input.equals(FIRST) || input.equals(SECOND)) {
            whoisTurn = input.equals(FIRST) ? 1 : 2;
        } else {
            System.out.println("Choose between " + FIRST + " and " + SECOND);
            setupFirstPlayer();
        }
    }

    private static void pencil() {
        int turnPencils;
        if (whoisTurn == 2) {
            if (pencils % 4 == 0) {
                turnPencils = 3;
            } else if (pencils == 1 || pencils % 2 == 0) {
                turnPencils = 1;
            } else {
                turnPencils = 2;
            }
            whoisTurn = 1;
            pencils -= turnPencils;
            System.out.println(turnPencils);
            return;
        }
        try {
            turnPencils = Integer.parseInt(scanner.nextLine());
            if (turnPencils < 1 || turnPencils > 3) {
                throw new IllegalStateException();
            } else if (turnPencils > pencils) {
                System.out.println("Too many pencils were taken");
                pencil();
            } else {
                pencils -= turnPencils;
                whoisTurn = whoisTurn == 1 ? 2 : 1;
            }
        } catch (NumberFormatException | IllegalStateException ignored) {
            System.out.println("Possible values: '1', '2' or '3'");
            pencil();
        }
    }
}