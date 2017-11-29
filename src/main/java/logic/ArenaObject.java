package logic;

import enums.Attribute;

import java.util.ArrayList;
import java.util.List;
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
    private List<Attribute> attributes = new ArrayList<>();

    public String getType() {
        return type;
    }

    public Integer getAttribute(Attribute key) {
        int index = attributes.indexOf(key);
        if (index >= 0)
            return attributes.get(index).getValue();
        return 0;
    }

    public void setAttribute(Attribute attribute) {
        int index = attributes.indexOf(attribute);
        if (index >= 0)
            attributes.set(index, attribute);
        else
            attributes.add(attribute);
    }

    public Stream<Attribute> getAttributes() {
        return attributes.stream();
    }

    public boolean fulfills(Stream<Attribute> requirements) {

        return requirements.allMatch(requirement -> getAttribute(requirement) >= requirement.getValue());
    }

    void setAttributes(List<Attribute> attributes) {
        attributes.forEach(this::setAttribute);
    }

    public void addAttribute(Attribute attribute) {
        setAttribute(attribute.setValue(attribute.getValue() + getAttribute(attribute)));
    }
}
