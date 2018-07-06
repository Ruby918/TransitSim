/* Brian */

import java.util.ArrayList;
import java.util.Date;

public class Trip {

    private TapInEvent startEvent;
    private ArrayList<TapEvent> events = new ArrayList<>();

    public Trip(TapInEvent tapInEvent) {
        this.startEvent = tapInEvent;
        this.events.add(tapInEvent);
    }

    public Date getStartDate() {
        return this.startEvent.getDate();
    }

    private boolean isTapInEventValid(TapInEvent tapInEvent) {
        return true;
    }

    private boolean isTapOutEventValid(TapOutEvent tapOutEvent) {
        return true;
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

}
