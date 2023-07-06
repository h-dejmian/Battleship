package game.utils;

import game.playfield.Board;
import game.Player;
import game.playfield.Square;

public class Display {
    private static final String BLUE_BG_BLACK_FONT = ConsoleColors.BLACK_BOLD + ConsoleColors.BLUE_BACKGROUND;
    private static final String BLUE_BG_BLACK_UNDERLINED = ConsoleColors.BLACK_UNDERLINED + ConsoleColors.BLUE_BACKGROUND;

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printMessage(String message, String color) {
        System.out.println(color + message + ConsoleColors.RESET);
    }

    public void printWelcomeMessage() {
        System.out.println(ConsoleColors.BLUE + "______       _   _   _      _____ _     _       \n" +
                "| ___ \\     | | | | | |    /  ___| |   (_)      \n" +
                "| |_/ / __ _| |_| |_| | ___\\ `--.| |__  _ _ __  \n" +
                "| ___ \\/ _` | __| __| |/ _ \\`--. \\ '_ \\| | '_ \\ \n" +
                "| |_/ / (_| | |_| |_| |  __/\\__/ / | | | | |_) |\n" +
                "\\____/ \\__,_|\\__|\\__|_|\\___\\____/|_| |_|_| .__/ \n" +
                "                                         | |    \n" +
                "                                         |_|" + ConsoleColors.RESET);
    }

    public void printWinMessage(Player player) {
        scrollScreenDown();
        System.out.println("Dear " + player + "...");
        System.out.println();
        System.out.println(ConsoleColors.BLUE + "     )    )                       )      )     ____ ____ ____ \n" +
                "  ( /( ( /(           (  (     ( /(   ( /(    |   /|   /|   / \n" +
                "  )\\()))\\())     (    )\\))(   ')\\())  )\\())   |  / |  / |  /  \n" +
                " ((_)\\((_)\\      )\\  ((_)()\\ )((_)\\  ((_)\\    | /  | /  | /   \n" +
                "__ ((_) ((_)  _ ((_) _(())\\_)() ((_)  _((_)   |/   |/   |/    \n" +
                "\\ \\ / // _ \\ | | | | \\ \\((_)/ // _ \\ | \\| |  (    (    (      \n" +
                " \\ V /| (_) || |_| |  \\ \\/\\/ /| (_) || .` |  )\\   )\\   )\\     \n" +
                "  |_|  \\___/  \\___/    \\_/\\_/  \\___/ |_|\\_| ((_) ((_) ((_)" + ConsoleColors.RESET);
    }

    public void printBoard(Board board, Player player) {
        Square[][] fields = board.getFields();

        scrollScreenDown();

        char letter = 'A';
        System.out.println("    " + player);
        System.out.println("   " + ConsoleColors.BLUE + " 1   2   3   4   5   6   7   8" + ConsoleColors.RESET);

        for (int i = 0; i < fields.length; i++) {

            System.out.print(ConsoleColors.YELLOW_BOLD + letter + "| " + ConsoleColors.RESET);

            displayFieldRow(fields[i], i);

            if (i > 1 && i < 5) System.out.print("   " + Board.getPlacingShipsGuide()[i - 2]);

            letter++;
            System.out.println();
        }

        System.out.println(board.getMessage());
    }

    public void printBoard(Player[] players) {
        scrollScreenDown();

        Square[][] fields1 = players[0].getShootingBoard().getFields();
        Square[][] fields2 = players[1].getShootingBoard().getFields();

        char letter = 'A';

        int gapBetweenNames = 43 - players[0].getName().length();
        System.out.print("    " + players[0]);
        for (int i = 0; i < gapBetweenNames; i++) System.out.print(" ");
        System.out.println(players[1]);

        System.out.println("   " + ConsoleColors.BLUE + " 1   2   3   4   5   6   7   8" +
                                            "              1   2   3   4   5   6   7   8" + ConsoleColors.RESET);

        for (int i = 0; i < fields1.length; i++) {

            System.out.print(ConsoleColors.YELLOW_BOLD + letter + "| " + ConsoleColors.RESET);
            displayFieldRow(fields1[i], i);

            System.out.print("         " + ConsoleColors.YELLOW_BOLD + letter + "| " + ConsoleColors.RESET);

            displayFieldRow(fields2[i], i);

            if (i < 6) System.out.print("   " + Board.getShootingGuide()[i]);

            letter++;
            System.out.println();
        }

        printScore(players);
        printBoardMessage(players[0]);
    }

    private void printScore(Player[] players) {
        System.out.println(ConsoleColors.YELLOW_BOLD +
                "   Score: " + players[0].getScore() + "                                " +
                "   Score: " + players[1].getScore() + ConsoleColors.RESET);
    }

    private void printBoardMessage(Player player) {
        Board board = player.getBoard();
        System.out.println(board.getMessage());
    }

    private void displayFieldRow(Square[] fields, int outerLoopIndex) {
        for (int j = 0; j < fields.length; j++) {
            if (outerLoopIndex == fields.length - 1 && j == fields.length - 1) {
                System.out.print(BLUE_BG_BLACK_FONT + " " + fields[j] + " " + ConsoleColors.RESET);
                continue;
            }
            if (outerLoopIndex == fields.length - 1) {
                System.out.print(BLUE_BG_BLACK_FONT + " " + fields[j] + " │" + ConsoleColors.RESET);
                continue;
            }

            if (j == fields.length - 1) {
                System.out.print(BLUE_BG_BLACK_UNDERLINED + " " + fields[j] + " " + ConsoleColors.RESET);
                continue;
            }
            System.out.print(BLUE_BG_BLACK_UNDERLINED + " " + fields[j] + " │" + ConsoleColors.RESET);
        }
    }

    private void scrollScreenDown() {
        for (int i = 0; i < 50; i++) System.out.println();
    }
}
