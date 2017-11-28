package sample;

import org.json.JSONException;
import org.json.JSONObject;

public class Weapon {

    private final String name;
    private final Atribute baseAtribute;

    public Weapon(String name, Atribute baseAtribute) {
        this.name = name;
        this.baseAtribute = baseAtribute;
    }

    public Weapon(JSONObject json) throws JSONException, IllegalArgumentException {
        this.name = json.getString(Keys.Name.name());
        this.baseAtribute = Atribute.valueOf(json.getString(Keys.Name.name()));
    }

    public String getName() {
        return name;
    }

    public Atribute getBaseAtribute() {
        return baseAtribute;
    }

    public JSONObject getJSONString() {
        JSONObject json = new JSONObject();
        json.append(Keys.Name.name(), name);
        json.append(Keys.Base.name(), baseAtribute.name());
        return json;
    }

    enum Keys {
        Name,
        Base
    }
}
