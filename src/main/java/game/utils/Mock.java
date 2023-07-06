package game.utils;

import game.Player;
import game.playfield.Board;
import game.playfield.Ship;
import game.playfield.Square;
import game.playfield.SquareStatus;

import java.util.ArrayList;
import java.util.List;

public class Mock {

    public static void mockData(Player player) {
        Board board = new Board(8);
        Board shootingBoard = new Board(8);

        Square[][] fields = board.getFields();

        List<Square> shipCoords = new ArrayList<>();
        fields[0][0] = new Square(0, 0, SquareStatus.SHIP);
        fields[0][1] = new Square(0, 1, SquareStatus.SHIP);
        shipCoords.add(fields[0][0]);
        shipCoords.add(fields[0][1]);
        Ship ship = new Ship(shipCoords);

        List<Square> shipCoords2 = new ArrayList<>();
        fields[2][0] = new Square(2, 0, SquareStatus.SHIP);
        fields[3][0] = new Square(3, 0, SquareStatus.SHIP);
        shipCoords2.add(fields[2][0]);
        shipCoords2.add(fields[3][0]);
        Ship ship2 = new Ship(shipCoords2);

        player.getShips().add(ship);
        player.getShips().add(ship2);

        player.setBoard(board);
        player.setShootingBoard(shootingBoard);
    }
}
