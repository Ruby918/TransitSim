/* Brian */

import java.util.Date;

abstract public class TapEvent {

    private Date date;
    private Station station;

    public TapEvent(Station station){
        this.station = station;
        this.date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public Station getStation() {
        return station;
    }
}
