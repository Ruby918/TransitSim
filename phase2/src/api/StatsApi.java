package api;

import java.util.ArrayList;
import transit.StatisticsManager;
import transit.TapEvent;
import transit.Transaction;
import transit.TransitFareManager;
import transit.Trip;
import transit.simplemodel.SimpleTap;
import transit.simplemodel.SimpleTransaction;
import transit.simplemodel.SimpleTrip;
import util.EasyLogger;

public class StatsApi extends ChildApi {

  public StatsApi(TransitFareManager transitFareManager, StatisticsManager statisticsManager,EasyLogger logger) {
    super(transitFareManager, statisticsManager,logger);
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

  public ArrayList<Transaction> getTransactions() {
    return transitFareManager.getTransactions();
  }

  public ArrayList<SimpleTransaction> getTransactionsSimple() {
    ArrayList<Transaction> transactions = getTransactions();
    ArrayList<SimpleTransaction> result = new ArrayList<>();
    for (Transaction transaction : transactions){
      result.add(new SimpleTransaction(transaction));
    }
    return result;
  }

  public ArrayList<TapEvent> getTaps() {
    return statisticsManager.getTaps();
  }

  public ArrayList<SimpleTap> getTapsSimple() {
    ArrayList<TapEvent> taps = getTaps();
    ArrayList<SimpleTap> result = new ArrayList<>();
    for (TapEvent tap : taps){
      result.add(new SimpleTap(tap));
    }
    return result;
  }
}
