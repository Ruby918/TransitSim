/* Loic */

public class SubwayStation extends Station {

  public static String genericIdentifier = "Subway Station";

  public SubwayStation(String name, Route route) {
    super(name, 0, 0.5, route);
  }

  public double getTapInPrice() {
    return this.tapInPrice;
  }

  public double getPassThroughPrice() {
    return this.passThroughPrice;
  }
}
