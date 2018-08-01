import java.util.Date;

public class PriceModifierOntarioTax extends PriceModifier {
  private final double TAX_MULTIPLIER = 1.15;

  public PriceModifierOntarioTax(TransitDate date) {
    super(date, date.addTime(Integer.MAX_VALUE), Integer.MAX_VALUE, "Ontario Tax");
  }

  @Override
  public double modifyPrice(double rawPrice) {
    if (isValid(new TransitDate(new Date()))) {
      numberOfTimesUsed++;
      rawPrice = rawPrice * TAX_MULTIPLIER;
    }
    return rawPrice;
  }
}
