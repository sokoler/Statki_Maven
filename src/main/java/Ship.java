public class Ship {

    private int row;
    private int col;
    private int length;
    private int direction;

    public Ship(int length)
    {
        this.length = length;
        this.row = -1;
        this.col = -1;
        this.direction = -1;
    }

    public void setLocation(int row, int col)
    {
        this.row = row;
        this.col = col;
    }

    public void setDirection(int direction)
    {
        this.direction = direction;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getDirection() {
        return direction;
    }
}
