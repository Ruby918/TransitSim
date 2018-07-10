/* Loic */
import java.lang.Math;
import java.util.ArrayList;

abstract public class Route {

  private String name;
  private ArrayList<Station> stations = new ArrayList<>();

  public Route(String name){
    this.name = name;
  }

  public Route(String name, ArrayList<Station> stations) {
    this.name = name;
    this.stations = stations;
  }

  abstract public void addStationByName(String name);
  abstract public String getGenericIdentifier();

  public String getName() {
    return this.name;
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

  public Station getStationByName(String name) {
    for (Station station: stations) {
      if (station.getName().equals(name)) {
        return station;
      }
    }
    return null;
  }

  public int getRouteLength(Station s1, Station s2) {
    int s1Index = this.stations.indexOf(s1);
    int s2Index = this.stations.indexOf(s2);
    return Math.abs(s1Index - s2Index);
  }

  @Override
  public String toString() {
    return this.name + " " + getGenericIdentifier();
  }

  public String toStringVerbose() {
    String ret = this.toString() + " (";
    for (Station station: this.stations) {
      ret += station.toString() + ", ";
    }
    ret = ret.substring(0,ret.length() - 2);
    return ret + ")";
  }
}
