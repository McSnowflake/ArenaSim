import exceptions.AttributeNotPresentException;
import logic.Attribute;
import logic.DataManager;
import logic.Weapon;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WeaponTest {

    private static String pathToSample = "src/test/resources/weapon_test.json";
    private Weapon sword = new Weapon("Sword", Attribute.Agility, 2, 3, 4);
    private Weapon club = new Weapon("Club", Attribute.Strength, 4, 3, 2);

    @BeforeTest
    public void cleanUp() {
        File testFile = new File(pathToSample);
        if (testFile.exists())
            testFile.delete();

    }

    @Test
    public void generationTest() throws JAXBException, IOException {

        DataManager<Weapon> weaponManager = DataManager.getWeaponManager(pathToSample);

        Assert.assertEquals(weaponManager.getList().size(), 0);

        weaponManager.add(sword);
        weaponManager.add(club);
        weaponManager.save2File(pathToSample);
    }

    @Test
    public void loadingTest() throws AttributeNotPresentException, JAXBException {

        DataManager<Weapon> weaponManager = DataManager.getWeaponManager(pathToSample);
        ArrayList<Weapon> weapons = weaponManager.getList();

        Assert.assertEquals(weapons.size(), 2);

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
