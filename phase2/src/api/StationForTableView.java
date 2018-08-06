package api;

import javafx.beans.property.SimpleStringProperty;
import transit.PrettyList;
import transit.Station;

public class StationForTableView {

  private SimpleStringProperty name;
  private SimpleStringProperty route;
  private SimpleStringProperty adjacent;

  public StationForTableView(Station station) {
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
