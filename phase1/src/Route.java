/* Loic */

import java.util.ArrayList;

abstract public class Route {

  public static String genericIdentifier = "Route";
  private String name;

  private ArrayList<Station> stations = new ArrayList<>();

  public Route(String name){
    this.name = name;
  }

  public Route(String name, ArrayList<Station> stations) {
    this.name = name;
    this.stations = stations;
  }

  public void addStation(Station station) {
    this.stations.add(station);
  }
  public void setStations(ArrayList<Station> stations){
    this.stations = stations;
  }

  public Station getStationByIndex(int index) {
    return this.stations.get(index);
  }
}
