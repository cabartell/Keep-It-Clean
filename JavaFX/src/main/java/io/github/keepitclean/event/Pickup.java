package io.github.keepitclean.event;

import io.github.keepitclean.Game;
import io.github.keepitclean.GameView;
import io.github.keepitclean.gui.Console;
import io.github.keepitclean.handler.PlayerHandler;
import io.github.keepitclean.handler.RoomHandler;
import io.github.keepitclean.model.Drop;
import javafx.event.Event;
import javafx.event.EventHandler;

import java.util.List;

class Pickup implements EventHandler {

    /**
     * The method called when a player interacts items on the ground
     *
     * @param event - Ignored
     */
    @Override
    public void handle(Event event) {
        // Create a copy of the rooms trash
        List<Drop> items = RoomHandler.getRoom(Game.getInstance().getPlayer().getCurrentRoom().getRoomType()).getDrops();

        // Check if the player has space left in the inventory
        if (PlayerHandler.getFreeInventorySpaces(Game.getInstance().getPlayer()) == 0) {
            Console.println("You don't have any space left in your inventory");
            return;
        }

        if (Game.getInstance().getPlayer().getEnergy() < 2) {
            Console.println("You are out of energy. Please sleep to regain some.");
            return;
        }

        // Get the index for the last items in the room at the desired location
        int index = -1;
        for (int i = items.size() - 1; i >= 0; i--) {
            if (items.get(i).getLocation().equals(PlayerHandler.getFacingLocation(Game.getInstance().getPlayer()))) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return;
        }

        Game.getInstance().getPlayer().removeEnergy(2);

        PlayerHandler.addInventory(Game.getInstance().getPlayer(), items.get(index).getElement());
        Console.println("You picked up 1x " + items.get(index).getElement());

        RoomHandler.removeDrop(Game.getInstance().getPlayer().getCurrentRoom().getRoomType(), index);
        Game.getInstance().setPollutionBar(Game.getInstance().getPollutionBar() - 1);

        GameView.updateView(Game.getInstance().getPlayer().getCurrentRoom().getRoomType());
    }
}
