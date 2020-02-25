package io.github.keepitclean.command;

import io.github.keepitclean.model.Command;
import io.github.keepitclean.CommandExecutor;
import io.github.keepitclean.Game;
import io.github.keepitclean.model.Player;

public class StatsCmd extends CommandExecutor {
    /**
     * Prints player stats including Money, current work speed and inventory space
     */

    public StatsCmd(Game game) {
        super(game);
    }

    @Override
    public boolean execute(Command command) {
        Player player = getGame().getPlayer();
        System.out.printf("PLAYER STATS: %nMoney: %s%nEnergy: %s%nInventory Space left: %s%nPollution score: %s%n%n" +
                        "Please type a command%n",
                player.getMoney(), player.getEnergy(), player.getFreeInventorySpaces(), getGame().getPollutionBar());
        return false;
    }
}
