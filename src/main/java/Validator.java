

public class Validator {

    public static boolean isShipWronglyPlaced(int row, int col, Direction dir, int length) {

        if (dir == Direction.HORIZONTAL) // Hortizontal
        {
            for (int i = col; i < col + length; i++) {
                if (col + length >= Setup.mapLength) {
                    break;
                }
                if (Setup.map.hasShip(row, i)) {
                    return true;
                }
            }
        } else if (dir == Direction.VERTICAL) // Vertical
        {
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
        }

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

}
