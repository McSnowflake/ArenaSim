package numbers;

import data.DataManager;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.Map;
import java.util.stream.Stream;

public class Rule {
    private final Map<Attribute, Integer> requirements;
    private final Map<Attribute, Value> bonus;
    private final boolean mandatory;

    public Rule(Map<Attribute, Integer> requirements, Map<Attribute, Value> bonus, boolean mandatory) {

        this.requirements = requirements;
        this.bonus = bonus;
        this.mandatory = mandatory;
    }

    public Rule(JsonObject ruleJSON) {

        this.bonus = DataManager.getValuesFromJSON(ruleJSON.getJsonObject(jsonKeys.bonus.name()));
        this.requirements = DataManager.getAttributesFromJSON(ruleJSON.getJsonObject(jsonKeys.requirements.name()));
        this.mandatory = ruleJSON.getBoolean(jsonKeys.isMandatory.name());
    }

    public JsonObject toJSON() {

        JsonObjectBuilder ruleJSON = Json.createObjectBuilder();
        ruleJSON.add(jsonKeys.bonus.name(), DataManager.getJsonFromValues(getBonus()));
        ruleJSON.add(jsonKeys.requirements.name(), DataManager.getJsonFromAttributes(getRequirements()));
        ruleJSON.add(jsonKeys.isMandatory.name(), mandatory);
        return ruleJSON.build();
    }

    public Stream<Map.Entry<Attribute, Integer>> getRequirements() {
        return requirements.entrySet().stream();
    }

    public Stream<Map.Entry<Attribute, Value>> getBonus() {
        return bonus.entrySet().stream();
    }

    public boolean isMandatory() {
        return mandatory;
    }

    private enum jsonKeys {
        bonus, requirements, isMandatory
    }
}
