package data;

import logic.Fighter;
import numbers.Attribute;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.Map;

public class FighterManager extends DataManager<Fighter> {

    private final static String FIGHTER_FILE_PATH = "src/main/resources/fighters.json";

    public FighterManager() {
        loadJSON(getFilePath());
    }

    public FighterManager(String path2json) {
        loadJSON(path2json);
    }

    @Override protected String getType() {
        return type.fighter.name();
    }

    @Override protected String getFilePath() {
        return FIGHTER_FILE_PATH;
    }

    @Override protected JsonObject encodeJSON(Fighter fighter) {

        JsonObjectBuilder equipmentJSON = Json.createObjectBuilder();
        equipmentJSON.add(jsonKeys.attributes.name(), getJsonFromAttributes(fighter.getBaseAttributes()));
        equipmentJSON.add(jsonKeys.type.name(), fighter.getType().name());
        equipmentJSON.add(jsonKeys._class.name(), fighter.getCClass().name());
        return equipmentJSON.build();
    }

    @Override
    protected Fighter decodeJSON(JsonObject fighterJSON) {

        Fighter.Type type = Fighter.Type.valueOf(fighterJSON.getString(jsonKeys.type.name()));
        Fighter.Class _class = Fighter.Class.valueOf(fighterJSON.getString(jsonKeys._class.name()));
        Map<Attribute, Integer> attributes = DataManager.getAttributesFromJSON(fighterJSON.getJsonObject(jsonKeys.attributes.name()));
        return new Fighter(type, _class, attributes);
    }
}
