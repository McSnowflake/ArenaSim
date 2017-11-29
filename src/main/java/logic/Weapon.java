package logic;

import enums.Attribute;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@XmlRootElement
public class Weapon extends ArenaObject {

    private List<Attribute> requirements = new ArrayList<>();
    // TODO
    private List<Rule> usageRules = new ArrayList<>();

    public Weapon(String type, int attack, int defence, int damage) {
        this.type = type;
        setAttribute(Attribute.Attack.setValue(attack));
        setAttribute(Attribute.Defence.setValue(defence));
        setAttribute(Attribute.Damage.setValue(damage));

    }

    public Weapon(String type, List<Attribute> requirements, List<Attribute> attributes) {
        this.type = type;
        this.requirements.addAll(requirements);
        setAttributes(attributes);
    }

    public String toString() {
        return type;
    }

    public boolean isUsable(Fighter fighter) {
        return fighter.fulfills(getRequirements());
    }

    public List<Attribute> getBoni(Fighter fighter) {
        List<Attribute> boni = new ArrayList<>();
        boni.addAll(usageRules.stream()
                .filter(rule -> fighter.fulfills(rule.getRequirements()))
                .flatMap(Rule::getBonus)
                .collect(Collectors.toList()));
        return boni;
    }

    public Stream<Attribute> getRequirements() {
        return requirements.stream();
    }
}
