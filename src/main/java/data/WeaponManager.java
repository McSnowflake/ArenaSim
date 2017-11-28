package data;

import logic.Attribute;
import logic.Weapon;
import org.json.JSONObject;

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
        json.put("baseAttribute", weapon.getBaseAttribute());
        json.put("attributes", getJsonFromAttributes(weapon.getAttributes()));
        return json;
    }

    @Override
    protected Weapon decodeJSON(JSONObject json) {
        String type = json.getString("type");
        Attribute baseAttribute = Attribute.valueOf(json.getString("baseAttribute"));
        Map<Attribute, Integer> attributes = this.getAttributesFromJSON(json.getJSONObject("attributes"));
        return new Weapon(type, baseAttribute, attributes);
    }
}

