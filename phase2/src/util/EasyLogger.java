package util;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.Handler;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;

public class EasyLogger implements Serializable {
  public Logger log;

  public EasyLogger(String fileName) {

    log = Logger.getLogger(fileName);

    // Disable default console handler
    log.setUseParentHandlers(false);

    // Associate the handler with the log.
    try {
      Handler consoleHandler = new ConsoleHandler();
      Handler fileHandler = new FileHandler("log/" + fileName + ".log", true);
      Handler fileHandlerSevere = new FileHandler("log/" + fileName + "-error.log", true);
      // set log levels
      log.setLevel(Level.ALL);
      consoleHandler.setLevel(Level.ALL);
      fileHandler.setLevel(Level.ALL);
      fileHandlerSevere.setLevel(Level.SEVERE);
      // add handlers to logger
      log.addHandler(consoleHandler);
      log.addHandler(fileHandler);
      log.addHandler(fileHandlerSevere);
    } catch (IOException e) {
      Handler consoleHandler = new ConsoleHandler();
      log.setLevel(Level.ALL);
      consoleHandler.setLevel(Level.ALL);
      log.addHandler(consoleHandler);
      log.log(Level.SEVERE, "Could not open or create log files.");
    }
  }
}
