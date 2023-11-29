package models;
public class Sale {

  private Client buyer;
  private Flight flight;
  private String hour;

  public Sale(Client buyer, Flight flight, String hour) {
    this.buyer = buyer;
    this.flight = flight;
    this.hour = hour;
  }

  public String toString() {
    String text = "";
    text += "Comprador: " + this.buyer + " | ";
    text += "Voô: " + this.flight + " | ";
    text += "Horário: " + this.hour;
    return text;
  }

  public Client getBuyer() {
    return buyer;
  }

  public Flight getFlight() {
    return flight;
  }

  public String getHour() {
    return hour;
  }
}
