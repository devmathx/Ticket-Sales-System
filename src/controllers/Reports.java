package controllers;

import java.util.ArrayList;

import interfaces.Controller;
import models.Report;
import services.FileTXT;

public class Reports implements Controller<Report> {

  private ArrayList<Report> reports = new ArrayList<>();
  private final FileTXT TXT;

  public Reports() {
    this.TXT = new FileTXT("flights");
    this.TXT.removeEmptyLines();
  }

  public Report find(String param) {
    throw new UnsupportedOperationException("Unimplemented method 'find'");
  }

  public boolean remove(String param) {
    throw new UnsupportedOperationException("Unimplemented method 'remove'");
  }

  public ArrayList<Report> getList() {
    return this.reports;
  }
  
}
