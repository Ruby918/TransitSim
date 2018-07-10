/* Brian */

import java.util.ArrayList;
import java.util.Date;
import java.lang.Math;

/**
 * A transit journey consisting of bus and subway rides.
 */
public class Trip {
  protected static final double MAX_CHARGE = 6;
  private double costSoFar;
  private TapInEvent startEvent;
  private TapEvent lastTapAdded;
  private ArrayList<TapEvent> tapEvents = new ArrayList<>();

  public Trip(TapInEvent tapInEvent) {
    this.startEvent = tapInEvent;
    this.tapEvents.add(tapInEvent);
    this.lastTapAdded = tapInEvent;
  }

  /**
   * Returns the <code>Date</code> of the first tap in of this <code>Trip</code>.
   *
   * @return the starting date of the first tap in
   */
  public Date getStartDate() {
    return this.startEvent.getDate();
  }

  /**
   * Returns the <code>Date</code> of the last tap of this <code>Trip</code>.
   *
   * @return the date of the last tap
   */
  public Date getEndDate() {
      return this.lastTapAdded.getDate();
  }

  /**
   * Returns an <code>ArrayList</code> of <code>TapEvent</code> that this <code>Trip</code> has.
   *
   * @return the list of tap events of this <code>Trip</code>
   */
  public ArrayList<TapEvent> getTapEvents() {
      return this.tapEvents;
  }

  /**
   * Returns the current cumulative charge for this <code>Trip</code>.
   *
   * @return the current cumulative charge for this <code>Trip</code>
   */
  public double getCostSoFar() {
    return this.costSoFar;
  }

  /**
   * Checks if the given <code>TapInEvent</code> is legal and if it is the start of a new <code>Trip</code>.
   * If it is the start of a new <code>Trip</code>,bit will throw a new <code>TripInvalidTapEventException</code>.
   * If it is not legal, it will throw a new <code>UnnaturalTapSequenceException</code>.
   * Else, it documents the given <code>TapInEvent</code>, and returns the charge for the tap.
   *
   *
   * @param tapInEvent the tap event to be registered
   * @return the charge of the tap
   * @throws TripInvalidTapEventException if the tap is the start of a new trip
   * @throws UnnaturalTapSequenceException if the tap is illegal
   */
  public double registerTapInEvent(TapInEvent tapInEvent)
      throws TripInvalidTapEventException, UnnaturalTapSequenceException {
    // check if tap event is valid for this trip
    // given the start time and location of the last tap
    // if it isn't, throw error
    // return the price of this tap
    if (startEvent == tapInEvent & tapInEvent.getStation() instanceof BusStation){
      double chargeAmount = BusStation.tapInPrice;
      costSoFar += chargeAmount;
      return chargeAmount;
    }
    if (!isTapInEventLegal(tapInEvent)) {
      throw new UnnaturalTapSequenceException();
    }
    if (!canAddTapInToCurrentTrip(tapInEvent)) {
      throw new TripInvalidTapEventException();
    } else {
      tapEvents.add(tapInEvent);
      lastTapAdded = tapInEvent;
      if (tapInEvent.getStation() instanceof BusStation) {
        double maxChargeAmount = MAX_CHARGE - costSoFar;
        double chargeAmount = Math.min(BusStation.tapInPrice, maxChargeAmount);
        costSoFar += chargeAmount;
        return chargeAmount;
      }
      // tap in was at a subway station
      return 0;
    }
  }

  /**
   * Checks if the given <code>TapOutEvent</code> is legal.
   * If it is not legal, it will throw a new <code> UnnaturalTapSequenceException</code>.
   * Else, it documents the given <code>TapInEvent</code>, and returns the charge for the tap.
   *
   * @param tapOutEvent the tap event to be registered
   * @return the charge for the tap
   * @throws UnnaturalTapSequenceException if the tap is illegal
   */
  public double registerTapOutEvent(TapOutEvent tapOutEvent)
      throws UnnaturalTapSequenceException {
    // check if tap event is valid for this trip
    // given the start time and location of the last tap
    // if it isn't, throw error
    // return the price of this tap
    // if tapOutEvent is past 2hrs. from first tapInEvent 2hrs = 2*60*60*1000 ms.
    // isTapInEventInSameTrip method
    if (!isTapOutEventLegal(tapOutEvent)) {
      throw new UnnaturalTapSequenceException();
    } else {
      tapEvents.add(tapOutEvent);
      lastTapAdded = tapOutEvent;
      if (tapOutEvent.getStation() instanceof SubwayStation) {
        {
            Station outStation = tapOutEvent.getStation();
          int routeLength = tapOutEvent.getStation().getRoute().getRouteLength(outStation, lastTapAdded.getStation());
          return routeLength*SubwayStation.passThroughPrice;
        }
      }
      return 0;
    }
  }

  private boolean canAddTapInToCurrentTrip(TapInEvent tapInEvent) {
    // if tapInEvent is past 2hrs. from first tapInEvent 2hrs = 2*60*60*1000 ms
    if (tapInEvent.getDate().getTime() - startEvent.getDate().getTime() > 7200000) {
      return false;
    }
    if (!tapInEvent.getStation().isAdjacentToStation(lastTapAdded.getStation())) {
      return false;
    }
    return true;
  }

  private boolean isTapInEventLegal(TapInEvent tapInEvent) {
    // 2 tapInEvents in a row
    if (lastTapAdded instanceof TapInEvent) {
      return false;
    }
    return true;
  }

  private boolean isTapOutEventLegal(TapOutEvent tapOutEvent) {
    // 2 tapOutEvents in a row
    if (lastTapAdded instanceof TapOutEvent) {
      return false;
    }
    // if last tap in wasn't at same route as tap out route
    if (!(lastTapAdded.getStation().getRoute() == tapOutEvent.getStation().getRoute())) {
      return false;
    }
    return true;
  }

  /**
   * Returns a <code>String</code> representation of this <code>Trip</code>.
   *
   * @return a <code>String</code> representation of this <code>Trip</code>
   */
  @Override
  public String toString(){
      StringBuilder ret = new StringBuilder("Start Date: " + this.getStartDate() +
              ". End Date: " + this.getEndDate() +
              "Current Cost of Trip: " + this.getCostSoFar());
      ret.append("Tap Log: ");
      for(TapEvent tapEvent: tapEvents){
          if (tapEvent instanceof TapInEvent)
              ret.append("Tap In at ");
          else
              ret.append("Tap Out at ");
          ret.append(tapEvent.getStation().getName() + ", ");
      }
      String rett = ret.toString();
      rett = rett.substring(0,ret.length()-2);
      rett += ".";
      return rett;
  }
}
