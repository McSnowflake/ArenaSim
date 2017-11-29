import java.util.logging.*;

public class AbstractTest {

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
}
