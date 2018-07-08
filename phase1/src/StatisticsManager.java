/* Dan */
import java.util.ArrayList;
import java.util.Date;

public class StatisticsManager {

    private static ArrayList<TapEvent> tapEvents = new ArrayList<>();
    private static ArrayList<Trip> tripEvents = new ArrayList<>();


    public static ArrayList<TapEvent> getTapEvents() {
        return tapEvents;
    }
    public static ArrayList<Trip> getTripEvents() {
        return tripEvents;
    }

    public static void addTripEvent(Trip trip){
        tripEvents.add(trip);
    }

    public static void addTapEvent(TapEvent tap){
        tapEvents.add(tap);
    }

    public static ArrayList<TapEvent> dateTap (Date date){
        ArrayList<TapEvent> dateMatchTapEvents = new ArrayList<>();

        for(int i = 0; i < tapEvents.size(); i++){
            if(tapEvents.get(i).getDate() == date){
                dateMatchTapEvents.add(tapEvents.get(i));
            }
        }
        return dateMatchTapEvents;
    }

    public static double calculateRevenue(ArrayList<TapEvent> taps){
        double revenue = 0;
        for(int i = 0; i < tripEvents.size(); i++){
            revenue += tripEvents.get(i).getCostSoFar();
        }
        return revenue;
    }

    public static double calculateProfit(ArrayList<TapEvent> taps, double cost){
        return cost - calculateRevenue(taps);
    }

    public static ArrayList<Station> calculateStationsReached(ArrayList<TapEvent> taps){
        //stub
        return new ArrayList<>(); // filler so i don't see red lines
    }

}
