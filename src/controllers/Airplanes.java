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

  /**
  * Armazenamento de Aviões, usando seu código como chave
  */
  private Map<String, Airplane> airplanes = new HashMap<>();

  /**
  * Construtior, cria arquivo, remove linhas e carrega os aviões do arquivo
  */
  public Airplanes() {
    super.airplaneFile = new FileTXT("airplanes");
    super.airplaneFile.removeEmptyLines();
    this.loadAirplanes();
  }

  /**
  * Cria novo airplane, pegando um codigo que começa com A e increvemento o tamanho do armazenamento de aviões
  * @name nome do avião
  * @seatsQuantify quantidade de acentos
  * @returns booleando que confirma ou não a criação
  */
  public boolean create(String name, int seatsQuantity) {
    final String code = "A" + (this.airplanes.size() + 1);
    Airplane airplane = new Airplane(code, name, seatsQuantity);
    
    this.airplanes.put(code, airplane);
    super.airplaneFile.write(airplane.toString());

    return true;
  }


  /**
  * Busca o objeto com base no parametro, retorna nulo caso não encontrar
  * @code string de identificação do objeto
  */
  public Airplane find(String code) {
    return this.airplanes.get(code);
  }


  /**
  * Remove o objeto com base na chave 
  * @code string de identificação do objeto a ser apagado
  * @return boolean de confirmação
  */
  public boolean remove(String code) {
    Airplane aux = this.airplanes.remove(code);

    if (aux == null) return false;

    this.airplanes.remove(aux);
    super.airplaneFile.remove(aux.toString());

    return true;
  }

  /**
  * Devolve arrayList com os objetos
  */
  public ArrayList<Airplane> getList() {
    return new ArrayList<>(this.airplanes.values());
  }


  /**
  * Carrega objetos do aruivo de database
  */
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
