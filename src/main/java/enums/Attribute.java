package enums;

public enum Attribute {

    Strength,
    Agility,
    Health,
    Attack,
    Defence,
    Damage,
    Armor;

    private int value = 0;

    public int getValue() {
        return value;
    }

    public Attribute setValue(int value) {
        this.value = value;
        return this;
    }
}
