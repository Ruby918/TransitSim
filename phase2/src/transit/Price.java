package transit;

import java.io.Serializable;
import java.util.ArrayList;

public class Price implements Serializable {
    private double rawPrice;
    private double finalPrice;
    private ArrayList<PriceModifier> priceModifiers = new ArrayList<>();
//    private final PriceModifier ONTARIO_TAX;

    public Price(){
        rawPrice = -1;
        finalPrice = -1;
//        ONTARIO_TAX = new PriceModifierOntarioTax(new TransitDate(new Date));
    }

    public Price(double rawPrice){
        this.rawPrice = rawPrice;
        finalPrice = -1;
    }
    public void applyPriceModifiers(TransitDate date){
        finalPrice = rawPrice;
        for (PriceModifier priceModifier: priceModifiers){
            if (priceModifier.isValid(date))
            finalPrice = priceModifier.modifyPrice(finalPrice);
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
