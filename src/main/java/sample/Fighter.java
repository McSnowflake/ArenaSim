package sample;

import java.util.HashMap;
import java.util.Map;

public class Fighter {

    private final String name;
    private final Integer health;
    private final Map<Atribute, Integer> atributes;

    public Fighter(String name, int strength, int agility, int health) {
        this.name = name;
        this.health = health;
        atributes = new HashMap<>();
        atributes.put(Atribute.Strength, strength);
        atributes.put(Atribute.Agility, agility);
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAtribute(Atribute key) {
        return atributes.get(key);
    }
}
