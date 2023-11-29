package models;

import controllers.Clients;
import controllers.Flights;

public class Sale {
  private String code;
  private Client buyer;
  private Flight flight;
  private String hour;

  public Sale(String code,Client buyer, Flight flight, String hour) {
    this.code = code;
    this.buyer = buyer;
    this.flight = flight;
    this.hour = hour;
  }

  public String getCode() {
    return code;
  }

  public Client getBuyer() {
    return buyer;
  }

  public Flight getFlight() {
    return flight;
  }

  public String toString() {
    String text = "";
    text += "Codigo: " + this.code + " | ";
    text += "Comprador: " + buyer.getRg() + " - " + buyer.getName() + " | ";
    text += "Voo: " + flight.getCode() + " - " + flight.getOrigin() + " -> " + flight.getDestiny() + " | ";
    text += "Horario: " + this.hour;
    return text;
  }

  public String getHour() {
    return hour;
  }

  public static Sale fromString(String input) {
    String[] parts = input.split("\\|");

    if (parts.length >= 4) { // Verifica se há pelo menos 4 partes
      String code = parts[0].trim().replace("Codigo: ", "");
      String buyerInfo = parts[1].trim().replace("Comprador: ", "");
      String flightInfo = parts[2].trim().replace("Voo: ", "");
      String hour = parts[3].trim().replace("Horário: ", "");

      // Criar um objeto Client a partir das informações do comprador
      String rgBuyer = buyerInfo.split(" - ")[0];
      Clients clients = new Clients();
      Client client = clients.find(rgBuyer);
      if(client == null){
        return null;
      }

      // Criar um objeto Flight a partir do código
      String codeFlight = flightInfo.split(" - ")[0];
      Flights flights =  new Flights();
      Flight flight = flights.find(codeFlight);
      if(flight == null){
        return null;
      }

      // Criar um objeto Sale
      return new Sale(code, client, flight, hour);
    } else {
      // Se não houver informações suficientes, você pode lançar uma exceção ou lidar de outra forma
      throw new IllegalArgumentException("Formato de entrada inválido: " + input);
    }
  }

  public String toCSVString() {
    StringBuilder csvString = new StringBuilder();

    // Adicione os campos relevantes ao CSV
    csvString.append(this.code).append(",");
    csvString.append(this.buyer.getRg()).append(",");
    csvString.append(this.buyer.getName()).append(",");
    csvString.append(this.flight.getCode()).append(",");
    csvString.append(this.flight.getOrigin()).append(",");
    csvString.append(this.flight.getDestiny()).append(",");
    csvString.append(this.hour);

    return csvString.toString();
  }
}
