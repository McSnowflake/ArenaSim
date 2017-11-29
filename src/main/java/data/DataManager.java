package data;

import enums.Attribute;
import logic.ArenaObject;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.logging.*;
import java.util.logging.Formatter;
import java.util.stream.Stream;

abstract class DataManager<T extends ArenaObject> {

    protected static Logger LOG = Logger.getAnonymousLogger();

    static {
        LOG.setUseParentHandlers(false);
        Handler conHdlr = new ConsoleHandler();
        conHdlr.setFormatter(new Formatter() {
            public String format(LogRecord record) {
                return record.getMessage() + "\n";
            }
        });
        LOG.addHandler(conHdlr);

    }

    private ArrayList<T> objectList = new ArrayList<>();

    public ArrayList<T> getList() {
        return objectList;
    }

    protected void loadJSON(String filePath) {
        JSONObject json;
        try {
            InputStream is = new FileInputStream(filePath);

            String jsonTxt = IOUtils.toString(is, "UTF-8");
            System.out.println(jsonTxt);
            json = new JSONObject(jsonTxt);
            JSONArray elements = json.getJSONArray("list");
            for (int i = 0; i < elements.length(); i++) {
                T element = decodeJSON(elements.getJSONObject(i));
                objectList.add(element);
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
            array.put(encodeJSON(object));
        }
        json.put("list", array);

        try (FileWriter file = new FileWriter(path2json)) {
            file.write(json.toString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + json);
        }
    }

    protected abstract String getFilePath();

    protected abstract JSONObject encodeJSON(T object);

    protected abstract T decodeJSON(JSONObject json);

    JSONObject getJsonFromAttributes(Stream<Attribute> attributes) {
        JSONObject jsonObject = new JSONObject();
        attributes.forEach(attribute -> jsonObject.put(attribute.name(), attribute.getValue()));
        return jsonObject;
    }

    List<Attribute> getAttributesFromJSON(JSONObject json) {
        List<Attribute> attributes = new ArrayList<>();
        Iterator<String> iterator = json.keys();
        while (iterator.hasNext()) {
            String key = iterator.next();
            try {
                Attribute attribute = Attribute.valueOf(key);
                attribute.setValue(json.getInt(key));
                attributes.add(attribute);
            } catch (IllegalArgumentException iae) {
                LOG.warning("Unknown attribute in json: " + key);
            }
        }
        return attributes;
    }
}