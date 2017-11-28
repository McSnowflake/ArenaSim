package logic;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DataManager<T extends ArenaObject> {

    private final static String WEAPON_FILE_PATH = "sample/weapons.json";
    private final static String FIGHTER_FILE_PATH = "sample/fighter.json";

    private ArrayList<T> objectList = new ArrayList<>();

    public static DataManager<Fighter> getFighterManager(String... file2path) {
        if (file2path.length == 0)
            return new DataManager<>(FIGHTER_FILE_PATH);
        else
            return new DataManager<>(file2path[0]);

    }

    public static DataManager<Weapon> getWeaponManager(String... file2path) {
        if (file2path.length == 0)
            return new DataManager<Weapon>(WEAPON_FILE_PATH);
        else
            return new DataManager<>(file2path[0]);

    }

    private DataManager(String path2json) {
        loadJSON(path2json);
    }

    public ArrayList<T> getList() {
        return objectList;
    }

    private void loadJSON(String filePath) {
        JSONObject json;
        try {
            InputStream is = new FileInputStream(filePath);

            String jsonTxt = IOUtils.toString(is, "UTF-8");
            System.out.println(jsonTxt);
            json = new JSONObject(jsonTxt);
            JSONArray elements = json.getJSONArray("list");
             for (int i = 0; i < elements.length(); i++) {
               elements.getJSONObject(i);
                 T t = null; // you can't use new A()!
                 t.load()
            }
        } catch (IOException e) {
            System.out.println("Creating new weapon json.");
            json = new JSONObject();
        }

        // docode to list
        // TODO objectList = ...;
    }

    public void add(T object) {
        objectList.add(object);
    }

    public void save2File(String path2json) throws IOException {

        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        for (T object : objectList) {
            array.put(object.toJSON());
        }
        json.put("list", array);

        try (FileWriter file = new FileWriter(path2json)) {
            file.write(json.toString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + json);
        }
    }
}
