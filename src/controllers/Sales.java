package controllers;

import java.util.ArrayList;
import java.util.Date;

import interfaces.Controller;
import models.Client;
import models.Flight;
import models.Sale;
import services.FileTXT;

public class Sales implements Controller<Sale> {
    
  private ArrayList<Sale> sales = new ArrayList<>();
  private final FileTXT TXT;

  public Sales() {
    this.TXT = new FileTXT("sales");
    this.TXT.removeEmptyLines();
  }

  public boolean create(Client buyer, Flight flight) {
    Date date = new Date();
    final String now = date.toString();
    Sale sale = new Sale(buyer, flight, now);

    final boolean contain = this.sales.contains(sale);
    if (contain) return false;

    this.sales.add(sale);
    
    this.TXT.write(sale.toString());

    return true;
  }

  public ArrayList<Sale> getList() {
    return this.sales;
  }

  public Sale find(String param) {
    throw new UnsupportedOperationException("Unimplemented method 'find'");
  }

  public boolean remove(String param) {
    throw new UnsupportedOperationException("Unimplemented method 'remove'");
  }
}
