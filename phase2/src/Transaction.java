import java.util.ArrayList;

public class Transaction {

  private final Card card;
  private final Price price;
  private final TransitDate date;
  static ArrayList<Transaction> transactions = new ArrayList<>();

  public Transaction(Card card, Price price, TransitDate date) {
    this.card = card;
    this.date = date;
    this.price = price;
    card.removeFunds(price.getFinalPrice());
    transactions.add(this);
  }

  public TransitDate getDate() {
    return this.date;
  }
}
