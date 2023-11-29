package views;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import app.ListSales;
import models.Sale;
import utils.Keyboard;

public class SaleView extends View {
    private ListSales screen;

    public SaleView() {
        this.screen = new ListSales(super.sales.getList());
        this.viewScreen();
    }

    public boolean menu() {
        String option;

        System.out.println("\n\n===== AREA VENDA DE PASSAGEM =====\n");
        System.out.println("(1) - Realizar Venda");
        System.out.println("(2) - Visualizar Vendas");
        System.out.println("(3) - Voltar");

        option = Keyboard.inputString("\n>> Informe sua opção: ");

        switch (option) {
            case "1":
                if (!this.create())
                    System.out.println("\t>> Erro ao cadastrar! <<\n");
                ;
                break;
            case "2":
                this.getFromView();
                break;
            case "3":
                return true;
            default:
                return false;
        }

        return true;
    }

    public boolean create() {
        System.out.println("\n\n== REALIZAR VENDA ==\n");
        String rg = Keyboard.inputString(">> RG do cliente: ");
        String code = Keyboard.inputString(">> Código do voo: ");

        if (super.sales.create(rg, code)) {
            System.out.println("\n\t>> Venda Realizada com Sucesso! <<\n");
            this.AttScreen();
            return true;
        }

        return false;
    }

    public boolean remove() {
        return false;
    }

    public void get() {
        ArrayList<Sale> sales = super.sales.getList();

        for (Sale s : sales) {
            System.out.println("(" + s.getCode() + ")" + " ->\t" + s);
        }
    }

    public void getFromView() {
        System.out.println("\n\n== LISTA DE VENDAS ==\n");

        ArrayList<Sale> sales = super.sales.getList();

        for (Sale sale : sales) {
            System.out.println("(" + sale.getCode() + ") ->\t" + sale);
        }
    }

    public void AttScreen() {
        SwingUtilities.invokeLater(() -> {
            screen.updateSalesList(super.sales.getList());
            screen.setVisible(true);
        });
    }

    public void viewScreen() {
        SwingUtilities.invokeLater(() -> {
            this.screen = new ListSales(super.sales.getList());
            this.screen.setVisible(true);
        });
    }
}
