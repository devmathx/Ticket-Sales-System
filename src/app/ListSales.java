package app;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import models.Sale;

public class ListSales extends JFrame {
  private JList<String> listSales;
  private DefaultListModel<String> listModel;

  public ListSales(ArrayList<Sale> sales) {
    // Configurações da tela
    setTitle("Listagem de Vendas");
    setSize(700, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Listagem
    listModel = new DefaultListModel<>();
    for (Sale sale : sales) {
      listModel.addElement(this.buildComponent(sale));
    }

    listSales = new JList<>(listModel);
    listSales.setCellRenderer(new SaleRenderer()); 
    listSales.setFixedCellHeight(40); 
    listSales.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    JScrollPane scrollPane = new JScrollPane(listSales);

    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(scrollPane, BorderLayout.CENTER);
  }

  private String buildComponent(Sale sale) {
    String text = "";
    text += "Codigo: " + sale.getCode();
    text += " -- Comprador: " + sale.getBuyer().getName();
    text += " -- Código de Voô: " + sale.getFlight().getCode();
    text += " -- Origem: " + sale.getFlight().getOrigin();
    text += " -- Destino: " + sale.getFlight().getDestiny();
    text += " -- Data: " + sale.getHour();
    
    return text;
  }

  public void updateSalesList(ArrayList<Sale> sales) {
    listModel.clear();
    for (Sale sale : sales) {
      listModel.addElement(buildComponent(sale));
    }
  }
}

class SaleRenderer extends DefaultListCellRenderer {
  @Override
  public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
    JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

    label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

    return label;
  }
}