/* Danya */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MapDataParser {

  private ArrayList<Route> routes = new ArrayList<>();

  public MapDataParser(String fileName) {

    try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
      String line = fileReader.readLine();
      while (line != null) {
        String[] data = line.split(", ");
        if (data[0].equals("Route")) {
          addRouteData(data);
        } else if (data[0] == "Station") {
          addStationData(data);
        } else if (data[0] == "Hub") {
          addHubData(data);
        }
        line = fileReader.readLine();
      }
    } catch (IOException e) {
      System.out.println("Cannot read map data file.");
    }
  }

  private void addRouteData(String[] data) {
    Route newRoute;
    if (data[1].equals("Subway")) {
      newRoute = new SubwayRoute(data[3]);
    }
    else {
      newRoute = new BusRoute(data[3]);
    }
    this.routes.add(newRoute);
  }

  private void addStationData(String[] data) {
    int routeId = Integer.parseInt(data[1]);
    Station station;
    Route route = this.routes.get(routeId);
    if (route instanceof SubwayRoute) {
      station = new SubwayStation(data[3], route);
    } else {
      station = new BusStation(data[3], route);
    }
    route.addStation(station);
  }

  private void addHubData(String[] data) {
    ArrayList<Station> stations = new ArrayList<>();
    String[] stationStrings = data[1].split(" \\| ");
    for (String stationString : stationStrings) {
      data = stationString.split(" ");
      int routeId = Integer.parseInt(data[0]);
      int stationId = Integer.parseInt(data[1]);
      stations.add(this.routes.get(routeId).getStationByIndex(stationId));
    }
    Map.makeAdjacent(stations);
  }

  public ArrayList<Route> getRoutes() {
    return this.routes;
  }
}
