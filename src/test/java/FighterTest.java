import data.FighterManager;
import exceptions.AttributeNotPresentException;
import exceptions.EquipmentNotSuitableException;
import exceptions.EquipmentTypeNotKnownException;
import logic.Fighter;
import numbers.Attribute;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FighterTest extends AbstractTest {

    private static String pathToSample = "src/test/resources/fighter_test.json";
    private Fighter warrior;
    private Fighter rogue;

    @BeforeTest
    public void cleanUp() throws EquipmentTypeNotKnownException, EquipmentNotSuitableException {
        File testFile = new File(pathToSample);
        if (testFile.exists())
            testFile.delete();
        init();
    }

    @Test
    public void generationTest() throws IOException {

        FighterManager fighterManager = new FighterManager(pathToSample);

        Assert.assertEquals(fighterManager.getList().size(), 0);

        fighterManager.add(warrior);
        fighterManager.add(rogue);
        fighterManager.save2File(pathToSample);
    }

    @Test
    public void loadingTest() throws AttributeNotPresentException {

        FighterManager fighterManager = new FighterManager(pathToSample);
        List<Fighter> fighters = fighterManager.getList();

        Assert.assertEquals(fighters.size(), 2);

        List<Attribute> attributes2Test = new ArrayList<>();
        attributes2Test.add(Attribute.Agility);
        attributes2Test.add(Attribute.Strength);
        attributes2Test.add(Attribute.Health);
        attributes2Test.add(Attribute.Armor);

        for (Fighter fighter : fighters) {

            Fighter fut;
            switch (fighter.getType()) {
            case Warrior:
                fut = warrior;
                break;
            case Rogue:
                fut = rogue;
                break;
            default:
                throw new AssertionError("Weapon not expected");
            }
            for (Attribute attribute : attributes2Test) {
                Assert.assertEquals(fighter.getAttribute(attribute), fut.getAttribute(attribute));
            }

        }
    }

    private void init() throws EquipmentTypeNotKnownException, EquipmentNotSuitableException {

        // create warrior
        Map<Attribute, Integer> attributes = new HashMap<>();
        attributes.put(Attribute.Strength, 12);
        attributes.put(Attribute.Agility, 9);
        attributes.put(Attribute.Defence, 1);
        warrior = new Fighter(Fighter.Type.Warrior, Fighter.Class.Human, attributes);

        // create rogue
        attributes = new HashMap<>();
        attributes.put(Attribute.Strength, 9);
        attributes.put(Attribute.Agility, 12);
        attributes.put(Attribute.Defence, 1);
        rogue = new Fighter(Fighter.Type.Rogue, Fighter.Class.Human, attributes);
    }
}
