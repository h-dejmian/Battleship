package game.playfield;

public class Square {
    private int x;
    private int y;
    private SquareStatus status;

    public Square(int x, int y, SquareStatus status) {
        this.x = x;
        this.y = y;
        this.status = status;
    }

    public SquareStatus getStatus() {
        return status;
    }

    public void setStatus(SquareStatus status) {
        this.status = status;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.valueOf(status.getSymbol());
    }
}
