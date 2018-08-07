package transit;

import java.io.Serializable;
import util.FormattedDate;

public class Price implements Serializable {
  private double rawPrice;
  private double finalPrice;
  private double maxFinalPrice;
  private PriceModifier priceModifier;
  private final PriceModifier ONTARIO_TAX;

  public Price() {
    this.rawPrice = 0;
    finalPrice = -1;
    ONTARIO_TAX = new PriceModifierOntarioTax();
    priceModifier = null;
    maxFinalPrice = Double.MAX_VALUE;
  }

  public Price(double rawPrice) {
    this.rawPrice = rawPrice;
    finalPrice = -1;
    ONTARIO_TAX = new PriceModifierOntarioTax();
    priceModifier = null;
    maxFinalPrice = Double.MAX_VALUE;
  }

  public Price(FormattedDate today) {
    rawPrice = -1;
    finalPrice = -1;
    ONTARIO_TAX = new PriceModifierOntarioTax();
    priceModifier = null;
    maxFinalPrice = Double.MAX_VALUE;
  }

  public Price(FormattedDate today, double rawPrice) {
    this.rawPrice = rawPrice;
    finalPrice = -1;
    ONTARIO_TAX = new PriceModifierOntarioTax();
    priceModifier = null;
    maxFinalPrice = Double.MAX_VALUE;
  }

  public Price(
      FormattedDate today, double rawPrice, PriceModifier priceModifier, double maxFinalPrice) {
    this.rawPrice = rawPrice;
    finalPrice = -1;
    ONTARIO_TAX = new PriceModifierOntarioTax();
    this.priceModifier = priceModifier;
    this.maxFinalPrice = maxFinalPrice;
  }

  /**
   * Sets the final price of this Price by applying the price modifiers. The final price will
   * adjusted downward to be the maxFinalPrice if need be.
   *
   * @param date the date the price modifiers are applied
   */
  public void applyPriceModifiers(FormattedDate date) {
    finalPrice = rawPrice;
    if (priceModifier != null) {
      if (priceModifier.isValid(date)) finalPrice = priceModifier.modifyPrice(finalPrice, date);
    }
    // apply ontario tax
    if (ONTARIO_TAX.isValid(date)) {
      finalPrice = ONTARIO_TAX.modifyPrice(finalPrice, date);
    }
    finalPrice = Math.min(finalPrice, maxFinalPrice);
  }

  public double getFinalPrice() {
    return finalPrice;
  }

  public void setFinalPrice(double finalPrice) {
    this.finalPrice = finalPrice;
  }

  public double getRawPrice() {
    return rawPrice;
  }

  /** @param priceModifier */
  public void setPriceModifier(PriceModifier priceModifier) {
    this.priceModifier = priceModifier;
  }
}
