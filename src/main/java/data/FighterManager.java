package data;

import enums.Attribute;
import logic.Fighter;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class FighterManager extends DataManager<Fighter> {

    private final static String FIGHTER_FILE_PATH = "src/main/resources/fighters.json";

    public FighterManager() {
        loadJSON(getFilePath());
    }

    public FighterManager(String path2json) {
        loadJSON(path2json);
    }

    @Override protected String getFilePath() {
        return FIGHTER_FILE_PATH;
    }

    @Override protected JSONObject encodeJSON(Fighter fighter) {
        JSONObject json = new JSONObject();
        json.put("type", fighter.getType());
        json.put("attributes", getJsonFromAttributes(fighter.getAttributes()));
        return json;    }

    @Override protected Fighter decodeJSON(JSONObject json) {
        String type = json.getString("type");
        Map<Attribute, Integer> attributes = this.getAttributesFromJSON(json.getJSONObject("attributes"));
        return new Fighter(type, attributes);

    }
}
