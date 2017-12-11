package logic;

import data.DataManager;
import numbers.Rule;

import javax.json.JsonObject;
import java.util.List;

public class Armor extends Equipment {

    public Armor(String name, Armor.Type armorType, List<Rule> usageRules) {
        super(name, Equipment.Type.Armor, armorType, usageRules);
    }

    public enum Type implements SubType {
        shield,
        body
    }
}
