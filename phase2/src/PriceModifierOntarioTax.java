import java.util.Date;

public class PriceModifierOntarioTax extends PriceModifier {
  public static final double TAX_MULTIPLIER = 1.15;
  public static final TransitDate START_DATE = new TransitDate(new Date());
  public static final TransitDate END_DATE = new TransitDate(new Date(Long.MAX_VALUE));
  public static final String NAME = "Ontario Tax";
  public static final int USAGE_LIMIT = Integer.MAX_VALUE;

  public PriceModifierOntarioTax() {
    super(START_DATE, END_DATE, USAGE_LIMIT, NAME);
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
