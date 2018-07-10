public class EventDataParser extends DataParser {

  private String message;
  private TransitFareManager ttc;

  public EventDataParser(String filename, TransitFareManager ttc) {
    super(filename);
    this.ttc = ttc;
  }

  protected void parseLine(String line){

    // Ignore empty line
    if (line.isEmpty()) return;

    // Reset message which will be printed after parsing line
    this.message = "";

    // Print input message
    System.out.println("INPUT    :   " + line);

    // Parse according to command type
    String[] lineData = line.split(": ");
    switch (lineData[0]) {
      case "Admin": parseAdminCommand(lineData); break;
      case "Customer": parseCustomerCommand(lineData); break;
      case "Card": parseCardCommand(lineData); break;
      default: this.message = "That is not a valid command type.";
    }

    // Print output message
    System.out.println("OUTPUT   :   " + this.message + System.lineSeparator());
  }

  private void parseAdminCommand(String[] data) {
    switch (data[1]) {
      case "Revenue": parseAdminRevenueCommand(data); break;
      case "Trips": parseAdminTripsCommand(data); break;
      case "Stations": parseAdminStationsCommand(data); break;
      case "Routes": parseAdminRoutesCommand(data); break;
      case "Customers": parseAdminCustomersCommand(data); break;
      case "Cards": parseAdminCardsCommand(data); break;
      case "Report": parseAdminReportCommand(data); break;
      default:  this.message = "That is not a valid admin command.";
    }
  }

  private void parseCustomerCommand(String[] data) {

    CustomerAccount customer = ttc.getCustomerById(Integer.parseInt(data[1]));
    if (customer == null) {
      this.message = "That customer does not exist.";
      return;
    }

    String[] parameters = data[2].split(", ");

    switch (parameters[0]) {
      case "Report": this.message = customer.toString(); break;
      case "Update Name":
        customer.setName(parameters[1]);
        this.message = "Successfully updated customer's name to " + parameters[1];
        break;
      case "Cards": this.message = customer.listCards(); break;
      case "Average Cost": this.message = customer.getAverageMonthlyCost(); break;
      case "Recent Trips":
        this.message = "Recent Trips:" + System.lineSeparator() + customer.listRecentTrips();
        break;
      default:  this.message = "That is not a valid customer command.";
    }
  }

  private void parseCardCommand(String[] data) {

    Card card = ttc.getCardById(Integer.parseInt(data[1]));
    if (card == null) {
      this.message = "That card does not exist.";
      return;
    }

    String[] parameters = data[2].split(", ");

    switch (parameters[0]) {
      case "Report": this.message = card.toString(); break;
      case "Add Funds":
        switch(parameters[1]) {
          case "10":
            card.addTenDollars();
            this.message = "Successfully added $10 to card.";
            break;
          case "20":
            card.addTwentyDollars();
            this.message = "Successfully added $20 to card.";
            break;
          case "50":
            card.addFiftyDollars();
            this.message = "Successfully added $50 to card.";
            break;
          default: this.message = "That is not a valid amount.";
        }
        break;
      case "Balance":
        this.message = "$" + String.valueOf(card.getBalance());
        break;
      case "Deactivate":
        card.deactivate();
        this.message = "Successfully deactivated card.";
        break;
      case "Tap In":
        parseCardTapIn(card, parameters);
        break;
      case "Tap Out":
        parseCardTapOut(card, parameters);
        break;
      default:  this.message = "That is not a valid card command.";
    }
  }

  private void parseAdminRevenueCommand(String[] data) {

  }

  private void parseAdminTripsCommand(String[] data) {

  }

  private void parseAdminStationsCommand(String[] data) {

  }

  private void parseAdminRoutesCommand(String[] data) {

  }

  private void parseAdminCustomersCommand(String[] data) {

  }

  private void parseAdminCardsCommand(String[] data) {

  }

  private void parseAdminReportCommand(String[] data) {

  }

  private void parseCardTapIn(Card card, String[] parameters) {

  }

  private void parseCardTapOut(Card card, String[] parameters) {

  }

}
