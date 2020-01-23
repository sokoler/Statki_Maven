

public class Validator {

    public static boolean isShipWronglyPlaced(int row, int col, Direction dir, int length) {

        if (dir == Direction.HORIZONTAL) {
            Boolean x = isPlaceOccupiedHorizontally(row, col, length);
            if (x) return true;
        } else if (dir == Direction.VERTICAL) {
            Boolean x = isPlaceOccupiedVertically(row, col, length);
            if (x) return true;
        }

        if (isOutOfTheMap(row, col, dir, length)) return true;

        return false;
    }

    private static boolean isOutOfTheMap(int row, int col, Direction dir, int length) {
        if (dir == Direction.HORIZONTAL) {
            int checker = length + col;
            if (checker > Setup.mapLength) {
                return true;
            }
        }

        if (dir == Direction.VERTICAL) {
            int checker = length + row;
            if (checker > Setup.mapLength) {
                return true;
            }
        }
        return false;
    }

    private static Boolean isPlaceOccupiedVertically(int row, int col, int length) {
        for (int i = row; i < row + length; i++) {
            if (row + length >= Setup.mapLength) {
                break;
            }
            if (row + length < Setup.mapLength) {
                if (Setup.map.hasShip(i, col)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static Boolean isPlaceOccupiedHorizontally(int row, int col, int length) {
        for (int i = col; i < col + length; i++) {
            if (col + length >= Setup.mapLength) {
                break;
            }
            if (Setup.map.hasShip(row, i)) {
                return true;
            }
        }
        return false;
    }

}
