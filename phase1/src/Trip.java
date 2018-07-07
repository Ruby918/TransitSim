/* Brian */

import jdk.nashorn.internal.runtime.arrays.ArrayIndex;

import java.util.ArrayList;
import java.util.Date;

public class Trip {
  private final double MAX_CHARGE = 6;
  private double costSoFar;
  private TapInEvent startEvent;
  private TapEvent lastTapAdded;
  private ArrayList<TapEvent> tapInEvents = new ArrayList<>();
  private ArrayList<TapEvent> tapOutEvents = new ArrayList<>();

  public Trip(TapInEvent tapInEvent) {
    this.startEvent = tapInEvent;
    this.tapInEvents.add(tapInEvent);
    this.lastTapAdded = tapInEvent;
  }

  public Date getStartDate() {
    return this.startEvent.getDate();
  }

  public double registerTapInEvent(TapInEvent tapInEvent) throws TripInvalidTapEventException {
    // check if tap event is valid for this trip
    // given the start time and location of the last tap
    // if it isn't, throw error
    // return the price of this tap
    if (!isTapInEventValid(tapInEvent)) {
      throw new TripInvalidTapEventException();
    } else if (tapInEvent.getStation() instanceof BusStation) {
      double maxChargeAmount = MAX_CHARGE - costSoFar;
      double chargeAmount;
      if (maxChargeAmount > tapInEvent.getStation().tapInPrice) {
        chargeAmount = tapInEvent.getStation().tapInPrice;
      } else chargeAmount = maxChargeAmount;
      costSoFar += chargeAmount;
      return chargeAmount;
    }
    return 0;
  }

  public double registerTapOutEvent(TapOutEvent tapOutEvent) throws TripInvalidTapEventException {
    // check if tap event is valid for this trip
    // given the start time and location of the last tap
    // if it isn't, throw error
    // return the price of this tap
    // if tapOutEvent is past 2hrs. from first tapInEvent 2hrs = 2*60*60*1000 ms.
    // isTapInEventInSameTrip method
    if (!isTapOutEventValid(tapOutEvent)) {
      throw new TripInvalidTapEventException();
    } else if (tapOutEvent.getStation() instanceof SubwayStation) {
      //if last tap wasn't also a subway station tap
      if (!(lastTapAdded.getStation() instanceof SubwayStation)){
        throw new TripInvalidTapEventException();
      }
      else{
        int distance = tapOutEvent.getStation().

      }
    }

    return 0;
  }

  private boolean isTapInEventValid(TapInEvent tapInEvent) {
    // if tapInEvent is past 2hrs. from first tapInEvent 2hrs = 2*60*60*1000 ms
    if (tapInEvent.getDate().getTime() - startEvent.getDate().getTime() > 7200000) {
      return false;
    }
    if (!tapInEvent.getStation().isAdjacentToStation(lastTapAdded.getStation())) {
      return false;
    }
    // 2 tapInEvents in a row
    if (lastTapAdded instanceof TapInEvent) {
      return false;
    }
    return true;
  }

  private boolean isTapOutEventValid(TapOutEvent tapOutEvent) {
    // 2 tapOutEvents in a row
    if (lastTapAdded instanceof TapOutEvent) {
      return false;
    }
    return true;
  }
}
