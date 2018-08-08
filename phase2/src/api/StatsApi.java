package api;

import java.util.ArrayList;
import transit.Route;
import transit.Station;
import transit.StatisticsManager;
import transit.TapEvent;
import transit.Transaction;
import transit.TransitFareManager;
import transit.Trip;
import transit.UserAccount;
import transit.simplemodel.SimpleRoute;
import transit.simplemodel.SimpleStation;
import transit.simplemodel.SimpleTap;
import transit.simplemodel.SimpleTransaction;
import transit.simplemodel.SimpleTrip;
import transit.simplemodel.SimpleUser;
import util.EasyLogger;
import util.FormattedDate;

public class StatsApi extends ChildApi {

  public StatsApi(TransitFareManager transitFareManager, StatisticsManager statisticsManager,
      EasyLogger logger) {
    super(transitFareManager, statisticsManager, logger);
  }

  public ArrayList<Trip> getTrips() {
    return transitFareManager.getTrips();
  }

  public ArrayList<SimpleTrip> getTripsSimple(ArrayList<Trip> trips) {
    ArrayList<SimpleTrip> result = new ArrayList<>();
    for (Trip trip : trips) {
      result.add(new SimpleTrip(trip));
    }
    return result;
  }

  public ArrayList<SimpleTrip> getTripsSimple() {
    return getTripsSimple(getTrips());
  }

  public ArrayList<SimpleTrip> getTripsSimple(FormattedDate date) {
    if (date == null) {
      return getTripsSimple();
    }
    return getTripsSimple(statisticsManager.getTripsOnDate(date));
  }

  public ArrayList<Transaction> getTransactions() {
    return transitFareManager.getTransactions();
  }

  public ArrayList<Station> getStations() {
    return transitFareManager.getMap().getStations();
  }

  public ArrayList<SimpleStation> getStationsSimple(ArrayList<Station> stations) {
    ArrayList<SimpleStation> result = new ArrayList<>();
    for (Station station : stations) {
      result.add(new SimpleStation(station));
    }
    return result;
  }

  public ArrayList<SimpleStation> getStationsSimple() {
    return getStationsSimple(getStations());
  }

  public ArrayList<SimpleStation> getStationsSimple(FormattedDate date) {
    if (date == null) {
      return getStationsSimple();
    }
    return getStationsSimple(statisticsManager.getStationsReachedOnDate(date));
  }

  public ArrayList<SimpleTransaction> getTransactionsSimple(FormattedDate date) {
    if (date == null) {
      return getTransactionsSimple();
    }
    return getTransactionsSimple(statisticsManager.getTransactionsOnDate(date));
  }

  public ArrayList<SimpleTransaction> getTransactionsSimple() {
    return getTransactionsSimple(getTransactions());
  }

  public ArrayList<SimpleTransaction> getTransactionsSimple(ArrayList<Transaction> transactions) {
    ArrayList<SimpleTransaction> result = new ArrayList<>();
    for (Transaction transaction : transactions) {
      result.add(new SimpleTransaction(transaction));
    }
    return result;
  }

  public ArrayList<TapEvent> getTaps() {
    return statisticsManager.getTaps();
  }

  public ArrayList<SimpleTap> getTapsSimple() {
    return getTapsSimple(getTaps());
  }

  public ArrayList<SimpleTap> getTapsSimple(FormattedDate date) {
    if (date == null) {
      return getTapsSimple();
    }
    return getTapsSimple(statisticsManager.getTapsOnDate(date));
  }

  public ArrayList<SimpleTap> getTapsSimple(ArrayList<TapEvent> taps) {
    ArrayList<SimpleTap> result = new ArrayList<>();
    for (TapEvent tap : taps) {
      result.add(new SimpleTap(tap));
    }
    return result;
  }

  public ArrayList<SimpleRoute> getRoutesSimple(FormattedDate date) {
    if (date == null) {
      return getRoutesSimple();
    }
    return getRoutesSimple(statisticsManager.getRoutesReachedOnDate(date));
  }

  public ArrayList<SimpleRoute> getRoutesSimple() {
    return getRoutesSimple(getRoutes());
  }

  public ArrayList<SimpleRoute> getRoutesSimple(ArrayList<Route> routes) {
    ArrayList<SimpleRoute> result = new ArrayList<>();
    for (Route route : routes) {
      result.add(new SimpleRoute(route));
    }
    return result;
  }

  public ArrayList<Route> getRoutes() {
    return transitFareManager.getMap().getRoutes();
  }

  public ArrayList<SimpleUser> getUsers(ArrayList<UserAccount> users) {
    ArrayList<SimpleUser> result = new ArrayList<>();
    for (UserAccount user : users) {
      result.add(new SimpleUser(user));
    }
    return result;
  }

  public ArrayList<SimpleUser> getUsers() {
    return getUsers(transitFareManager.getUsers());
  }

  public ArrayList<SimpleUser> getUsers(FormattedDate date) {
    if (date == null) {
      return getUsers();
    }
    return getUsers(statisticsManager.getUsersReachedOnDate(date));
  }
}
