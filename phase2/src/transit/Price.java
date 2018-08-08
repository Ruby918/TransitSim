package transit;/* brian */

import java.io.Serializable;
import util.FormattedDate;

public class Price implements Serializable {

  private double rawPrice;
  private double finalPrice;
  private double maxFinalPrice;
  private PriceModifier priceModifier;
  private final PriceModifier ONTARIO_TAX;
  private boolean taxOn;

  private boolean hasFinalPrice = false;

  public Price() {
    this.rawPrice = 0;
    ONTARIO_TAX = new PriceModifierOntarioTax();
    priceModifier = null;
    maxFinalPrice = Double.MAX_VALUE;
    taxOn = false;
  }

  public Price(double rawPrice) {
    this.rawPrice = rawPrice;
    ONTARIO_TAX = new PriceModifierOntarioTax();
    priceModifier = null;
    maxFinalPrice = Double.MAX_VALUE;
    taxOn = false;
  }

  public Price(FormattedDate today) {
    ONTARIO_TAX = new PriceModifierOntarioTax();
    priceModifier = null;
    maxFinalPrice = Double.MAX_VALUE;
    taxOn = false;
  }

  public Price(FormattedDate today, double rawPrice) {
    this.rawPrice = rawPrice;
    ONTARIO_TAX = new PriceModifierOntarioTax();
    priceModifier = null;
    maxFinalPrice = Double.MAX_VALUE;
    taxOn = false;
  }

  public Price(
      FormattedDate today, double rawPrice, PriceModifier priceModifier, double maxFinalPrice) {
    this.rawPrice = rawPrice;
    ONTARIO_TAX = new PriceModifierOntarioTax();
    this.priceModifier = priceModifier;
    this.maxFinalPrice = maxFinalPrice;
    taxOn = false;
  }
  public Price(
          FormattedDate today, double rawPrice, PriceModifier priceModifier, double maxFinalPrice, boolean taxOn) {
    this.rawPrice = rawPrice;
    ONTARIO_TAX = new PriceModifierOntarioTax();
    this.priceModifier = priceModifier;
    this.maxFinalPrice = maxFinalPrice;
    this.taxOn = taxOn;
  }

  /**
   * Sets the final price of this Price by applying the price modifiers. The final price will
   * adjusted downward to be the maxFinalPrice if need be.
   *
   * @param date the date the price modifiers are applied
   */
  public void applyPriceModifiers(FormattedDate date) {
    if (rawPrice != 0) {
      finalPrice = rawPrice;
      if (priceModifier != null) {
        if (priceModifier.isValid(date)) {
          finalPrice = priceModifier.modifyPrice(finalPrice, date);
        }
      }
      // apply ontario tax
      if (taxOn & ONTARIO_TAX.isValid(date)) {
        finalPrice = ONTARIO_TAX.modifyPrice(finalPrice, date);
      }
      finalPrice = Math.min(finalPrice, maxFinalPrice);
      hasFinalPrice = true;
    }
  }

  public double getFinalPrice() {
    return finalPrice;
  }

  public boolean hasFinalPrice() {
    return hasFinalPrice;
  }

  public void setFinalPrice(double finalPrice) {
    this.finalPrice = finalPrice;
    hasFinalPrice = true;
  }

  public double getRawPrice() {
    return rawPrice;
  }

  /** @param priceModifier */
  public void setPriceModifier(PriceModifier priceModifier) {
    this.priceModifier = priceModifier;
  }
}
