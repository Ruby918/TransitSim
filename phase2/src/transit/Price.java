package transit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Price implements Serializable {
  private double rawPrice;
  private double finalPrice;
  private double maxFinalPrice;
  private ArrayList<PriceModifier> priceModifiers;
  private final PriceModifier ONTARIO_TAX;

  public Price() {
    this.rawPrice = 0;
    finalPrice = -1;
    ONTARIO_TAX = new PriceModifierOntarioTax();
    priceModifiers = null;
    maxFinalPrice = Double.MAX_VALUE;
  }

  public Price(double rawPrice) {
    this.rawPrice = rawPrice;
    finalPrice = -1;
    ONTARIO_TAX = new PriceModifierOntarioTax();
    priceModifiers = null;
    maxFinalPrice = Double.MAX_VALUE;
  }

  public Price(TransitDate today) {
    rawPrice = -1;
    finalPrice = -1;
    ONTARIO_TAX = new PriceModifierOntarioTax();
    priceModifiers = null;
    maxFinalPrice = Double.MAX_VALUE;
  }

  public Price(TransitDate today, double rawPrice) {
    this.rawPrice = rawPrice;
    finalPrice = -1;
    ONTARIO_TAX = new PriceModifierOntarioTax();
    priceModifiers = null;
    maxFinalPrice = Double.MAX_VALUE;
  }

  public Price(
      TransitDate today,
      double rawPrice,
      ArrayList<PriceModifier> priceModifiers,
      double maxFinalPrice) {
    this.rawPrice = rawPrice;
    finalPrice = -1;
    ONTARIO_TAX = new PriceModifierOntarioTax();
    this.priceModifiers = priceModifiers;
    this.maxFinalPrice = maxFinalPrice;
  }

  /**
   * Sets the final price of this Price by applying the price modifiers. The final price will
   * adjusted downward to be the maxFinalPrice if need be.
   *
   * @param date the date the price modifiers are applied
   */
  public void applyPriceModifiers(TransitDate date) {
    finalPrice = rawPrice;
    if (priceModifiers != null) {
      for (PriceModifier priceModifier : priceModifiers) {
        if (priceModifier.isValid(date)) finalPrice = priceModifier.modifyPrice(finalPrice);
      }
      // apply ontario tax
      if (ONTARIO_TAX.isValid(date)) {
        finalPrice = ONTARIO_TAX.modifyPrice(finalPrice);
      }
      finalPrice = Math.max(finalPrice, maxFinalPrice);
    }
  }

  public double getFinalPrice() {
    return finalPrice;
  }

  public void setFinalPrice(double finalPrice) {
    this.finalPrice = finalPrice;
  }

  /** @param priceModifiers */
  public void setPriceModifiers(ArrayList<PriceModifier> priceModifiers) {
    this.priceModifiers = priceModifiers;
  }
}
