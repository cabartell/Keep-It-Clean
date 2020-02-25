package io.github.keepitclean.event;

import io.github.keepitclean.Game;
import io.github.keepitclean.gui.Console;
import io.github.keepitclean.handler.PlayerHandler;
import io.github.keepitclean.handler.RoomHandler;
import io.github.keepitclean.model.Element;
import io.github.keepitclean.model.RoomType;
import javafx.event.Event;
import javafx.event.EventHandler;


class Clean implements EventHandler {

    /**
     * The method called when a player interacts with a sink.
     *
     * @param event - Ignored
     */
    @Override
    public void handle(Event event) {
        // Check if the player have enough energy required for this action
        if (Game.getInstance().getPlayer().getEnergy() < 5) {
            Console.println("You are out of energy. Please sleep to regain some.");
            return;
        }
        Game.getInstance().getPlayer().removeEnergy(5);

        //Add the item to the inventory
        if (PlayerHandler.addInventory(Game.getInstance().getPlayer(), Element.SWAB)) {
            Console.println("The swab was added to your inventory. Go to the recycling center to get rid of it");
        } else {
            Console.println("You didn't have any space left in your inventory. The item was dropped on the floor");
            Game.getInstance().setPollutionBar(Game.getInstance().getPollutionBar() + 2);
        }

        RoomHandler.setLocked(RoomType.HOME, RoomType.STREET, false);
    }

}
