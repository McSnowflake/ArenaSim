import exceptions.EquipmentNotSuitableException;
import exceptions.EquipmentTypeNotKnownException;
import logic.Fighter;
import logic.Weapon;
import numbers.Attribute;
import numbers.Die;
import numbers.Rule;
import numbers.Value;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static numbers.Attribute.Agility;
import static numbers.Attribute.Strength;

public class EquipmentTest extends AbstractTest {

    private Fighter warrior;
    private Fighter rogue;

    private Weapon sword;
    private Weapon club;

    @BeforeTest
    private void init() throws EquipmentTypeNotKnownException, EquipmentNotSuitableException {

        // create warrior
        Map<Attribute, Integer> attributes = new HashMap<>();
        attributes.put(Strength, 12);
        attributes.put(Agility, 9);
        attributes.put(Attribute.Defence, 1);
        warrior = new Fighter(Fighter.Type.Warrior, Fighter.Class.Human, attributes);

        // create rogue
        attributes = new HashMap<>();
        attributes.put(Strength, 9);
        attributes.put(Agility, 12);
        attributes.put(Attribute.Defence, 1);
        rogue = new Fighter(Fighter.Type.Rogue, Fighter.Class.Human, attributes);

        // create sword
        List<Rule> rules = new ArrayList<>();
        Map<Attribute, Integer> baseRequirement = new HashMap<>();
        baseRequirement.put(Agility, 9);
        baseRequirement.put(Strength, 9);
        Map<Attribute, Value> baseAttribute = new HashMap<>();
        baseAttribute.put(Attribute.Attack, new Value(Die.LOW));
        rules.add(new Rule(baseRequirement, baseAttribute, true));
        Map<Attribute, Integer> bonusRequirement = new HashMap<>();
        bonusRequirement.put(Agility, 12);
        Map<Attribute, Value> bonusAttribute = new HashMap<>();
        bonusAttribute.put(Attribute.Attack, new Value(2));
        rules.add(new Rule(bonusRequirement, bonusAttribute, false));
        sword = new Weapon("Sword", Weapon.Type.cutting, Agility, rules);

        // create club
        rules = new ArrayList<>();
        baseRequirement = new HashMap<>();
        baseRequirement.put(Strength, 10);
        baseRequirement.put(Agility, 8);
        baseAttribute = new HashMap<>();
        baseAttribute.put(Attribute.Attack, new Value(Die.LOW));
        rules.add(new Rule(baseRequirement, baseAttribute, true));
        bonusRequirement = new HashMap<>();
        bonusRequirement.put(Strength, 13);
        bonusAttribute = new HashMap<>();
        bonusAttribute.put(Attribute.Attack, new Value(2));
        rules.add(new Rule(bonusRequirement, bonusAttribute, false));
        club = new Weapon("Club", Weapon.Type.beating, Strength, rules);

        warrior.equip(club);
        rogue.equip(sword);
    }

    @Test
    public void requirementsTest() throws EquipmentTypeNotKnownException {

        Assert.assertTrue(warrior.equip(sword));
        Assert.assertTrue(warrior.equip(club));
        Assert.assertTrue(rogue.equip(sword));
        Assert.assertFalse(rogue.equip(club));
    }

    @Test
    public void weaponBonusTest() throws EquipmentTypeNotKnownException {

        warrior.equip(club);
        warrior.printAttributes();

        Assert.assertEquals(warrior.getAttribute(Attribute.Attack), new Value(Die.LOW), "Attack != Base.");

        warrior.alterBaseAttribute(Strength, 1);
        warrior.calcStats();
        warrior.printAttributes();

        Assert.assertEquals(warrior.getAttribute(Attribute.Attack), new Value(Die.LOW, 2), "Attack != Bonus.");

    }
}
