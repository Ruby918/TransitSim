/* Danya */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MapDataParser {

  private ArrayList<Route> routes = new ArrayList<>();

  public MapDataParser(String fileName) {

    try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {


    } catch (IOException e) {
    }
  }

  private void addRouteData(String[] data) {

  }

  private void addStationData(String[] data) {

  }

  private void addHubData(String[] data) {
    
  }

  public ArrayList<Route> getRoutes() {
    return this.routes;
  }
}
