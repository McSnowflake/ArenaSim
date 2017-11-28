package sample;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

abstract class DataManager<T> {

    protected ArrayList<T> objectList;

    protected DataManager(String path2json) {
        openJSON(path2json);
    }

    public ArrayList<T> getList() {
        return objectList;
    }

    // TODO NICO MARSHALL
    protected JSONObject openJSON(String filePath) {
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
