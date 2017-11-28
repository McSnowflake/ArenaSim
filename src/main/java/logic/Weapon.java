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

    enum Keys {
        Name,
        Base
    }

    public String toString(){
        return name+":"+baseAttribute.name();
    }
}
