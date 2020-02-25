package io.github.keepitclean.gui;

import io.github.keepitclean.Game;
import io.github.keepitclean.GameView;
import io.github.keepitclean.handler.RoomHandler;
import io.github.keepitclean.model.*;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

public class DrawRoom {

    /**
     * Draws the GUI for the room
     *
     * @param roomType - Which room should be drawn
     * @return - The room in a GridPane
     */
    public static GridPane drawRoom(RoomType roomType) {
        Room room = RoomHandler.getRoom(roomType);
        if (room.getGridPane() != null) {
            updatePlayerLocation(Game.getInstance().getPlayer());
            return room.getGridPane();
        }

        // The grid the player walks in
        GridPane gp = new GridPane();

        //Set the height and width of the GridPane
        List<ColumnConstraints> col = new ArrayList<>();
        for (Element[] element : room.getOccupiedFields()) {
            if (element != null)
                col.add(new ColumnConstraints(75));
        }
        List<RowConstraints> row = new ArrayList<>();
        for (Element element : room.getOccupiedFields()[0]) {
            row.add(new RowConstraints(75));
        }
        gp.getColumnConstraints().addAll(col);
        gp.getRowConstraints().addAll(row);

        //Add all drops for the room
        for (Drop drop : room.getDrops()) {
            addImage(room, drop);
        }

        //Set the background image
        Background bg = new Background(new BackgroundImage(new Image(room.getRoomType().getBackgroundImage()), null, null, null, null));
        gp.setBackground(bg);
        room.setGridPane(gp);

        //Place NPC in the room
        for (NPC npc : room.getNpcs()) {
            addImage(room, npc.getLocation(), "npc.png");
        }

        updatePlayerLocation(Game.getInstance().getPlayer());

        return gp;
    }

    /**
     * Updates where on the screen the player is
     *
     * @param player - The player
     */
    public static void updatePlayerLocation(Player player) {
        Node playerView = player.getPlayerView();
        // Remove the player from the grid
        player.getCurrentRoom().getGridPane().getChildren().remove(player.getPlayerView());

        // Rotate the player
        if (player.getLocation().getDirection() != null) {
            playerView.setRotate(player.getLocation().getDirection().getRotation());
            player.setPlayerView(playerView);
        }
        // Add the player again
        player.getCurrentRoom().getGridPane().add(player.getPlayerView(), player.getLocation().getX(), player.getLocation().getY());

        // Check if the player is standing on an exit
        if (RoomHandler.isExit(Game.getInstance().getPlayer())) {
            Exit exit = RoomHandler.getExit(Game.getInstance().getPlayer());
            if (exit == null) {
                return;
            }
            // Move the player to the new room
            Game.getInstance().getPlayer().setCurrentRoom(RoomHandler.getRoom(exit.getNewRoomType()));
            Game.getInstance().getPlayer().setLocation(exit.getStartLocation());
            GameView.updateView(exit.getNewRoomType());
            addPlayer(player);
        }
    }

    /**
     * Adds to player to the grid at the current location
     *
     * @param player - The player to add
     */
    public static void addPlayer(Player player) {
        player.getCurrentRoom().getGridPane().getChildren().remove(player.getPlayerView());
        player.getCurrentRoom().getGridPane().add(player.getPlayerView(), player.getLocation().getX(), player.getLocation().getY());
    }

    /**
     * Get parameters for a drop, so an image can be added
     *
     * @param room - What room should the drop be added in
     * @param drop - Which drop should be added
     */
    public static void addImage(Room room, Drop drop) {
        // Set the index the drop is added at, to easily be removed when picked up.
        // If the player is in the room, the index needs to be subtracted by one, otherwise it is the current size
        drop.setIndex(room.getGridPane().getChildren().size() - (Game.getInstance().getPlayer().getCurrentRoom().equals(room) ? 1 : 0));
        // Call the method to add the image
        addImage(room, drop.getLocation(), "items/" + drop.getElement().name() + ".png");
    }

    /**
     * Adds a image of a element on the ground
     *
     * @param room     - The room the image should be added in
     * @param location - Where the element should be added
     * @param path     - The relative path for the image file
     */
    private static void addImage(Room room, Location location, String path) {
        ImageView img = new ImageView(new Image(path));
        if (location.getDirection() != null) {
            img.setRotate(location.getDirection().getRotation());
        }
        if (room.getGridPane() == null) {
            DrawRoom.drawRoom(room.getRoomType());
        }
        room.getGridPane().add(img, location.getX(), location.getY());
    }

    /**
     * Remove a image from the GridPane
     *
     * @param room  - The room the image should be removed from
     * @param index - The index of the image to remove
     */
    public static void removeImage(Room room, int index) {
        if (room.getGridPane() == null) {
            DrawRoom.drawRoom(room.getRoomType());
        }
        room.getGridPane().getChildren().remove(index);

        // Loop thru all drops in the room, and subtract one from the index, if it after the one just removed
        for (Drop drop : room.getDrops()) {
            if (drop.getIndex() > index) {
                drop.setIndex(drop.getIndex() - 1);
            }
        }
    }
}
