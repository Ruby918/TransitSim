/* Danya */

import java.util.ArrayList;
import java.util.Date;

public class TransitFareManager {

  private ArrayList<CustomerAccount> customers = new ArrayList<>();
  private Map map;

  public TransitFareManager(Map map) {
    this.map = map;
  }

  public static void main(String[] args) {
    MapDataParser mapData = new MapDataParser("map.txt");
    ArrayList<Route> routes = mapData.getRoutes();

    Map map = new Map(routes);
    TransitFareManager ttc = new TransitFareManager(map);
  }

  public String getDailyReport(Date date) {
    // stub
    return "";
  }

  public Card generateCard(CustomerAccount customer) {
    // stub
    return new Card();
  }

}
