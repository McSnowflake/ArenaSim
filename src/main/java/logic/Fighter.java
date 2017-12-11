package logic;

import exceptions.AttributeNotPresentException;
import exceptions.EquipmentTypeNotKnownException;
import exceptions.NoWeaponException;
import numbers.Attribute;
import numbers.Rule;
import numbers.Value;

import java.util.Map;
import java.util.logging.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Fighter {

    private static Logger LOG = Logger.getGlobal();

    private final Type type;
    private final Class _class;
    private final Map<Attribute, Integer> baseAttributes;

    private Weapon weapon = null;
    private Armor wear = null;
    private Map<Attribute, Value> attributes;

    public Fighter(Type type, Class _class, Map<Attribute, Integer> baseAttributes) {
        this.type = type;
        this._class = _class;
        this.baseAttributes = baseAttributes;

        this.calcStats();
    }

    public void calcStats() {
        attributes = baseAttributes.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> new Value(entry.getValue())));
        if (wear != null) {
            alterAttributes(wear.getBonus(this));
        }
        if (weapon != null) {
            alterAttributes(weapon.getBonus(this));
        }
    }

    public Type getType() {
        return type;
    }

    public Value getAttribute(Attribute key) {
        if (attributes.containsKey(key))
            return attributes.get(key);
        return null;
    }

    public int getBaseOf(Attribute key) {
        if (attributes.containsKey(key))
            return attributes.get(key).getBase();
        return 0;
    }

    public int roll(Attribute key) {
        if (attributes.containsKey(key))
            return attributes.get(key).get();
        return 0;
    }

    public void setAttribute(Attribute attribute, Value value) {
        attributes.put(attribute, value);
    }

    public void alterAttribute(Attribute attribute, Value value) {
        Value attributeToChange = attributes.get(attribute);
        if (attributeToChange == null) {
            attributes.put(attribute, value);
        } else {
            attributeToChange.addToBase(value.getBase());
            attributeToChange.addToDice(value.getDice());
        }
    }

    public void alterAttribute(Attribute attribute, Integer value) {
        attributes.put(attribute, attributes.get(attribute).addToBase(value));
    }

    public void alterBaseAttribute(Attribute attribute, Integer value) {
        baseAttributes.put(attribute, baseAttributes.get(attribute) + value);
    }

    public Stream<Map.Entry<Attribute, Value>> getAttributes() {
        return attributes.entrySet().stream();
    }

    public Stream<Map.Entry<Attribute, Integer>> getBaseAttributes() {
        return baseAttributes.entrySet().stream();
    }

    public boolean fulfills(Stream<Map.Entry<Attribute, Integer>> requirements) {

        return requirements.allMatch(requirement -> requirement.getValue() <= getAttribute(requirement.getKey()).getBase());
    }

    public void alterAttributes(Stream<Map.Entry<Attribute, Value>> attributes) {
        attributes.forEach(entry -> alterAttribute(entry.getKey(), entry.getValue()));
    }

    public void printAttributes() {
        LOG.fine(getType().name());
        getAttributes().forEach(attribute -> LOG.fine(attribute.getKey().name() + " : " + attribute.getValue().print()));
    }

    public boolean equip(Equipment equipment) throws EquipmentTypeNotKnownException {

        boolean result = true;

        Weapon weaponOnStand = null;
        if (weapon != null) {
            weaponOnStand = weapon;
            weapon = null;
        }

        switch (equipment.getType()) {
        case Armor:
            wear = null;
            calcStats();
            if (this.canUse(equipment)) {
                wear = (Armor) equipment;

            } else {
                result = false;
                equipment = weaponOnStand;
            }
        case Weapon:
            calcStats();
            if (this.canUse(equipment)) {
                weapon = (Weapon) equipment;
            } else
                result = false;
            break;
        default:
            throw new EquipmentTypeNotKnownException(equipment.getType());
        }
        calcStats();
        return result;
    }

    public void attack(Fighter target) throws NoWeaponException, AttributeNotPresentException {

        LOG.fine(this.type + " attacks " + target.type);
        if (!target.defend(attributes.get(Attribute.Attack).get() + getBaseOf(weapon.getBaseAttribute()))) {
            target.receive(roll(Attribute.Damage));
        } else {
            LOG.fine(target.type + " parried");
        }
    }

    private void receive(int hitValue) throws AttributeNotPresentException {
        int damage = hitValue - getBaseOf(Attribute.Armor);
        if (damage > 0) {
            alterAttribute(Attribute.Health, -1 * damage);
            LOG.fine(this.type + " received " + damage + " damage and has now " + this.getAttribute(Attribute.Health)+"!!!");
        } else {
            LOG.fine("damage blocked by armor");
        }
    }

    private boolean defend(int attackValue) throws NoWeaponException, AttributeNotPresentException {
        return (roll(Attribute.Defence) + getBaseOf(Attribute.Agility)) >= attackValue;
    }

    public boolean canUse(Equipment equipment) {
        return this.fulfills(equipment.getRules().filter(Rule::isMandatory).flatMap(Rule::getRequirements));
    }

    public Class getCClass() {
        return _class;
    }

    public enum Type {
        Warrior,
        Rogue,
        Sorcerer
    }

    public enum Class {
        Elf,
        Dwarf,
        Human
    }
}
