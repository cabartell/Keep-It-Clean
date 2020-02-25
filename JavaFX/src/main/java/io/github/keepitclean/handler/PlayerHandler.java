package io.github.keepitclean.handler;

import io.github.keepitclean.Game;
import io.github.keepitclean.gui.Inventory;
import io.github.keepitclean.model.Drop;
import io.github.keepitclean.model.Element;
import io.github.keepitclean.model.Location;
import io.github.keepitclean.model.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.jetbrains.annotations.Nullable;

public class PlayerHandler {

    /**
     * Add an item to the players inventory
     *
     * @param player - The {@link io.github.keepitclean.model.Player} to whom the item should be added to.
     * @param item   - The {@link io.github.keepitclean.model.Element} to add the the inventory.
     */
    public static boolean addInventory(Player player, Element item) {
        Element[] inventory = player.getInventory();
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == null) {
                inventory[i] = item;
                player.setInventory(inventory);
                Inventory.updateInventory(player);
                return true;
            }
        }
        RoomHandler.addDrop(Game.getInstance().getPlayer().getCurrentRoom().getRoomType(), new Drop(Element.SWAB, Game.getInstance().getPlayer().getLocation()));
        return false;
    }

    /**
     * Loops thru the players enventory, and count how many empty spaces they have left
     *
     * @param player - The {@link io.github.keepitclean.model.Player} to check the inventory for
     * @return - The amount of free inventory spaces left
     */
    public static int getFreeInventorySpaces(Player player) {
        int i = 0;
        for (Element type : player.getInventory()) {
            if (type == null) {
                i++;
            }
        }
        return i;
    }

    /**
     * Remove the item at the specified index, and move all
     *
     * @param player - The {@link io.github.keepitclean.model.Player}
     * @param index  - The index of the item which should be removed
     */
    public static void removeInventory(Player player, int index) {
        Element[] inventory = player.getInventory();
        inventory[index] = null;
        // Remove the following to the start of the array
        for (int i = index; i < inventory.length; i++) {
            // Check if the item is null
            if (inventory[i] == null) {
                // Start new loop from the next on the i loop
                for (int j = i + 1; j < inventory.length; j++) {
                    // Check if the items is not null
                    if (inventory[j] != null) {
                        // Move the item down to the current position, and break the j loop
                        inventory[i] = inventory[j];
                        inventory[j] = null;
                        break;
                    }
                }
            }
        }
        player.setInventory(inventory);
        Inventory.updateInventory(Game.getInstance().getPlayer());
    }

    /**
     * Check the location of the player, and return the location the player is facing
     *
     * @param player - The {@link io.github.keepitclean.model.Player} to get the facing direction from
     * @return - The {@link io.github.keepitclean.model.Location} the player is viewing.
     */
    @Nullable
    public static Location getFacingLocation(Player player) {
        if (player.getLocation().getDirection() == null) {
            return null;
        }
        int x = player.getLocation().getX();
        int y = player.getLocation().getY();
        switch (player.getLocation().getDirection()) {
            case NORTH:
                y--;
                break;
            case EAST:
                x++;
                break;
            case SOUTH:
                y++;
                break;
            case WEST:
                x--;
                break;
        }
        if (x < 0 || x > 15 || y < 0 || y > 8) {
            return null;
        }
        return new Location(x, y, player.getLocation().getDirection());
    }

    /**
     * Load the image view of the player (The image on the screen)
     *
     * @param player - The {@link io.github.keepitclean.model.Player}
     */
    public static void loadPlayerView(Player player) {
        ImageView playerView = new ImageView(new Image("player.png"));
        if (player.getLocation().getDirection() != null) {
            playerView.rotateProperty().set(player.getLocation().getDirection().getRotation());
        }
        player.setPlayerView(playerView);
    }
}
