import logic.Attribute;
import logic.Fighter;
import logic.Weapon;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EquipmentTest {

    @Test
    public void requirementsTest() {

        Fighter warrior = new Fighter("Warrior", 12, 8, 21);
        Fighter roque = new Fighter("Roque", 9, 12, 17);

        Weapon sword = new Weapon("Sword", Attribute.Agility);
        Weapon club = new Weapon("Club", Attribute.Strength);

        Assert.assertFalse(warrior.setWeapon(sword));
        Assert.assertTrue(warrior.setWeapon(club));
        Assert.assertTrue(roque.setWeapon(sword));
        Assert.assertFalse(roque.setWeapon(club));
    }
}
