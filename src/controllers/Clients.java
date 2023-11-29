package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import interfaces.Controller;
import models.Client;
import services.FileTXT;


/**
 * Controle de Clientes
 */
public class Clients extends ControllerFields implements Controller<Client> {
  
  /**
  * Armazenamento de Clientes, usando seu RG como chave
  */
  private Map<String, Client> clients = new HashMap<>();

  /**
  * Construtior, cria arquivo, remove linhas e carrega os clientes do arquivo
  */
  public Clients() {
    super.clientFile = new FileTXT("clients");
    super.clientFile.removeEmptyLines();
    this.loadClients();
  }


  /**
  * Cria novo cliente usando seu RG como chave
  * @name nome do cliente
  * @phone número de telefone
  * @rg rg do cliente
  * @returns booleando que confirma ou não a criação
  */
  public boolean create(String name, String phone, String rg) {
    if(this.clients.containsKey(rg)){
      return false;
    }
    
    Client client = new Client(rg, name, phone);
    this.clients.put(rg, client);

    super.clientFile.write(client.toString());

    return true;
  }

  /**
  * Busca o objeto com base no parametro, retorna nulo caso não encontrar
  * @code string de identificação do objeto
  */
  public Client find(String rg) {
    return this.clients.get(rg);
  }

  /**
  * Remove o objeto com base na chave 
  * @code string de identificação do objeto a ser apagado
  * @return boolean de confirmação
  */
  public boolean remove(String rg) {
    Client aux = this.clients.remove(rg);

    if (aux == null) return false;

    this.clients.remove(rg);
    super.clientFile.remove(aux.toString());

    return true;
  }

  /**
  * Devolve arrayList com os objetos
  */
  public ArrayList<Client> getList() {
    return new ArrayList<>(this.clients.values());
  }

  public void addClient(Client client){
    clients.put(client.getRg(), client);
  }


  /**
  * Carrega objetos do aruivo de database
  */
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
