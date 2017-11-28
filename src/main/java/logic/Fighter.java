package logic;

import java.util.HashMap;
import java.util.Map;

public class Fighter {

    private final String name;
    private final Map<Attribute, Integer> atributes;

    private Weapon weapon;

    public Fighter(String name, int strength, int agility, int health) {
        this.name = name;
        atributes = new HashMap<>();
        atributes.put(Attribute.Strength, strength);
        atributes.put(Attribute.Agility, agility);
        atributes.put(Attribute.Health, health);
    }

    public boolean setWeapon(Weapon weapon) {
        if (fulfills(weapon.getBaseAtribute(), 10)) {
            this.weapon = weapon;
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public int getAttribute(Attribute key) {
        return atributes.get(key);
    }

    public boolean fulfills(Attribute attribute, int value) {
        return (atributes.containsKey(attribute) && (atributes.get(attribute) >= value));
    }
}
