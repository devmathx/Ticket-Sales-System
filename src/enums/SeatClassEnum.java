package enums;

public class SeatClassEnum {
  
  public static final SeatClassEnum ECONOMIC = new SeatClassEnum("Econômica");
  public static final SeatClassEnum FIRST_CLASS = new SeatClassEnum("Primeira classe");
  public static final SeatClassEnum EXECUTIVE = new SeatClassEnum("Executiva");

  private String extenso;

  private SeatClassEnum(String s) {
    this.extenso = s;
  }

  public String getExtenso() {
    return extenso;
  }
}

