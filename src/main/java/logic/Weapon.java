package logic;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "weapon")
public class Weapon extends ArenaObject {

    @XmlAttribute
    private final Attribute baseAttribute;

    public Weapon() {
        this.type = "";
        attributes = new HashMap<>();
        baseAttribute = Attribute.Strength;
    }

    public Weapon(String type, Attribute baseAttribute, int attack, int defence, int damage) {
        this.type = type;
        attributes = new HashMap<>();
        this.baseAttribute = baseAttribute;
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
}
