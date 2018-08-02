package transit;

import ui.TitleScreen;
import ui.UiController;

public class Main {

  /**
   * Main method of the app. Creates a map using the map configuration at map.txt, creates a transit
   * fare manager with that map, and then applying the events in events.txt to the new transit fare
   * manager.
   */

  private static TransitFareManager transitFareManager;

  public static void main(String[] args) {

    TransitLogger logger = new TransitLogger();
    String path = "data/transitFareManager.ser";

    try {
      DataReadWrite<TransitFareManager> dataReadWrite = new DataReadWrite<>(path);
      transitFareManager = dataReadWrite.read();
      StatisticsManager stats = new StatisticsManager(transitFareManager);
    } catch (DataWriteException e) {
      logger.error("Couldn't write serializable file " + path);
    } catch (DataReadException e) {
      logger.error("Couldn't read from serializable file " + path);

      // Create data from events.txt and map.txt
      Map map = new Map();
      MapConfigFileParser mapConfigFileParser = new MapConfigFileParser("map.txt", map);
      mapConfigFileParser.parse();
      transitFareManager = new TransitFareManager(map);
      StatisticsManager stats = new StatisticsManager(transitFareManager);
      EventConfigFileParser eventConfigFileParser = new EventConfigFileParser("events.txt", transitFareManager, stats, logger);
      eventConfigFileParser.parse();
      try {
        DataReadWrite<TransitFareManager> dataReadWrite = new DataReadWrite<>(path);
        dataReadWrite.save(transitFareManager);
      } catch (Exception e2) {}
    }

    UiController.transitFareManager = transitFareManager;
    TitleScreen.view();

  }
}
