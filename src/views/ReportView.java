package views;

import java.util.ArrayList;

import models.Client;
import utils.Keyboard;

public class ReportView extends View {

    public boolean menu(){
        String option;
        
        System.out.println("\n\n===== AREA RELATORIOS =====\n");
        System.out.println("(1) - Por cliente");
        System.out.println("(2) - Por Avião");
        System.out.println("(3) - Por origem");
        System.out.println("(4) - Por Destino");
        System.out.println("(5) - Voltar");

        option = Keyboard.inputString("\n>> Informe sua opção: ");

        switch (option) {
            case "1":
                if(!this.porCliente()) System.out.println("\t>> Erro ao criar relatório <<\n");
                break;
            case "2":
                if(!this.porAirplane()) System.out.println("\t>> Erro ao criar relatório <<\n");
                break;
            case "3":
                if(!this.porOrigem()) System.out.println("\t>> Erro ao criar relatório <<\n");
                break;
            case "4":
                if(!this.porDestino()) System.out.println("\t>> Erro ao criar relatório <<\n");
                break;
            case "5":
                return true;
            default:
                return false;
        }

        return true;
    }

    public boolean create(){
        return true;
    }

    public boolean porCliente(){
        String rg = Keyboard.inputString("\n>> Informe o RG: ");

        if(reports.createReportClient(rg)){
            System.out.println("Criado com sucesso");
            return true;
        }
        else{
            System.out.println("Erro ao criar!");
            return false;
        }
    }

    public boolean porAirplane(){
        String code = Keyboard.inputString("\n>> Informe o código do aivão: ");

        if(reports.createReportAirplane(code)){
            System.out.println("\n>> Criado com sucesso <<\n");
            return true;
        }

        return false;
    }

    public boolean porOrigem(){
        String origin = Keyboard.inputString("\n>> Informe a origem: ");

        if(reports.createReportOrigin(origin)){
            System.out.println("\n>> Criado com sucesso <<\n");
            return true;
        }

        return false;
    }

    public boolean porDestino(){
        String destiny = Keyboard.inputString("\n>> Informe o destino: ");

        if(reports.createReportDestiny(destiny)){
            System.out.println("\n>> Criado com sucesso <<\n");
            return true;
        }

        return false;
    }





    public boolean remove(){
       return false;
    }

    public void get(){}
}
