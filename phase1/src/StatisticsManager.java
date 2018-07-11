/* Dan */
// imports utility libraries needed for program.
import java.util.ArrayList;
import java.util.Date;

/**
 * Class that manages information and statistics of the transit system.
 *
 * @author group 0136
 */
public class StatisticsManager {

  // Instance variables storing information the transit system.
  private static ArrayList<Date> invalidTapEventsDates = new ArrayList<>();
  private static ArrayList<Trip> trips = new ArrayList<>();

  /**
   * Returns an array list of dates where invalid taps occurred.
   *
   * @return - array list of dates where invalid taps occurred.
   */
  public static ArrayList<Date> getInvalidTapEvents() {
    return invalidTapEventsDates;
  }

  /**
   * Returns an array list trips taken.
   *
   * @return - array list trips taken.
   */
  public static ArrayList<Trip> getTrips() {
    return trips;
  }

  /**
   * Adds a trip to the array list of recorded trips.
   *
   * @param trip - trip to add to the array list of recorded trips.
   */
  public static void addTrip(Trip trip) {
    trips.add(trip);
  }

  /**
   * Adds a invalid tap date to the array list of recorded invalid taps dates.
   *
   * @param date - date to add to the array list of recorded invalid tap dates.
   */
  public static void addInvalidTapEvent(Date date) {
    invalidTapEventsDates.add(date);
  }

  /**
   * returns the number of invalid taps on a single day.
   *
   * @param day - the day of which invalid taps occurred.
   * @return - the number of invalid taps on a single day.
   */
  public static int countInvalidTapsOnDate(Date day) {
    int counter = 0;

    for (Date invalidTapEventsDate : invalidTapEventsDates) {
      if (DateUtils.datesOnSameDay(day, invalidTapEventsDate)) {
        counter++;
      }
    }
    return counter;
  }

  /**
   * returns the number of invalid taps on a multiple days.
   *
   * @param days - the array list of days of which invalid taps occurred.
   * @return - the number of invalid taps on a multiple days.
   */
  public static int countInvalidTapsMultiDays(ArrayList<Date> days) {
    int counter = 0;

    for (Date day : days) {
      for (Date invalidTapEventsDate : invalidTapEventsDates) {
        if (DateUtils.datesOnSameDay(day, invalidTapEventsDate)) {
          counter++;
        }
      }
    }
    return counter;
  }

  /**
   * returns the number of taps on a single day.
   *
   * @param date - the day of which taps occurred.
   * @return - the number of taps on a single day.
   */
  public static ArrayList<TapEvent> getTapsOnDate(Date date) {
    ArrayList<TapEvent> dateMatchTapEvents = new ArrayList<>();

    for (Trip trip : trips) {
      for (int x = 0; x < trip.getTapEvents().size(); x++) {
        if (DateUtils.datesOnSameDay(trip.getTapEvents().get(x).getDate(), date)) {
          dateMatchTapEvents.add(trip.getTapEvents().get(x));
        }
      }
    }
    return dateMatchTapEvents;
  }

  /**
   * returns the number of trips on a single day.
   *
   * @param date - the day of which trips occurred.
   * @return - the number of trips on a single day.
   */
  public static ArrayList<Trip> getTripsOnDate(Date date) {
    ArrayList<Trip> tripsOnDate = new ArrayList<>();
    for (Trip trip : trips) {
      if (DateUtils.datesOnSameDay(trip.getStartDate(), date)
          || DateUtils.datesOnSameDay(trip.getEndDate(), date)) tripsOnDate.add(trip);
    }
    return tripsOnDate;
  }

  /**
   * returns revenue collected from recorded trips.
   *
   * @param trip - array list of trips.
   * @return - revenue collected from recorded trips.
   */
  public static double calculateRevenueFromTrips(ArrayList<Trip> trip) {
    double revenue = 0;
    for (Trip aTrip : trip) {
      revenue += aTrip.getCost();
    }
    return revenue;
  }

  /**
   * returns revenue gained on a single day.
   *
   * @param date - the day to return revenue from.
   * @return - revenue gained on a single day.
   */
  public static double calculateRevenueOnDate(Date date) {
    ArrayList<Trip> tripsOnDate = getTripsOnDate(date);
    return calculateRevenueFromTrips(tripsOnDate) + countInvalidTapsOnDate(date) * 6;
  }

  /**
   * Calculate total revenue gained thus far.
   *
   * @return - total revenue.
   */
  public static double calculateRevenue() {
    return calculateRevenueFromTrips(trips) + invalidTapEventsDates.size() * 6;
  }

  /**
   * Calculate total profit gained thus far.
   *
   * @param cost - total cost of running the transit system.
   * @param trips - array list of trips that will be used determine revenue.
   * @return - total profit.
   */
  public static double calculateProfit(ArrayList<Trip> trips, double cost) {
    return cost - calculateRevenueFromTrips(trips);
  }

  /**
   * Calculate array list of stations used on one day.
   *
   * @param date - date to record the number of stations reached.
   * @return - array list of stations used on one day.
   */
  public static ArrayList<Station> getStationsReachedOnDate(Date date) {
    ArrayList<Trip> trips = getTripsOnDate(date);
    ArrayList<Station> stationsReached = new ArrayList<>();
    for (Trip trip : trips) {
      for (TapEvent event : trip.getTapEvents()) {
        if (DateUtils.datesOnSameDay(date, event.getDate())) { //determine if station was tapped on the particular date
          stationsReached.add(event.getStation());
        }
      }
    }
    return stationsReached;
  }
}
