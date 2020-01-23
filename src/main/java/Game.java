import java.util.*;

public class Game {

    private static int counterOfShots = 0;
    private static int counterOfMisses = 0;
    private static int counterOfHits = 0;

    public static void startApp() {

        Setup.setupGame();
        checkUserAnswer();

    }

    public static int scan() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static void checkUserAnswer() {

        while (true) {

            int col = -1, row = -1;

            System.out.print("Enter X coordinate: ");
            col = scan();
            System.out.print("Enter Y coordinate: ");
            row = scan();

            guess(col , row);

            if (Setup.map.isWin()) {
                System.out.println("YOU WON");
                System.out.println("\n Number of shots : " + counterOfShots);
                System.out.println("\n Number of hits : " + counterOfHits);
                System.out.println("\n Number of misses : " + counterOfMisses);
                break;
            }
        }
    }

    public static void guess(int col, int row) {

        if ((col >= 0 && col < Setup.mapLength) && (row >= 0 && row < Setup.mapLength)) {

            counterOfShots++;
            if (Setup.map.isShotHere(row, col)) {
                System.out.println(" YOU ALREADY HIT HERE !");
            } else if (Setup.map.hasShip(row, col)) {
                {
                    counterOfHits++;
                    Setup.map.markHit(row, col);
                    System.out.println(" YOU HIT AT " + col + "," + row);
                }
            } else {
                counterOfMisses++;
                Setup.map.markMiss(row, col);
                System.out.println(" YOU MISSED AT " + col + "," + row);
            }

        } else {
            System.out.println("Invalid location!");
        }

        Setup.map.printMap();

    }


}
