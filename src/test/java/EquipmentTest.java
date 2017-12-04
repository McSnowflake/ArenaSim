import data.FighterManager;
import data.WeaponManager;
import enums.Dice;
import exceptions.AttributeNotPresentException;
import exceptions.ObjectNotFoundException;
import logic.Fighter;
import logic.Rule;
import logic.Weapon;
import enums.Attribute;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;

public class EquipmentTest extends AbstractTest {

    private FighterManager fighterManager = new FighterManager("src/test/resources/equipment_test_fighters.json");
    private WeaponManager weaponManager = new WeaponManager("src/test/resources/equipment_test_weapons.json");

    private Fighter warrior;
    private Fighter rogue;

    private Weapon sword;
    private Weapon club;

    @BeforeTest
    public void init() throws ObjectNotFoundException {
        warrior = fighterManager.get("Warrior");
        rogue = fighterManager.get("Rogue");

        sword = weaponManager.get("Sword");
        club = weaponManager.get("Club");


    }

    @Test
    public void requirementsTest() {

        Assert.assertFalse(warrior.equip(sword));
        Assert.assertTrue(warrior.equip(club));
        Assert.assertTrue(rogue.equip(sword));
        Assert.assertFalse(rogue.equip(club));
    }

    @Test
    public void weaponBonusTest() {

        warrior = new Fighter("Warrior", 12, 8, 21, 5);
        warrior.printAttributes();

        sword = new Weapon("Sword", 6, 4, 6);
        sword.printAttributes();

        HashMap<Attribute, Integer> ruleRequ = new HashMap<>();
        ruleRequ.put(Attribute.Agility, 9);
        ruleRequ.put(Attribute.Strength, 13);

        HashMap<Attribute, Integer> ruleBonus = new HashMap<>();
        ruleRequ.put(Attribute.Attack, 4);
        ruleRequ.put(Attribute.Defence, 6);
        ruleRequ.put(Attribute.Damage, 4);

        Dice bonusDice = Dice.HIGH;

        Rule rule = new Rule(ruleRequ, ruleBonus, bonusDice);

        sword.addRule(rule);

        Assert.assertTrue(warrior.equip(sword), "Succesfully equipped sword.");

        Assert.assertEquals((int)warrior.getAttribute(Attribute.Attack), 6, "Attack value has Bonus.");
        Assert.assertEquals((int)warrior.getAttribute(Attribute.Defence), 4, "Defence value has Bonus.");
        Assert.assertEquals((int)warrior.getAttribute(Attribute.Damage), 6, "Damage value has Bonus.");



    }
}
