import data.WeaponManager;
import exceptions.AttributeNotPresentException;
import logic.Weapon;
import numbers.Attribute;
import numbers.Die;
import numbers.Rule;
import numbers.Value;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static numbers.Attribute.Agility;
import static numbers.Attribute.Strength;

public class WeaponTest extends AbstractTest {

    private static String pathToSample = "src/test/resources/weapon_test.json";
    private Weapon sword;
    private Weapon club;

    @BeforeTest
    public void init() {
        File testFile = new File(pathToSample);
        if (testFile.exists())
            testFile.delete();

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
    }

    @Test
    public void generationTest() throws IOException {

        WeaponManager equipmentManager = new WeaponManager(pathToSample);
        equipmentManager.add(sword);
        equipmentManager.add(club);
        equipmentManager.save2File(pathToSample);
    }

    @Test
    public void loadingTest() throws AttributeNotPresentException {

        WeaponManager equipmentManager = new WeaponManager(pathToSample);
        List<Weapon> weapons = equipmentManager.getList();

        List<Attribute> attributes2Test = new ArrayList<>();
        attributes2Test.add(Attribute.Attack);
        attributes2Test.add(Attribute.Defence);
        attributes2Test.add(Attribute.Damage);

        for (Weapon weapon : weapons) {
            Weapon wut;
            switch (weapon.getName()) {
            case "Sword":
                wut = sword;
                break;
            case "Club":
                wut = club;
                break;
            default:
                throw new AssertionError("Weapon not expected");
            }

            //  attributes2Test.forEach(attribute -> Assert.assertEquals(weapon.getAttribute(attribute), wut.getAttribute(attribute)));
        }
    }
}

