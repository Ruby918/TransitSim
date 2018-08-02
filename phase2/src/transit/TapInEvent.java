package transit;/* Brian */

/**
 * A tap in at a <code>BusStation</code> or <code>SubwayStation</code>.
 */
public class TapInEvent extends TapEvent {

  public TapInEvent(Station station, TransitDate date, Card card) {
    super(station, date, card);
  }

  @Override
  public String toString() {
    return "Tap In at " + getStation().toString()
        + " (" + getTransitDate().toDateTimeString() + ")";
  }
}
