/* Dan */

import java.util.ArrayList;

/**
 * Class that manages information and statistics of the transit system.
 *
 * @author group 0136
 */
public class StatisticsManager {

  /**
   * returns the number of trips on a single day.
   *
   * @param date - the day of which trips occurred.
   * @return - the number of trips on a single day.
   */
  public static ArrayList<Trip> getTripsOnDate(TransitDate date) {
    ArrayList<Trip> tripsOnDate = new ArrayList<>();
    for (Trip trip : Trip.trips) {
      if (trip.getStartDate().onSameDay(date) || trip.getEndDate().onSameDay(date)) {
        tripsOnDate.add(trip);
      }
    }
    return tripsOnDate;
  }

  /**
   * returns revenue gained on a single day.
   *
   * @param date - the day to return revenue from.
   * @return - revenue gained on a single day.
   */
  public static double calculateRevenueOnDate(TransitDate date) {
    double sum = 0;
    for (Transaction transaction : Transaction.transactions) {
      if (date.onSameDay(transaction.getDate())) {
        sum += transaction.getAmount();
      }
    }
    return sum;
  }

  /**
   * Calculate total revenue gained thus far.
   *
   * @return - total revenue.
   */
  public static double calculateRevenue() {
    double sum = 0;
    for (Transaction transaction : Transaction.transactions) {
      sum += transaction.getAmount();
    }
    return sum;
  }

  /**
   * Calculate array list of stations used on one day.
   *
   * @param date - date to record the number of stations reached.
   * @return - array list of stations used on one day.
   */
  public static ArrayList<Station> getStationsReachedOnDate(TransitDate date) {
    ArrayList<Trip> trips = getTripsOnDate(date);
    ArrayList<Station> stationsReached = new ArrayList<>();
    for (Trip trip : trips) {
      for (TapEvent event : trip.getTapEvents()) {
        if (date.onSameDay(
            event.getTransitDate())) { // determine if station was tapped on the particular date
          stationsReached.add(event.getStation());
        }
      }
    }
    return stationsReached;
  }
}
