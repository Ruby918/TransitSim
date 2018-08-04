package transit;

import java.util.ArrayList;
import java.util.Date;
/**
 * A PriceModifier that allows 1 free trip a day, for 30 days
 */
public class PriceModifierMonthlyPass extends PriceModifier {

    public PriceModifierMonthlyPass(TransitDate date) {
        super(date, date.addTime(30), Integer.MAX_VALUE, "Monthly Pass");
    }


    @Override
    public double modifyPrice(double rawPrice, TransitDate dateUsed) {
        if (isValid(new TransitDate(new Date()))) {
            numberOfTimesUsed++;
            rawPrice = 0;
            this.addDateUsed(dateUsed);
        }
        return rawPrice;
    }
    public boolean isValid(TransitDate date){
        return !this.usedToday(date) && super.isValid(date);
    }

    private boolean usedToday(TransitDate today){
        for (TransitDate dateUsed: this.getDatesUsed()){
            if (dateUsed.onSameDay(today)){
                return true;
            }
        }
        return false;
    }
}
