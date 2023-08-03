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
        pencils = Integer.parseInt(scanner.nextLine());

        System.out.println("Who will be the first (John, Jack):");
        whoisTurn = scanner.nextLine().equals(FIRST) ? 1 : 2;

        while (pencils > 0) {
            takeTurn();
        }
    }

    private static void takeTurn() {
        System.out.println("|".repeat(pencils) + "\n" + (whoisTurn == 1 ? FIRST : SECOND) + " 's turn:");
        pencils -= Integer.parseInt(scanner.nextLine());
        whoisTurn = whoisTurn == 1 ? 2 : 1;
    }
}
