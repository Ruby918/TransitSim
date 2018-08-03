package transit;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.Handler;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;

public class TransitLogger {
  public final Logger log =
      Logger.getLogger(EventConfigFileParser.class.getName());

  public TransitLogger() {

    // Disable default console handler
    log.setUseParentHandlers(false);

    // Associate the handler with the log.
    try {
      Handler consoleHandler = new ConsoleHandler();
      Handler fileHandler = new FileHandler("log/events.log", true);
      log.setLevel(Level.ALL);
      consoleHandler.setLevel(Level.ALL);
      fileHandler.setLevel(Level.ALL);
      log.addHandler(consoleHandler);
      log.addHandler(fileHandler);
    } catch (IOException e) {
      Handler consoleHandler = new ConsoleHandler();
      log.setLevel(Level.ALL);
      consoleHandler.setLevel(Level.ALL);
      log.addHandler(consoleHandler);
      log.log(Level.SEVERE, "Could not open log file.");
    }
  }

}
