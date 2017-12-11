package numbers;

public enum Die {

    HIGH(3),
    MIDDLE(2),
    LOW(1),
    ZERO(0);

    int max;

    Die(int max) {
        this.max = max;
    }

    public int roll() {
        return (int) Math.round(Math.random() * max);
    }

}
