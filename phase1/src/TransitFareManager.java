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

    Map thisMap = new Map();
    TransitFareManager ttc = new TransitFareManager(thisMap);
    MapDataParser mapData = new MapDataParser("map.txt", thisMap);
    mapData.parse();
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
