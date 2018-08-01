import java.util.ArrayList;

public class Price {
    private double rawPrice;
    private double finalPrice;
    private ArrayList<PriceModifier> priceModifiers = new ArrayList<>();

    public Price(){
        rawPrice = -1;
        finalPrice = -1;
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
    public void addPriceModifier(PriceModifier priceModifier){
        priceModifiers.add(priceModifier);
    }
}
