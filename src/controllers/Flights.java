package controllers;

import java.util.ArrayList;

import enums.SeatClassEnum;
import interfaces.Controller;
import models.Airplane;
import models.Flight;
import services.FileTXT;

public class Flights implements Controller<Flight> {

  private ArrayList<Flight> flights = new ArrayList<>();
  private final FileTXT TXT;

  public Flights() {
    this.TXT = new FileTXT("flights");
    this.TXT.removeEmptyLines();
  }

  public boolean create(String origin, String destiny, String flightTime, SeatClassEnum seatClass, Airplane airplane) {
    Flight flight = new Flight(origin, destiny, flightTime, seatClass, airplane);

    final boolean contain = this.flights.contains(flight);
    if (contain) return false;

    this.flights.add(flight);
    
    this.TXT.write(flight.toString());

    return true;
  }

  public ArrayList<Flight> getList() {
    return this.flights;
  }

  public Flight find(String param) {
    throw new UnsupportedOperationException("Unimplemented method 'find'");
  }

  public boolean remove(String param) {
    throw new UnsupportedOperationException("Unimplemented method 'remove'");
  }
}
