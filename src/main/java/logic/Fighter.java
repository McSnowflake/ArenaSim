package logic;

import exceptions.AttributeNotPresentException;
import exceptions.NoWeaponException;
import org.json.JSONObject;

import java.util.Map;

public class Fighter extends ArenaObject {

    private Weapon weapon = null;

    public Fighter(String type, Integer strength, Integer agility, Integer health, Integer armor) {

        this.type = type;
        attributes.put(Attribute.Strength, strength);
        attributes.put(Attribute.Agility, agility);
        attributes.put(Attribute.Health, health);
        attributes.put(Attribute.Armor, armor);
    }

    public Fighter(String type, Map<Attribute, Integer> attributes) {

        this.type = type;
        this.attributes = attributes;
    }

    public boolean setWeapon(Weapon weapon) {
        if (fulfills(weapon.getBaseAttribute(), 10)) {
            this.weapon = weapon;
            LOG.info(type + " is now armed with a " + weapon.getType());
            return true;
        }
        LOG.warning(type + " could not hold a " + weapon.getType() + " : " + weapon.getBaseAttribute() + " to low");
        return false;
    }

    public void attack(Fighter target) throws NoWeaponException, AttributeNotPresentException {
        int attackValue = this.getAttackValue();
        LOG.info(this.type + " attacks " + target.type);
        if (!target.defend(attackValue)) {
            int damageValue = weapon.getAttribute(Attribute.DamageValue);
            target.receive(damageValue);
        } else {
            LOG.info(target.type + " parried");
        }
    }

    private void receive(int hitValue) throws AttributeNotPresentException {
        int damage = hitValue - getAttribute(Attribute.Armor);
        if (damage > 0) {
            int currentHealth = getAttribute(Attribute.Health);
            attributes.put(Attribute.Health, currentHealth - damage);
            LOG.info(this.type + " received " + damage + " damage and has now " + this.getAttribute(Attribute.Health));
        } else {
            LOG.info("damage blocked by armor");

        }
    }

    private boolean defend(int attackvalue) throws NoWeaponException, AttributeNotPresentException {
        try {
            int defensValue = getAttribute(Attribute.Agility) + weapon.getAttribute(Attribute.DefenceBonus);
            return defensValue >= attackvalue;
        } catch (NullPointerException npe) {
            throw new NoWeaponException();
        }
    }

    private int getAttackValue() throws NoWeaponException, AttributeNotPresentException {
        try {
            return getAttribute(weapon.getBaseAttribute()) + weapon.getAttribute(Attribute.AttackBonus);
        } catch (NullPointerException npe) {
            throw new NoWeaponException();
        }

    }

    public Weapon load(JSONObject jsonObject) {
        return null;
    }
}
