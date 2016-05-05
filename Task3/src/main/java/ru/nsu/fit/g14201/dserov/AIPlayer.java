package ru.nsu.fit.g14201.dserov;

import ru.nsu.fit.g14201.dserov.model.Game;
import ru.nsu.fit.g14201.dserov.model.Player;

/**
 * Created by dserov on 21/04/16.
 */
public abstract class AIPlayer extends Player {
    private Game game;
    public AIPlayer(Game game) {
        super(false);
        this.game = game;
    }
}
