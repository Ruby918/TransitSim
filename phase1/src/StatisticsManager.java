/* Dan */
import java.util.ArrayList;
import java.util.Date;

public class StatisticsManager {

  private static ArrayList<Date> invalidTapEventsDates = new ArrayList<>();
  private static ArrayList<Trip> trips = new ArrayList<>();

  public static ArrayList<Date> getInvalidTapEvents() {
    return invalidTapEventsDates;
  }
  public static ArrayList<Trip> getTrips() {
    return trips;
  }

  public static void addTrip(Trip trip) {
    trips.add(trip);
  }

  public static void addInvalidTapEvent(Date date) {
    invalidTapEventsDates.add(date);
  }

  public static int countInvalidTapsOnDate(Date day) {
    int counter = 0;

    for (int i = 0; i < invalidTapEventsDates.size(); i++) {
      if (DateUtils.datesOnSameDay(day, invalidTapEventsDates.get(i))) {
        counter++;
      }
    }
    return counter;
  }

  public static int countInvalidTapsMultiDays(ArrayList<Date> days) {
    int counter = 0;

    for (int i = 0; i < days.size(); i++) {
      for (int x = 0; x < invalidTapEventsDates.size(); x++) {
        if (DateUtils.datesOnSameDay(days.get(i), invalidTapEventsDates.get(x))) {
          counter++;
        }
      }
    }
    return counter;
  }

  public static ArrayList<TapEvent> getTapsOnDate(Date date) {
    ArrayList<TapEvent> dateMatchTapEvents = new ArrayList<>();

    for (int i = 0; i < trips.size(); i++) {
      for (int x = 0; x < trips.get(i).getTapEvents().size(); x++) {
        if (DateUtils.datesOnSameDay(trips.get(i).getTapEvents().get(x).getDate(), date)) {
          dateMatchTapEvents.add(trips.get(i).getTapEvents().get(x));
        }
      }
    }
    return dateMatchTapEvents;
  }

  public static ArrayList<Trip> getTripsOnDate(Date date) {
    ArrayList<Trip> tripsOnDate = new ArrayList<>();
    for (Trip trip : trips) {
      if (
          DateUtils.datesOnSameDay(trip.getStartDate(), date) ||
              DateUtils.datesOnSameDay(trip.getEndDate(), date)
          )
        tripsOnDate.add(trip);
    }
    return tripsOnDate;
  }

  public static double calculateRevenueFromTrips(ArrayList<Trip> trip) {
    double revenue = 0;
    for (int i = 0; i < trip.size(); i++) {
      revenue += trip.get(i).getCost();
    }
    return revenue;
  }

  public static double calculateRevenueOnDate(Date date) {
    ArrayList<Trip> tripsOnDate = getTripsOnDate(date);
    return calculateRevenueFromTrips(tripsOnDate)
        + countInvalidTapsOnDate(date) * 6;
  }

  public static double calculateRevenue() {
    return calculateRevenueFromTrips(trips) + invalidTapEventsDates.size() * 6;
  }

  public static double calculateProfit(ArrayList<Trip> trips, double cost) {
    return cost - calculateRevenueFromTrips(trips);
  }

  public static ArrayList<Station> getStationsReachedOnDate(Date date) {
    ArrayList<Trip> trips = getTripsOnDate(date);
    ArrayList<Station> stationsReached = new ArrayList<>();
    for (Trip trip : trips) {
      for (TapEvent event : trip.getTapEvents()) {
        if (DateUtils.datesOnSameDay(date, event.getDate())) {
          stationsReached.add(event.getStation());
        }
      }
    }
    return stationsReached;
  }

  public static String listStationsReachedOnDate(Date date) {
    ArrayList<Station> stationsReached = getStationsReachedOnDate(date);
    String ret = "";
    if (stationsReached.size() == 0) return "No Stations.";
    for (Station station : stationsReached) {
      ret += station.toString();
      ret += System.lineSeparator();
    }
    return ret.trim();
  }
}
