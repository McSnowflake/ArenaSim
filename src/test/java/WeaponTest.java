import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import sample.Atribute;
import sample.Weapon;
import sample.WeaponManager;

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

        Weapon sword = new Weapon("Sword", Atribute.Agility);
        Weapon club = new Weapon("Club", Atribute.Strength);

        WeaponManager weaponManager = new WeaponManager(pathToSample);
        weaponManager.add(sword);
        weaponManager.add(club);
        weaponManager.save2File();
    }

    @Test
    public void loadingTest() {

        WeaponManager weaponManager = new WeaponManager(pathToSample);
        ArrayList<Weapon> weapons = weaponManager.getList();

        for (Weapon weapon : weapons) {
            switch (weapon.getName()) {
            case "Sword":
                Assert.assertEquals(weapon.getBaseAtribute(), Atribute.Agility);
                break;
            case "Club":
                Assert.assertEquals(weapon.getBaseAtribute(), Atribute.Strength);
                break;
            default:
                throw new AssertionError("Weapon not expected");
            }
        }

    }
}
