package logic;

import enums.Attribute;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.*;
import java.util.stream.Stream;

public abstract class ArenaObject {

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
    private Map<Attribute, Integer> attributes = new HashMap<>();

    public String getType() {
        return type;
    }

    public Integer getAttribute(Attribute key) {
        if (attributes.containsKey(key))
            return attributes.get(key);
        return 0;
    }

    public void setAttribute(Attribute attribute, Integer value) {
        attributes.put(attribute, value);
    }

    public Stream<Map.Entry<Attribute, Integer>> getAttributes() {
        return attributes.entrySet().stream();
    }

    public boolean fulfills(Stream<Map.Entry<Attribute, Integer>> requirements) {

        return requirements.allMatch(requirement -> requirement.getValue() >= getAttribute(requirement.getKey()));
    }

    void setAttributes(Map<Attribute, Integer> attributes) {
        attributes.forEach(this::setAttribute);
    }

    public void addAttribute(Attribute attribute, Integer value) {
        setAttribute(attribute, value + getAttribute(attribute));
    }

    protected void subAttribute(Attribute attribute, Integer value) {
        setAttribute(attribute, value - getAttribute(attribute));
    }

    public void printAttributes() {
        System.out.println(getType());
        getAttributes().forEach(attribute -> System.out.println(attribute.getKey().name() + " : " + attribute.getValue()));
    }
}
