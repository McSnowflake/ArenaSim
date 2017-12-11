package data;

import logic.Armor;
import logic.Equipment;
import numbers.Rule;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.List;

public class ArmorManager extends DataManager<Armor> {

    private final static String WEAPON_FILE_PATH = "src/main/resources/armor.json";

    public ArmorManager() {
        loadJSON(getFilePath());
    }

    public ArmorManager(String path2json) {
        loadJSON(path2json);
    }

    @Override protected String getType() {
        return type.fighter.name();
    }

    @Override protected String getFilePath() {
        return WEAPON_FILE_PATH;
    }

    @Override protected JsonObject encodeJSON(Armor armor) {
        JsonObjectBuilder equipmentJSON = Json.createObjectBuilder();
        equipmentJSON.add(jsonKeys.usageRules.name(), getJsonFromRules(armor.getRules()));
        equipmentJSON.add(jsonKeys.subType.name(), armor.getSubType().name());
        equipmentJSON.add(jsonKeys.type.name(), armor.getType().name());
        equipmentJSON.add(jsonKeys.name.name(), armor.getName());
        return equipmentJSON.build();
    }

    @Override protected Armor decodeJSON(JsonObject armorJSON) {
        String name = armorJSON.getString(jsonKeys.name.name());
        Armor.Type type = Armor.Type.valueOf(armorJSON.getString(jsonKeys.subType.name()));
        List<Rule> usageRules = DataManager.getRulesFromJSON(armorJSON.getJsonArray(jsonKeys.usageRules.name()));
        return new Armor(name, type, usageRules);
    }
}

