/* Loic */

public class BusStation extends Station{

  @Override
  public String getGenericIdentifier() {
    return "Bus Stop";
  }

  public BusStation(String name, Route route) {
    super(name,2, 0, route);
  }
}
