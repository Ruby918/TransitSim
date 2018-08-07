package transit;

import java.io.Serializable;
import util.FormattedDate;

public class Transaction implements Serializable {

  private final Card card;
  private final Price price;
  private final FormattedDate date;

  public Transaction(Card card, Price price, FormattedDate date) {
    this.card = card;
    this.date = date;
    this.price = price;
    card.removeFunds(price.getFinalPrice());
  }

  public FormattedDate getDate() {
    return this.date;
  }

  public double getAmount() {
    return this.price.getFinalPrice();
  }
}
