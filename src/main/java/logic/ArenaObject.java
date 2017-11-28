package logic;

import exceptions.AttributeNotPresentException;
import org.json.JSONObject;

import java.util.Map;
import java.util.logging.*;

abstract class ArenaObject<T> {

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

    protected String type;
    protected Map<Attribute, Object> attributes;

    public String getType() {
        return type;
    }

    public T getAttribute(Attribute key, Class<T> type) throws AttributeNotPresentException {

        if (attributes.containsKey(key))
            return (T) attributes.get(key);
        throw new AttributeNotPresentException();

    }

    public boolean fulfills(Attribute attribute, int value) {
        return (attributes.containsKey(attribute) && (attributes.get(attribute) >= value));
    }

    JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", getType());
        for (Map.Entry<Attribute, Integer> attribute : attributes.entrySet()) {
            jsonObject.put(attribute.getKey().name(), attribute.getValue());
        }
        return jsonObject;
    }
}
