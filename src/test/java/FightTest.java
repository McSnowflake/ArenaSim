import exceptions.AttributeNotPresentException;
import exceptions.NoWeaponException;
import logic.Attribute;
import logic.Fighter;
import logic.Weapon;
import org.testng.annotations.Test;

public class FightTest extends AbstractTest {

    private Fighter warrior;
    private Fighter rogue;

    private Weapon sword;
    private Weapon club;

    private void init() {

        warrior = new Fighter("Warrior", 12, 8, 21, 5);
        rogue = new Fighter("Roque", 9, 13, 17, 3);

        sword = new Weapon("Sword", Attribute.Agility, 6, 4, 6);
        club = new Weapon("Club", Attribute.Strength, 4, 2, 8);

        warrior.setWeapon(club);
        rogue.setWeapon(sword);
    }

    @Test
    public void deathMatch() throws NoWeaponException, InterruptedException, AttributeNotPresentException {

        init();

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
            init();
        }
        LOG.info("Rogue wins: " + rogue_count);
        LOG.info("Warrior wins: " + warrior_count);
    }
}
