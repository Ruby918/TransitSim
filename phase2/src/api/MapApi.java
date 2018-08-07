package api;

import java.util.ArrayList;
import transit.*;
import util.EasyLogger;

public class MapApi extends ChildApi{
  public MapApi(TransitFareManager transitFareManager, EasyLogger logger) {
    super(transitFareManager, logger);
  }

  public ArrayList<SimpleStation> getStationsSimple() {
    ArrayList<Station> stations =  getStations();
    ArrayList<SimpleStation> result = new ArrayList<>();
    for (Station station : stations){
      result.add(new SimpleStation(station));
    }
    return result;
  }

  public ArrayList<SimpleStation> getStationsSimple(SimpleRoute route) {
    ArrayList<Station> stations =  getStations();
    ArrayList<SimpleStation> result = new ArrayList<>();
    for (Station station : stations){
      if (station.getRoute().getName().equals(route.getName())
          && station.getRoute().getGenericIdentifier().equals(route.getType())) {
        result.add(new SimpleStation(station));
      }
    }
    return result;
  }

  public ArrayList<Station> getStations() {
    return transitFareManager.getMap().getStations();
  }

  public ArrayList<SimpleRoute> getRoutesSimple() {
    ArrayList<Route> routes =  getRoutes();
    ArrayList<SimpleRoute> result = new ArrayList<>();
    for (Route route : routes){
      result.add(new SimpleRoute(route));
    }
    return result;
  }

  public ArrayList<Route> getRoutes() {
    return transitFareManager.getMap().getRoutes();
  }

}
