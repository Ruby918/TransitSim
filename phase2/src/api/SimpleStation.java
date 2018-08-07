package api;

import javafx.beans.property.SimpleStringProperty;
import util.PrettyList;
import transit.Station;

public class SimpleStation {

  private SimpleStringProperty name;
  private SimpleStringProperty route;
  private SimpleStringProperty adjacent;

  public SimpleStation(Station station) {
    name = new SimpleStringProperty(station.getName());
    route = new SimpleStringProperty(station.getRoute().toStringSimple());
    PrettyList<Station> adjacentStations = new PrettyList<>(station.getAdjacent(), "Stations");
    adjacent = new SimpleStringProperty(adjacentStations.toString());
  }

  public String getName() {
    return name.get();
  }

  public String getRoute() {
    return route.get();
  }

  public String getAdjacent() {
    return adjacent.get();
  }
}
