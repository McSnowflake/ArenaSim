package logic;

import exceptions.NoWeaponException;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.*;

public class Fighter {

    private static Logger LOG = Logger.getAnonymousLogger();

    static {
        LOG.setUseParentHandlers(false);
        Handler conHdlr = new ConsoleHandler();
        conHdlr.setFormatter(new Formatter() {
            public String format(LogRecord record) {
                return record.getMessage() + "\n";
            }
        });
        LOG.addHandler(conHdlr);

    }

    private final String type;
    private final Map<Attribute, Integer> atributes;

    private Weapon weapon = null;
    private int armor;

    public Fighter(String type, int strength, int agility, int health) {
        this.type = type;
        atributes = new HashMap<>();
        atributes.put(Attribute.Strength, strength);
        atributes.put(Attribute.Agility, agility);
        atributes.put(Attribute.Health, health);
    }

    public boolean setWeapon(Weapon weapon) {
        if (fulfills(weapon.getBaseAtribute(), 10)) {
            this.weapon = weapon;
            LOG.info(type + " is now armed with a " + weapon);
            return true;
        }
        LOG.warning(type + " could not hold a " + weapon);
        return false;
    }

    public String getType() {
        return type;
    }

    public int getAttribute(Attribute key) {
        return atributes.get(key);
    }

    public boolean fulfills(Attribute attribute, int value) {
        return (atributes.containsKey(attribute) && (atributes.get(attribute) >= value));
    }

    public void attack(Fighter target) throws NoWeaponException {
        int attackValue = this.getAttackValue();
        LOG.info(this.type + " attacks " + target.type);
        if (!target.defend(attackValue)) {
            int damageValue = weapon.getDamageValue();
            target.receive(damageValue);
        } else {
            LOG.info(target.type + " parried");
        }
    }

    private void receive(int hitValue) {
        int damage = hitValue - armor;
        if (damage > 0) {
            int currentHealth = atributes.get(Attribute.Health);
            atributes.put(Attribute.Health, currentHealth - damage);
            LOG.info(this.type + " received " + damage + " damage and has now " + this.getAttribute(Attribute.Health));
        } else {
            LOG.info("damage blocked by armor");

        }
    }

    private boolean defend(int attackvalue) throws NoWeaponException {
        try {
            int defensValue = getAttribute(Attribute.Agility) + weapon.getDefenceBonus();
            return defensValue >= attackvalue;
        } catch (NullPointerException npe) {
            throw new NoWeaponException();
        }
    }

    private int getAttackValue() throws NoWeaponException {
        try {
            return getAttribute(weapon.getBaseAtribute()) + weapon.getAttackValue();
        } catch (NullPointerException npe) {
            throw new NoWeaponException();
        }

    }
}
