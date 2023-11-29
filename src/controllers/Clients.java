package controllers;

import java.util.ArrayList;

import interfaces.Controller;
import models.Client;
import services.FileTXT;

public class Clients implements Controller<Client> {

  private ArrayList<Client> clients = new ArrayList<>();
  private final FileTXT TXT;

  public Clients() {
    this.TXT = new FileTXT("clients");
    this.TXT.removeEmptyLines();
  }

  public boolean create(String name, String phone, String rg) {
    Client client = new Client(rg, name, phone);

    final boolean contain = this.clients.contains(client);
    if (contain) return false;

    this.clients.add(client);
    
    this.TXT.write(client.toString());

    return true;
  }

  public Client find(String rg) {
    for (Client c : this.clients) {
      if (c.getRg() == rg) {
        return c;
      }
    }

    return null;
  }

  public boolean remove(String rg) {
    Client aux = this.find(rg);

    if (aux == null) return false;

    this.clients.remove(aux);
    this.TXT.remove(aux.toString());

    return true;
  }

  public ArrayList<Client> getList() {
    return this.clients;
  }
}
