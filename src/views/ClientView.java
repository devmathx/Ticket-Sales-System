package views;

import java.util.ArrayList;
import models.Client;
import utils.Keyboard;

public class ClientView extends View {
    
    public ClientView(){}

    public boolean menu(){
        String option;
        
        System.out.println("\n\n===== AREA CLIENTES =====\n");
        System.out.println("(1) - Cadastrar Cliente");
        System.out.println("(2) - Visualizar Clientes");
        System.out.println("(3) - Remover Clientes");
        System.out.println("(4) - Voltar");

        option = Keyboard.inputString("\n>> Informe sua opção: ");

        switch (option) {
            case "1":
                if(!this.create()) System.out.println("\t>> Erro ao cadastrar! <<\n");
                break;
            case "2":
                this.get();
                break;
            case "3":
                if(!this.remove()) System.out.println("\t>> Erro, Cliente não encontrado! <<\n");
                break;
            case "4":
                return true;
            default:
                return false;
        }

        return true;
    }

    public boolean create() {
        System.out.println("\n\n== CADASTRO DE CLIENTE ==\n");
        String name = Keyboard.inputString(">> Nome Completo: ");
        String phone = Keyboard.inputString(">> Número de Telefone: ");
        String rg = Keyboard.inputString(">> RG: ");

        if(name == null || phone == null || rg.length() != 9){
            return false;
        }

        if(clients.create(name, phone, rg)){
            System.out.println("\t>> Cliente cadatrado com sucesso! <<\n");
            return true;
        }
    
        return false;
    }

    public boolean remove() {
        System.out.println("\n\n== REMOVER CLIENTE ==\n");
        String rg = Keyboard.inputString(">> Informe o RG que deseja remover: ");

        rg = rg.trim();

        Client client = clients.find(rg);
        if(client !=  null){
            clients.remove(rg);
            System.out.println("\t>> Cliente removido com sucesso! <<\n");
            return true;
        }

        return false;
    }

    public void get() {
        System.out.println("\n\n== LISTA DE CLIENTES ==\n");

        ArrayList<Client> clients = super.clients.getList();
        
        for(Client client :  clients){
            System.out.println("(" + client.getRg() + ") ->\t" + client);
        }
    }
}
