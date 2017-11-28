import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import logic.Attribute;
import logic.DataManager;
import logic.Weapon;

import java.io.File;
import java.util.ArrayList;

public class WeaponTest {
    private static String pathToSample = "sample/weapon_test.json";

    @BeforeTest
    public void cleanUp() {
        File testFile = new File(pathToSample);
        if (testFile.exists())
            testFile.delete();

    }

    @Test
    public void generationTest() {

        Weapon sword = new Weapon("Sword", Attribute.Agility);
        Weapon club = new Weapon("Club", Attribute.Strength);

        DataManager<Weapon> weaponManager = DataManager.getWeaponManager(pathToSample);
        weaponManager.add(sword);
        weaponManager.add(club);
        weaponManager.save2File();
    }

    @Test
    public void loadingTest() {

        DataManager<Weapon> weaponManager = DataManager.getWeaponManager(pathToSample);
        ArrayList<Weapon> weapons = weaponManager.getList();

        for (Weapon weapon : weapons) {
            switch (weapon.getName()) {
            case "Sword":
                Assert.assertEquals(weapon.getBaseAtribute(), Attribute.Agility);
                break;
            case "Club":
                Assert.assertEquals(weapon.getBaseAtribute(), Attribute.Strength);
                break;
            default:
                throw new AssertionError("Weapon not expected");
            }
        }

    }
}
