
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class ShipsTests {

    private Square[][] squares;
    private Map map;

    @Before
    public void setUp(){
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
    public void failsSetStatus(){
        squares[5][5].setStatus("OK");
        assertEquals(squares[5][5].getStatus(),"NO");
    }

    @Test
    public void testHitShoot() {

        squares[6][7].setShip(true);
        squares[6][7].markHit();

        assertEquals(squares[6][7].getStatus(), "X");

    }

    @Test
    public void testMissShoot() {
        squares[5][5].setShip(true);
        squares[4][4].markMiss();

        assertEquals(squares[4][4].getStatus(), "#");
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
        map.markHit(3,3);
        map.markMiss(5,5);

        assertTrue(map.isShotHere(3,3));
        assertTrue(map.isShotHere(5,5));
    }

    @Test
    public void testIsWin(){
        Ship[] ships = new Ship[2];
        Ship tempShip = new Ship(5);
        Ship tempShip2 = new Ship(4);

        ships[0] = tempShip;
        ships[1] = tempShip2;

        map.setPointsToWin(ships);

        assertEquals(map.getPointsToWin(), 9);
    }

    @Test
    public void testEnterInvalidLocation(){

        int col = 12;
        int row = 5;

            assertThrows(ArrayIndexOutOfBoundsException.class, () ->{
                map.markHit(row, col);
            });


    }

}



