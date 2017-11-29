import data.WeaponManager;
import enums.Attribute;
import exceptions.AttributeNotPresentException;
import logic.Weapon;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WeaponTest extends AbstractTest {

    private static String pathToSample = "src/test/resources/weapon_test.json";
    Weapon sword = new Weapon("Sword", 3, 4, 2);
    Weapon club = new Weapon("Club", 3, 2, 4);

    @BeforeTest
    public void cleanUp() {
        File testFile = new File(pathToSample);
        if (testFile.exists())
            testFile.delete();

    }

    @Test
    public void generationTest() throws IOException {

        WeaponManager weaponManager = new WeaponManager(pathToSample);
        weaponManager.add(sword);
        weaponManager.add(club);
        weaponManager.save2File(pathToSample);
    }

    @Test
    public void loadingTest() throws AttributeNotPresentException {

        WeaponManager weaponManager = new WeaponManager(pathToSample);
        ArrayList<Weapon> weapons = weaponManager.getList();

        List<Attribute> attributes2Test = new ArrayList<>();
        attributes2Test.add(Attribute.Attack);
        attributes2Test.add(Attribute.Defence);
        attributes2Test.add(Attribute.Damage);

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
