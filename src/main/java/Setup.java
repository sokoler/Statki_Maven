import java.util.*;

public class Setup {

    public static Ship[] ships;
    public static int mapLength;
    public static Map map;

    private static final List<Direction> VALUES =
            Collections.unmodifiableList(Arrays.asList(Direction.values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static void setupGame() {

        setSizeOfMap();

        setShipsAmount();

        setEachShipLength();

        setupShips();
    }


    public static Direction randomDirection() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    public static int scan() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static void setEachShipLength() {
        for (int i = 0; i < ships.length; i++) {
            int[] shipsLength = new int[ships.length];
            do {
                System.out.println("Enter " + (i + 1) + " ship length (between 1 to 5)");
                shipsLength[i] = scan();
            } while (shipsLength[i] < 1 || shipsLength[i] > 5);
            Ship tempShip = new Ship(shipsLength[i]);
            ships[i] = tempShip;
        }
        map.setPointsToWin(ships);
    }

    public static void setShipsAmount() {
        do {
            System.out.println("Enter ships amount (between 1 to 10)");
            ships = new Ship[scan()];
        } while (ships.length < 1 || ships.length > 10);
    }

    public static void setSizeOfMap() {
        do {
            System.out.println("Enter the size of the map (between 10 to 20)");
            mapLength = scan();
        } while (mapLength < 10 || mapLength > 20);
        map = new Map(mapLength);
    }

    public static void setupShips() {

        int counter = 0;
        int normalCounter = 1;

        do {
            for (Ship s : ships) {
                System.out.println("\nShip " + normalCounter + ": Length : " + s.getLength());

                int length = ships[counter].getLength();

                int row = RANDOM.nextInt(mapLength);
                int col = RANDOM.nextInt(mapLength);
                Direction direction = VALUES.get(RANDOM.nextInt(SIZE));

                while (Validator.isShipWronglyPlaced(row, col, direction, length)) {

                    row = RANDOM.nextInt(mapLength);
                    col = RANDOM.nextInt(mapLength);

                }
                ships[counter].setLocation(row, col);
                ships[counter].setDirection(direction);
                map.addShip(ships[counter]);

                counter++;
                normalCounter++;
            }
        }
        while (counter == ships.length - 1);
    }

}
