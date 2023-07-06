package game;

import game.playfield.Board;
import game.playfield.Ship;
import game.utils.ConsoleColors;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final List<Ship> ships = new ArrayList<>();
    private final String name;
    private final String color;
    private Board board;
    private Board shootingBoard = new Board(8);
    private int score;

    public Player(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Board getShootingBoard() {
        return shootingBoard;
    }

    public void setShootingBoard(Board shootingBoard) {
        this.shootingBoard = shootingBoard;
    }

    public List<Ship> getShips() {
        return ships;
    }

    @Override
    public String toString() {
        return color + name + ConsoleColors.RESET;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
