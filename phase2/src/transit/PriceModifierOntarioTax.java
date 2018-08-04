package transit;

import java.util.Date;

public class PriceModifierOntarioTax extends PriceModifier {
  private final double TAX_MULTIPLIER = 1.15;

  public PriceModifierOntarioTax() {
    super(new TransitDate(new Date(0)), new TransitDate(new Date(0)).addTime(Integer.MAX_VALUE), Integer.MAX_VALUE, "Ontario Tax");
  }
  public Price

  @Override
  public double modifyPrice(double rawPrice) {
    if (isValid(new TransitDate(new Date()))) {
      numberOfTimesUsed++;
      rawPrice = rawPrice * TAX_MULTIPLIER;
    }
    return rawPrice;
  }
}
