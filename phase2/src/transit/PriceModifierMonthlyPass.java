package transit;

import java.util.Date;
import util.FormattedDate;

/**
 * A PriceModifier that allows 1 free trip a day, for 30 days
 */
public class PriceModifierMonthlyPass extends PriceModifier {

    public PriceModifierMonthlyPass(FormattedDate date) {
        super(date, date.addTime(30), Integer.MAX_VALUE, "Monthly Pass");
    }

    @Override
    public double modifyPrice(double rawPrice, FormattedDate dateUsed) {
        if (isValid(new FormattedDate(new Date()))) {
            numberOfTimesUsed++;
            rawPrice = 0;
            this.addDateUsed(dateUsed);
        }
        return rawPrice;
    }
    public boolean isValid(FormattedDate date){
        return !this.usedToday(date) && super.isValid(date);
    }

    private boolean usedToday(FormattedDate today){
        for (FormattedDate dateUsed: this.getDatesUsed()){
            if (dateUsed.onSameDay(today)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "The One-Way Commute, 1 Month (1 Free Per Day)";
    }
}
