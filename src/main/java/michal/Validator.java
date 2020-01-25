package michal;


public class Validator {

    private Map map;

    private Validator() {

        throw new IllegalStateException("Utility class");
    }

    public Validator(Map map) {
        this.map = map;
    }

    boolean isShipWronglyPlaced(int row, int col, Direction dir, int length, int mapLength) {

        if (dir == Direction.HORIZONTAL) {
            boolean x = isPlaceOccupiedHorizontally(row, col, length, mapLength);
            if (x) return true;
        } else if (dir == Direction.VERTICAL) {
            boolean x = isPlaceOccupiedVertically(row, col, length, mapLength);
            if (x) return true;
        }

        return isOutOfTheMap(row, col, dir, length, mapLength);
    }

    boolean isOutOfTheMap(int row, int col, Direction dir, int length, int mapLength) {
        if (dir == Direction.HORIZONTAL) {
            int checker = length + col;
            if (checker > mapLength) {
                return true;
            }
        }

        if (dir == Direction.VERTICAL) {
            int checker = length + row;
            return checker > mapLength;
        }
        return false;
    }

    boolean isPlaceOccupiedVertically(int row, int col, int length, int mapLength) {
        for (int i = row; i < row + length; i++) {
            if (row + length >= mapLength) {
                break;
            }
            if (map.hasShip(i, col)) {
                return true;
            }
        }
        return false;
    }

    boolean isPlaceOccupiedHorizontally(int row, int col, int length, int mapLength) {
        for (int i = col; i < col + length; i++) {
            if (col + length >= mapLength) {
                break;
            }
            if (map.hasShip(row, i)) {
                return true;
            }
        }
        return false;
    }

}
