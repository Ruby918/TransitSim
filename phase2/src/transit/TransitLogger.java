package transit;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.Handler;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;

public class TransitLogger {
  private final Logger logger =
      Logger.getLogger(EventConfigFileParser.class.getName());

  public TransitLogger() {

    // Associate the handler with the logger.
    try {
      Handler consoleHandler = new ConsoleHandler();
      Handler fileHandler = new FileHandler("log/events.log", true);
      logger.setLevel(Level.ALL);
      consoleHandler.setLevel(Level.ALL);
      fileHandler.setLevel(Level.ALL);
      logger.addHandler(consoleHandler);
      logger.addHandler(fileHandler);
    } catch (IOException e) {
      Handler consoleHandler = new ConsoleHandler();
      logger.setLevel(Level.ALL);
      consoleHandler.setLevel(Level.ALL);
      logger.addHandler(consoleHandler);
      logger.log(Level.SEVERE, "Could not open log file.");
      e.printStackTrace();
    }
  }

  public void log(String message) {
    logger.log(Level.FINEST, message);
  }

  public void error(String message) {
    logger.log(Level.SEVERE, message);
  }

}
