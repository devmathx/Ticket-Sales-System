package models;

public class Client {
  private String rg;
  private String name;
  private String phone;

  public Client(String rg, String name, String phone) {
    this.rg = rg;
    this.name = name;
    this.phone = phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRg() {
    return this.rg;
  }

  public String getName() {
    return this.name;
  }

  public String toString() {
    String text = "";
    text += "RG: " + this.rg + " | ";
    text += "Nome: " + this.name + " | ";
    text += "Telefone: " + this.phone;
    return text;
  }

  public static Client fromString(String input) {
    String[] parts = input.split("\\|");

    if (parts.length >= 3) { // Verifica se há pelo menos 3 partes
      String rg = parts[0].trim().replace("RG: ", "");
      String nome = parts[1].trim().replace("Nome: ", "");
      String telefone = parts[2].trim().replace("Telefone: ", "");

      return new Client(rg, nome, telefone);
    } else {
      throw new IllegalArgumentException("Formato de entrada inválido: " + input);
    }
  }

  public Client find(String rgBuyer) {
    return null;
  }
}
