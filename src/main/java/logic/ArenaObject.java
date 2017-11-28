package logic;

import exceptions.AttributeNotPresentException;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.Map;
import java.util.logging.*;

public class ArenaObject {

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

    @XmlAttribute
    protected String type;
    @XmlElement
    protected Map<Attribute, Integer> attributes;
    private Weapon weapon = null;

    public String getType() {
        return type;
    }

    public int getAttribute(Attribute key) throws AttributeNotPresentException {
        Integer value = attributes.get(key);
        if (value == null)
            throw new AttributeNotPresentException();
        else
            return attributes.get(key);
    }

    public boolean fulfills(Attribute attribute, int value) {
        return (attributes.containsKey(attribute) && (attributes.get(attribute) >= value));
    }

}
