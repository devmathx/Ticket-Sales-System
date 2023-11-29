package views;

import controllers.Airplanes;
import controllers.Clients;
import controllers.Flights;
import controllers.Reports;
import controllers.Sales;

public abstract class View {

    protected Airplanes airplanes = new Airplanes();
    protected Clients clients = new Clients();
    protected Flights flights = new Flights();
    protected Sales sales = new Sales();
    protected Reports reports = new Reports();

    public abstract boolean menu();
    public abstract void get();
    public abstract boolean remove();
    public abstract boolean create();
}