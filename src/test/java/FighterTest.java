import logic.Attribute;
import logic.DataManager;
import logic.Fighter;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;

public class FighterTest {
    private static String pathToSample = "sample/fighter_test.json";

    @BeforeTest
    public void cleanUp() {
        File testFile = new File(pathToSample);
        if (testFile.exists())
            testFile.delete();

    }

    @Test
    public void generationTest() {

        Fighter warrior = new Fighter("Warrior", 12, 8, 21);
        Fighter roque = new Fighter("Rogue", 9, 12, 17);

        DataManager<Fighter> fighterManager = DataManager.getFighterManager(pathToSample);
        fighterManager.add(warrior);
        fighterManager.add(roque);
        fighterManager.save2File();
    }

    @Test
    public void loadingTest() {

        DataManager<Fighter> fighterManager = DataManager.getFighterManager(pathToSample);
        ArrayList<Fighter> fighters = fighterManager.getList();

        for (Fighter fighter : fighters) {
            switch (fighter.getType()) {
            case "Warrior":
                Assert.assertEquals(fighter.getAttribute(Attribute.Agility), 8);
                Assert.assertEquals(fighter.getAttribute(Attribute.Strength), 12);
                Assert.assertEquals(fighter.getAttribute(Attribute.Health), 21);
                break;
            case "Rogue":
                Assert.assertEquals(fighter.getAttribute(Attribute.Agility), 12);
                Assert.assertEquals(fighter.getAttribute(Attribute.Strength), 9);
                Assert.assertEquals(fighter.getAttribute(Attribute.Health), 17);
                break;
            default:
                throw new AssertionError("Weapon not expected");
            }
        }

    }
}
