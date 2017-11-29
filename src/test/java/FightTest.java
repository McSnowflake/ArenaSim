import exceptions.AttributeNotPresentException;
import exceptions.NoWeaponException;
import logic.Attribute;
import logic.Fighter;
import logic.Weapon;
import org.testng.annotations.Test;

import java.util.logging.Logger;

public class FightTest extends AbstractTest {

    private Fighter warrior = new Fighter("Warrior", 10, 8, 21, 3);
    private Fighter rogue = new Fighter("Roque", 9, 12, 17, 2);

    private Weapon sword = new Weapon("Sword", Attribute.Agility, 6, 4, 4);
    private Weapon club = new Weapon("Club", Attribute.Strength, 4, 2, 5);

    @Test
    public void deathMatch() throws NoWeaponException, InterruptedException, AttributeNotPresentException {

        warrior.setWeapon(club);
        rogue.setWeapon(sword);

        int rogue_count = 0;
        int warrior_count = 0;

        for (int i = 0; i < 1000; i++) {

            LOG.info("fight nr " + i + " started");

            while (warrior.getAttribute(Attribute.Health) > 0 && rogue.getAttribute(Attribute.Health) > 0) {
                rogue.attack(warrior);
                warrior.attack(rogue);
            }
            if (warrior.getAttribute(Attribute.Health) <= 0) {
                rogue_count++;
                LOG.info("warrior died");
            }
            if (rogue.getAttribute(Attribute.Health) <= 0) {
                warrior_count++;
                LOG.info("rogue died");
            }
        }
        LOG.info("Rogue wins: " + rogue_count);
        LOG.info("Warrior wins: " + warrior_count);
    }
}
