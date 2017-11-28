package logic;

import org.json.JSONObject;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;

@XmlRootElement
public class Weapon extends ArenaObject {

    public Weapon() {
        this.type = "";
        attributes = new HashMap<>();
    }

    public Weapon(String type, Attribute baseAttribute, int attack, int defence, int damage) {
        this.type = type;
        attributes = new HashMap<>();
        attributes.put(Attribute.BaseAttribute, baseAttribute);
        attributes.put(Attribute.AttackBonus, attack);
        attributes.put(Attribute.DefenceBonus, defence);
        attributes.put(Attribute.DamageValue, damage);
    }

/*    public Weapon(JSONObject json) throws JSONException, IllegalArgumentException {
        this.name = json.getString(Keys.Name.name());
        this.baseAttribute = Attribute.valueOf(json.getString(Keys.Name.name()));
    }*/

    public Attribute getBaseAttribute() {
        return baseAttribute;
    }

/*    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        json.append(Keys.Name.name(), name);
        json.append(Keys.Base.name(), baseAttribute.name());
        return json;
    }*/

    public String toString() {
        return type + ":" + baseAttribute.name();
    }

    @Override JSONObject toJSON() {
        JSONObject jsonObject = super.toJSON();
        jsonObject.put("baseAttribute", baseAttribute.name());
        return jsonObject;
    }
}
