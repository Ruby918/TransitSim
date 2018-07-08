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

  public int getRouteLength(Station s1, Station s2) {
    int s1Index = this.stations.indexOf(s1);
    int s2Index = this.stations.indexOf(s2);
    return abs(s1Index - s2Index);
  }
}
