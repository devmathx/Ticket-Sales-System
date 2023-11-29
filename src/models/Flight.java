package models;

import enums.SeatClassEnum;

public class Flight {
  
  private String origin; 
  private String destiny;
  private String flightTime; // Considerar criar/achar classe para lidar con horários
  private SeatClassEnum seatClass;
  private Airplane airplane;  

  public Flight(String origin, String destiny, String flightTime, SeatClassEnum seatClass, Airplane airplane) {
    this.origin = origin;
    this.destiny = destiny;
    this.flightTime = flightTime;
    this.seatClass = seatClass;
    this.airplane = airplane;
  }

  public String toString() {
    String text = "";
    text += "Origem: " + this.origin + " | ";
    text += "Destino: " + this.destiny + " | ";
    text += "Tempo de voô: " + this.flightTime + " | ";
    text += "Classe: " + this.seatClass + " | ";
    text += "Código do avião: " + this.airplane.getCode();
    return text;
  }

}
