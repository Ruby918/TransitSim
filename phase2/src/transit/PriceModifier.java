package transit;

import java.util.ArrayList;

public abstract class PriceModifier implements PriceModifiable, Expirable {
  private TransitDate startDate;
  private TransitDate endDate;
  private ArrayList<Transaction> transactionsAppliedTo;
  private String name;
  int numberOfTimesUsed;
  // maximum number of times the price modifier can be used; -1 if there is no limit;
  private final int USAGE_LIMIT;

  public PriceModifier(TransitDate startDate, TransitDate endDate, int USAGE_LIMIT, String name) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.USAGE_LIMIT = USAGE_LIMIT;
    numberOfTimesUsed = 0;
    this.name = name;
  }

  @Override
  public abstract double modifyPrice(double rawPrice);

  @Override
  public boolean isValid(TransitDate dateUsed) {
    return (USAGE_LIMIT == -1 || dateUsed.compareTo(startDate) > 0
        & dateUsed.compareTo(endDate) <= 0
        & numberOfTimesUsed < USAGE_LIMIT);
  }

  public ArrayList<Transaction> getTransactionsAppliedTo() {
    return transactionsAppliedTo;
  }

  @Override
  public String toString(){
    String ret = name;
    ret += '\n'+ "Start date: " + startDate;
    ret +='\n' + "Expiry date:" + endDate;
    ret +='\n' + "Number of Times Used" + numberOfTimesUsed;
    ret +='\n'+ "Usage Limit" + USAGE_LIMIT;
    StringBuilder rett = new StringBuilder(ret);
    for (Transaction transaction: transactionsAppliedTo){
      rett.append('\n');
      rett.append(transaction.toString());
    }
    return rett.toString();
  }
}
