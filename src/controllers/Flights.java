package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import enums.SeatClassEnum;
import interfaces.Controller;
import models.Airplane;
import models.Flight;
import services.FileTXT;

public class Flights extends ControllerFields implements Controller<Flight> {
  private Map<String, Flight> flights = new HashMap<>();

  public Flights() {
    super();
    super.flightFile = new FileTXT("flights");
    super.flightFile.removeEmptyLines();
    this.loadFlights();
  }

  public Flights(boolean flag){
    super();
    super.flightFile = new FileTXT("flights");
    super.flightFile.removeEmptyLines();
  }

  public boolean create(String origin, String destiny, String flightTime, SeatClassEnum seatClass, Airplane airplane) {
    final String code = "F" + (this.flights.size() + 1); // Gerar código aleatório
    Flight flight = new Flight(code, origin, destiny, flightTime, seatClass, airplane);

    if(this.flights.containsKey(code)) return false;

    this.flights.put(code, flight);
    super.flightFile.write(flight.toString());

    super.airplaneFile = new FileTXT("airplanes");
    String oldAirplane = airplane.toString();
    airplane.setAvailable(false);
    String newAirplane = airplane.toString();
    super.airplaneFile.rewrite(oldAirplane, newAirplane);

    return true;
  }

  public ArrayList<Flight> getList() {
    return new ArrayList<>(this.flights.values());
  }

  public Flight find(String code) {
    return flights.get(code);
  }

  public boolean remove(String code) {
    Flight aux = this.flights.remove(code);

    if (aux == null) return false;

    this.flights.remove(code);
    super.flightFile.remove(aux.toString());

    return true;
  }

  private void loadFlights() {
    ArrayList<String> rows = super.flightFile.read();

    for (String flightString : rows) {
      Flight flight = Flight.fromString(flightString);
      if (flight != null) {
          this.flights.put(flight.getCode(), flight);
      }
    }
  }

  public boolean newStatus(String code) {
    Flight flight = this.flights.get(code);

    if(flight == null){
      return false;
    }

    String oldFlight = flight.toString();
    flight.setStatusFly(!flight.getStatusFly());
    String newFlight = flight.toString();
    super.flightFile.rewrite(oldFlight, newFlight);

    return true;
  }
}
