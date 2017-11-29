package logic;

public enum Attribute {

    Strength(Integer.class),
    Agility(Integer.class),
    Health(Integer.class),
    AttackBonus(Integer.class),
    DefenceBonus(Integer.class),
    DamageValue(Integer.class),
    Armor(Integer.class),
    BaseAttribute(Attribute.class);

    private final Class type;

    Attribute(Class type) {
        this.type = type;
    }

    public Class getType() {
        return type;
    }
}
