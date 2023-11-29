package models;
public class Airplane {
  
  private String code;
  private String name;
  private int seatsQuantity;
  private int busy = 0;
  private boolean available = true; //está

  public Airplane(String code, String name, int seatsQuantity) {
    this.code = code;
    this.name = name;
    this.seatsQuantity = seatsQuantity;
  }

  public Airplane(String code, String name, int seatsQuantity, int busy, boolean used) {
    this.code = code;
    this.name = name;
    this.seatsQuantity = seatsQuantity;
    this.busy = busy;
    this.available = used;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean occupySeat() {
    if (this.busy == this.seatsQuantity) {
      return false;
    }

    this.busy++;
    return true;
  }

  public boolean getAvailable(){
    return this.available;
  }

  public void setAvailable(boolean available) {
      this.available = available;
  }

  public String getName() {
    return name;
  }

  public String getCode() {
    return code;
  }


  public int getBusy() {
      return busy;
  }

  public int getSeatsQuantity() {
      return seatsQuantity;
  }

  public void setSeatsQuantity(int seatsQuantity) {
      this.seatsQuantity = seatsQuantity;
  }

  public void setBusy(int busy) {
      this.busy = busy;
  }

  public String toString() {
    String text = "";
    text += "Codigo: " + this.code + " | ";
    text += "Nome: " + this.name + " | ";
    text += "Assentos: " + this.seatsQuantity + " | ";
    text += "Ocupados: " + this.busy + " | ";
    text += "Disponivel: " + this.available;
    return text;
  }


  public static Airplane fromString(String input) {
    String[] parts = input.split("\\|");

    if (parts.length >= 3) { // Verifica se há pelo menos 3 partes
        String codigo = parts[0].trim().replace("Codigo: ", "");
        String nome = parts[1].trim().replace("Nome: ", "");
        int assentos = Integer.parseInt(parts[2].trim().replace("Assentos: ", ""));
        int ocupados = Integer.parseInt(parts[3].trim().replace("Ocupados: ", ""));
        boolean available = Boolean.valueOf(parts[4].trim().replace("Disponivel: ", "")).booleanValue();

        return new Airplane(codigo, nome, assentos, ocupados, available);
    } else {
        // Se não houver informações suficientes, você pode lançar uma exceção ou lidar de outra forma
        throw new IllegalArgumentException("Formato de entrada inválido: " + input);
    }
  }
}
