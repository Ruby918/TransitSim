/* Dan */
import java.util.ArrayList;
import java.util.Date;

public class StatisticsManager {

    private static ArrayList<TapEvent> tapEvents = new ArrayList<>();

    public static ArrayList<TapEvent> getTapEvents() {
        return tapEvents;
    }

    public static void addTapEvent(TapEvent tap){
        this.tapEvents.add(tap);
    }

    public static ArrayList<TapEvent> dateTap (Date date){
        //stub
        return new ArrayList<>();//filler so i don't see red lines
    }

    public static double calculateRevenue(ArrayList<TapEvent> taps){
        //stub
        return 1.0;
    }

    public static double calculateProfit(ArrayList<TapEvent> taps, double cost){
        //stub
        return 1.0;
    }

    public static ArrayList<Station> calculateStationsReached(ArrayList<TapEvent> taps){
        //stub
        return new ArrayList<>(); // filler so i don't see red lines
    }

    //StatisticsManager.getStationsReached(ArrayList<TapEvent> events) { ArrayList<Station> stations; for (TapEvent event : events) { stations.add(event.getStation()) }
/////note the doc mentioned a particular set of tap events for all so I assumed that this will not always be all tap events, this may be an incorrect assumtion.
}
