package logic;

import enums.Attribute;
import enums.Dice;
import exceptions.AttributeNotPresentException;
import exceptions.NoWeaponException;

import java.util.Map;

public class Fighter extends ArenaObject {

    private Weapon weapon = null;
    private Armor wear = null;

    private Dice attack = Dice.ZERO;
    private Dice defence = Dice.ZERO;
    private Dice damage = Dice.ZERO;
    private int armor = 0;

    public Fighter(String type, Integer strength, Integer agility, Integer health, Integer armor) {

        this.type = type;
        setAttribute(Attribute.Strength, strength);
        setAttribute(Attribute.Agility, agility);
        setAttribute(Attribute.Health, health);
        setAttribute(Attribute.Armor, armor);
    }

    public Fighter(String type, Map<Attribute, Integer> attributes) {

        this.type = type;
        setAttributes(attributes);
    }

    public boolean equip(Weapon weapon) {

        if (!weapon.isUsable(this)) {
            LOG.fine(type + " could not use a " + weapon.getType());
            return false;
        }
        if (this.weapon != null) {
            weapon.getAttributes().forEach(this::subAttribute);
            this.weapon.getBoni(this).forEach(this::subAttribute);
        }
        this.weapon = weapon;
        weapon.getAttributes().forEach(this::addAttribute);
        weapon.getBoni(this).forEach(this::addAttribute);
        LOG.fine(type + " equipped " + weapon.getType());
        return true;
    }

    public void attack(Fighter target) throws NoWeaponException, AttributeNotPresentException {

        LOG.fine(this.type + " attacks " + target.type);
        if (!target.defend(attack.roll())) {
            target.receive(damage.roll());
        } else {
            LOG.fine(target.type + " parried");
        }
    }

    private void receive(int hitValue) throws AttributeNotPresentException {
        int damage = hitValue - armor;
        if (damage > 0) {
            int currentHealth = getAttribute(Attribute.Health);
            setAttribute(Attribute.Health,currentHealth - damage);
            LOG.fine(this.type + " received " + damage + " damage and has now " + this.getAttribute(Attribute.Health));
        } else {
            LOG.fine("damage blocked by armor");
        }
    }

    private boolean defend(int attackValue) throws NoWeaponException, AttributeNotPresentException {
        return defence.roll() >= attackValue;
    }
}
