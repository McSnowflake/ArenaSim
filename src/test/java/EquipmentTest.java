import data.FighterManager;
import data.WeaponManager;
import exceptions.ObjectNotFoundException;
import logic.Fighter;
import logic.Weapon;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class EquipmentTest extends AbstractTest {

    private FighterManager fighterManager = new FighterManager("src/test/resources/equipment_test_fighters.json");
    private WeaponManager weaponManager = new WeaponManager("src/test/resources/equipment_test_weapons.json");

    private Fighter warrior;
    private Fighter rogue;

    private Weapon sword;
    private Weapon club;

    @BeforeTest
    public void init() throws ObjectNotFoundException {
        warrior = fighterManager.get("Warrior");
        rogue = fighterManager.get("Rogue");

        sword = weaponManager.get("Sword");
        club = weaponManager.get("Club");
    }

    @Test
    public void requirementsTest() {

        Assert.assertFalse(warrior.equip(sword));
        Assert.assertTrue(warrior.equip(club));
        Assert.assertTrue(rogue.equip(sword));
        Assert.assertFalse(rogue.equip(club));
    }
}
