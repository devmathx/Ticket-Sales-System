import java.util.ArrayList;

import javax.swing.SwingUtilities;

import app.ListSales;
import controllers.Sales;
import enums.SeatClassEnum;
import models.Airplane;
import models.Client;
import models.Flight;

public class Main {
  public static void main(String[] args) {

    Sales sales = new Sales();
    Client client = new Client("1234456789", "Matheus", "5199488535");
    Airplane airplane = new Airplane("000", "Jato", 30);
    Flight flight = new Flight("China", "Brasil", "10", SeatClassEnum.ECONOMIC, airplane);

    sales.create(client, flight);
    sales.create(client, flight);
    sales.create(client, flight);
    sales.create(client, flight);

    SwingUtilities.invokeLater(() -> {
      ListSales screen = new ListSales(sales.getList());
      screen.setVisible(true);
    });
  }
}
