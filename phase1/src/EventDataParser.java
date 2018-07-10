import java.util.Date;

public class EventDataParser extends DataParser {

  private String message;
  private TransitFareManager ttc;

  public EventDataParser(String filename, TransitFareManager ttc) {
    super(filename);
    this.ttc = ttc;
  }

  protected void parseLine(String line) {

    // Ignore empty line
    if (line.isEmpty()) {
      return;
    }

    // Reset the message which will be printed after parsing this line
    message = "";

    // Print input message
    System.out.println("INPUT    :   " + line);

    // Parse according to command type
    String[] lineData = line.split(": ");
    switch (lineData[0]) {
      case "Admin":
        parseAdminCommand(lineData);
        break;
      case "Customer":
        parseCustomerCommand(lineData);
        break;
      case "Card":
        parseCardCommand(lineData);
        break;
      default:
        message = "That is not a valid command type.";
    }

    // Print output message
    System.out.println("OUTPUT   :   " + message + System.lineSeparator());
  }

  private void parseAdminCommand(String[] data) {

    String[] parameters = data[1].split(", ");

    switch (parameters[0]) {
      case "Revenue":
        message = "$";
        switch (parameters[1]) {
          case "Total":
            message += StatisticsManager.calculateRevenue();
            break;
          default:
            Date date = DateUtils.getDateFromDateString(parameters[1]);
            message += StatisticsManager.calculateRevenueOnDate(date);
        }
        break;
      case "Trips":
        message = "Trips: " + System.lineSeparator();
        switch (parameters[1]) {
          case "Total":
            message += indentString(StatisticsManager.listTrips());
            break;
          default:
            Date date = DateUtils.getDateFromDateString(parameters[1]);
            message += indentString(StatisticsManager.listTripsOnDate(date));
        }
        break;
      case "Stations":
        Date date = DateUtils.getDateFromDateString(parameters[1]);
        message = "Stations: " + System.lineSeparator()
            + indentString(StatisticsManager.listStationsReachedOnDate(date));
        break;
      case "Routes":
        message = "Routes:" + System.lineSeparator()
            + indentString(ttc.listRoutes());
        break;
      case "Customers":
        switch (parameters[1]) {
          case "Total":
            message = "Customers: " + System.lineSeparator()
                + indentString(ttc.listCustomers());
            break;
          case "Create":
            ttc.createCustomerAccount(parameters[2], parameters[3]);
            message = "Successfully created customer account.";
            break;
          default:
            message = "That is not a valid admin customer command.";
        }
        break;
      case "Cards":
        message = "Cards: " + System.lineSeparator()
            + indentString(ttc.listCards());
        break;
      default:
        message = "That is not a valid admin command.";
    }
  }

  private void parseCustomerCommand(String[] data) {

    CustomerAccount customer = ttc.getCustomerById(Integer.parseInt(data[1]));
    if (customer == null) {
      message = "That customer does not exist.";
      return;
    }

    String[] parameters = data[2].split(", ");

    switch (parameters[0]) {
      case "Details":
        message = customer.toString();
        break;
      case "Update Name":
        customer.setName(parameters[1]);
        message = "Successfully updated customer's name to " + parameters[1] + ".";
        break;
      case "Cards":
        switch (parameters[1]){
          case "New":
            ttc.generateCard(customer);
            message = "Successfully added a card to this customer.";
            break;
          case "View":
            message = "Cards: " + System.lineSeparator()
                + indentString(customer.listCards());
            break;
          default: message = "That is not a valid customer card command.";
        }
        break;
      case "Average Cost":
        message = customer.getAverageMonthlyCost();
        break;
      case "Recent Trips":
        message = "Recent Trips:" + System.lineSeparator()
            + indentString(customer.listRecentTrips());
        break;
      default:
        message = "That is not a valid customer command.";
    }
  }

  private void parseCardCommand(String[] data) {

    Card card = ttc.getCardById(Integer.parseInt(data[1]));
    if (card == null) {
      message = "That card does not exist.";
      return;
    }

    String[] parameters = data[2].split(", ");

    switch (parameters[0]) {
      case "Details":
        message = card.toString();
        break;
      case "Add Funds":
        switch (parameters[1]) {
          case "10":
            card.addTenDollars();
            message = "Successfully added $10 to card.";
            break;
          case "20":
            card.addTwentyDollars();
            message = "Successfully added $20 to card.";
            break;
          case "50":
            card.addFiftyDollars();
            message = "Successfully added $50 to card.";
            break;
          default:
            message = "That is not a valid amount.";
        }
        break;
      case "Balance":
        message = "$" + String.valueOf(card.getBalance());
        break;
      case "Deactivate":
        card.deactivate();
        message = "Successfully deactivated card.";
        break;
      case "Tap In":
        parseCardTapIn(card, parameters);
        break;
      case "Tap Out":
        parseCardTapOut(card, parameters);
        break;
      default:
        message = "That is not a valid card command.";
    }
  }

  private void parseCardTapIn(Card card, String[] parameters) {
    Station station = ttc.getMap().getStationByNameAndRoute(parameters[3], parameters[2], parameters[1]);
    if (station == null) {
      message = "That is not a valid station.";
      return;
    }
    Date date;
    try {
      date = DateUtils.getDateFromDatetimeString(parameters[4]);
    } catch (ArrayIndexOutOfBoundsException e) {
      message = "Error: That datetime is incorrectly formatted.";
      return;
    }
    try {
      card.tapIn(station, date);
    } catch(TapDeactivatedCardException e) {
      message = "Error: That card has been deactivated.";
      return;
    } catch (InsufficientFundsException e) {
      message = "Error: That card has insufficient funds.";
      return;
    } catch (IllegalTapLocationException e) {
      message = "Error: The location of this tap event is highly irregular. You have been charged for a $6 trip.";
      return;
    }
    message = "You have successfully tapped in at "
        + station.toString() + " using " + card.toString() + ".";
  }

  private void parseCardTapOut(Card card, String[] parameters) {
    Station station = ttc.getMap().getStationByNameAndRoute(parameters[3], parameters[2], parameters[1]);
    if (station == null) {
      message = "That is not a valid station.";
      return;
    }
    Date date;
    try {
      date = DateUtils.getDateFromDatetimeString(parameters[4]);
    } catch (ArrayIndexOutOfBoundsException e) {
      message = "Error: That datetime is incorrectly formatted.";
      return;
    }
    try {
      card.tapOut(station, date);
    } catch(TapDeactivatedCardException e) {
      message = "Error: That card has been deactivated.";
      return;
    } catch (IllegalTapLocationException e) {
      message = "Error: The location of this tap event is highly irregular. You have been charged for a $6 trip.";
      return;
    }
    message = "You have successfully tapped out at "
        + station.toString() + " using " + card.toString() + ".";
  }

}
