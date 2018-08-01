import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class Main {

  /**
   * Main method of the app. Creates a map using the map configuration at map.txt, creates a transit
   * fare manager with that map, and then applying the events in events.txt to the new transit fare
   * manager.
   */

  private static TransitFareManager transitFareManager;

  public static void main(String[] args) {

    TransitLogger logger = new TransitLogger();
    // Reads serializable transit fare manager from file.
    // Populates the record list using stored data, if it exists.
    String path = "data/transitFareManager.ser";
    try {
      File file = new File(path);
      if (file.exists()) {
        readFromFile(path);

        // Create stats manager
        StatisticsManager stats = new StatisticsManager(transitFareManager);

      } else {
        file.createNewFile();

        // Create map
        Map thisMap = new Map();
        MapConfigFileParser mapData = new MapConfigFileParser("map.txt", thisMap);
        mapData.parse();

        // Create transit fare manager
        transitFareManager = new TransitFareManager(thisMap);

        // Create stats manager
        StatisticsManager stats = new StatisticsManager(transitFareManager);

        // Process events from events.txt
        EventConfigFileParser eventData = new EventConfigFileParser("events.txt", transitFareManager, stats, logger);
        eventData.parse();

        saveToFile("data/transitFareManager.ser");

      }
    } catch(Exception e) {

    }
  }


  public static void readFromFile(String path) throws ClassNotFoundException {

    try {
      InputStream file = new FileInputStream(path);
      InputStream buffer = new BufferedInputStream(file);
      ObjectInput input = new ObjectInputStream(buffer);

      //deserialize the Map
      transitFareManager = (TransitFareManager) input.readObject();
      input.close();
    } catch (IOException ex) {
    }
  }


  public static void saveToFile(String filePath) {

    try {

      OutputStream file = new FileOutputStream(filePath);
      OutputStream buffer = new BufferedOutputStream(file);
      ObjectOutput output = new ObjectOutputStream(buffer);

      output.writeObject(transitFareManager);
      output.close();
    } catch (Exception e) {

    }
  }
}
