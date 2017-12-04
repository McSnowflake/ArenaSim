package logic;

import enums.Attribute;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@XmlRootElement
public class Weapon extends ArenaObject {

    private Map<Attribute, Integer> requirements = new HashMap<>();

    private List<Rule> usageRules = new ArrayList<>();

    public Weapon(String type, int attack, int defence, int damage) {
        this.type = type;
        setAttribute(Attribute.Attack, attack);
        setAttribute(Attribute.Defence, defence);
        setAttribute(Attribute.Damage, damage);

    }

    public Weapon(String type, Map<Attribute, Integer> requirements, Map<Attribute, Integer> attributes) {
        this.type = type;
        this.requirements.putAll(requirements);
        setAttributes(attributes);
    }

    public String toString() {
        return type;
    }

    public boolean isUsable(Fighter fighter) {

        if (getRequirements().count() == 0){
            return true;
        }

        return fighter.fulfills(getRequirements());
    }

    public Map<Attribute, Integer> getBoni(Fighter fighter) {
        Map<Attribute, Integer> boni = new HashMap<>();
        boni.putAll(usageRules.stream()
                .filter(rule -> fighter.fulfills(rule.getRequirements()))
                .flatMap(Rule::getBonus)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        return boni;
    }

    public Stream<Map.Entry<Attribute, Integer>> getRequirements() {
        return requirements.entrySet().stream();
    }

    public void addRule(Rule rule){
        this.usageRules.add(rule);
    }
}
