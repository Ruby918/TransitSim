/* Loic */

import java.util.ArrayList;

/**
 * Class modeling a Bus Route
 *
 * @author group 0136
 */
public class BusRoute extends Route {

    /** BusRoute constructor that does not require stations on its route. */
  public BusRoute(String name){
    super(name);
  }
  /** BusRoute constructor that is initialized with a route of stations. */
  public BusRoute(String name, ArrayList<Station> stations) {
    super(name, stations);
  }

    /**
     * returns the generic identifier of a Bus Route.
     *
     * @return - the generic identifier
     */
  @Override
  public String getGenericIdentifier() {
    return "Bus Route";
  }

    /**
     * Add a station to the Bus Route.
     *
     * @param name
     */
  @Override
  public void addStationByName(String name){
    this.addStation(new BusStation(name, this));
  }
}
