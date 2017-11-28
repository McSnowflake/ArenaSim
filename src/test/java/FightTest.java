import exceptions.NoWeaponException;
import logic.Attribute;
import logic.Fighter;
import logic.Weapon;
import org.testng.annotations.Test;

public class FightTest {

    private Fighter warrior = new Fighter("Warrior", 12, 8, 21);
    private Fighter rogue = new Fighter("Roque", 9, 12, 17);

    private Weapon sword = new Weapon("Sword", Attribute.Agility);
    private Weapon club = new Weapon("Club", Attribute.Strength);

    @Test
    public void deathMatch() throws NoWeaponException, InterruptedException {

        warrior.setWeapon(club);
        rogue.setWeapon(sword);

        while (warrior.getAttribute(Attribute.Health) > 0 && rogue.getAttribute(Attribute.Health) > 0) {
            int pause = 250;
            rogue.attack(warrior);
            Thread.sleep(pause);

            warrior.attack(rogue);
            Thread.sleep(pause);
        }
    }

}
