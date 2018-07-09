/* Danya */

import java.util.ArrayList;

public class MapDataParser extends DataParser {

  private Map map;

  public MapDataParser(String filename, Map map) {
    super(filename);
    this.map = map;
  }

  @Override
  protected void parseLine(String line) {
    String[] data = line.split(": ");
    if (data[0].equals("Route")) {
      addRouteData(data[1].split(", "));
    } else if (data[0].equals("Station")) {
      addStationData(data[1].split(", "));
    } else if (data[0].equals("Hub")) {
      addHubData(data[1]);
    }
  }

  private void addRouteData(String[] data) {
    String type = data[0];
    String name = data[1];
    this.map.addRouteByNameAndType(name, type);
  }

  private void addStationData(String[] data) {
    String routeType = data[0];
    String routeName = data[1];
    String stationName = data[2];
    Route route = map.getRouteByNameAndType(routeName, routeType);
    route.addStationByName(stationName);
  }

  private void addHubData(String data) {
    ArrayList<Station> stations = new ArrayList<>();
    String[] stationStrings = data.split(" \\| ");
    for (String stationString : stationStrings) {
      String[] stationData = stationString.split(", ");
      String routeType = stationData[0];
      String routeName = stationData[1];
      String stationName = stationData[2];
      Station station = map.getStationByNameAndRoute(stationName, routeName, routeType);
      stations.add(station);
    }
    Map.makeAdjacent(stations);
  }
}
