package io.github.keepitclean.event;

import io.github.keepitclean.Game;
import io.github.keepitclean.model.Direction;
import io.github.keepitclean.model.Element;
import io.github.keepitclean.model.Location;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Walk implements EventHandler {

    /**
     * The method called when a player walks.
     *
     * @param event - The KeyEvent, used to get the direction
     */
    @Override
    public void handle(Event event) {
        if (!(event instanceof KeyEvent)) {
            return;
        }
        KeyCode key = ((KeyEvent) event).getCode();
        // Get the players current location
        int x = Game.getInstance().getPlayer().getLocation().getX();
        int y = Game.getInstance().getPlayer().getLocation().getY();
        Direction direction;
        Element[][] occupiedFields = Game.getInstance().getPlayer().getCurrentRoom().getOccupiedFields();
        // Add/subtract one to the correct axis.
        switch (key) {
            case W:
                direction = Direction.NORTH;
                if (y > 0 && isWalkable(occupiedFields[x][y - 1])) {
                    y--;
                }
                break;
            case D:
                direction = Direction.EAST;
                if (x < occupiedFields.length - 1 && isWalkable(occupiedFields[x + 1][y])) {
                    x++;
                }
                break;
            case S:
                direction = Direction.SOUTH;
                if (y < occupiedFields[x].length - 1 && isWalkable(occupiedFields[x][y + 1])) {
                    y++;
                }
                break;
            case A:
                direction = Direction.WEST;
                if (x > 0 && isWalkable(occupiedFields[x - 1][y])) {
                    x--;
                }
                break;
            default:
                return;
        }
        Game.getInstance().getPlayer().setLocation(new Location(x, y, direction));
    }

    /**
     * Check if you can walk on the element.
     *
     * @param element - The {@link io.github.keepitclean.model.Element} to check
     * @return - Weather the element is walkable.
     */
    private static boolean isWalkable(Element element) {
        return element == null || element.isWalkable();
    }
}
