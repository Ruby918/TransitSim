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
    public double modifyPrice(double rawPrice) {
        if (isValid(new TransitDate(new Date()))) {
            numberOfTimesUsed++;
            rawPrice = 0;
        }
        return rawPrice;
    }
    public boolean isValid(TransitDate date){
        return !this.usedToday(date) && super.isValid(date);
    }

    private boolean usedToday(TransitDate today){
        for (Transaction transaction: this.getTransactionsAppliedTo()){
            if (transaction.getDate().onSameDay(today)){
                return true;
            }
        }
        return false;
    }
}
