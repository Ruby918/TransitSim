package api;

import java.util.ArrayList;
import transit.BusRoute;
import transit.BusStation;
import transit.Route;
import transit.Station;
import transit.StatisticsManager;
import transit.SubwayRoute;
import transit.SubwayStation;
import transit.TransitFareManager;
import transit.simplemodel.SimpleRoute;
import transit.simplemodel.SimpleStation;
import util.EasyLogger;

public class MapApi extends ChildApi {

  public MapApi(TransitFareManager transitFareManager, StatisticsManager statisticsManager,
      EasyLogger logger) {
    super(transitFareManager, statisticsManager, logger);
  }

  public Station getStation(SimpleStation station) {
    String routeType = station.getType().equals("Subway Station") ? "Subway" : "Bus";
    return transitFareManager.getMap()
        .getStationByNameAndRoute(station.getName(), station.getRoute(), routeType);
  }

  public ArrayList<SimpleStation> getStationsSimple() {
    ArrayList<Station> stations = getStations();
    ArrayList<SimpleStation> result = new ArrayList<>();
    for (Station station : stations) {
      result.add(new SimpleStation(station));
    }
    return result;
  }

  public ArrayList<SimpleStation> getStationsSimple(SimpleRoute route) {
    ArrayList<Station> stations = getStations();
    ArrayList<SimpleStation> result = new ArrayList<>();
    for (Station station : stations) {
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
    ArrayList<Route> routes = getRoutes();
    ArrayList<SimpleRoute> result = new ArrayList<>();
    for (Route route : routes) {
      result.add(new SimpleRoute(route));
    }
    return result;
  }

  public ArrayList<Route> getRoutes() {
    return transitFareManager.getMap().getRoutes();
  }

  public ArrayList<Route> getRoutes(Station station) {
    ArrayList<Route> routes = transitFareManager.getMap().getRoutes();
    ArrayList<Route> result = new ArrayList<>();
    for (Route route : routes) {
      if (station instanceof BusStation && route instanceof BusRoute) {
        result.add(route);
      }
      if (station instanceof SubwayStation && route instanceof SubwayRoute) {
        result.add(route);
      }
    }

    return result;
  }

  public void saveStation(Station station, String name, Route route,
      ArrayList<Station> adjacentStations) {
    if (station != null) {
      station.setName(name);
      station.setRoute(route);
      // remove former adjacency
      ArrayList<Station> oldAdjacency = new ArrayList<>();
      oldAdjacency.addAll(station.getAdjacent());
      oldAdjacency.add(station);
      transitFareManager.getMap().removeAdjacency(oldAdjacency);
      // add new adjacency
      ArrayList<Station> newAdjacency = new ArrayList<>();
      newAdjacency.addAll(adjacentStations);
      newAdjacency.add(station);
      transitFareManager.getMap().createAdjacency(newAdjacency);
      logger.log.fine("Successfully updated station.");
    } else {
      logger.log.warning("Failed to update station.");
    }
  }
}
