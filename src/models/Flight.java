package models;

import java.util.ArrayList;

import controllers.Airplanes;
import controllers.Clients;
import enums.SeatClassEnum;

public class Flight {
  private String code;
  private String origin; 
  private String destiny;
  private String flightTime; // Considerar criar/achar classe para lidar con horários
  private SeatClassEnum seatClass;
  private Airplane airplane;  
  private ArrayList<Client> clients;
  private boolean statusFly = false;

  public Flight(String code, String origin, String destiny, String flightTime, SeatClassEnum seatClass, Airplane airplane) {
    this.code = code;
    this.origin = origin;
    this.destiny = destiny;
    this.seatClass = seatClass;
    this.flightTime = flightTime;
    this.airplane = airplane;
    this.clients = new ArrayList<>();
  }

  public void setStatusFly(boolean statusFly) {
    this.statusFly = statusFly;
  }

  public boolean getStatusFly() {
    return this.statusFly;
  }

  public boolean addClient(Client client){
    boolean clientExists = clients.stream().anyMatch(c -> client.getRg().equals(c.getRg()));

    if(clientExists){
      return false;
    }

    this.clients.add(client);
    return true;
  }

  public ArrayList<Client> getClients(){
    return clients;
  }

  public Airplane getAirplane() {
      return airplane;
  }

  public String getDestiny() {
      return destiny;
  }

  public String getOrigin() {
      return origin;
  }

  public String getFlightTime() {
      return flightTime;
  }

  public SeatClassEnum getSeatClass() {
      return seatClass;
  }

  public String getCode() {
      return code;
  }

  public boolean removeClient(Client client){

    for(int i = 0 ; i < clients.size() ; i++){
      
      if(clients.get(i).getRg().trim().equals(client.getRg().trim())){
        clients.remove(i);
        return true;
      }
    }
   
    return false;
  }

  public String toString() {
    String text = "";
    text += "Codigo: " + this.code + " | ";
    text += "Origem: " + this.origin + " | ";
    text += "Destino: " + this.destiny + " | ";
    text += "Tempo de voo: " + this.flightTime + " | ";
    text += "Status: " + this.statusFly + " | ";
    text += "Classe: " + this.seatClass + " | ";
    text += "Codigo do aviao: " + this.airplane.getCode() + " | ";
    
    // Adiciona informações dos clientes
    text += "Clientes: ";
  
    for (Client client : clients) {
        text += client.getRg() + ", ";
    }
    text = text.replaceAll(", $", ""); // Remove a última vírgula se houver

    return text;
}
  
  public static Flight fromString(String input) {
    String[] parts = input.split("\\|");

    if (parts.length >= 6) { // Verifica se há pelo menos 6 partes
        String code = parts[0].trim().replace("Codigo: ", "");
        String origin = parts[1].trim().replace("Origem: ", "");
        String destiny = parts[2].trim().replace("Destino: ", "");
        String flightTime = parts[3].trim().replace("Tempo de voo: ", "");
        boolean status = Boolean.valueOf(parts[4].trim().replace("Status: ", "")).booleanValue();
        SeatClassEnum seatClass = SeatClassEnum.valueOf(parts[5].trim().replace("Classe: ", ""));
        String airplaneCode = parts[6].trim().replace("Codigo do aviao: ", "");

        // A partir daqui, você pode criar um objeto Airplane com o código
        // e qualquer lógica adicional necessária para preencher o objeto Flight

        // Exemplo:
        Airplanes airplanes = new Airplanes();
        Airplane airplane = airplanes.find(airplaneCode);
        if(airplane == null){
          return null;
        }
        
        Clients clients = new Clients();

        // Criar um objeto Flight
        Flight flight = new Flight(code, origin, destiny, flightTime, seatClass, airplane);
        flight.setStatusFly(status);

        // Adicionar clientes se houver informações sobre eles
        if (parts.length >= 8) {
            String[] clientsInfo = parts[7].trim().replace("Clientes: ", "").split(", ");
            for (String clientRg : clientsInfo) {
                Client client = clients.find(clientRg);
                if(client != null) {
                  flight.addClient(client);
                }
            }
        }

        return flight;
    } else {
        // Se não houver informações suficientes, você pode lançar uma exceção ou lidar de outra forma
        throw new IllegalArgumentException("Formato de entrada inválido: " + input);
    }
}

}


