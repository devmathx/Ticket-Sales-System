package enums;

public enum SeatClassEnum {
    ECONOMIC("Econômica"),
    FIRST_CLASS("Primeira classe"),
    EXECUTIVE("Executiva");

    private final String extenso;

    private SeatClassEnum(String extenso) {
        this.extenso = extenso;
    }

    public String getExtenso() {
        return extenso;
    }
}

