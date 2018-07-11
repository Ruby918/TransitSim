/* Brian */

/**
 * Thrown to indicate when a <code>TapEvent</code> is illegal according to the previous tap history of a
 * <code>Trip</code>.
 */
public class UnnaturalTapSequenceException extends Exception{
    public UnnaturalTapSequenceException(){
        super("This tap created an illegal tap sequence.");
    }
}
