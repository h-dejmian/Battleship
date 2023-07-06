package game.playfield;

public enum SquareStatus {
    EMPTY(" "), SHIP("●"), HIT("●"), MISSED("—"), SUNK_SHIP("ø");
    private final String symbol;

    SquareStatus(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
