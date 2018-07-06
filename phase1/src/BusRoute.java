/* Loic */

import java.util.ArrayList;

public class BusRoute extends Route {

  public BusRoute(String name){
    super(name);
  }
  public BusRoute(String name, ArrayList<Station> stations) {
    super(name, stations);
  }
}
