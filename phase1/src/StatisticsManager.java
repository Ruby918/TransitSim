/* Dan */
import java.util.ArrayList;
import java.util.Date;

public class StatisticsManager {

  private static ArrayList<Date> invalidTapEventsDates = new ArrayList<>();
  private static ArrayList<Trip> tripEvents = new ArrayList<>();
  private static double unnaturalTapSequenceInstances = 0;

  public static ArrayList<Date> getInvalidTapEvents() {
    return invalidTapEventsDates;
  }

  public static ArrayList<Trip> getTripEvents() {
    return tripEvents;
  }

  public static double getUnnaturalTapSequenceInstances() {
    return unnaturalTapSequenceInstances;
  }

  public static void addTripEvent(Trip trip) {
    tripEvents.add(trip);
  }

  public static void addInvalidTapEvent(Date date) {
    invalidTapEventsDates.add(date);
  }

  public static void incrementUnnaturalTapSequenceInstances() {
    unnaturalTapSequenceInstances++;
  }

  public int invalidTapsOnOneDay(Date day) {
    int counter = 0;

    for (int i = 0; i < invalidTapEventsDates.size(); i++) {
      if (invalidTapEventsDates.get(i).equals(day)) {
        counter++;
      }
    }
    return counter;
  }

  public int invalidTapsMultiDays(ArrayList<Date> days) {
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

  public static ArrayList<TapEvent> dateTap(Date date) {
    ArrayList<TapEvent> dateMatchTapEvents = new ArrayList<>();

    for (int i = 0; i < tripEvents.size(); i++) {
      for (int x = 0; x < tripEvents.get(i).gettapEvents().size(); x++) {
        if (tripEvents.get(i).gettapEvents().get(x).getDate().equals(date)) {
          dateMatchTapEvents.add(tripEvents.get(i).gettapEvents().get(x));
        }
      }
    }
    return dateMatchTapEvents;
  }

  public static double calculateRevenue(ArrayList<Trip> trip) {
    double revenue = 0;
    for (int i = 0; i < trip.size(); i++) {
      revenue += trip.get(i).getCostSoFar();
    }
    //        return revenue + (invalidTapEventsDates.size()) *6;
    return revenue;
  }

  public static double calculateProfit(ArrayList<Trip> taps, double cost) {
    return cost - calculateRevenue(taps);
  }

  public static ArrayList<Station> calculateStationsReached(ArrayList<TapEvent> taps) {
    // stub
    return new ArrayList<>(); // filler so i don't see red lines
  }
}
