import exceptions.AttributeNotPresentException;
import exceptions.EquipmentNotSuitableException;
import exceptions.EquipmentTypeNotKnownException;
import exceptions.NoWeaponException;
import logic.Fighter;
import logic.Weapon;
import numbers.Attribute;
import numbers.Die;
import numbers.Rule;
import numbers.Value;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static numbers.Attribute.*;

public class FightTest extends AbstractTest {

    private static AtomicInteger rogue_count = new AtomicInteger(0);
    private static AtomicInteger warrior_count = new AtomicInteger(0);

    @Test
    public void deathMatch() {

        long startTime = System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < 100_000; i++) {
            Runnable worker = new Task();
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {        }

        long stopTime = System.currentTimeMillis();

        LOG.info("Rogue wins: " + rogue_count);
        LOG.info("Warrior wins: " + warrior_count);
        LOG.info("Time for calculation (in ms): " + (stopTime - startTime));
    }

    private class Task implements Runnable {

        private Fighter[] fighter = new Fighter[2];
        private Weapon sword;
        private Weapon club;

        @Override public void run() {
            turn t = new turn();
            try {
                init();
                while (fighter[0].getBaseOf(Attribute.Health) > 0 && fighter[1].getBaseOf(Attribute.Health) > 0) {
                    fighter[t.get1()].attack(fighter[t.get2()]);
                    t.turn();
                }
                if (fighter[0].getBaseOf(Attribute.Health) <= 0) {
                    FightTest.rogue_count.incrementAndGet();
                    LOG.fine("warrior died");
                }
                if (fighter[1].getBaseOf(Attribute.Health) <= 0) {
                    FightTest.warrior_count.incrementAndGet();
                    LOG.fine("rogue died");
                }
            } catch (NoWeaponException | AttributeNotPresentException | EquipmentNotSuitableException | EquipmentTypeNotKnownException e) {
                e.printStackTrace();
            }
        }

        private void init() throws EquipmentTypeNotKnownException, EquipmentNotSuitableException {

            // create warrior
            Map<Attribute, Integer> attributes = new HashMap<>();
            attributes.put(Strength, 11);
            attributes.put(Agility, 9);
            attributes.put(Health, 21);
            attributes.put(Attribute.Defence, 1);
            attributes.put(Attribute.Armor, 1);
            fighter[0] = new Fighter(Fighter.Type.Warrior, Fighter.Class.Human, attributes);

            // create rogue
            attributes = new HashMap<>();
            attributes.put(Strength, 9);
            attributes.put(Agility, 12);
            attributes.put(Health, 17);
            attributes.put(Attribute.Defence, 1);
            fighter[1] = new Fighter(Fighter.Type.Rogue, Fighter.Class.Human, attributes);

            // create sword
            List<Rule> rules = new ArrayList<>();
            Map<Attribute, Integer> baseRequirement = new HashMap<>();
            baseRequirement.put(Agility, 9);
            baseRequirement.put(Strength, 9);
            Map<Attribute, Value> baseAttribute = new HashMap<>();
            baseAttribute.put(Attribute.Attack, new Value(Die.MIDDLE));
            baseAttribute.put(Attribute.Defence, new Value(Die.MIDDLE));
            baseAttribute.put(Attribute.Damage, new Value(Die.MIDDLE));
            rules.add(new Rule(baseRequirement, baseAttribute, true));
            Map<Attribute, Integer> bonusRequirement = new HashMap<>();
            bonusRequirement.put(Agility, 12);
            Map<Attribute, Value> bonusAttribute = new HashMap<>();
            bonusAttribute.put(Attribute.Attack, new Value(1));
            bonusAttribute.put(Attribute.Damage, new Value(1));
            bonusAttribute.put(Attribute.Defence, new Value(1));
            rules.add(new Rule(bonusRequirement, bonusAttribute, false));
            sword = new Weapon("Sword", Weapon.Type.cutting, Agility, rules);

            // create club
            rules = new ArrayList<>();
            baseRequirement = new HashMap<>();
            baseRequirement.put(Strength, 10);
            baseRequirement.put(Agility, 8);
            baseAttribute = new HashMap<>();
            baseAttribute.put(Attribute.Attack, new Value(Die.MIDDLE));
            baseAttribute.put(Attribute.Defence, new Value(Die.LOW));
            baseAttribute.put(Attribute.Damage, new Value(Die.MIDDLE));
            rules.add(new Rule(baseRequirement, baseAttribute, true));
            bonusRequirement = new HashMap<>();
            bonusRequirement.put(Strength, 12);
            bonusAttribute = new HashMap<>();
            bonusAttribute.put(Attribute.Attack, new Value(2));
            bonusAttribute.put(Attribute.Damage, new Value(2));
            rules.add(new Rule(bonusRequirement, bonusAttribute, false));
            club = new Weapon("Club", Weapon.Type.beating, Strength, rules);

            fighter[0].equip(club);
            fighter[1].equip(sword);
        }

    }

    private class turn {
        boolean first = false;

        int get1() {
            if (first)
                return 1;
            else
                return 0;
        }

        int get2() {
            if (first)
                return 0;
            else
                return 1;
        }

        void turn() {
            first = !first;
        }
    }
}
