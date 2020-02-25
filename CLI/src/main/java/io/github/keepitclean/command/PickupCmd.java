package io.github.keepitclean.command;

import io.github.keepitclean.model.Command;
import io.github.keepitclean.CommandExecutor;
import io.github.keepitclean.Game;
import io.github.keepitclean.Utils;
import io.github.keepitclean.model.ItemType;

import java.util.List;

public class PickupCmd extends CommandExecutor {
    /**
     * Pickup checks if inventory used is greater than or equal to the player's inventory size
     * if inventory is full, the player is informed, nothing happens (end of method)
     * else, inventoryUsed is increased by one
     * player uses 5 air to pick up an item
     */
    public PickupCmd(Game game) {
        super(game);
    }

    /**
     * Pickup items from the ground.
     *
     * @param command - The processed command
     * @return - The return outcome is ignored.
     */
    @Override
    public boolean execute(Command command) {
        // Create a copy of the rooms trash
        List<ItemType> items = super.getPlayer().getCurrentRoom().getTrash();

        // Check if there are items on the ground
        if (items.size() == 0) {
            System.out.println("Hmmm... I cannot find any items on the ground.");
            return false;
        }

        // Check if the player has space left in the inventory
        if (super.getPlayer().getFreeInventorySpaces() == 0) {
            System.out.println("You don't have any space left in your inventory");
            return false;
        }

        // Check if the player have selected a item to pickup
        if (!command.hasSecondWord()) {
            Utils.listItems("Please select one of following items to pickup", items);
            return false;
        }

        // Try to parse second word to an integer
        int index;
        try {
            index = Integer.parseInt(command.getSecondWord()) - 1;
        } catch (IllegalArgumentException ignored) {
            Utils.listItems("Please select one of following items to pickup", items);
            return false;
        }

        // Check if the input is larger than the amount of items, or if the item is null
        if (index < 0 || index >= items.size() || items.get(index) == null) {
            Utils.listItems("Please select one of following items to pickup", items);
            return false;
        }

        if (super.getPlayer().getEnergy() < 2) {
            System.out.println("You are out of energy. Please sleep to regain some.");
            return false;
        }
        super.getGame().getPlayer().removeEnergy(2);
        super.getPlayer().addInventory(items.get(index));
        System.out.println("You picked up 1x" + items.get(index));
        super.getPlayer().printFreeInventorySpace();
        super.getPlayer().getCurrentRoom().removeTrash(index);
        super.getGame().setPollutionBar(super.getGame().getPollutionBar() - 1);

        return false;
    }
}
