import exceptions.AttributeNotPresentException;
import enums.Attribute;
import logic.Fighter;
import data.FighterManager;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FighterTest extends AbstractTest  {

    private static String pathToSample = "src/test/resources/fighter_test.json";
    private Fighter warrior = new Fighter("Warrior", 12, 8, 21, 3);
    private Fighter rogue = new Fighter("Rogue", 9, 12, 17, 2);

    @BeforeTest
    public void cleanUp() {
        File testFile = new File(pathToSample);
        if (testFile.exists())
            testFile.delete();

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
            case "Warrior":
                fut = warrior;
                break;
            case "Rogue":
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
}
