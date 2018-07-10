/* Loic */

import java.util.ArrayList;

public class Map {
  private ArrayList<Route> routes = new ArrayList<>();

  public void addRoute(Route route) {
    this.routes.add(route);
  }

  public ArrayList<Route> getRoutes() {
    return this.routes;
  }

  public boolean routeIsType(Route route, String type) {
    return (((type.equals("Subway")) && route instanceof SubwayRoute) ||
        ((type.equals("Bus")) && route instanceof BusRoute));
  }

  public Route getRouteByNameAndType(String name, String type) {
    for (Route route : routes){
      if (this.routeIsType(route, type) && route.getName().equals(name)) return route;
    }
    return null;
  }

  public void addRouteByNameAndType(String name, String type) {
    Route newRoute;
    if (type.equals("Subway")) {
      newRoute = new SubwayRoute(name);
    }
    else {
      newRoute = new BusRoute(name);
    }
    this.routes.add(newRoute);
  }

  public Station getStationByNameAndRoute(String stationName, String routeName, String routeType) {
    Route route = this.getRouteByNameAndType(routeName, routeType);
    if (route == null) {
      return null;
    }
    return route.getStationByName(stationName);
  }

  public static void makeAdjacent(ArrayList<Station> stations) {
    for (int i=0; i<stations.size(); i++) {
      for (int j=0; j<stations.size(); j++) {
        if (!(j == i)) {
          stations.get(i).addAdjacentStation(stations.get(j));
        }
      }
    }
  }
}
