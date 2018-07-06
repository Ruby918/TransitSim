/* Brian */

import java.util.ArrayList;
import java.util.Date;

public class Trip {

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
    return 0;
  }

  public double registerTapOutEvent(TapOutEvent tapOutEvent) throws TripInvalidTapEventException {
    // check if tap event is valid for this trip
    // given the start time and location of the last tap
    // if it isn't, throw error
    // return the price of this tap
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
      // if tapOutEvent is past 2hrs. from first tapInEvent 2hrs = 2*60*60*1000 ms. isTapInEventInSameTrip method
      if (tapOutEvent.getDate().getTime() - startEvent.getDate().getTime() > 7200000) {
          return false;
      }
      // 2 tapOutEvents in a row
    if (lastTapAdded instanceof TapOutEvent) {
      return false;
    }
    return true;
  }
}
