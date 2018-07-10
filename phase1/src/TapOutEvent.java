/* Brian */

import java.util.Date;

/**
 * A tap out at a <code>BusStation</code> or <code>SubwayStation</code>
 */
public class TapOutEvent extends TapEvent {

    public TapOutEvent(Station station, Date date) {
        super(station, date);
    }

    public TapOutEvent(Station station) {
        super(station);
    }
}
