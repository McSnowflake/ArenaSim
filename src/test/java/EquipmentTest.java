import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import sample.Atribute;
import sample.Fighter;
import sample.Weapon;

public class EquipmentTest {
    Fighter warrior = new Fighter("Warrior", 12, 8, 21);
    Fighter roque = new Fighter("Roque", 9, 12, 17);

    @BeforeTest
    public void create() {
    }

    @Test
    public void requirementsTest() {

        Fighter warrior = new Fighter("Warrior", 12, 8, 21);
        Fighter roque = new Fighter("Roque", 9, 12, 17);

        Weapon sword = new Weapon("Sword", Atribute.Agility);
        Weapon club = new Weapon("Club", Atribute.Strength);

        Assert.assertFalse(warrior.setWeapon(sword));
        Assert.assertTrue(warrior.setWeapon(club));
        Assert.assertTrue(roque.setWeapon(sword));
        Assert.assertFalse(roque.setWeapon(club));
    }
}
