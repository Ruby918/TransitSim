/* Brian */

import java.util.ArrayList;

/**
 * A Tap at a <code>BusStation</code> or <code>SubwayStation</code>.
 */
public abstract class TapEvent {

  private TransitDate date;
  private Station station;
  private Card card;
  private boolean flagged = false;

  protected ArrayList<TapEvent> unnaturalTapEvents = new ArrayList<>();

  public TapEvent(Station station, TransitDate date, Card card) {
    this.station = station;
    this.date = date;
    this.card = card;
  }

  /**
   * Returns the <code>Date</code> this <code>TapEvent</code> was made.
   *
   * @return the <code>Date</code> this <code>TapEvent</code> was made.
   */
  public TransitDate getTransitDate() {
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

  public void flagAsUnnatural() {
    this.flagged = true;
    unnaturalTapEvents.add(this);
  }

  public boolean isFlagged() {
    return this.flagged;
  }

  public boolean hasCard(Card card) {
    return this.card.equals(card);
  }
}
