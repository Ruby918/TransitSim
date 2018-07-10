/* Loic */

import java.util.ArrayList;

abstract public class Station {

  protected double passThroughPrice;
  protected double tapInPrice;
  private final String name;
  private final Route route;
  private final ArrayList<Station> adjacentStations = new ArrayList<>();

  public Station(String name, double tapInPrice, double passThroughPrice, Route route){
    this.name = name;
    this.route = route;
    this.tapInPrice = tapInPrice;
    this.passThroughPrice = passThroughPrice;
  }

  abstract public String getGenericIdentifier();

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
      return this.name + " " + getGenericIdentifier();
  }

}
