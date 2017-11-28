package sample;

import java.util.HashMap;
import java.util.Map;

public class Fighter {

    private final String name;
    private final Map<Atribute, Integer> atributes;

    private Weapon weapon;

    public Fighter(String name, int strength, int agility, int health) {
        this.name = name;
        atributes = new HashMap<>();
        atributes.put(Atribute.Strength, strength);
        atributes.put(Atribute.Agility, agility);
        atributes.put(Atribute.Health, health);
    }

    public boolean setWeapon(Weapon weapon) {
        if (fullfilles(weapon.getBaseAtribute(), 10)) {
            this.weapon = weapon;
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public int getAtribute(Atribute key) {
        return atributes.get(key);
    }

    public boolean fullfilles(Atribute atribute, int value) {
        return (atributes.containsKey(atribute) && (atributes.get(atribute) >= value));
    }
}
