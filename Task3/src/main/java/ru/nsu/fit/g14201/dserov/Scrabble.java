package ru.nsu.fit.g14201.dserov;

import ru.nsu.fit.g14201.dserov.model.Game;

/**
 * Hello world!
 *
 */
public class Scrabble
{
    public static void main( String[] args )
    {
        Game game = new Game(new HumanPlayer(), new HumanPlayer());
        ViewController controller = new ViewController(game);
        controller.init();

    }
}
