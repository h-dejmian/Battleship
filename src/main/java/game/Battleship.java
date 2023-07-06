package game;

import game.utils.Display;
import game.utils.Input;

public class Battleship {

    public static void main(String[] args) {
        Display display = new Display();
        Input input = new Input();

        Game game = new Game(display, input);
        game.start();
    }
}
