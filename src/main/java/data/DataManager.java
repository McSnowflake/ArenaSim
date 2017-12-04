package data;

import enums.Attribute;
import exceptions.ObjectNotFoundException;
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

    private Map<String, T> objectList = new HashMap<>();

    public List<T> getList() {
        return new ArrayList<>(objectList.values());
    }

    public T get(String key) throws ObjectNotFoundException {

        if (objectList.containsKey(key))
            return objectList.get(key);

        LOG.warning(key + " not found");
        throw new ObjectNotFoundException(key);
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
                objectList.put(element.getType(), element);
            }
        } catch (IOException e) {
            LOG.warning(filePath + " not found. Empty dataManager created");
        }
    }

    public void add(T object) {
        objectList.put(object.getType(), object);
    }

    public void save2File(String path2json) throws IOException {

        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        for (T object : objectList.values()) {
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

    JSONObject getJsonFromAttributes(Stream<Map.Entry<Attribute, Integer>> attributes) {
        JSONObject jsonObject = new JSONObject();
        attributes.forEach(attribute -> jsonObject.put(attribute.getKey().name(), attribute.getValue()));
        return jsonObject;
    }

    Map<Attribute, Integer> getAttributesFromJSON(JSONObject json) {
        Map<Attribute, Integer> attributes = new HashMap<>();
        Iterator<String> iterator = json.keys();
        while (iterator.hasNext()) {
            String key = iterator.next();
            try {
                attributes.put(Attribute.valueOf(key), json.getInt(key));
            } catch (IllegalArgumentException iae) {
                LOG.warning("Unknown attribute in json: " + key);
            }
        }
        return attributes;
    }
}