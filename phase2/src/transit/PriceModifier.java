package transit;

import java.util.ArrayList;

public abstract class PriceModifier implements PriceModifiable, Expirable {
  private TransitDate startDate;
  private TransitDate endDate;
  private String name;
  int numberOfTimesUsed;
  // maximum number of times the price modifier can be used; -1 if there is no limit;
  private final int USAGE_LIMIT;
  private ArrayList<TransitDate> datesUsed;

  public PriceModifier(TransitDate startDate, TransitDate endDate, int USAGE_LIMIT, String name) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.USAGE_LIMIT = USAGE_LIMIT;
    numberOfTimesUsed = 0;
    this.name = name;
    datesUsed = new ArrayList();
  }

  public ArrayList<TransitDate> getDatesUsed() {
    return datesUsed;
  }

  public void addDateUsed(TransitDate dateUsed){
    this.datesUsed.add(dateUsed);
  }
  @Override
  public abstract double modifyPrice(double rawPrice, TransitDate dateUsed);

  @Override
  public boolean isValid(TransitDate dateUsed) {
    return (USAGE_LIMIT == -1 || dateUsed.compareTo(startDate) > 0
        & dateUsed.compareTo(endDate) <= 0
        & numberOfTimesUsed < USAGE_LIMIT);
  }

  @Override
  public String toString(){
    String ret = name;
    ret += '\n'+ "Start date: " + startDate;
    ret +='\n' + "Expiry date:" + endDate;
    ret +='\n' + "Number of Times Used" + numberOfTimesUsed;
    ret +='\n'+ "Usage Limit" + USAGE_LIMIT;
    return ret.toString();
  }
}
