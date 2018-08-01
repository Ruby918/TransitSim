
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
    }
  }
}
