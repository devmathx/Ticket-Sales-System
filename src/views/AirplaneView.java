package views;

import java.util.ArrayList;
import models.Airplane;
import utils.Keyboard;

public class AirplaneView extends View {

    public AirplaneView() {}

    public boolean menu() {
        String option;

        System.out.println("\n\n===== AREA AVIÕES =====\n");
        System.out.println("(1) - Cadastrar Avião");
        System.out.println("(2) - Visualizar Aviões");
        System.out.println("(3) - Remover Avião");
        System.out.println("(4) - Voltar");

        option = Keyboard.inputString("\n>> Informe sua opção: ");

        switch (option) {
            case "1":
                if (!this.create()) System.out.println("\t>> Erro ao cadastrar! <<\n");
                break;
            case "2":
                this.get();
                break;
            case "3":
                if (!this.remove())System.out.println("\t>> Erro, avião não encontrado! <<\n");
                break;
            case "4":
                return true;
            default:
                return false;
        }

        return true;
    }

    public boolean create() {
        System.out.println("\n\n== CADASTRO DE AVIÃO ==\n");
        String name = Keyboard.inputString(">> Nome: ");
        Integer number = Keyboard.inputInt(">> Número de Acentos: ");

        if (name == null || number == null) {
            return false;
        }

        if (airplanes.create(name, number)) {
            System.out.println("\t>> Avião cadatrado com sucesso! <<\n");
            return true;
        }

        return false;
    }

    public boolean remove() {
        System.out.println("\n\n== REMOVER AVIÃO ==\n");
        String code = Keyboard.inputString(">> Informe o código do avião que deseja remover: ");

        Airplane airplane = super.airplanes.find(code);
        if (airplane != null) {
            super.airplanes.remove(code);
            System.out.println("\t>> Avião removido com sucesso! <<\n");
            return true;
        }

        return false;
    }

    public void get() {
        System.out.println("\n\n== LISTA DE AVIÕES ==\n");

        ArrayList<Airplane> airplanes = super.airplanes.getList();

        for (Airplane airplane : airplanes) {
            System.out.println("(" + airplane.getCode() + ") ->\t" + airplane);
        }

    }

    public boolean getAvailable() {
        System.out.println("\nAviões disponíveis: ");
        ArrayList<Airplane> airplanes = super.airplanes.getList();
        int count = 0;

        for (Airplane airplane : airplanes) {
            if (airplane.getAvailable()) {
                count++;
                System.out.println(airplane);
            }
        }

        if (count == 0) {
            System.out.println("Não existem aviões disponíveis");
            return false;
        }

        return true;
    }
}
