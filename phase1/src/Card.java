/* Dan */

import java.util.ArrayList;

public class Card {

  private double balance = 0;
  private ArrayList<Trip> trips = new ArrayList<>();
  private Trip activeTrip = null;
  private boolean isActive = true;

  public Card() {
    this.trips.add(this.activeTrip);
  }

  public void deactivate() {
    this.isActive = false;
  }

  public void reactivate() {
    this.isActive = true;
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

  public void tapIn(Station station) throws InsufficientFundsException, tapDeactivatedCardException {

    // check if card is active
    if (!this.isActive) throw new tapDeactivatedCardException();

    TapInEvent tapInEvent = new TapInEvent(station);
    if (this.activeTrip == null) {
      this.activeTrip = new Trip(tapInEvent);
      StatisticsManager.addTripEvent(this.activeTrip);
    } else {
        try {
          double price = 0;
          try {
            price = activeTrip.registerTapInEvent(tapInEvent);
          } catch (UnnaturalTapSequenceException e) {

            StatisticsManager.incrementUnnaturalTapSequenceInstances();
            StatisticsManager.addInvalidTapEvent(tapInEvent.getDate());
            this.balance -= 6;
            deactivate();
            e.printStackTrace();
          }
        if (this.balance >= price) {
          this.balance -= price;
          } else {

              this.deactivate();
              throw new InsufficientFundsException();
            }

        } catch (TripInvalidTapEventException e) {
          this.activeTrip = new Trip(tapInEvent);
          StatisticsManager.addTripEvent(this.activeTrip);
          this.trips.add(this.activeTrip); }
    }
  }

  public void tapOut(Station station) throws InsufficientFundsException, tapDeactivatedCardException{
    TapOutEvent tapOutEvent = new TapOutEvent(station);

    if (!this.isActive) throw new tapDeactivatedCardException();
    // check if active trip is null

    try {
      activeTrip.registerTapOutEvent(tapOutEvent);
    } catch (TripInvalidTapEventException e) {
      // pathological behaviour (cannot tap out when there is no active trip)
      // TODO
    }
  }
}
