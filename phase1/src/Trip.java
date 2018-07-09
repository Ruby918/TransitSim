/* Brian */

import jdk.nashorn.internal.runtime.arrays.ArrayIndex;

import java.util.ArrayList;
import java.util.Date;
import java.lang.Math;
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

  public Date getStartDate() {
    return this.startEvent.getDate();
  }

  public ArrayList<TapEvent> gettapEvents() {
      return this.tapEvents;
  }

  public double getCostSoFar() {
    return this.costSoFar;
  }

  public double registerTapInEvent(TapInEvent tapInEvent)
      throws TripInvalidTapEventException, UnnaturalTapSequenceException {
    // check if tap event is valid for this trip
    // given the start time and location of the last tap
    // if it isn't, throw error
    // return the price of this tap
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
        double chargeAmount = Math.min(tapInEvent.getStation().tapInPrice, maxChargeAmount);
        costSoFar += chargeAmount;
        return chargeAmount;
      }
      // tap in was at a subway station
      return 0;
    }
  }

  public double registerTapOutEvent(TapOutEvent tapOutEvent)
      throws TripInvalidTapEventException, UnnaturalTapSequenceException {
    // check if tap event is valid for this trip
    // given the start time and location of the last tap
    // if it isn't, throw error
    // return the price of this tap
    // if tapOutEvent is past 2hrs. from first tapInEvent 2hrs = 2*60*60*1000 ms.
    // isTapInEventInSameTrip method
    if (!isTapOutEventLegal(tapOutEvent)) {
      throw new UnnaturalTapSequenceException();
    }
    if (!canAddTapOutToCurrentTrip(tapOutEvent)) {
      throw new TripInvalidTapEventException();
    } else {
      tapEvents.add(tapOutEvent);
      lastTapAdded = tapOutEvent;
      if (tapOutEvent.getStation() instanceof SubwayStation) {
        {
            Station outStation = tapOutEvent.getStation();
          int routeLength = tapOutEvent.getStation().getRoute().getRouteLength(outStation, lastTapAdded.getStation());
          return routeLength*outStation.passThroughPrice;
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

  private boolean canAddTapOutToCurrentTrip(TapOutEvent tapOutEvent) {
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
}
