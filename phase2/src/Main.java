import transit.DataReadException;
import transit.DataWriteException;
import transit.TransitFareManager;
import transit.TransitLogger;
import api.Api;

import ui.TitleScreen;
import ui.UiController;

public class Main {

  /**
   * Main method of the app. Creates a map using the map configuration at map.txt, creates a transit
   * fare manager with that map, and then applying the events in events.txt to the new transit fare
   * manager.
   */

  private static TransitFareManager transitFareManager;
  private static TransitLogger logger = new TransitLogger();

  public static void main(String[] args) {

    String path = "data/transitFareManager.ser";
    Api api = new Api(logger);
    UiController.api = api;
    UiController.logger = logger;

    try {
      api.loadApplicationStateFromFile(path);
    } catch (DataWriteException e) {
      logger.log.severe("Couldn't write serializable file " + path);
    } catch (DataReadException e) {
      logger.log.severe("Couldn't load application state from file " + path);
      api.loadApplicationStateFromEventsTxt();
      api.saveApplicationStateToFile(path);
    }
    TitleScreen.view();

  }

}
