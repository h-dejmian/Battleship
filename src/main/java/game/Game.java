package game;

import game.playfield.*;
import game.utils.Display;
import game.utils.Input;

import java.util.List;

import static game.utils.Mock.mockData;

public class Game {
    private Display display;
    private Input input;

    public Game(Display display, Input input) {
        this.display = display;
        this.input = input;
    }

    public void start() {
        display.printWelcomeMessage();
        Player[] players = input.getPlayers();

//        mockData(players[0]);
//        mockData(players[1]);

        initShipPlacementPhase(players[0]);
        initShipPlacementPhase(players[1]);

        initShootingPhase(players);
    }

    private void initShipPlacementPhase(Player player) {
        display.printMessage(player + " ship placement:");
        display.printMessage("");

        Board board = new Board(8);

        int shipOrdinal = 0;
        while (shipOrdinal < ShipType.values().length) {

            display.printBoard(board, player);
            board.setMessage("");

            ShipType currentShip = ShipType.values()[shipOrdinal];

            display.printMessage(player + " please provide coordinates for " +
                    currentShip.toString().toLowerCase() + "(" +
                    currentShip.getLength() + " length)");

            String userInput = input.getUserInput(false);
            Square[] shipBorders = input.splitInput(userInput);

            List<Square> shipCoordinates = input.getShipCoordinates(shipBorders);
            if (!board.isShipValid(shipCoordinates, currentShip.getLength())) continue;

            Ship ship = new Ship(shipCoordinates);
            player.getShips().add(ship);

            board.placeShipOnBoard(shipCoordinates);
            shipOrdinal++;
        }

        display.printBoard(board, player);
        board.setMessage("");

        display.printMessage(player + " these are you ships!");
        input.promptEnterKey();

        player.setBoard(board);
    }

    private void initShootingPhase(Player[] players) {
        display.printBoard(players);

        int playerIndex = 0;
        Player currentPlayer = players[playerIndex];

        while (true) {
            display.printMessage("");
            display.printMessage(currentPlayer + " please provide coordinate for a shot:");
            String userInput = input.getUserInput(true);

            Square shot = input.getCoordinate(userInput);

            Player enemy = players[playerIndex ^ 1];

            shot(currentPlayer, enemy, shot);

            if (hasPlayerWon(enemy)) {
                display.printWinMessage(currentPlayer);
                break;
            }

            display.printBoard(players);

            playerIndex ^= 1;
            currentPlayer = players[playerIndex];
        }
    }

    private void shot(Player currentPlayer, Player enemy, Square shot) {
        Square[][] shootingBoard = currentPlayer.getShootingBoard().getFields();
        Square[][] enemyBoard = enemy.getBoard().getFields();
        int shotX = shot.getX();
        int shotY = shot.getY();

        SquareStatus status = enemy.getBoard().getFieldStatus(shot);

        switch (status) {
            case SHIP: {
                shootingBoard[shotX][shotY].setStatus(SquareStatus.HIT);
                enemyBoard[shotX][shotY].setStatus(SquareStatus.HIT);

                currentPlayer.getBoard().setMessage("You hit enemy ship!");

                Ship ship = enemy.getBoard().getShipByCoordinate(enemy, shot);
                addPoints(currentPlayer, SquareStatus.HIT, ship);

                if (enemy.getBoard().isShipSunk(ship)) {
                    enemy.getBoard().sunkShip(ship);
                    currentPlayer.getShootingBoard().sunkShip(ship);

                    addPoints(currentPlayer, SquareStatus.SUNK_SHIP, ship);
                    currentPlayer.getBoard().setMessage("You sunk enemy ship!");
                }
                break;
            }
            case EMPTY: {
                shootingBoard[shotX][shotY].setStatus(SquareStatus.MISSED);
                enemyBoard[shotX][shotY].setStatus(SquareStatus.MISSED);

                currentPlayer.getBoard().setMessage("You missed!");
                break;
            }
            case MISSED: {
                currentPlayer.getBoard().setMessage("Already tried!");
                break;
            }
            case HIT: {
                currentPlayer.getBoard().setMessage("Already hit!");
            }
        }
    }

    private void addPoints(Player player, SquareStatus status, Ship ship) {
        int shipLength = ship.getCoordinates().size();
        int pointsToAdd = 0;

        if (status == SquareStatus.HIT) {
            pointsToAdd += 10;
        }
        if (status == SquareStatus.SUNK_SHIP) {
            int sunkReward = 10 * shipLength;
            pointsToAdd += sunkReward;
        }
        player.setScore(player.getScore() + pointsToAdd);
    }

    private boolean hasPlayerWon(Player enemy) {
        Square[][] enemyFields = enemy.getBoard().getFields();

        for (int i = 0; i < enemyFields.length; i++) {
            for (int j = 0; j < enemyFields[0].length; j++) {
                if (enemyFields[i][j].getStatus() == SquareStatus.SHIP) {
                    return false;
                }
            }
        }
        return true;
    }
}
