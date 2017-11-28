package logic;

import org.json.JSONException;
import org.json.JSONObject;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Weapon {

    @XmlAttribute
    private final String name;

    @XmlAttribute
    private final Attribute baseAttribute;
    private int attackValue = 2;
    private int defenceBonus = 2;
    private int damageValue = 3;

    public Weapon(){
        name = "";
        baseAttribute = null;
    }

    public Weapon(String name, Attribute baseAtribute) {
        this.name = name;
        this.baseAttribute = baseAtribute;
    }

    public Weapon(JSONObject json) throws JSONException, IllegalArgumentException {
        this.name = json.getString(Keys.Name.name());
        this.baseAttribute = Attribute.valueOf(json.getString(Keys.Name.name()));
    }

    public String getName() {
        return name;
    }

    public Attribute getBaseAtribute() {
        return baseAttribute;
    }

    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        json.append(Keys.Name.name(), name);
        json.append(Keys.Base.name(), baseAttribute.name());
        return json;
    }

    public int getAttackValue() {
        return attackValue;
    }

    public int getDefenceBonus() {
        return defenceBonus;
    }

    public int getDamageValue() {
        return damageValue;
    }

    enum Keys {
        Name,
        Base
    }

    public String toString(){
        return name+":"+baseAttribute.name();
    }
}
