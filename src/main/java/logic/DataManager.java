package logic;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DataManager<T> {

    private final static String WEAPON_FILE_PATH = "sample/weapons.json";
    private final static String FIGHTER_FILE_PATH = "sample/fighter.json";

    private ArrayList<T> objectList;

    public static DataManager<Fighter> getFighterManager(String... file2path) {
        if (file2path.length == 0)
            return new DataManager<>(FIGHTER_FILE_PATH);
        else
            return new DataManager<>(file2path[0]);

    }

    public static DataManager<Weapon> getWeaponManager(String... file2path) {
        if (file2path.length == 0)
            return new DataManager<>(WEAPON_FILE_PATH);
        else
            return new DataManager<>(file2path[0]);

    }

    private DataManager(String path2json) {
        openJSON(path2json);
    }

    public ArrayList<T> getList() {
        return objectList;
    }

    // TODO NICO MARSHALL
    private JSONObject openJSON(String filePath) {
        JSONObject json;
        try {
            InputStream is = new FileInputStream(filePath);

            String jsonTxt = IOUtils.toString(is, "UTF-8");
            System.out.println(jsonTxt);
            json = new JSONObject(jsonTxt);
        } catch (IOException e) {
            System.out.println("Creating new weapon json.");
            json = new JSONObject();
        }
        JSONArray elements = json.getJSONArray("list");
        for (int i = 0; i < elements.length(); i++) {
            objectList.add((T) elements.get(i));
        }
        // docode to list
        return json;
    }

    public void add(T object) {
        objectList.add(object);
    }

    public void save2File() {
        // TODO NICO
    }
}
