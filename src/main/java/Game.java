import java.util.Random;
import java.util.Scanner;

public class Game {

    private static Ship[] ships;
    private static int mapLength;
    private static Map map;

    private static int counterOfShots = 0;
    private static int counterOfMisses = 0;
    private static int counterOfHits = 0;

    public static void main(String[] args) {

        setSizeOfMap();

        setShipsAmount();

        setEachShipLength();

        setupShips();

        checkUserAnswer();
    }

    private static void checkUserAnswer() {

        while (true) {

            guess();

            if (map.isWin()) {
                System.out.println("YOU WON");
                System.out.println("\n Number of shots : " + counterOfShots);
                System.out.println("\n Number of hits : " + counterOfHits);
                System.out.println("\n Number of misses : " + counterOfMisses);
                break;
            }
        }
    }

    private static void setEachShipLength() {
        for (int i = 0; i < ships.length; i++) {
            Scanner scanner = new Scanner(System.in);
            int[] shipsLength = new int[ships.length];
            do {
                System.out.println("Enter " + (i + 1) + " ship length (between 1 to 5)");
                shipsLength[i] = scanner.nextInt();
            } while (shipsLength[i] < 1 || shipsLength[i] > 5);
            Ship tempShip = new Ship(shipsLength[i]);
            ships[i] = tempShip;
        }
        map.setPointsToWin(ships);
    }

    private static void setShipsAmount() {
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter ships amount (between 1 to 10)");
            ships = new Ship[scanner.nextInt()];
        } while (ships.length < 1 || ships.length > 10);
    }

    private static void setSizeOfMap() {
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the size of the map (between 10 to 20)");
            mapLength = scanner.nextInt();
        } while (mapLength < 10 || mapLength > 20);
        map = new Map(mapLength);
    }

    private static void setupShips() {

        int counter = 0;
        int normalCounter = 1;
        Random rand = new Random();

        do {
            for (Ship s : ships) {
                System.out.println("\nShip " + normalCounter + ": Length : " + s.getLength());

                int length = ships[counter].getLength();

                int dir = rand.nextInt(2);
                int row = rand.nextInt(mapLength);
                int col = rand.nextInt(mapLength);

                while (isShipWronglyPlaced(row, col, dir, length)) {

                    row = rand.nextInt(mapLength);
                    col = rand.nextInt(mapLength);

                }
                ships[counter].setLocation(row, col);
                ships[counter].setDirection(dir);
                map.addShip(ships[counter]);

                counter++;
                normalCounter ++;
            }
        }
        while (counter == ships.length - 1);
    }

    private static void guess() {

            map.printMap();

            int col = -1, row = -1;

            Scanner input = new Scanner(System.in);
            System.out.print("Enter X coordinate: ");
            col = input.nextInt();
            System.out.print("Enter Y coordinate: ");
            row = input.nextInt();

            if ((col >= 0 && col < mapLength) && (row >= 0 && row < mapLength)) {

                counterOfShots++;
                if(map.isShotHere(row, col)) {
                    System.out.println(" YOU ALREADY HIT HERE !");
                }
                else if (map.hasShip(row, col)) {
                    {
                        counterOfHits++;
                        map.markHit(row, col);
                        System.out.println(" YOU HIT AT " + col + "," + row);
                    }
                } else {
                    counterOfMisses++;
                    map.markMiss(row, col);
                    System.out.println(" YOU MISSED AT " + col + "," + row);
                }


            } else {
                System.out.println("Invalid location!");
            }
    }

    private static boolean isShipWronglyPlaced(int row, int col, int dir, int length) {

        if (dir == 0) // Hortizontal
        {
            for (int i = col; i < col + length; i++) {
                if (col + length >= mapLength) {
                    break;
                }
                if (map.hasShip(row, i)) {
                    return true;
                }
            }
        } else if (dir == 1) // Vertical
        {
            for (int i = row; i < row + length; i++) {
                if (row + length >= mapLength) {
                    break;
                }
                if (row + length < mapLength) {
                    if (map.hasShip(i, col)) {
                        return true;
                    }
                }
            }
        }

        if (dir == 0) {
            int checker = length + col;
            if (checker > mapLength) {
                return true;
            }
        }

        if (dir == 1) {
            int checker = length + row;
            if (checker > mapLength) {
                return true;
            }
        }

        return false;
    }


}








