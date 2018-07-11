///* Danya */
//
//public class CardTransaction implements Comparable<CardTransaction>{
//  private double amount;
//  private TransitDate date;
//  private Card card;
//  private CustomerAccount customer;
//
//  public CardTransaction(TransitDate date, Card card, double amount) {
//    this.date = date;
//    this.card = card;
//    this.amount = amount;
//    this.customer = card.getCurrentOwner();
//  }
//
//  public TransitDate getDate() {
//    return date;
//  }
//
//  @Override
//  public int compareTo(CardTransaction ct) {
//    if (getDate() == null || ct.getDate() == null)
//      return 0;
//    return getDate().compareTo(ct.getDate());
//  }
//}
