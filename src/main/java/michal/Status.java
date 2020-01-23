package michal;

public enum Status {
    MISSED("#"),
    HIT("X"),
    NOT_SET(" ");

    private final String name;

    Status(String s) {
        name = s;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
