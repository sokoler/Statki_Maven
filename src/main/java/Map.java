public class Map {

    private Square[][] map;
    private int points;
    private int pointsToWin;

    public Map(int number) {
        map = new Square[number][number];

        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                Square temp = new Square();
                map[row][col] = temp;
            }
        }
    }

    public boolean isShotHere(int row, int col) {
        if (map[row][col].getStatus().equals("X") || map[row][col].getStatus().equals("#")) {
            return true;
        }
        return false;
    }

    public void markHit(int row, int col) {
        map[row][col].markHit();
        points++;
    }

    public void markMiss(int row, int col) {
        map[row][col].markMiss();
    }

    public boolean hasShip(int row, int col) {
        return map[row][col].isShip();
    }

    public void addShip(Ship s) {
        int row = s.getRow();
        int col = s.getCol();
        int length = s.getLength();
        int dir = s.getDirection();

        if (dir == 0) // Hortizontal
        {
            for (int i = col; i < col + length; i++) {
                map[row][i].setShip(true);
            }
        } else if (dir == 1) // Vertical
        {
            for (int i = row; i < row + length; i++) {
                map[i][col].setShip(true);
            }
        }
    }

    public boolean isWin() {

        if (points >= pointsToWin)
            return true;
        else
            return false;
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
        for (int i = 0; i < map.length; i++)
            System.out.print("  " + i);
        System.out.println();

        for (int x = 0; x < map.length; x++) {
            if (x <= 9)
                System.out.print(" " + x);
            else
                System.out.print(x);

            for (int y = 0; y < map[x].length; y++) {
                System.out.print(map[x][y]);
            }
            System.out.println(x);
        }
    }

    public int getPointsToWin() {
        return pointsToWin;
    }
}
