package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import interfaces.Controller;
import models.Client;
import services.FileTXT;

public class Clients extends ControllerFields implements Controller<Client> {
  private Map<String, Client> clients = new HashMap<>();

  public Clients() {
    super.clientFile = new FileTXT("clients");
    super.clientFile.removeEmptyLines();
    this.loadClients();
  }

  public boolean create(String name, String phone, String rg) {
    if(this.clients.containsKey(rg)){
      return false;
    }
    
    Client client = new Client(rg, name, phone);
    this.clients.put(rg, client);

    super.clientFile.write(client.toString());

    return true;
  }

  public Client find(String rg) {
    return this.clients.get(rg);
  }

  public boolean remove(String rg) {
    Client aux = this.clients.remove(rg);

    if (aux == null) return false;

    this.clients.remove(rg);
    super.clientFile.remove(aux.toString());

    return true;
  }

  public ArrayList<Client> getList() {
    return new ArrayList<>(this.clients.values());
  }

  public void addClient(Client client){
    clients.put(client.getRg(), client);
  }

 private void loadClients() {
    ArrayList<String> rows = super.clientFile.read();

    for (String clientString : rows) {
        Client client = Client.fromString(clientString);
        if (client != null) {
            this.clients.put(client.getRg(), client);
        }
    }
  }
}
