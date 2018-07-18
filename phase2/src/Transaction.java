import java.util.ArrayList;

public class Transaction {

  private final Card card;
  private final double amount;
  private final TransitDate date;

  static ArrayList<Transaction> transactions = new ArrayList<>();

  public Transaction(Card card, double amount, TransitDate date) {
    this.card = card;
    this.date = date;
    this.amount = amount;
    transactions.add(this);
  }

  public double getAmount() {
    return this.amount;
  }

  public TransitDate getDate() {
    return this.date;
  }
}
