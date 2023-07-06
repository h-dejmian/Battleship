package game.playfield;

import java.util.List;

public class Ship {
    private List<Square> coordinates;

    public Ship(List<Square> coordinates) {
        this.coordinates = coordinates;
    }

    public List<Square> getCoordinates() {
        return coordinates;
    }
}
