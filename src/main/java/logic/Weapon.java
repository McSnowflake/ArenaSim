package logic;

import numbers.Attribute;
import numbers.Rule;

import java.util.List;

public class Weapon extends Equipment {

    private final Attribute baseAttribute;

    public Weapon(String name, Weapon.Type weaponType, Attribute baseAttribute, List<Rule> usageRules) {
        super(name, Equipment.Type.Weapon, weaponType, usageRules);
        this.baseAttribute = baseAttribute;
    }

    public Attribute getBaseAttribute() {
        return baseAttribute;
    }

    public enum Type implements SubType {
        thrusting,
        cutting,
        beating
    }
}
