package michal;

public class Square {

    private boolean ship;
    private Status status;

    public Square() {
        status = Status.NOT_SET;
        ship = false;
    }

    public void markHit() {
        setStatus(Status.HIT);
    }

    public void markMiss() {
        setStatus(Status.MISSED);
    }

    public boolean isShip() {
        return ship;
    }

    public void setShip(boolean ship) {
        this.ship = ship;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "|" + getStatus().toString() + "|";
    }
}
