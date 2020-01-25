package michal;

public class Field {

    private Square[][] squares;
    private int points;
    private int pointsToWin;

    public Field(int number) {
        squares = new Square[number][number];

        for (int row = 0; row < squares.length; row++) {
            for (int col = 0; col < squares[row].length; col++) {
                Square temp = new Square();
                squares[row][col] = temp;
            }
        }
    }

    public boolean isShotHere(int row, int col) {
        return squares[row][col].getStatus() == Status.HIT || squares[row][col].getStatus() == Status.MISSED;
    }

    public void markHit(int row, int col) {
        squares[row][col].markHit();
        points++;
    }

    public void markMiss(int row, int col) {
        squares[row][col].markMiss();
    }

    public boolean hasShip(int row, int col) {
        return squares[row][col].isShip();
    }

    public void addShip(Ship s) {
        int row = s.getRow();
        int col = s.getCol();
        int length = s.getLength();
        Direction direction = s.getDirection();

        if (direction == Direction.HORIZONTAL) {
            for (int i = col; i < col + length; i++) {
                squares[row][i].setShip(true);
            }
        } else if (direction == Direction.VERTICAL) {
            for (int i = row; i < row + length; i++) {
                squares[i][col].setShip(true);
            }
        }
    }

    public boolean isWin() {

        return points >= pointsToWin;
    }

    public void setPointsToWin(Ship[] ships) {
        int sumOfShip = 0;
        for (Ship ship : ships) {
            sumOfShip += ship.getLength();
        }
        this.pointsToWin = sumOfShip;
    }

    public void printMap() {

        System.out.println();
        for (int i = 0; i < squares.length; i++)
            System.out.print("  " + i);
        System.out.println();

        for (int x = 0; x < squares.length; x++) {
            if (x <= 9)
                System.out.print(" " + x);
            else
                System.out.print(x);

            for (int y = 0; y < squares[x].length; y++) {
                System.out.print(squares[x][y]);
            }
            System.out.println(x);
        }
    }

    public int getPointsToWin() {
        return pointsToWin;
    }

    public Square[][] getSquares() {
        return squares;
    }

    public void setSquares(Square[][] squares) {
        this.squares = squares;
    }

}
