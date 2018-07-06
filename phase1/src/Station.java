/* Loic */

import java.util.ArrayList;

abstract public class Station {

  private double tapInPrice;
  private double passThroughPrice;
  private String name;
  private Route route;
  private ArrayList<Station> adjacentStations = new ArrayList<>();

  public static String genericIdentifier;

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

}
