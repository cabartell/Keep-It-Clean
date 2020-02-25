package io.github.keepitclean.command;

import io.github.keepitclean.model.Command;
import io.github.keepitclean.CommandExecutor;
import io.github.keepitclean.Game;
import io.github.keepitclean.Utils;
import io.github.keepitclean.model.ItemType;

import java.util.Arrays;
import java.util.List;

public class InventoryCmd extends CommandExecutor {
    public InventoryCmd(Game game) {
        super(game);
    }

    @Override
    public boolean execute(Command command) {
        List<ItemType> items = Arrays.asList(super.getPlayer().getInventory());
        Utils.listItems("You have following items in your inventory", items);
        return false;
    }
}
