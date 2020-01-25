
import michal.*;
import michal.Map;
import org.junit.Before;
import org.junit.Test;


import java.util.*;

import static org.junit.Assert.*;

public class ShipsTests {

    private Square[][] squares;
    private Map map;
    private Setup setup;
    private int points;
    private int shots;
    private static final List<Direction> VALUES =
            Collections.unmodifiableList(Arrays.asList(Direction.values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();


    @Before
    public void setUp() {
        squares = new Square[10][10];
        map = new Map(10);

        for (int row = 0; row < squares.length; row++) {
            for (int col = 0; col < squares[row].length; col++) {
                Square temp = new Square();
                squares[row][col] = temp;
            }
        }

    }

    @Test
    public void testCreate() {

        assertNotNull(squares);
    }

    @Test
    public void testHitShoot() {

        squares[6][7].setShip(true);
        squares[6][7].markHit();

        assertEquals(Status.HIT, squares[6][7].getStatus());

    }

    @Test
    public void testMissShoot() {
        squares[5][5].setShip(true);
        squares[4][4].markMiss();

        assertEquals(Status.MISSED, squares[4][4].getStatus());
    }

    @Test
    public void testIsCorrectShip() {
        squares[2][3].setShip(true);

        assertTrue(squares[2][3].isShip());
    }

    @Test
    public void testIsWrongShip() {
        squares[2][3].setShip(true);

        assertFalse(squares[4][4].isShip());
    }

    @Test
    public void testIsAlreadyShotHere() {
        map.markHit(3, 3);
        map.markMiss(5, 5);

        assertTrue(map.isShotHere(3, 3));
        assertTrue(map.isShotHere(5, 5));
    }

    @Test
    public void testIsWin() {
        Ship[] ships = new Ship[2];
        Ship tempShip = new Ship(5);
        Ship tempShip2 = new Ship(4);

        ships[0] = tempShip;
        ships[1] = tempShip2;

        map.setPointsToWin(ships);

        assertEquals(9, map.getPointsToWin());
    }

    @Test
    public void testEnterInvalidLocation() {

        int col = 12;
        int row = 5;

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            map.markHit(row, col);
        });

    }

    @Test
    public void testSetupShipss() {

        Ship[] ships = new Ship[2];
        Ship tempShip = new Ship(5);
        Ship tempShip2 = new Ship(4);

        ships[0] = tempShip;
        ships[1] = tempShip2;

        setup = new Setup(10, map, ships);

        setup.setupShips();

        assertTrue(map.hasShip(1, 2));

    }

    @Test
    public void testAddShip() {
        Ship ship = new Ship(3);
        ship.setCol(1);
        ship.setRow(1);
        ship.setDirection(Direction.HORIZONTAL);
        map.addShip(ship);

        assertTrue(map.hasShip(1, 1));
        assertTrue(map.hasShip(1, 2));
        assertTrue(map.hasShip(1, 3));
    }

    @Test
    public void testGuess() {

        Ship[] ships = new Ship[2];
        Ship tempShip = new Ship(5);
        Ship tempShip2 = new Ship(4);

        ships[0] = tempShip;
        ships[1] = tempShip2;

        setup = new Setup(10, map, ships);

        Game game = new Game();
        game.guess(2, 2, setup);

        assertEquals(Status.MISSED, squares[2][2].getStatus());

    }

    @Test
    public void testSetupShips() {

        int counter = 0;
        int normalCounter = 1;

        Ship[] ships = new Ship[1];

        Ship tempShip = new Ship(3);

        ships[0] = tempShip;

        do {
            for (Ship s : ships) {

                int row = 1;
                int col = 1;
                row++;
                Direction direction = Direction.VERTICAL;

                ships[counter].setLocation(row, col);
                ships[counter].setDirection(direction);
                map.addShip(ships[counter]);

                counter++;
                normalCounter++;
            }
        }
        while (counter == ships[0].getLength());

        assertTrue(map.hasShip(2, 1));
        assertTrue(map.hasShip(3, 1));
        assertTrue(map.hasShip(4, 1));
    }


}



