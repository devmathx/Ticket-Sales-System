package views;

import java.util.ArrayList;
import enums.SeatClassEnum;
import models.Airplane;
import models.Flight;
import utils.Keyboard;

public class FlightView extends View{
    
    public FlightView(){}

    public boolean menu(){
        String option;
        
        System.out.println("\n\n===== AREA VOOS =====\n");
        System.out.println("(1) - Cadastrar Voo");
        System.out.println("(2) - Visualizar Voos");
        System.out.println("(3) - Remover Voos");
        System.out.println("(4) - Atualizar status");
        System.out.println("(5) - Voltar");

        option = Keyboard.inputString("\n>> Informe sua opção: ");

        switch (option) {
            case "1":
                if(!this.create()) System.out.println("\t>> Erro ao cadastrar voo! <<\n");
                break;
            case "2":
                this.getFromView();
                break;
            case "3":
                if(!this.remove()) System.out.println("\t>> Erro, Voo não encontrado! <<\n");
                break;
            case "4":
                if(!this.setStatusFly()) System.out.println("\t>> Erro ao mudar status! <<\n");
                return true;    
            default:
                return false;
        }

        return true;
    }

    public boolean create(){
        System.out.println("\n\n== CADASTRO DE VOO ==\n");
        String origin = Keyboard.inputString(">> Origem: ");
        String destiny = Keyboard.inputString(">> Destino: ");
        String horario = Keyboard.inputString(">> Horario do Voo: ");

        System.out.println("Classe: ");
        for (SeatClassEnum classe : SeatClassEnum.values()) {
            System.out.println("\t" + classe.ordinal() + ". " + classe);
        }

        Integer classe = Keyboard.inputInt(">> Sua classe: ");
        if(classe < 0 && classe >= SeatClassEnum.values().length) {
            System.out.println("\n>> Classe inválida <<\n");
            return false;
        }

        AirplaneView view = new AirplaneView();
        if(!view.getAvailable()){
            return false;
        }   

        String AirplaneCode = Keyboard.inputString("\n>> Informe o código do avião: ");
        Airplane airplane = super.airplanes.find(AirplaneCode);

        if(airplane == null){
            System.out.println("\n>> Código inválido <<");
            return false;
        }
        
        if(super.flights.create(origin, destiny, horario, SeatClassEnum.values()[classe], airplane)){
            System.out.println("\n\t>> Voo Cadastrado com sucesso <<\n");
            return true;
        }


        return false;
    }

    public boolean remove(){
        System.out.println("\n\n== REMOVER VOO ==\n");
        this.get();

        String option = Keyboard.inputString("\n>> Informe o numero do voo: ");
        if(super.flights.remove(option)){
            System.out.println("\n\t>> Voo removido com sucesso <<\n");
            return true;
        }


        return false;
    }

    public void get(){
        ArrayList<Flight> flights = super.flights.getList();

        int i = 0;
        for(Flight flight : flights){
            System.out.println("(" + flight.getCode()+ ")" + " ->\t" + flight);
            i++;
        }
    }

    public void getFromView(){
        System.out.println("\n\n== LISTA DE VOOS ==\n");

        ArrayList<Flight> flights = super.flights.getList();
        
        for(Flight flight :  flights){
            System.out.println("(" + flight.getCode() + ") ->\t" + flight);
        }
    }

    public boolean setStatusFly(){
        System.out.println("\n\n== ALTERAR STATUS DE VOO ==\n");
        this.get();

        String option = Keyboard.inputString("\n>> Informe o numero do voo: ");
        Flight flight = super.flights.find(option);
        
        if(flight == null){
            System.out.println("\n>> Este voo não existe <<");
            return false;
        }

        String dataText;
        if(!flight.getStatusFly()){
            dataText = "não iniciado, para em voo (1 - Sim | 2 - Não)? ";
        }
        else{
            dataText = "em voo para finalizado (1 - Sim | 2 - Não)? ";
        }


        String finalizeQuestion = Keyboard.inputString("Você confirma a atualização de status de " + dataText);

        if(finalizeQuestion.equals("1")){
           if(!super.flights.newStatus(flight.getCode())){
                return false;
           }

            System.out.println("\n\t>> Status alterado com sucesso! <<\n");
            return true;
        }
        else{
            System.out.println(">> Operação cancelada <<");
            return false;
        }
    }
}