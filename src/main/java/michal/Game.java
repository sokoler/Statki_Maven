package michal;

import java.util.Scanner;

public class Game {

    private int counterOfShots;
    private int counterOfMisses;
    private int counterOfHits;

    private int mapLength;
    private Field field;
    private Setup setup;
    private Ship[] ships;

    public void startApp() {

        setup = new Setup(mapLength, field, ships);
        setup.setupGame();
        checkUserAnswer();
    }

    public static int scan() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public void checkUserAnswer() {

        while (true) {

            int col = -1;
            int row = -1;

            System.out.print("Enter X coordinate: ");
            col = scan();
            System.out.print("Enter Y coordinate: ");
            row = scan();

            guess(col, row, setup);

            setup.getField().printMap();

            if (setup.getField().isWin()) {
                System.out.println("YOU WON");
                System.out.println("\n Number of shots : " + counterOfShots);
                System.out.println("\n Number of hits : " + counterOfHits);
                System.out.println("\n Number of misses : " + counterOfMisses);
                break;
            }
        }
    }

    public void guess(int col, int row, Setup setup) {

        if ((col >= 0 && col < setup.getMapLength()) && (row >= 0 && row < setup.getMapLength())) {

            counterOfShots++;
            if (setup.getField().isShotHere(row, col)) {
                System.out.println(" YOU ALREADY HIT HERE !");
            } else if (setup.getField().hasShip(row, col)) {
                counterOfHits++;
                setup.getField().markHit(row, col);
                System.out.println(" YOU HIT AT " + col + "," + row);

            } else {
                counterOfMisses++;
                setup.getField().markMiss(row, col);
                System.out.println(" YOU MISSED AT " + col + "," + row);
            }

        } else {
            System.out.println("Invalid location!");
        }

    }

}
