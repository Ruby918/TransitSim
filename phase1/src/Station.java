/* Loic */

import java.util.ArrayList;

abstract public class Station {

  protected static double tapInPrice;
  protected static double passThroughPrice;
  protected String name;
  protected Route route;
  protected ArrayList<Station> adjacentStations = new ArrayList<>();

  protected static String GENERIC_IDENTIFIER;

  public Station(String name, double tapInPrice, double passThroughPrice, Route route){
    this.tapInPrice = tapInPrice;
    this.passThroughPrice = passThroughPrice;
    this.name = name;
    this.route = route;
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

  public String toString() {
      return GENERIC_IDENTIFIER;
  }

}
