/* Loic */

import java.util.ArrayList;

abstract public class Station {

    protected static double passThroughPrice;
    protected static double tapInPrice;
  private final String name;
  private final Route route;
  private final ArrayList<Station> adjacentStations = new ArrayList<>();

  private static String GENERIC_IDENTIFIER;

  public Station(String name, double tapInPrice, double passThroughPrice, Route route){
      Station.passThroughPrice = passThroughPrice;
    this.name = name;
    this.route = route;
    Station.tapInPrice = tapInPrice;
  }

  public void addAdjacentStation(Station station) {
    this.adjacentStations.add(station);
  }

  public boolean isAdjacentToStation(Station station) {
    return this.adjacentStations.contains(station);
  }

  public Route getRoute() {
    return this.route;
  }
  public String getName() {
    return this.name;
  }

  @Override
  public String toString() {
      return GENERIC_IDENTIFIER+" "+this.name;
  }

}
