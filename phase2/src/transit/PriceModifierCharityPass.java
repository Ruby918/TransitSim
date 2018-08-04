package transit;

import java.util.Date;
/**
 * A price modifier that increases the price by $1 for 30 days
 */
public class PriceModifierCharityPass extends PriceModifier {

    public PriceModifierCharityPass(TransitDate date) {
        super(date, date.addTime(30), Integer.MAX_VALUE, "Charity Pass");
    }


    @Override
    public double modifyPrice(double rawPrice) {
        if (isValid(new TransitDate(new Date()))) {
            numberOfTimesUsed++;
            rawPrice += 1;
        }
        return rawPrice;
    }
}
