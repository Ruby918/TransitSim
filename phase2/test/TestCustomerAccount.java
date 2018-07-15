import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestCustomerAccount {

  Route subwayRoute = new SubwayRoute("Subway Route");
  SubwayStation ss1 = new SubwayStation("SS1", subwayRoute);
  SubwayStation ss2 = new SubwayStation("SS1", subwayRoute);
  SubwayStation ss3 = new SubwayStation("SS1", subwayRoute);
  Route busRoute = new BusRoute("Bus Route");
  BusStation bs1 = new BusStation("BS1", busRoute);
  BusStation bs2 = new BusStation("BS1", busRoute);

  public TestCustomerAccount() {
    subwayRoute.addStation(ss1);
    subwayRoute.addStation(ss2);
    subwayRoute.addStation(ss3);
    busRoute.addStation(bs1);
    busRoute.addStation(bs2);
  }

  @Test
  void CustomerHasNoCards() {
    CustomerAccount customer = new CustomerAccount("Name", "email", 0);
    assertEquals(0, customer.getCards().size());
  }

  @Test
  void CustomerHasCard() {
    CustomerAccount customer = new CustomerAccount("Name", "email", 0);
    customer.addCard(new Card(0));
    assertEquals(1, customer.getCards().size());
  }

  @Test
  void CustomerAddCards() {
    CustomerAccount customer = new CustomerAccount("Name", "email", 0);
    customer.addCard(new Card(1));
    customer.addCard(new Card(2));
    customer.addCard(new Card(3));
    assertEquals(3, customer.getCards().size());
  }

  @Test
  void TestAverageCostNoTapsNoCards() {
    CustomerAccount customer = new CustomerAccount("Name", "email", 0);
    assertEquals(0, customer.calculateAverageMonthlyCost());
  }

  @Test
  void TestAverageCostNoTaps() {
    CustomerAccount customer = new CustomerAccount("Name", "email", 0);
    customer.addCard(new Card(0));
    assertEquals(0, customer.calculateAverageMonthlyCost());
  }

  @Test
  void TestAverageCostNoCompleteSubwayTrips() throws
      InsufficientFundsException, TapDeactivatedCardsException, IllegalTapLocationException {
    CustomerAccount customer = new CustomerAccount("Name", "email", 0);
    Card card = new Card(0);
    customer.addCard(card);
    card.tapIn(ss1, TransitDate.createFromDatetimeString("10/05/1990 12:30"));
    assertEquals(0, customer.calculateAverageMonthlyCost());
  }

  @Test
  void TestAverageCostOneSubwayTrip() throws
      InsufficientFundsException, TapDeactivatedCardsException, IllegalTapLocationException {
    CustomerAccount customer = new CustomerAccount("Name", "email", 0);
    Card card = new Card(0);
    customer.addCard(card);
    card.tapIn(ss1, TransitDate.createFromDatetimeString("10/05/1990 12:30"));
    card.tapOut(ss2, TransitDate.createFromDatetimeString("10/05/1990 12:30"));
    assertEquals(0.5, customer.calculateAverageMonthlyCost());
  }

  @Test
  void TestAverageCostOneSubwayTripPerMonth() throws
      InsufficientFundsException, TapDeactivatedCardsException, IllegalTapLocationException {
    CustomerAccount customer = new CustomerAccount("Name", "email", 0);
    Card card = new Card(0);
    customer.addCard(card);
    card.tapIn(ss1, TransitDate.createFromDatetimeString("10/05/1990 12:30"));
    card.tapOut(ss2, TransitDate.createFromDatetimeString("10/05/1990 12:30"));
    card.tapIn(ss1, TransitDate.createFromDatetimeString("10/06/1990 12:30"));
    card.tapOut(ss2, TransitDate.createFromDatetimeString("10/06/1990 12:30"));
    card.tapIn(ss1, TransitDate.createFromDatetimeString("10/07/1990 12:30"));
    card.tapOut(ss2, TransitDate.createFromDatetimeString("10/07/1990 12:30"));
    card.tapIn(ss1, TransitDate.createFromDatetimeString("10/08/1990 12:30"));
    card.tapOut(ss2, TransitDate.createFromDatetimeString("10/08/1990 12:30"));
    assertEquals(0.5, customer.calculateAverageMonthlyCost());
  }

  @Test
  void TestAverageCostOneSubwayTripPerMonthMultipleCards() throws
      InsufficientFundsException, TapDeactivatedCardsException, IllegalTapLocationException {
    CustomerAccount customer = new CustomerAccount("Name", "email", 0);
    Card card1 = new Card(0);
    Card card2 = new Card(0);
    Card card3 = new Card(0);
    customer.addCard(card1);
    customer.addCard(card2);
    customer.addCard(card3);
    card1.tapIn(ss1, TransitDate.createFromDatetimeString("10/05/1990 12:30"));
    card1.tapOut(ss2, TransitDate.createFromDatetimeString("10/05/1990 12:30"));
    card2.tapIn(ss1, TransitDate.createFromDatetimeString("10/06/1990 12:30"));
    card2.tapOut(ss2, TransitDate.createFromDatetimeString("10/06/1990 12:30"));
    card3.tapIn(ss1, TransitDate.createFromDatetimeString("10/07/1990 12:30"));
    card3.tapOut(ss2, TransitDate.createFromDatetimeString("10/07/1990 12:30"));
    card1.tapIn(ss1, TransitDate.createFromDatetimeString("10/08/1990 12:30"));
    card1.tapOut(ss2, TransitDate.createFromDatetimeString("10/08/1990 12:30"));
    assertEquals(0.5, customer.calculateAverageMonthlyCost());
  }

  @Test
  void TestAverageCostBusTapIn() throws
      InsufficientFundsException, TapDeactivatedCardsException, IllegalTapLocationException {
    CustomerAccount customer = new CustomerAccount("Name", "email", 0);
    Card card = new Card(0);
    customer.addCard(card);
    card.tapIn(bs1, TransitDate.createFromDatetimeString("10/05/1990 12:30"));
    assertEquals(2, customer.calculateAverageMonthlyCost());
  }

  @Test
  void TestAverageCostBusAndSubwayTrips() throws
      InsufficientFundsException, TapDeactivatedCardsException, IllegalTapLocationException {
    CustomerAccount customer = new CustomerAccount("Name", "email", 0);
    Card card = new Card(0);
    customer.addCard(card);
    card.tapIn(bs1, TransitDate.createFromDatetimeString("10/05/1990 12:30"));
    card.tapOut(bs2, TransitDate.createFromDatetimeString("10/05/1990 12:30"));
    card.tapIn(ss1, TransitDate.createFromDatetimeString("10/08/1990 12:30"));
    card.tapOut(ss2, TransitDate.createFromDatetimeString("10/08/1990 12:30"));
    assertEquals(1.25, customer.calculateAverageMonthlyCost());
  }

  @Test
  void TestAverageCostBusAndSubwayTripsMultipleCards() throws
      InsufficientFundsException, TapDeactivatedCardsException, IllegalTapLocationException {
    CustomerAccount customer = new CustomerAccount("Name", "email", 0);
    Card card1 = new Card(0);
    Card card2 = new Card(0);
    customer.addCard(card1);
    customer.addCard(card2);
    card1.tapIn(bs1, TransitDate.createFromDatetimeString("10/05/1990 12:30"));
    card1.tapOut(bs2, TransitDate.createFromDatetimeString("10/05/1990 12:30"));
    card2.tapIn(ss1, TransitDate.createFromDatetimeString("10/08/1990 12:30"));
    card2.tapOut(ss2, TransitDate.createFromDatetimeString("10/08/1990 12:30"));
    assertEquals(1.25, customer.calculateAverageMonthlyCost());
  }

  @Test
  void TestRecentTripsNoTrips() {
    CustomerAccount customer = new CustomerAccount("Name", "email", 0);
    assertEquals(0, customer.calculateRecentTrips().size());
  }

  @Test
  void TestRecentTripsOneTrips() throws
      InsufficientFundsException, TapDeactivatedCardsException, IllegalTapLocationException {
    CustomerAccount customer = new CustomerAccount("Name", "email", 0);
    Card card = new Card(0);
    customer.addCard(card);
    card.tapIn(bs1, TransitDate.createFromDatetimeString("10/05/1990 12:30"));
    card.tapOut(bs2, TransitDate.createFromDatetimeString("10/05/1990 12:30"));
    assertEquals(1, customer.calculateRecentTrips().size());
  }

  @Test
  void TestRecentTripsThreeTrips() throws
      InsufficientFundsException, TapDeactivatedCardsException, IllegalTapLocationException {
    CustomerAccount customer = new CustomerAccount("Name", "email", 0);
    Card card = new Card(0);
    customer.addCard(card);
    card.tapIn(bs1, TransitDate.createFromDatetimeString("10/05/1990 12:30"));
    card.tapOut(bs2, TransitDate.createFromDatetimeString("10/05/1990 12:30"));
    card.tapIn(bs1, TransitDate.createFromDatetimeString("11/05/1990 12:30"));
    card.tapOut(bs2, TransitDate.createFromDatetimeString("11/05/1990 12:30"));
    card.tapIn(bs1, TransitDate.createFromDatetimeString("12/05/1990 12:30"));
    card.tapOut(bs2, TransitDate.createFromDatetimeString("12/05/1990 12:30"));
    assertEquals(3, customer.calculateRecentTrips().size());
  }

  @Test
  void TestRecentTripsMultipleDaysInOrder() throws
      InsufficientFundsException, TapDeactivatedCardsException, IllegalTapLocationException {
    CustomerAccount customer = new CustomerAccount("Name", "email", 0);
    Card card = new Card(0);
    customer.addCard(card);
    card.tapIn(bs1, TransitDate.createFromDatetimeString("10/05/1990 12:30"));
    card.tapOut(bs2, TransitDate.createFromDatetimeString("10/05/1990 12:30"));
    card.tapIn(bs1, TransitDate.createFromDatetimeString("11/05/1990 12:30"));
    card.tapOut(bs2, TransitDate.createFromDatetimeString("11/05/1990 12:30"));
    card.tapIn(bs1, TransitDate.createFromDatetimeString("12/05/1990 12:30"));
    card.tapOut(bs2, TransitDate.createFromDatetimeString("12/05/1990 12:30"));
    card.tapIn(bs1, TransitDate.createFromDatetimeString("8/05/1990 12:30"));
    card.tapOut(bs2, TransitDate.createFromDatetimeString("8/05/1990 12:30"));
    card.tapIn(bs1, TransitDate.createFromDatetimeString("9/05/1990 12:30"));
    card.tapOut(bs2, TransitDate.createFromDatetimeString("9/05/1990 12:30"));
    card.tapIn(bs1, TransitDate.createFromDatetimeString("13/05/1990 12:30"));
    card.tapOut(bs2, TransitDate.createFromDatetimeString("13/05/1990 12:30"));
    card.tapIn(bs1, TransitDate.createFromDatetimeString("7/05/1990 12:30"));
    card.tapOut(bs2, TransitDate.createFromDatetimeString("7/05/1990 12:30"));
    ArrayList<Trip> trips = customer.calculateRecentTrips();
    assertEquals(3, trips.size());
    assertTrue(trips.get(2).getStartDate().onSameDay(TransitDate.createFromDateString("13/05/1990")));
    assertTrue(trips.get(1).getStartDate().onSameDay(TransitDate.createFromDateString("12/05/1990")));
    assertTrue(trips.get(0).getStartDate().onSameDay(TransitDate.createFromDateString("11/05/1990")));
  }

  @Test
  void TestRecentTripsMultipleMonthsInOrder() throws
      InsufficientFundsException, TapDeactivatedCardsException, IllegalTapLocationException {
    CustomerAccount customer = new CustomerAccount("Name", "email", 0);
    Card card = new Card(0);
    customer.addCard(card);
    card.tapIn(bs1, TransitDate.createFromDatetimeString("10/06/1990 12:30"));
    card.tapOut(bs2, TransitDate.createFromDatetimeString("10/06/1990 12:30"));
    card.tapIn(bs1, TransitDate.createFromDatetimeString("11/05/1990 12:30"));
    card.tapOut(bs2, TransitDate.createFromDatetimeString("11/05/1990 12:30"));
    card.tapIn(bs1, TransitDate.createFromDatetimeString("12/05/1990 12:30"));
    card.tapOut(bs2, TransitDate.createFromDatetimeString("12/05/1990 12:30"));
    card.tapIn(bs1, TransitDate.createFromDatetimeString("8/05/1990 12:30"));
    card.tapOut(bs2, TransitDate.createFromDatetimeString("8/05/1990 12:30"));
    card.tapIn(bs1, TransitDate.createFromDatetimeString("9/05/1990 12:30"));
    card.tapOut(bs2, TransitDate.createFromDatetimeString("9/07/1990 12:30"));
    card.tapIn(bs1, TransitDate.createFromDatetimeString("13/07/1990 12:30"));
    card.tapOut(bs2, TransitDate.createFromDatetimeString("13/05/1990 12:30"));
    card.tapIn(bs1, TransitDate.createFromDatetimeString("7/05/1990 12:30"));
    card.tapOut(bs2, TransitDate.createFromDatetimeString("7/05/1990 12:30"));
    ArrayList<Trip> trips = customer.calculateRecentTrips();
    assertEquals(3, trips.size());
    assertTrue(trips.get(2).getStartDate().inSameMonth(TransitDate.createFromDateString("13/07/1990")));
    assertTrue(trips.get(1).getStartDate().inSameMonth(TransitDate.createFromDateString("12/06/1990")));
    assertTrue(trips.get(0).getStartDate().inSameMonth(TransitDate.createFromDateString("11/05/1990")));
  }
}
