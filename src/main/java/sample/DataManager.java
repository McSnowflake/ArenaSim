package sample;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

abstract class DataManager<T> {

    private static JSONObject json;


    private DataManager() {
    }

    public static List<T> getList() {
       JSONArray opjects = json.getJSONArray("array");

    }

    protected static JSONObject openJSON(String filePath) {
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
        DataManager.json = json;
    }

}
