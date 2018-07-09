/* Loic */

import java.util.ArrayList;

abstract public class Station {

  protected final double TAP_IN_PRICE;
  protected final double PASS_THROUGH_PRICE;
  protected String name;
  protected Route route;
  protected ArrayList<Station> adjacentStations = new ArrayList<>();

  protected static String GENERIC_IDENTIFIER;

  public Station(String name, double tapInPrice, double passThroughPrice, Route route){
    this.TAP_IN_PRICE = tapInPrice;
    this.PASS_THROUGH_PRICE = passThroughPrice;
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

}
