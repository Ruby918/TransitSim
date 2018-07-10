/* Dan */

import java.util.ArrayList;
import java.util.Date;

public class Card {
    private static final int MAX_CHARGE = 6;
    private double balance = 19;
    private ArrayList<Trip> trips = new ArrayList<>();
    private Trip activeTrip = null;
    private boolean isActive = true;
    private int cardId;

    public Card(int id) {
        this.cardId = id;
    }

    public int getCardId() {
        return cardId;
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

    public void tapIn(Station station, Date date)
            throws InsufficientFundsException, tapDeactivatedCardException {
        boolean tripExceptionRaised = false;

        // check if card is active
        if (!this.isActive) throw new tapDeactivatedCardException();

        TapInEvent tapInEvent = new TapInEvent(station, date);
        if (this.activeTrip == null) {
            this.activeTrip = new Trip(tapInEvent);
            StatisticsManager.addTrip(this.activeTrip);
            this.trips.add(this.activeTrip);
        }
        double price = 0;
        try {
            price = activeTrip.registerTapInEvent(tapInEvent);
        } catch (UnnaturalTapSequenceException e) {

            StatisticsManager.incrementUnnaturalTapSequenceInstances();
            StatisticsManager.addInvalidTapEvent(tapInEvent.getDate());
            this.balance -= MAX_CHARGE;
            tripExceptionRaised = true;
        } catch (TripInvalidTapEventException f) {
            this.activeTrip = null;
            tapIn(station, date);
            tripExceptionRaised = true;
        }
        if (!tripExceptionRaised & this.balance >= price) {
            this.balance -= price;
        } else {
            throw new InsufficientFundsException();
        }
    }

    public void tapOut(Station station, Date date)
            throws InsufficientFundsException, tapDeactivatedCardException {
        if (!this.isActive) throw new tapDeactivatedCardException();

        TapOutEvent tapOutEvent = new TapOutEvent(station, date);
        // check if active trip is null
        if (this.activeTrip != null) {
            double price = 0;
            boolean tripExceptionRaised = false;
            try {
                price = activeTrip.registerTapOutEvent(tapOutEvent);
            } catch (UnnaturalTapSequenceException e) {
                StatisticsManager.incrementUnnaturalTapSequenceInstances();
                StatisticsManager.addInvalidTapEvent(tapOutEvent.getDate());
                this.balance -= MAX_CHARGE;
                this.activeTrip = null;
                tripExceptionRaised = true;
            }
            if (!tripExceptionRaised & price > this.balance) {
                throw new InsufficientFundsException();
            } else {
                this.balance -= price;
            }
        }
    }

    @Override
    public String toString() {
        String ret = "Card " + Integer.toString(this.cardId) + " ($" + Double.toString(this.balance);
        if (this.isActive){
            ret += ")";
        }
        else{
            ret += ", deactivated)";
        }
        return ret;
    }
}