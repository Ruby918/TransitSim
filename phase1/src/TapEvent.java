/* Brian */

import java.util.Date;

/**
 * A Tap at a <code>BusStation</code> or <code>SubwayStation</code>
 */
abstract public class TapEvent {

    private Date date;
    private Station station;

    public TapEvent(Station station){
        this.station = station;
        this.date = new Date();
    }

    /**
     * Returns the <code>Date</code> this <code>TapEvent</code> was made.
     *
     * @returnthe <code>Date</code> this <code>TapEvent</code> was made.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Returns the <code>Station</code> at which this <code>TapEvent</code> was made.
     *
     * @return the <code>Station</code> at which this <code>TapEvent</code> was made.
     */
    public Station getStation() {
        return station;
    }
}
