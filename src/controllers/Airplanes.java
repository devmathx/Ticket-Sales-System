package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import interfaces.Controller;
import models.Airplane;
import services.FileTXT;

/**
 * Controle de aeronaves
 */
public class Airplanes extends ControllerFields implements Controller<Airplane> {

  private Map<String, Airplane> airplanes = new HashMap<>();

  public Airplanes() {
    super.airplaneFile = new FileTXT("airplanes");
    super.airplaneFile.removeEmptyLines();
    this.loadAirplanes();
  }

  public boolean create(String name, int seatsQuantity) {
    final String code = "A" + (this.airplanes.size() + 1);
    Airplane airplane = new Airplane(code, name, seatsQuantity);
    
    this.airplanes.put(code, airplane);
    super.airplaneFile.write(airplane.toString());

    return true;
  }

  public Airplane find(String code) {
    return this.airplanes.get(code);
  }

  public boolean remove(String code) {
    Airplane aux = this.airplanes.remove(code);

    if (aux == null) return false;

    this.airplanes.remove(aux);
    super.airplaneFile.remove(aux.toString());

    return true;
  }

  public ArrayList<Airplane> getList() {
    return new ArrayList<>(this.airplanes.values());
  }

  private void loadAirplanes() {
    ArrayList<String> rows = super.airplaneFile.read();

    for (String clientString : rows) {
      Airplane airplane = Airplane.fromString(clientString);
      if (airplane != null) {
        this.airplanes.put(airplane.getCode(), airplane);
      }
    }
  }
}
