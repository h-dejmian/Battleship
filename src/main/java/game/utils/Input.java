package game.utils;

import game.*;
import game.playfield.Board;
import game.playfield.Square;
import game.playfield.SquareStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {
    private final Scanner scanner = new Scanner(System.in);
    private final Display display = new Display();

    public String getUserInput(boolean isSingleCoordinate) {
        String userInput = scanner.nextLine().toUpperCase();

        if (userInput.equals("Q")) {
            display.printMessage("Bye!");
            System.exit(0);
        }

        while (!isUserInputValid(userInput, isSingleCoordinate)) {
            userInput = scanner.nextLine().toUpperCase();
        }

        return userInput;
    }

    public Player[] getPlayers() {
        Player[] players = new Player[2];

        display.printMessage("Please provide player 1 name:");
        String player1Name = scanner.nextLine();

        display.printMessage("Please provide player 2 name:");
        String player2Name = scanner.nextLine();

        players[0] = new Player(player1Name, ConsoleColors.WHITE);
        players[1] = new Player(player2Name, ConsoleColors.CYAN);

        return players;
    }

    private boolean isUserInputValid(String userInput, boolean isSingleCoordinate) {

        if (isSingleCoordinate) {
            if (userInput.length() != 2) {
                display.printMessage("Incorrect input length!", ConsoleColors.RED);
                return false;
            }
            return isCoordinateValid(userInput);
        }

        if (userInput.length() < 5 || userInput.length() > 7) {
            display.printMessage("Incorrect input length!", ConsoleColors.RED);
            return false;
        }

        if (!userInput.contains(" ")) {
            display.printMessage("game.utils.Input should be divided with space!", ConsoleColors.RED);
            return false;
        }

        String[] inputDivided = userInput.split(" ");
        String firstCoordinate = inputDivided[0];
        String secondCoordinate = inputDivided[1];

        if (!isCoordinateValid(firstCoordinate)) return false;
        if (!isCoordinateValid(secondCoordinate)) return false;

        return true;
    }

    private boolean isCoordinateValid(String coordinate) {
        if (!Character.isLetter(coordinate.charAt(0))) {
            display.printMessage("First character should be a letter!", ConsoleColors.RED);
            return false;
        }
        if (!Character.isDigit(coordinate.charAt(1))) {
            display.printMessage("Second character should be a number!", ConsoleColors.RED);
            return false;
        }
        if (coordinate.length() > 2 && !Character.isDigit(coordinate.charAt(2))) {
            display.printMessage("Third character should be a number!", ConsoleColors.RED);
            return false;
        }
        int firstChar = coordinate.charAt(0) - 65;
        int secondChar = Integer.parseInt(String.valueOf(coordinate.charAt(1)));

        if(firstChar < 0 || firstChar > 7 || secondChar < 0 || secondChar > 8) {
            display.printMessage("Numbers out of bounds!", ConsoleColors.RED);
            return false;
        }

        return true;
    }

    public Square[] splitInput(String userInput) {
        String[] inputDivided = userInput.split(" ");
        Square[] coordinates = new Square[2];

        for (int i = 0; i < inputDivided.length; i++) {
            coordinates[i] = getCoordinate(inputDivided[i]);
        }

        return coordinates;
    }

    public void promptEnterKey() {
        display.printMessage("Press \"ENTER\" to continue...");
        scanner.nextLine();
    }

    public Square getCoordinate(String userInput) {
        int x = userInput.charAt(0) - 65;
        int y;

        if (userInput.length() > 2) {
            String yStr = "" + userInput.charAt(1) + userInput.charAt(2);
            y = Integer.parseInt(yStr) - 1;
        } else {
            y = Character.getNumericValue(userInput.charAt(1)) - 1;
        }

        return new Square(x, y, SquareStatus.SHIP);
    }

    public List<Square> getShipCoordinates(Square[] shipBorders) {
        List<Square> shipCoordinates = new ArrayList<>();

        if (Board.isShipHorizontal(shipBorders)) {
            int index = shipBorders[0].getY();

            for (int i = index; i <= shipBorders[1].getY(); i++) {
                shipCoordinates.add(new Square(shipBorders[0].getX(), i, SquareStatus.SHIP));
            }
        } else {
            int index = shipBorders[0].getX();

            for (int i = index; i <= shipBorders[1].getX(); i++) {
                shipCoordinates.add(new Square(i, shipBorders[0].getY(), SquareStatus.SHIP));
            }
        }

        return shipCoordinates;
    }


}
