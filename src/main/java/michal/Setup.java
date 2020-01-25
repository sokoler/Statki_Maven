package michal;

import java.util.*;

public class Setup {

    private Ship[] ships;
    private int fieldLength;
    private Field field;

    private static final List<Direction> VALUES =
            Collections.unmodifiableList(Arrays.asList(Direction.values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    private Setup() {
        throw new IllegalStateException("Utility class");
    }

    public Setup(int mapLength, Field field, Ship[] ships) {
        this.fieldLength = mapLength;
        this.field = field;
        this.ships = ships;
    }

    public void setupGame() {

        setSizeOfMap();

        setShipsAmount();

        setEachShipLength();

        setupShips();

    }

    public static int scan() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public void setEachShipLength() {
        for (int i = 0; i < ships.length; i++) {
            int[] shipsLength = new int[ships.length];
            do {
                System.out.println("Enter " + (i + 1) + " ship length (between 1 to 5)");
                shipsLength[i] = scan();
            } while (shipsLength[i] < 1 || shipsLength[i] > 5);
            Ship tempShip = new Ship(shipsLength[i]);
            ships[i] = tempShip;
        }
        field.setPointsToWin(ships);
    }

    public void setShipsAmount() {
        do {
            System.out.println("Enter ships amount (between 1 to 10)");
            ships = new Ship[scan()];
        } while (ships.length < 1 || ships.length > 10);
    }

    public void setSizeOfMap() {

        do {
            System.out.println("Enter the size of the map (between 10 to 20)");
            fieldLength = scan();
        } while (fieldLength < 10 || fieldLength > 20);
        field = new Field(fieldLength);
    }

    public void setupShips() {

        int counter = 0;
        int normalCounter = 1;

         Validator validator = new Validator(field);

        do {
            for (Ship s : ships) {
                System.out.println("\nShip " + normalCounter + ": Length : " + s.getLength());

                int length = ships[counter].getLength();

                int row = RANDOM.nextInt(fieldLength);
                int col = RANDOM.nextInt(fieldLength);
                Direction direction = VALUES.get(RANDOM.nextInt(SIZE));

                while (validator.isShipWronglyPlaced(row, col, direction, length, fieldLength)) {

                    row = RANDOM.nextInt(fieldLength);
                    col = RANDOM.nextInt(fieldLength);

                }
                ships[counter].setLocation(row, col);
                ships[counter].setDirection(direction);
                field.addShip(ships[counter]);

                counter++;
                normalCounter++;
            }
        }
        while (counter == ships.length - 1);
    }

    public int getFieldLength() {
        return fieldLength;
    }

    public void setFieldLength(int mapLength) {
        this.fieldLength = mapLength;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
