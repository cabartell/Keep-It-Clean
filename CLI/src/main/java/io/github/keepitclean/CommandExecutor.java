package io.github.keepitclean;

import io.github.keepitclean.model.Command;
import io.github.keepitclean.model.Player;

public abstract class CommandExecutor {

    private final Game game;

    public CommandExecutor(Game game) {
        this.game = game;
    }

    public abstract boolean execute(Command command);

    public Player getPlayer() {
        return getGame().getPlayer();
    }

    public Game getGame() {
        return game;
    }
}
