package transit;

import java.util.Date;
import util.FormattedDate;

/**
 * A price modifier that increases the price by $1 for 30 days
 */
public class PriceModifierCharityPass extends PriceModifier {

    public PriceModifierCharityPass(FormattedDate date) {
        super(date, date.addTime(30), Integer.MAX_VALUE, "Charity Pass");
    }


    @Override
    public double modifyPrice(double rawPrice, FormattedDate dateUsed) {
        if (isValid(new FormattedDate(new Date()))) {
            numberOfTimesUsed++;
            rawPrice += 1;
            this.addDateUsed(dateUsed);
        }
        return rawPrice;
    }
}
