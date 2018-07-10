/* Loic */

public class SubwayStation extends Station {

  public SubwayStation(String name, Route route) {
    super(name, 0, 0.5, route);
  }

  @Override
  public String getGenericIdentifier() {
    return "Subway Station";
  }
}
