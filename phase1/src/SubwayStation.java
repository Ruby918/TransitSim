/* Loic */

/**
 * Class modeling a Subway Station
 *
 * @author group 0136
 */
public class SubwayStation extends Station {

    /**
     * Constructor of SubwayStation
     *
     * @param name
     * @param route
     */
  public SubwayStation(String name, Route route) {
    super(name, 0, 0.5, route);
  }

    /**
     * Get the generic Identifier of the subway station
     *
     * @return - The generic identifier of a Subway Station.
     */
  @Override
  public String getGenericIdentifier() {
    return "Subway Station";
  }
}
