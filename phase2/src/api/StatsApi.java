package api;

import java.util.ArrayList;
import transit.TransitFareManager;
import transit.Trip;
import transit.simplemodel.SimpleTrip;
import util.EasyLogger;

public class StatsApi extends ChildApi {

  public StatsApi(TransitFareManager transitFareManager, EasyLogger logger) {
    super(transitFareManager, logger);
  }

  public ArrayList<Trip> getTrips() {
    return transitFareManager.getTrips();
  }

  public ArrayList<SimpleTrip> getTripsSimple() {
    ArrayList<Trip> trips = getTrips();
    ArrayList<SimpleTrip> result = new ArrayList<>();
    for (Trip trip : trips){
      result.add(new SimpleTrip(trip));
    }
    return result;
  }
}
