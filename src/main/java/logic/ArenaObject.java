package logic;

import exceptions.AttributeNotPresentException;

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

    protected String type;
    protected Map<Attribute, Integer> atributes;
    private Weapon weapon = null;

    public String getType() {
        return type;
    }

    public int getAttribute(Attribute key) throws AttributeNotPresentException {
        Integer value = atributes.get(key);
        if (value == null)
            throw new AttributeNotPresentException();
        else
            return atributes.get(key);
    }

    public boolean fulfills(Attribute attribute, int value) {
        return (atributes.containsKey(attribute) && (atributes.get(attribute) >= value));
    }

}
