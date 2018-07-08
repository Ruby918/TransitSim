/* Loic */

import java.util.ArrayList;

public class Map {
  private ArrayList<Route> routes;

  public Map(ArrayList<Route> routes) {
    this.routes = routes;
  }

  public void makeAdjacent(ArrayList<Station> stations) {
    for (int i=0; i<stations.size(); i++) {
      for (int j=0; j<stations.size(); j++) {
        if (!(j == i)) {
          stations.get(i).addAdjacentStation(stations.get(j));
        }
      }
    }
  }
}
