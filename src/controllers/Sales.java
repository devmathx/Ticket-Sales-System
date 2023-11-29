package controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import interfaces.Controller;
import models.Client;
import models.Flight;
import models.Sale;
import services.FileTXT;

public class Sales extends ControllerFields implements Controller<Sale> {
    
  private Map<String, Sale> sales = new HashMap<>();

  public Sales() {
    super.salesFile = new FileTXT("sales");
    super.salesFile.removeEmptyLines();
    this.loadSales();
  }

  public boolean create(String rgBuyer, String codeFlight) {
    Clients clients = new Clients();
    Client buyer = clients.find(rgBuyer);

    Flights flights = new Flights();
    Flight flight = flights.find(codeFlight);


    if(buyer == null || flight == null){
      System.out.println("\n>> Cliente ou voo não existem! <<");
      return false;
    }

    final String code = "S" + (this.sales.size() + 1); // Gerar código aleatório
    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String formattedDate = dateFormat.format(date);

    if(this.sales.containsKey(code)){
      return false;
    }
    Sale sale = new Sale(code, buyer, flight, formattedDate);
    this.sales.put(code, sale);

    super.flightFile = new FileTXT("flights");
    super.airplaneFile = new FileTXT("airplanes");

    String oldFlight = flight.toString();
    String oldAirplane = flight.getAirplane().toString();
  

    flights.find(codeFlight).addClient(buyer); 
    System.out.println(flights.find(codeFlight).getAirplane());
    flights.find(codeFlight).getAirplane().setBusy(flights.find(codeFlight).getAirplane().getBusy() + 1);
    flight = flights.find(codeFlight);

    System.out.println(flights.find(codeFlight).getAirplane());
   
    String newFlight = flight.toString();
    String newAirplane = flight.getAirplane().toString();
    

    flightFile.rewrite(oldFlight, newFlight);
    airplaneFile.rewrite(oldAirplane, newAirplane);

    super.salesFile.write(sale.toString());

    return true;
  }

  public ArrayList<Sale> getList() {
    return new ArrayList<>(this.sales.values());
  }

  public Sale find(String param) {
    return this.sales.get(param);
  }

  public boolean remove(String param) {
    throw new UnsupportedOperationException("Unimplemented method 'find'");
  }

  private void loadSales() {
    ArrayList<String> rows = super.salesFile.read();

    for (String saleString : rows) {
      Sale sale = Sale.fromString(saleString);
      if (sale != null) {
          this.sales.put(sale.getCode(), sale);
      }
    }
  }
}
