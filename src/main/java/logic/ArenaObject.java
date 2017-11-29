package logic;

import exceptions.AttributeNotPresentException;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.*;

public abstract class ArenaObject<T extends ArenaObject> {

    protected static Logger LOG = Logger.getAnonymousLogger();

    static {
        LOG.setLevel(Level.INFO);
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
    protected Map<Attribute, Integer> attributes = new HashMap<>();

    public String getType() {
        return type;
    }

    public <T extends ArenaObject> T setAttribute(Attribute attribute, Integer value) {
        attributes.put(attribute, value);
        return (T) this;
    }

    public Integer getAttribute(Attribute key) throws AttributeNotPresentException {

        if (attributes.containsKey(key))
            return attributes.get(key);
        throw new AttributeNotPresentException();

    }

    public Map<Attribute, Integer> getAttributes() {
        Map<Attribute, Integer> copy = new HashMap<>();
        copy.putAll(attributes);
        return copy;

    }

    public boolean fulfills(Attribute attribute, int value) {
        return (attributes.containsKey(attribute) && (attributes.get(attribute) >= value));
    }

}
