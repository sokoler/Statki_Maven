package michal;

public class Map {

    private Square[][] field;
    private int points;
    private int pointsToWin;

    public Map(int number) {
        field = new Square[number][number];

        for (int row = 0; row < field.length; row++) {
            for (int col = 0; col < field[row].length; col++) {
                Square temp = new Square();
                field[row][col] = temp;
            }
        }
    }

    public boolean isShotHere(int row, int col) {
        return field[row][col].getStatus() == Status.HIT || field[row][col].getStatus() == Status.MISSED;
    }

    public void markHit(int row, int col) {
        field[row][col].markHit();
        points++;
    }

    public void markMiss(int row, int col) {
        field[row][col].markMiss();
    }

    public boolean hasShip(int row, int col) {
        return field[row][col].isShip();
    }

    public void addShip(Ship s) {
        int row = s.getRow();
        int col = s.getCol();
        int length = s.getLength();
        Direction direction = s.getDirection();

        if (direction == Direction.HORIZONTAL) {
            for (int i = col; i < col + length; i++) {
                field[row][i].setShip(true);
            }
        } else if (direction == Direction.VERTICAL) {
            for (int i = row; i < row + length; i++) {
                field[i][col].setShip(true);
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
        for (int i = 0; i < field.length; i++)
            System.out.print("  " + i);
        System.out.println();

        for (int x = 0; x < field.length; x++) {
            if (x <= 9)
                System.out.print(" " + x);
            else
                System.out.print(x);

            for (int y = 0; y < field[x].length; y++) {
                System.out.print(field[x][y]);
            }
            System.out.println(x);
        }
    }

    public int getPointsToWin() {
        return pointsToWin;
    }

    public Square[][] getMap() {
        return field;
    }

    public void setMap(Square[][] map) {
        this.field = map;
    }
}
