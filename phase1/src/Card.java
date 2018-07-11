/* Dan */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class Card {
  private static final int MAX_CHARGE = 6;
  private double balance = 19;
  private ArrayList<Trip> trips = new ArrayList<>();
  private ArrayList<Date> invalidTapEventDates = new ArrayList<>();
  private Trip activeTrip = null;
  private boolean isActive = true;
  private int cardId;

  public Card(int id) {
    this.cardId = id;
  }

  public int getCardId() {
    return cardId;
  }

  public ArrayList<Trip> getTrips() {
    return trips;
  }

  public ArrayList<Date> getInvalidTapEventDates() {
    return invalidTapEventDates;
  }

  public void deactivate() {
    this.isActive = false;
  }

  public double getBalance() {
    return this.balance;
  }

  public void addTenDollars() {
    this.balance += 10;
  }

  public void addTwentyDollars() {
    this.balance += 20;
  }

  public void addFiftyDollars() {
    this.balance += 50;
  }

  public void addInvalidTap(TapEvent tapEvent) {
    this.invalidTapEventDates.add(tapEvent.getDate());
    StatisticsManager.addInvalidTapEvent(tapEvent.getDate());
    this.balance -= MAX_CHARGE;
    this.activeTrip = null;
  }

  public void tapIn(Station station, Date date)
      throws InsufficientFundsException, TapDeactivatedCardException, IllegalTapLocationException {

    // check if card is active
    if (!this.isActive) throw new TapDeactivatedCardException();
    // check if card has sufficient funds
    if (this.balance < 0) throw new InsufficientFundsException();

    // create tap in event
    TapInEvent tapInEvent = new TapInEvent(station, date);

    // create new trip, if there isn't a currently active trip
    if (this.activeTrip == null) {
      this.activeTrip = new Trip();
      StatisticsManager.addTrip(this.activeTrip);
      this.trips.add(this.activeTrip);
    }

    // register the tap to the active trip
    double price = 0;
    try {
      price = activeTrip.registerTapInEvent(tapInEvent);
    } catch (UnnaturalTapSequenceException e) {
      // this tap took a place at a nonsensical location
      addInvalidTap(tapInEvent);
      throw new IllegalTapLocationException();
    } catch (TripInvalidTapEventException f) {
      // this tap is not contiguous (in time and space) to the previous tap
      this.activeTrip = null;
      tapIn(station, date);
    }

    // charge the card the price of this tap
    balance -= price;
  }

  public void tapOut(Station station, Date date)
      throws TapDeactivatedCardException, IllegalTapLocationException {

    // check if card is active
    if (!this.isActive) throw new TapDeactivatedCardException();

    // create tap out event
    TapOutEvent tapOutEvent = new TapOutEvent(station, date);

    // check if there is a currently active trip
    if (this.activeTrip == null) {
      // cannot tap out without a currently active trip
      addInvalidTap(tapOutEvent);
      throw new IllegalTapLocationException();
    }

    // register tap out event with current trip
    double price;
    try {
      price = activeTrip.registerTapOutEvent(tapOutEvent);
    } catch (UnnaturalTapSequenceException e) {
      addInvalidTap(tapOutEvent);
      throw new IllegalTapLocationException();
    }

    // charge the card the price of this tap
    balance -= price;
  }

  public ArrayList<Trip> getRecentTrips() {
    // The following line of code is from https://stackoverflow.com/a/44525425/3200577
    // (User: Stimpson Cat)
    trips.sort(Comparator.comparing(o -> o.getStartDate()));
    if (trips.size() <= 3) return trips;
    else return new ArrayList<>(trips.subList(trips.size() - 3, trips.size()));
  }

  @Override
  public String toString() {
    String ret = "Card " + Integer.toString(this.cardId) + " ($" + Double.toString(this.balance);
    if (this.isActive) {
      ret += ")";
    } else {
      ret += ", deactivated)";
    }
    return ret;
  }
}
