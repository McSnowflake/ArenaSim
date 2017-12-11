package data;

import logic.Weapon;
import numbers.Attribute;
import numbers.Rule;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.List;

public class WeaponManager extends DataManager<Weapon> {

    private final static String WEAPON_FILE_PATH = "src/main/resources/weapons.json";

    public WeaponManager() {
        loadJSON(getFilePath());
    }

    public WeaponManager(String path2json) {
        loadJSON(path2json);
    }

    @Override
    protected String getType() {
        return type.weapon.name();
    }

    @Override protected String getFilePath() {
        return WEAPON_FILE_PATH;
    }

    @Override
    protected JsonObject encodeJSON(Weapon object) {

        JsonObjectBuilder equipmentJSON = Json.createObjectBuilder();
        equipmentJSON.add(jsonKeys.usageRules.name(), DataManager.getJsonFromRules(object.getRules()));
        equipmentJSON.add(jsonKeys.baseAttribute.name(), object.getBaseAttribute().name());
        equipmentJSON.add(jsonKeys.subType.name(), object.getSubType().name());
        equipmentJSON.add(jsonKeys.type.name(), object.getType().name());
        equipmentJSON.add(jsonKeys.name.name(), object.getName());
        return equipmentJSON.build();
    }

    @Override
    protected Weapon decodeJSON(JsonObject json) {

        Attribute baseAttribute = Attribute.valueOf(json.getString(jsonKeys.baseAttribute.name()));
        String name = json.getString(jsonKeys.name.name());
        Weapon.Type type = Weapon.Type.valueOf(json.getString(jsonKeys.subType.name()));
        List<Rule> usageRules = DataManager.getRulesFromJSON(json.getJsonArray(jsonKeys.usageRules.name()));
        return new Weapon(name, type, baseAttribute, usageRules);
    }
}

