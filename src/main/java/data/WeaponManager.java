package data;

import enums.Attribute;
import logic.Weapon;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class WeaponManager extends DataManager<Weapon> {

    private final static String WEAPON_FILE_PATH = "src/main/resources/weapons.json";

    public WeaponManager() {
        loadJSON(getFilePath());
    }

    public WeaponManager(String path2json) {
        loadJSON(path2json);
    }

    @Override protected String getFilePath() {
        return WEAPON_FILE_PATH;
    }

    @Override
    protected JSONObject encodeJSON(Weapon weapon) {
        JSONObject json = new JSONObject();
        json.put("type", weapon.getType());
        json.put("attributes", getJsonFromAttributes(weapon.getAttributes()));
        json.put("requirements", getJsonFromAttributes(weapon.getRequirements()));
        return json;
    }

    @Override
    protected Weapon decodeJSON(JSONObject json) {
        String type = json.getString("type");
        Map<Attribute,Integer> attributes = this.getAttributesFromJSON(json.getJSONObject("attributes"));
        Map<Attribute,Integer> requirements = this.getAttributesFromJSON(json.getJSONObject("requirements"));
        return new Weapon(type, requirements, attributes);
    }
}

