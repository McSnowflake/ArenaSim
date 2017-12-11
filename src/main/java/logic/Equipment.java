package logic;

import data.DataManager;
import numbers.Attribute;
import numbers.Rule;
import numbers.Value;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Stream;

public abstract class Equipment {

    protected static Logger LOG = Logger.getGlobal();

    private final List<Rule> usageRules;
    private final String name;
    private final Type type;
    private final SubType subType;

    protected Equipment(String name, Type type, SubType subType, List<Rule> usageRules) {
        this.name = name;
        this.type = type;
        this.subType = subType;
        this.usageRules = usageRules;
    }

    public Type getType() {
        return type;
    }

    public SubType getSubType() {
        return subType;
    }

    public String getName() {
        return name;
    }

    public Stream<Map.Entry<Attribute, Value>> getBonus(Fighter fighter) {
        return usageRules.stream().filter(rule -> fighter.fulfills(rule.getRequirements())).flatMap(Rule::getBonus);
    }

    public Stream<Rule> getRules() {
        return usageRules.stream();
    }

    public enum Type {
        Armor,
        Weapon
    }

    public interface SubType {
        public String name();
    }
}
