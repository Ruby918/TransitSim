/* Brian */

import jdk.nashorn.internal.runtime.arrays.ArrayIndex;

import java.util.ArrayList;
import java.util.Date;

public class Trip {
  private static final double MAX_CHARGE = 6;
  private double costSoFar;
  private TapInEvent startEvent;
  private TapEvent lastTapAdded;
  private ArrayList<TapEvent> tapEvents = new ArrayList<>();

  public Trip(TapInEvent tapInEvent) {
    this.startEvent = tapInEvent;
    this.tapEvents.add(tapInEvent);
    this.lastTapAdded = tapInEvent;
  }

  public Date getStartDate() {
    return this.startEvent.getDate();
  }

  public double registerTapInEvent(TapInEvent tapInEvent) throws TripInvalidTapEventException, UnnaturalTapSequenceException {
    // check if tap event is valid for this trip
    // given the start time and location of the last tap
    // if it isn't, throw error
    // return the price of this tap
    if (isTapInEventValid(tapInEvent)){
      tapEvents.add(tapInEvent);
      lastTapAdded = tapInEvent;
      if (tapInEvent.getStation() instanceof BusStation) {
      double maxChargeAmount = MAX_CHARGE - costSoFar;
      double chargeAmount;
      if (maxChargeAmount > tapInEvent.getStation().tapInPrice) {
        chargeAmount = tapInEvent.getStation().tapInPrice;
      } else chargeAmount = maxChargeAmount;
      costSoFar += chargeAmount;
      return chargeAmount;
    }
    //tap in was at a subway station
    return 0;
  }
  //unreachable statement, since when the outer if is false, it will always throw an exception.
  return -1000;
  }

  public double registerTapOutEvent(TapOutEvent tapOutEvent) throws UnnaturalTapSequenceException {
    // check if tap event is valid for this trip
    // given the start time and location of the last tap
    // if it isn't, throw error
    // return the price of this tap
    // if tapOutEvent is past 2hrs. from first tapInEvent 2hrs = 2*60*60*1000 ms.
    // isTapInEventInSameTrip method
    if (isTapOutEventValid(tapOutEvent)){
      tapEvents.add(tapOutEvent);
      lastTapAdded = tapOutEvent;
      if (tapOutEvent.getStation() instanceof SubwayStation) {
      {
        //int distance = tapOutEvent.getStation().
        return 0;
      }
    }
    return 0;
  }
    //unreachable statement, since when the outer if is false, it will always throw an exception.
  return -1000;
  }

  private boolean isTapInEventValid(TapInEvent tapInEvent) throws TripInvalidTapEventException, UnnaturalTapSequenceException{
    // if tapInEvent is past 2hrs. from first tapInEvent 2hrs = 2*60*60*1000 ms
    if (tapInEvent.getDate().getTime() - startEvent.getDate().getTime() > 7200000) {
      throw new TripInvalidTapEventException();
    }
    if (!tapInEvent.getStation().isAdjacentToStation(lastTapAdded.getStation())) {
      throw new TripInvalidTapEventException();
    }
    // 2 tapInEvents in a row
    if (lastTapAdded instanceof TapInEvent) {
      throw new UnnaturalTapSequenceException();
    }
    return true;
  }

  private boolean isTapOutEventValid(TapOutEvent tapOutEvent) throws UnnaturalTapSequenceException{
    // 2 tapOutEvents in a row
    if (lastTapAdded instanceof TapOutEvent) {
      throw new UnnaturalTapSequenceException();
    }
    //if last tap in wasn't at same route as tap out route
    if (!(lastTapAdded.getStation().getRoute()==tapOutEvent.getStation().getRoute())){
      throw new UnnaturalTapSequenceException();
    }
    return true;
  }
}
