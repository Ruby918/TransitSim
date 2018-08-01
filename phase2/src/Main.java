public class Main {

  /**
   * Main method of the app. Creates a map using the map configuration at map.txt, creates a transit
   * fare manager with that map, and then applying the events in events.txt to the new transit fare
   * manager.
   */
  public static void main(String[] args) {
    // Create map
    Map thisMap = new Map();
    MapConfigFileParser mapData = new MapConfigFileParser("map.txt", thisMap);
    mapData.parse();

    // Create stats manager
    StatisticsManager stats = new StatisticsManager();

    // Create transit fare manager
    TransitFareManager ttc = new TransitFareManager(thisMap, stats);

    // Process events from events.txt
    EventConfigFileParser eventData = new EventConfigFileParser("events.txt", ttc);
    eventData.parse();
  }
}
