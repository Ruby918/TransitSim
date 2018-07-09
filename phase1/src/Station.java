/* Loic */

import java.util.ArrayList;

abstract public class Station {

  protected static final double TAP_IN_PRICE;
  protected static final double PASS_THROUGH_PRICE;
  protected static final String NAME;
  protected static final Route ROUTE;
  protected static final ArrayList<Station> ADJACENT_STATIONS = new ArrayList<>();

  protected static String GENERIC_IDENTIFIER;

  public Station(String name, double tapInPrice, double passThroughPrice, Route route){
    this.TAP_IN_PRICE = tapInPrice;
    this.PASS_THROUGH_PRICE = passThroughPrice;
    this.NAME = name;
    this.ROUTE = route;
  }

  public void addAdjacentStation(Station station) {
    this.ADJACENT_STATIONS.add(station);
  }

  public boolean isAdjacentToStation(Station station) {
    return this.ADJACENT_STATIONS.contains(station);
  }

  public Route getRoute() {
    return this.ROUTE;
  }

}
