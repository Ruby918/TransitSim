package transit;/* Brian */

import java.io.Serializable;
import java.util.ArrayList;
import util.FormattedDate;

/**
 * A Tap at a <code>BusStation</code> or <code>SubwayStation</code>.
 */
public abstract class TapEvent implements Serializable {

  private FormattedDate date;
  private Station station;
  private Card card;
  private boolean flagged = false;

  protected ArrayList<TapEvent> unnaturalTapEvents = new ArrayList<>();

  public TapEvent(Station station, FormattedDate date, Card card) {
    this.station = station;
    this.date = date;
    this.card = card;
  }

  /**
   * Returns the <code>Date</code> this <code>TapEvent</code> was made.
   *
   * @return the <code>Date</code> this <code>TapEvent</code> was made.
   */
  public FormattedDate getTransitDate() {
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
