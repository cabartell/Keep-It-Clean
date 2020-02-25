package io.github.keepitclean.event;

import io.github.keepitclean.Game;
import io.github.keepitclean.handler.PlayerHandler;
import io.github.keepitclean.handler.RoomHandler;
import io.github.keepitclean.model.Element;
import io.github.keepitclean.model.Location;
import io.github.keepitclean.model.NPC;
import javafx.event.Event;
import javafx.event.EventHandler;

public class Interact implements EventHandler {

    /**
     * The method called when a player interacts.
     *
     * @param event - The KeyPress event passed
     */
    @Override
    public void handle(Event event) {
        // Check if the player interacts with a valid location
        Location loc = PlayerHandler.getFacingLocation(Game.getInstance().getPlayer());
        if (loc == null) {
            return;
        }
        // Check what element the player interact with
        Element element = (Game.getInstance().getPlayer().getCurrentRoom().getOccupiedFields()[loc.getX()][loc.getY()]);
        if (element == null) {
            return;
        }
        // Call the required EventHandler for the element interacted with
        switch (element) {
            case TRASH:
            case FISH:
            case BOTTLE:
            case FISHING_ROD:
            case GARBAGE_BAG:
            case SWAB:
                new Pickup().handle(event);
                break;
            case NPC:
                // Get the EventHandler bound to the NPC
                NPC npc = RoomHandler.getNPC(Game.getInstance().getPlayer().getCurrentRoom().getRoomType(), PlayerHandler.getFacingLocation(Game.getInstance().getPlayer()));
                if (npc == null) {
                    return;
                }
                npc.getEvent().handle(event);
                break;
            case BED:
                new Sleep().handle(event);
                break;
            case WATER:
                new Fish().handle(event);
                break;
            case SINK:
                new Clean().handle(event);
        }
    }
}
