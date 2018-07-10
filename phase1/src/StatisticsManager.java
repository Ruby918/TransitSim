/* Dan */
import java.util.ArrayList;
import java.util.Date;

public class StatisticsManager {

  private static ArrayList<Date> invalidTapEventsDates = new ArrayList<>();
  private static ArrayList<Trip> trips = new ArrayList<>();
  private static double unnaturalTapSequenceInstances = 0;

  public static ArrayList<Date> getInvalidTapEvents() {
    return invalidTapEventsDates;
  }
  public static ArrayList<Trip> getTrips() {
    return trips;
  }
  public static double getUnnaturalTapSequenceInstances() {
    return unnaturalTapSequenceInstances;
  }

  public static void addTrip(Trip trip) {
    trips.add(trip);
  }

  public static void addInvalidTapEvent(Date date) {
    invalidTapEventsDates.add(date);
  }

  public static void incrementUnnaturalTapSequenceInstances() {
    unnaturalTapSequenceInstances++;
  }

  public static int countInvalidTapsOnDate(Date day) {
    int counter = 0;

    for (int i = 0; i < invalidTapEventsDates.size(); i++) {
      if (invalidTapEventsDates.get(i).equals(day)) {
        counter++;
      }
    }
    return counter;
  }

  public static int countInvalidTapsMultiDays(ArrayList<Date> days) {
    int counter = 0;

    for (int i = 0; i < days.size(); i++) {
      for (int x = 0; x < invalidTapEventsDates.size(); x++) {
        if (days.get(i).equals(invalidTapEventsDates.get(x))) {
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
        if (trips.get(i).getTapEvents().get(x).getDate().equals(date)) {
          dateMatchTapEvents.add(trips.get(i).getTapEvents().get(x));
        }
      }
    }
    return dateMatchTapEvents;
  }

  public static ArrayList<Trip> getTripsOnDate(Date date) {
    ArrayList<Trip> tripsOnDate = new ArrayList<>();
    for (Trip trip : trips) {
      if (trip.getStartDate().equals(date) || trip.getEndDate().equals(date))
        tripsOnDate.add(trip);
    }
    return tripsOnDate;
  }

  public static double calculateRevenueFromTrips(ArrayList<Trip> trip) {
    double revenue = 0;
    for (int i = 0; i < trip.size(); i++) {
      revenue += trip.get(i).getCostSoFar();
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

  public static ArrayList<Station> calculateStationsReached(ArrayList<TapEvent> taps) {
    // stub
    return new ArrayList<>(); // filler so i don't see red lines
  }
}
