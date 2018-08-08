package transit;/* Dan */

import java.util.ArrayList;
import util.FormattedDate;

/**
 * Class that manages information and statistics of the transit system.
 *
 * @author group 0136
 */
public class StatisticsManager {

  private TransitFareManager transitFareManager;

  public StatisticsManager(TransitFareManager transitFareManager) {
    this.transitFareManager = transitFareManager;
  }

  /**
   * returns the number of trips on a single day.
   *
   * @param date - the day of which trips occurred.
   * @return - the number of trips on a single day.
   */
  public ArrayList<Trip> getTripsOnDate(FormattedDate date) {
    ArrayList<Trip> tripsOnDate = new ArrayList<>();
    for (Trip trip : transitFareManager.getTrips()) {
      if (trip.getStartDate().onSameDay(date) || trip.getEndDate().onSameDay(date)) {
        tripsOnDate.add(trip);
      }
    }
    return tripsOnDate;
  }

  public ArrayList<TapEvent> getTaps() {
    ArrayList<TapEvent> taps = new ArrayList<>();
    for (Trip trip : transitFareManager.getTrips()) {
      taps.addAll(trip.getTapEvents());
    }
    return taps;
  }

  public ArrayList<TapEvent> getTapsOnDate(FormattedDate date) {
    ArrayList<TapEvent> tapsOnDate = new ArrayList<>();
    for (TapEvent tap : getTaps()) {
      if (tap.getTransitDate().onSameDay(date)) {
        tapsOnDate.add(tap);
      }
    }
    return tapsOnDate;
  }

  public ArrayList<Transaction> getTransactionsOnDate(FormattedDate date) {
    ArrayList<Transaction> transactions = new ArrayList<>();
    for (Transaction transaction : transitFareManager.getTransactions()) {
      if (transaction.getDate().onSameDay(date)) {
        transactions.add(transaction);
      }
    }
    return transactions;
  }

  /**
   * returns revenue gained on a single day.
   *
   * @param date - the day to return revenue from.
   * @return - revenue gained on a single day.
   */
  public double calculateRevenueOnDate(FormattedDate date) {
    double sum = 0;
    for (Transaction transaction : transitFareManager.getTransactions()) {
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
  public double calculateRevenue() {
    double sum = 0;
    for (Transaction transaction : transitFareManager.getTransactions()) {
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
  public ArrayList<Station> getStationsReachedOnDate(FormattedDate date) {
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

  public ArrayList<Route> getRoutesReachedOnDate(FormattedDate date) {
    ArrayList<Route> routes = new ArrayList<>();
    for (Station station : getStationsReachedOnDate(date)) {
      if (!routes.contains(station.getRoute())) routes.add(station.getRoute());
    }
    return routes;
  }

  public ArrayList<UserAccount> getUsersReachedOnDate(FormattedDate date) {
    ArrayList<UserAccount> users = transitFareManager.getUsers();
    ArrayList<UserAccount> usersReached = new ArrayList<>();
    for (UserAccount user : users) {
      for (Card card : user.getCards()) {
        for (Trip trip : card.getTrips()) {
          if (trip.getStartDate().onSameDay(date) || trip.getEndDate().onSameDay(date)) {
            if (!usersReached.contains(user))
              usersReached.add(user);
          }
        }
      }
    }
    return usersReached;
  }

}
