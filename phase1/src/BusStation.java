/* Loic */

public class BusStation extends Station{

  public static String genericIdentifier = "Bus Stop";

  public BusStation(String name, Route route) {
    super(name,2, 0, route);
  }
}
