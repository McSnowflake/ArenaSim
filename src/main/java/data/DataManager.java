package data;

import exceptions.ObjectNotFoundException;
import numbers.Attribute;
import numbers.Rule;
import numbers.Value;

import javax.json.*;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

abstract public class DataManager<T> {

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

        try (JsonReader jsonReader = Json.createReader(new FileInputStream(filePath))) {
            JsonObject object = jsonReader.readObject();
            JsonArray array = object.getJsonArray(jsonKeys.elements.name());
            array.forEach(jsonValue -> add(decodeJSON(jsonValue.asJsonObject())));

        } catch (IOException e) {
            LOG.warning(filePath + " not found. Empty dataManager created");
        }
    }

    public void add(T object) {
        objectList.put(object.toString(), object);
    }

    public void save2File(String path2json) throws IOException {

        try (JsonWriter jsonWriter = Json.createWriter(new FileWriter(path2json))) {

            JsonArrayBuilder objectsJSON = Json.createArrayBuilder();
            objectList.forEach((key, value) -> objectsJSON.add(encodeJSON(value)));

            JsonObjectBuilder JSON = Json.createObjectBuilder();
            JSON.add(jsonKeys.elements.name(), objectsJSON.build());
            JSON.add(jsonKeys.type.name(), getType());
            JSON.add(jsonKeys.timeStamp.name(), LocalDateTime.now().toString());
            jsonWriter.writeObject(JSON.build());
        }
    }

    abstract protected String getType();

    protected abstract String getFilePath();

    protected abstract JsonObject encodeJSON(T object);

    abstract protected T decodeJSON(JsonObject json);

    public static JsonObject getJsonFromValues(Stream<Map.Entry<Attribute, Value>> values) {

        JsonObjectBuilder attributesJSON = Json.createObjectBuilder();
        values.forEach(attribute -> attributesJSON.add(attribute.getKey().name(), attribute.getValue().toJSON()));
        return attributesJSON.build();
    }

    public static Map<Attribute, Value> getValuesFromJSON(JsonObject valuesJSON) {

        return valuesJSON.entrySet().stream()
                .collect(Collectors.toMap(entry -> Attribute.valueOf(entry.getKey()), entry -> new Value(entry.getValue().asJsonObject())));
    }

    public static JsonObject getJsonFromAttributes(Stream<Map.Entry<Attribute, Integer>> attributes) {

        JsonObjectBuilder attributesJSON = Json.createObjectBuilder();
        attributes.forEach(attribute -> attributesJSON.add(attribute.getKey().name(), attribute.getValue()));
        return attributesJSON.build();
    }

    public static Map<Attribute, Integer> getAttributesFromJSON(JsonObject attributesJSON) {

        return attributesJSON.entrySet().stream()
                .collect(Collectors.toMap(entry -> Attribute.valueOf(entry.getKey()), entry -> ((JsonNumber) entry.getValue()).intValue()));
    }

    public static JsonArray getJsonFromRules(Stream<Rule> rules) {

        JsonArrayBuilder rulesJSON = Json.createArrayBuilder();
        rules.forEach(rule -> rulesJSON.add(rule.toJSON()));
        return rulesJSON.build();
    }

    public static List<Rule> getRulesFromJSON(JsonArray rulesJSON) {

        return rulesJSON.stream()
                .map(JsonValue::asJsonObject)
                .map(Rule::new)
                .collect(Collectors.toList());
    }

    enum type {
        weapon,
        armor,
        fighter
    }

    enum jsonKeys {
        usageRules,
        attributes,
        name,
        type,
        subType,
        _class,
        elements,
        timeStamp,
        baseAttribute
    }
}