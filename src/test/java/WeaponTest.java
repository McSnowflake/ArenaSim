import exceptions.AttributeNotPresentException;
import logic.Attribute;
import logic.DataManager;
import logic.Weapon;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WeaponTest {

    private static String pathToSample = "sample/weapon_test.json";
    Weapon sword = new Weapon("Sword", Attribute.Agility, 2, 3, 4);
    Weapon club = new Weapon("Club", Attribute.Strength, 4, 3, 2);

    @BeforeTest
    public void cleanUp() {
        File testFile = new File(pathToSample);
        if (testFile.exists())
            testFile.delete();

    }

    @Test
    public void generationTest() {

        DataManager<Weapon> weaponManager = DataManager.getWeaponManager(pathToSample);
        weaponManager.add(sword);
        weaponManager.add(club);
        weaponManager.save2File();
    }

    @Test
    public void loadingTest() throws AttributeNotPresentException {

        DataManager<Weapon> weaponManager = DataManager.getWeaponManager(pathToSample);
        ArrayList<Weapon> weapons = weaponManager.getList();

        List<Attribute> attributes2Test = new ArrayList<>();
        attributes2Test.add(Attribute.AttackBonus);
        attributes2Test.add(Attribute.DefenceBonus);
        attributes2Test.add(Attribute.DamageValue);

        for (Weapon weapon : weapons) {
            Weapon wut;
            switch (weapon.getType()) {
            case "Sword":
                wut = sword;
                break;
            case "Club":
                wut = club;
                break;
            default:
                throw new AssertionError("Weapon not expected");
            }
            for (Attribute attribute : attributes2Test) {
                Assert.assertEquals(weapon.getAttribute(attribute), wut.getAttribute(attribute));
            }
        }

    }
}
