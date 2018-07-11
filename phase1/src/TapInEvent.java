/* Brian */

import java.util.Date;

/**
 * A tap in at a <code>BusStation</code> or <code>SubwayStation</code>
 */
public class TapInEvent extends TapEvent {

    public TapInEvent(Station station, TransitDate date) {
        super(station, date);
    }

    public TapInEvent(Station station) {
        super(station);
    }
}
