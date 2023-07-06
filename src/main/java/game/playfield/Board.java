package game.playfield;

import game.Player;
import game.utils.ConsoleColors;

import java.util.List;

public class Board {
    private final Square[][] fields;
    private static String message = "";
    private static final String[] PLACING_SHIPS_GUIDE = {"Format your coordinates as follows:",
            "First coordinate + SPACE + last coordinate",
            "For example: B2 C3"};
    private static final String[] SHOOTING_GUIDE = {"Format your coordinate as follows:",
            "Letter for a row + Number for a column",
            "For example: B2",
            "",
            "Legend:   ●  --> hit    —  --> missed shot",
            "          ø  --> sunk ship"};


    public Board(int size) {
        this.fields =  new Square[size][size];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < fields.length; i++) {
            for(int j = 0; j < fields[0].length; j++) {
                fields[i][j] = new Square(i, j, SquareStatus.EMPTY);
            }
        }
    }

    public boolean isShipValid(List<Square> shipCoordinates, int currentShipLength) {
        if(shipCoordinates.size() != currentShipLength) {
            message = ConsoleColors.RED + "Incorrect ship length!" + ConsoleColors.RESET;
            return false;
        }
        for(Square square : shipCoordinates) {
            int x = square.getX();
            int y = square.getY();

            if(fields[x][y].getStatus() == SquareStatus.SHIP) {
                message = ConsoleColors.RED + "There is already ship in this place!" + ConsoleColors.RESET;
                return false;
            }
            if(isShipTooNear(square)) {
                message = ConsoleColors.RED + "game.playfield.Ship is too near!" + ConsoleColors.RESET;
                return false;
            }
        }
        return true;
    }

    public boolean isShipTooNear(Square square) {
        int x = square.getX();
        int y = square.getY();

        if(y > 0 && y < fields.length - 1 && fields[x][y - 1].getStatus() == SquareStatus.SHIP) return true;
        if(y > 0 && y < fields.length - 1 && fields[x][y + 1].getStatus() == SquareStatus.SHIP) return true;

        for(int i = y - 1; i <= y + 1; i++) {
            if(i < 0 || i >= fields.length || x < 1 || x >= fields.length - 1) continue;
            if(fields[x - 1][i].getStatus() == SquareStatus.SHIP) return true;
            if(fields[x + 1][i].getStatus() == SquareStatus.SHIP) return true;
        }

        return false;
    }

    public boolean isShipSunk(Ship ship) {
        List<Square> coordinates = ship.getCoordinates();

        for(Square coordinate : coordinates) {
            if(coordinate.getStatus() == SquareStatus.SHIP) return false;
        }
        return true;
    }

    public void sunkShip(Ship ship) {
        List<Square> coordinates = ship.getCoordinates();

        for(Square coordinate : coordinates) {
            fields[coordinate.getX()][coordinate.getY()].setStatus(SquareStatus.SUNK_SHIP);
        }
    }

    public Ship getShipByCoordinate(Player player, Square inputSquare) {
        List<Ship> ships = player.getShips();

        for(Ship ship : ships) {
            List<Square> coordinates = ship.getCoordinates();
            for(Square shipCoordinate : coordinates) {
                if(shipCoordinate.getX() == inputSquare.getX() && shipCoordinate.getY() == inputSquare.getY()) {
                    return ship;
                }
            }
        }
        return null;
    }

    public void placeShipOnBoard(List<Square> coordinates) {

        for (Square coordinate : coordinates) {
            int x = coordinate.getX();
            int y = coordinate.getY();

            fields[x][y] = coordinate;
        }
    }

    public static boolean isShipHorizontal(Square[] shipBorders) {
        return shipBorders[0].getX() == shipBorders[1].getX();
    }

    public SquareStatus getFieldStatus(Square square) {
        return fields[square.getX()][square.getY()].getStatus();
    }

    public Square[][] getFields() {
        return fields;
    }

    public String getMessage() {
        return ConsoleColors.YELLOW_BOLD + message + ConsoleColors.RESET;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String[] getPlacingShipsGuide() {
        return PLACING_SHIPS_GUIDE;
    }

    public static String[] getShootingGuide() {
        return SHOOTING_GUIDE;
    }


}
