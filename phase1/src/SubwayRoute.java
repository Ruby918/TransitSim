/* Loic */

import java.util.ArrayList;

/**
 * Class modeling a Subway Route.
 *
 * @author group 0136
 */
public class SubwayRoute extends Route {

  /** constructor of SubwayRoute */
  public SubwayRoute(String name){
    super(name);
  }
  /** alternative constructor of SubwayRoute that takes stations within its route. */
  public SubwayRoute(String name, ArrayList<Station> stations) {
    super(name, stations);
  }

  /** get the generic identifier of a Subway Route */
  @Override
  public String getGenericIdentifier() {
    return "Subway Line";
  }

  /**
   * Add a station by its name.
   *
   * @param name
   */
  @Override
  public void addStationByName(String name){
    this.addStation(new SubwayStation(name, this));
  }
}
