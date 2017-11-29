package enums;

public enum Dice {

    HIGH(3),
    MIDDLE(2),
    LOW(1),
    ZERO(0);

    int max;

    Dice(int max) {
        this.max = max;
    }

    public int roll() {
        return (int) Math.round(Math.random() * max);
    }
}
