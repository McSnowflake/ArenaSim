import java.util.logging.*;

public class AbstractTest {

    protected static Logger LOG = Logger.getGlobal();

    static {
        LOG.setLevel(Level.FINE);
        LOG.setUseParentHandlers(false);
        Handler conHdlr = new ConsoleHandler();
        conHdlr.setLevel(Level.INFO);
        conHdlr.setFormatter(new Formatter() {
            public String format(LogRecord record) {
                return record.getMessage() + "\n";
            }
        });
        LOG.addHandler(conHdlr);
    }
}
