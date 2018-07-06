/* Loic */

import java.util.ArrayList;

public class SubwayRoute extends Route {

  public static String genericIdentifier = "Subway Line";

  public SubwayRoute(String name){
    super(name);
  }
  public SubwayRoute(String name, ArrayList<Station> stations) {
    super(name, stations);
  }
}
