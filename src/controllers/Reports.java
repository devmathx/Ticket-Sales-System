package controllers;

import java.util.ArrayList;

import interfaces.Controller;
import models.Airplane;
import models.Client;
import models.Report;
import models.Sale;
import services.FileCSV;

/**
 * Controle de Relatórios
 */
public class Reports extends ControllerFields implements Controller<Report> {
   /**
  * Armazenamento de relatórios
  */
  private ArrayList<Report> reports = new ArrayList<>();
  private FileCSV CSV;
  private Sales sales = new Sales();
  private ArrayList<Sale> fullSales;

  /**
  * Construtior, coleta todas as vendas de sales
  */
  public Reports() {
    fullSales = sales.getList();
  }

  /**
  * Cria relatório com base no rg do cliente
  * @RG string rg do usuário
  * @return bolleando indicando se o documento foi criado com sucesso
  */
  public boolean createReportClient(String RG) {
    Clients clients  = new Clients();
    Client client = clients.find(RG);

    if(client == null){
      return false;
    }

    ArrayList<Sale> salesClient = new ArrayList<>();

    for(Sale s : fullSales){
      if(s.getBuyer().getRg().equals(client.getRg())){
        salesClient.add(s);
      }
    }

    if(salesClient.size() == 0){
      System.out.println("\n>> Não existem vendas para esse cliente <<");
      return false;
    }

    this.CSV = new FileCSV("Relatorio_" + client.getRg() + "_" + client.getName());
    String csvString = "";
    
    for(Sale s : salesClient){
      csvString += s.toCSVString() + "\n";
    }

    this.CSV.write(csvString, false);

    return true;
  }
    
  /**
  * Cria relatório com base no codigo do avião
  * @code código do avião a ser gerado o relatório
  * @return bolleando indicando se o documento foi criado com sucesso
  */
  public boolean createReportAirplane(String code){
    Airplanes airplanes = new Airplanes();
    Airplane airplane = airplanes.find(code);

    if(airplane == null){
      return false;
    }

    ArrayList<Sale> salesAirplane = new ArrayList<>();

    for(Sale s : fullSales){
      if(s.getFlight().getAirplane().getCode().equals(airplane.getCode())){
        salesAirplane.add(s);
      }
    }

    if(salesAirplane.size() == 0){
      System.out.println("\n>> Não existem vendas para esse cavião <<");
      return false;
    }

    this.CSV = new FileCSV("Relatorio_" + airplane.getCode() + "_" + airplane.getName());
    String csvString = "";
    
    for(Sale s : salesAirplane){
      csvString += s.toCSVString() + "\n";
    }

    this.CSV.write(csvString, false);

    return true;
  }

  /**
  * Cria relatório com base na origem
  * @origin string de origem do voo
  * @return bolleando indicando se o documento foi criado com sucesso
  */
  public boolean createReportOrigin(String origin){
    ArrayList<Sale> salesOrigin = new ArrayList<>();

    for(Sale s : fullSales){
      if(s.getFlight().getOrigin().equals(origin)){
        salesOrigin.add(s);
      }
    }

    if(salesOrigin.size() == 0){
      System.out.println("\n>> Nenhuma venda com esta origem <<");
      return false;
    }

    this.CSV = new FileCSV("Relatorio_Origem_" + origin);
    String csvString = "";
    
    for(Sale s : salesOrigin){
      csvString += s.toCSVString() + "\n";
    }

    this.CSV.write(csvString, false);

    return true;
  }

  /**
  * Cria relatório com base no destino
  * @destiny string de origem do voo
  * @return bolleano indicando se o documento foi criado com sucesso
  */
  public boolean createReportDestiny(String destiny){
    ArrayList<Sale> salesDestiny = new ArrayList<>();

    for(Sale s : fullSales){
      if(s.getFlight().getDestiny().equals(destiny)){
        salesDestiny.add(s);
      }
    }

    if(salesDestiny.size() == 0){
      System.out.println("\n>> Nenhuma venda com este destino <<");
      return false;
    }

    this.CSV = new FileCSV("Relatorio_Destino_" + destiny);
    String csvString = "";
    
    for(Sale s : salesDestiny){
      csvString += s.toCSVString() + "\n";
    }

    this.CSV.write(csvString, false);

    return true;
  }

  public Report find(String param) {
    throw new UnsupportedOperationException("Unimplemented method 'find'");
  }

  public boolean remove(String param) {
    throw new UnsupportedOperationException("Unimplemented method 'remove'");
  }

  /**
  * Devolve arrayList com os objetos
  */
  public ArrayList<Report> getList() {
    return this.reports;
  }
}
