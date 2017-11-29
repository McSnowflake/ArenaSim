package logic;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

@XmlRootElement
public class Weapon extends ArenaObject {

    private Attribute baseAttribute;

    public Weapon(String type, Attribute baseAttribute, int attack, int defence, int damage) {
        this.type = type;
        this.baseAttribute = baseAttribute;
        this.attributes.put(Attribute.AttackBonus, attack);
        this.attributes.put(Attribute.DefenceBonus, defence);
        this.attributes.put(Attribute.DamageValue, damage);
    }

    public Weapon(String type, Attribute baseAttribute, Map<Attribute, Integer> attributes) {
        this.type = type;
        this.baseAttribute = baseAttribute;
        this.attributes = attributes;
    }

    public Weapon(String type, Attribute baseAttribute) {
        this.type = type;
        this.baseAttribute = baseAttribute;
    }

    public Attribute getBaseAttribute() {
        return baseAttribute;
    }

    public String toString() {
        return type + ":" + baseAttribute.name();
    }

    public int getAttackValue(Map<Attribute,Integer> attributes) {
        return 0;
    }

    public int getDamageValue(Map<Attribute,Integer> attributes) {
        return 0;
    }

    public boolean isUsable(Map<Attribute,Integer> attributes) {
        return false;
    }
}
