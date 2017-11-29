import logic.Attribute;
import logic.Fighter;
import logic.Weapon;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EquipmentTest extends AbstractTest {

    private Fighter warrior = new Fighter("Warrior", 12, 8, 21, 3);
    private Fighter rogue = new Fighter("Roque", 9, 12, 17, 2);

    private Weapon sword = new Weapon("Sword", Attribute.Agility, 4, 3, 2);
    private Weapon club = new Weapon("Club", Attribute.Strength, 2, 3, 4);

    @Test
    public void requirementsTest() {

        Assert.assertFalse(warrior.setWeapon(sword));
        Assert.assertTrue(warrior.setWeapon(club));
        Assert.assertTrue(rogue.setWeapon(sword));
        Assert.assertFalse(rogue.setWeapon(club));
    }
}
