package io.github.keepitclean.command;

import io.github.keepitclean.model.Command;
import io.github.keepitclean.CommandExecutor;
import io.github.keepitclean.Game;

public class QuitCmd extends CommandExecutor {
    public QuitCmd(Game game) {
        super(game);
    }

    /**
     * @param command - The processed command
     * @return - Whether or not the game should be quit.
     */
    @Override
    public boolean execute(Command command) {
        if (!command.hasSecondWord() || !command.getSecondWord().equalsIgnoreCase("confirm")) {
            System.out.println("Please write 'quit confirm' to exit the game");
            return false;
        } else {
            return true;
        }
    }
}
