package io.github.keepitclean.command;

import io.github.keepitclean.model.Command;
import io.github.keepitclean.CommandExecutor;
import io.github.keepitclean.Game;

public class UnknownCmd extends CommandExecutor {
    public UnknownCmd(Game game) {
        super(game);
    }

    @Override
    public boolean execute(Command command) {
        super.getGame().getParser().showCommands();
        return false;
    }
}
