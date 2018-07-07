/* Loic */

import java.util.*;

public class Map {
  private ArrayList<Route> routes;

  public Map(ArrayList<Route> routes) {
    this.routes = routes;
  }

  public void makeAdjacent(Station[] stations) {
    stations[0].addAdjacentStation(stations[1]);
    stations[1].addAdjacentStation(stations[0]);
  }
}
