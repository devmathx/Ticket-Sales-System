package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import enums.SeatClassEnum;
import interfaces.Controller;
import models.Airplane;
import models.Flight;
import services.FileTXT;

/**
 * Controle de voos
 */
public class Flights extends ControllerFields implements Controller<Flight> {
  /**
  * Armazenamento de voos, usando seu código como chave
  */
  private Map<String, Flight> flights = new HashMap<>();

  /**
  * Construtior, cria arquivo, remove linhas e carrega os voos do arquivo
  */
  public Flights() {
    super();
    super.flightFile = new FileTXT("flights");
    super.flightFile.removeEmptyLines();
    this.loadFlights();
  }

  /**
  * Construtior, cria arquivo, remove linhas
  * @flag parametro de controle para não pegar imediaamente os voos do arquivo
  */
  public Flights(boolean flag){
    super();
    super.flightFile = new FileTXT("flights");
    super.flightFile.removeEmptyLines();
  }

  /**
  * Cria novo flight, pegando um codigo que começa com F e increvemento o tamanho do armazenamento de aviões
  * @origin origem do voo
  * @destiny destino do voo
  * @flightTime horário do voo
  * @seatClass classe do voo
  * @airplane objeto do tipo avião que irá realizar o voo
  * @returns booleando que confirma ou não a criação
  */
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

  /**
  * Devolve arrayList com os objetos
  */
  public ArrayList<Flight> getList() {
    return new ArrayList<>(this.flights.values());
  }

  /**
  * Busca o objeto com base no parametro, retorna nulo caso não encontrar
  * @code string de identificação do objeto
  */
  public Flight find(String code) {
    return flights.get(code);
  }

  /**
  * Remove o objeto com base na chave 
  * @code string de identificação do objeto a ser apagado
  * @return boolean de confirmação
  */
  public boolean remove(String code) {
    Flight aux = this.flights.remove(code);

    if (aux == null) return false;

    this.flights.remove(code);
    super.flightFile.remove(aux.toString());

    return true;
  }

  /**
  * Carrega objetos do aruivo de database
  */
  private void loadFlights() {
    ArrayList<String> rows = super.flightFile.read();

    for (String flightString : rows) {
      Flight flight = Flight.fromString(flightString);
      if (flight != null) {
          this.flights.put(flight.getCode(), flight);
      }
    }
  }

  /**
  * Modifica o status do voo, em endamento ou não inciado
  * @code codigo do voo
  */
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
