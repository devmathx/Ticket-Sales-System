import controllers.Airplanes;
import controllers.Clients;
import utils.Keyboard;
import views.AirplaneView;
import views.ClientView;
import views.FlightView;
import views.ReportView;
import views.SaleView;
import views.View;

public class App {
  private Clients clients;
  private Airplanes airplanes;
  //CADASTRO DE CLIENTES
  //CADASTRO DE AVIÕES
  //CADASTRO DE VOOS
  //VENDA DE PASSAGENS
  //RELATÓRIOS DE VENDAS


  public App() {
    // airplanes = new Airplanes();



  }

  public void execute() {
      menu();
  }



  public void menu(){
    
    String option;
    boolean flag = false;
    View selected;


    do{
        System.out.println("\n===== VENDA DE PASSAGENS AEREAS =====\n");
        System.out.println("( 1 ) - Area Clientes");
        System.out.println("( 2 ) - Area Aviões"); 
        System.out.println("( 3 ) - Area Voos");
        System.out.println("( 4 ) - Area de vendas");
        System.out.println("( 5 ) - Emitir relário de vendas");
        System.out.println("( 0 ) - Encerrar Serviço");  
        option = Keyboard.inputString("\n>> Informe sua opção: ");  



        switch (option) {
          case "1" : 
            selected = new ClientView();
            flag = selected.menu();
            break;

          case "2":
            selected = new AirplaneView();
            flag = selected.menu();
            break;

          case "3":
            selected = new FlightView();
            flag = selected.menu();
            break;

          case "4":
            selected = new SaleView();
            flag = selected.menu();
            break;

          case "5":
            selected = new ReportView();
            flag = selected.menu();
            break;

          case "0": 
            System.out.println("\nPrograma Encerrado...\n");
            return;
        
          default:
            flag = false;
            break;
        }

        
        if(!flag){
          String newTry = Keyboard.inputString("Opção inválida...\nDeseja tentar novamente? (1 - Sim | 2 - Não)");
          if(newTry.equals("1")) flag = true;
        }
      }while(flag);

      System.out.println("\nPrograma Encerrado...\n");
  }



  private boolean cadastroAviao(){
    System.out.println("\n== CADASTRO DE AVIÃO ==\n");
    String name = Keyboard.inputString(">> Nome do Avião: ");
    Integer seatsQuantity = Keyboard.inputInt(">> Número de acentos: ");


    if(name == null | seatsQuantity == null){
       return false;
    }

    if(airplanes.create(name, seatsQuantity)){
      return true;
    }
    
    return false;
  }


  private boolean cadastroFlight(){
    System.out.println("\n== CADASTRO DE VOO ==\n");
    String name = Keyboard.inputString(">> Nome do Avião: ");
    Integer seatsQuantity = Keyboard.inputInt(">> Número de acentos: ");


    if(name == null | seatsQuantity == null){
       return false;
    }

    if(airplanes.create(name, seatsQuantity)){
      return true;
    }
    
    return false;
  }
}
