package transit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Price implements Serializable {
    private double rawPrice;
    private double finalPrice;
    private ArrayList<PriceModifier> priceModifiers = new ArrayList<>();
    private final PriceModifier ONTARIO_TAX;

    public Price() {
        this.rawPrice = 0;
        finalPrice = -1;
        ONTARIO_TAX = new PriceModifierOntarioTax(new TransitDate(new Date()));
    }

    public Price(double rawPrice) {
        this.rawPrice = rawPrice;
        finalPrice = -1;
        ONTARIO_TAX = new PriceModifierOntarioTax(new TransitDate(new Date()));
    }

    public Price(TransitDate today){
        rawPrice = -1;
        finalPrice = -1;
        ONTARIO_TAX = new PriceModifierOntarioTax(today);
    }

    public Price(TransitDate today, double rawPrice){
        this.rawPrice = rawPrice;
        finalPrice = -1;
        ONTARIO_TAX = new PriceModifierOntarioTax(today);
    }
    public void applyPriceModifiers(TransitDate date){
        finalPrice = rawPrice;
        for (PriceModifier priceModifier: priceModifiers){
            if (priceModifier.isValid(date))
            finalPrice = priceModifier.modifyPrice(finalPrice);
        }
        //apply ontario tax
        if (ONTARIO_TAX.isValid(date)){
            finalPrice = ONTARIO_TAX.modifyPrice(finalPrice);
        }

    }

    public double getFinalPrice() {
        return finalPrice;
    }
    public void setFinalPrice( double finalPrice){
        this.finalPrice = finalPrice;
    }

    /**
     *
     * @param priceModifier
     */
    public void addPriceModifier(PriceModifier priceModifier){
        priceModifiers.add(priceModifier);
    }
}
