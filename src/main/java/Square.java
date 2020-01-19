public class Square {

    private static final String HIT = "X";
    private static final String MISSED = "#";

    private boolean ship;
    private String status;

    public Square()
    {
        status =" ";
        ship = false;
    }

    public void markHit()
    {
        setStatus(HIT);
    }

    public void markMiss()
    {
        setStatus(MISSED);
    }

    public boolean isShip() {
        return ship;
    }

    public void setShip(boolean ship) {
        this.ship = ship;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "|"+ getStatus() +"|";
    }
}
