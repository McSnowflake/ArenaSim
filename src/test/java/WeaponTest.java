import org.testng.Assert;
import org.testng.annotations.Test;
import sample.Atribute;
import sample.Weapon;
import sample.WeaponManager;

import java.util.ArrayList;

public class WeaponTest {

    @Test
    public void weaponGenerationTest() {

        String pathToSample = "sample/weapon_test.json";
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
