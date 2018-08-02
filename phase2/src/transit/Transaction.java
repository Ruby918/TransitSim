package transit;

import java.io.Serializable;

public class Transaction implements Serializable {

  private final Card card;
  private final Price price;
  private final TransitDate date;

  public Transaction(Card card, Price price, TransitDate date) {
    this.card = card;
    this.date = date;
    this.price = price;
    card.removeFunds(price.getFinalPrice());
  }

  public TransitDate getDate() {
    return this.date;
  }

  public double getAmount() {
    return this.price.getFinalPrice();
  }
}