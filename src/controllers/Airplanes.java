package controllers;

import java.util.ArrayList;

import interfaces.Controller;
import models.Airplane;
import services.FileTXT;

public class Airplanes implements Controller<Airplane> {

  private ArrayList<Airplane> airplanes = new ArrayList<>();
  private final FileTXT TXT;

  public Airplanes() {
    this.TXT = new FileTXT("airplaines");
    this.TXT.removeEmptyLines();
  }

  public boolean create(String name, int seatsQuantity) {
    final String code = "A" + (this.airplanes.size() + 1); // Gerar código aleatório

    Airplane airplane = new Airplane(code, name, seatsQuantity);
    
    final boolean contain = this.airplanes.contains(airplane);
    if (contain) return false;

    this.airplanes.add(airplane);

    this.TXT.write(airplane.toString());

    return true;
  }

  public Airplane find(String name) {
    for (Airplane a : this.airplanes) {
      if (a.getName() == name) return a;
    }

    return null;
  }

  public boolean remove(String name) {
    Airplane aux = find(name);

    if (aux == null) return false;

    this.airplanes.remove(aux);
    this.TXT.remove(aux.toString());

    return true;
  }

  public ArrayList<Airplane> getList() {
    return this.airplanes;
  }
}
