import transit.DataReadException;
import transit.DataReadWrite;
import transit.DataWriteException;
import transit.EventConfigFileParser;
import transit.Map;
import transit.MapConfigFileParser;
import transit.StatisticsManager;
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

  public static void main(String[] args) {

    TransitFareManager transitFareManager;
    TransitLogger logger = new TransitLogger();
    StatisticsManager stats;
    String path = "data/transitFareManager.ser";

    try {
      DataReadWrite<TransitFareManager> dataReadWrite = new DataReadWrite<>(path);
      transitFareManager = dataReadWrite.read();
      stats = new StatisticsManager(transitFareManager);
      Api api = new Api(transitFareManager, stats, logger);
      UiController.api = api;
    } catch (DataWriteException e) {
      logger.error("Couldn't write serializable file " + path);
    } catch (DataReadException e) {
      logger.error("Couldn't read from serializable file " + path);

      // Create data from events.txt and map.txt
      Map map = new Map();
      MapConfigFileParser mapConfigFileParser = new MapConfigFileParser("map.txt", map);
      mapConfigFileParser.parse();
      transitFareManager = new TransitFareManager(map);
      stats = new StatisticsManager(transitFareManager);
      EventConfigFileParser eventConfigFileParser = new EventConfigFileParser("events.txt", transitFareManager, stats, logger);
      eventConfigFileParser.parse();
      Api api = new Api(transitFareManager, stats, logger);
      UiController.api = api;
      try {
        DataReadWrite<TransitFareManager> dataReadWrite = new DataReadWrite<>(path);
        dataReadWrite.save(transitFareManager);
      } catch (Exception e2) {}
    }

    TitleScreen.view();

  }
}
