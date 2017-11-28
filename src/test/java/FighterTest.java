import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import sample.Atribute;
import sample.DataManager;
import sample.Fighter;

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
        Fighter roque = new Fighter("Roque", 9, 12, 17);

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
            switch (fighter.getName()) {
            case "Warrior":
                Assert.assertEquals(fighter.getAtribute(Atribute.Agility), 8);
                Assert.assertEquals(fighter.getAtribute(Atribute.Strength), 12);
                Assert.assertEquals(fighter.getAtribute(Atribute.Health), 21);
                break;
            case "Roque":
                Assert.assertEquals(fighter.getAtribute(Atribute.Agility), 12);
                Assert.assertEquals(fighter.getAtribute(Atribute.Strength), 9);
                Assert.assertEquals(fighter.getAtribute(Atribute.Health), 17);
                break;
            default:
                throw new AssertionError("Weapon not expected");
            }
        }

    }
}
