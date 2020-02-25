package io.github.keepitclean.handler;

import io.github.keepitclean.event.Recycle;
import io.github.keepitclean.event.Sell;
import io.github.keepitclean.gui.DrawRoom;
import io.github.keepitclean.model.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class RoomHandler {
    private static final Set<Room> rooms = new HashSet<>();

    private RoomHandler() {
    }

    /**
     * Intilizes all the rooms in the game.
     * First rooms are created.
     * Then hitboxes are set.
     * Then NPC and exits are added.
     * And lastly, the room is added to our set of rooms room.
     */
    public static void initialize() {
        // Define all rooms
        Room home = new Room(RoomType.HOME);
        Room kitchen = new Room(RoomType.KITCHEN);
        Room bathroom = new Room(RoomType.BATHROOM);
        Room bedroom = new Room(RoomType.BEDROOM);
        Room street = new Room(RoomType.STREET);
        Room beach = new Room(RoomType.BEACH);
        Room dock = new Room(RoomType.DOCK);
        Room recycleCenter = new Room(RoomType.RECYCLE);

        // Add obstacles to the rooms
        addObstacle(home, Element.BARRIER, new Location(0, 6), new Location(1, 6), new Location(2, 6), new Location(3, 6), new Location(4, 6), new Location(5, 6), new Location(6, 6), new Location(7, 6), new Location(8, 6), new Location(9, 6), new Location(10, 6), new Location(11, 6), new Location(12, 6), new Location(13, 6), new Location(14, 6), new Location(15, 6), new Location(3, 0), new Location(4, 0), new Location(5, 0), new Location(6, 0), new Location(7, 0));
        addObstacle(kitchen, Element.BARRIER, new Location(3, 1), new Location(4, 1), new Location(5, 1), new Location(6, 1), new Location(7, 1), new Location(8, 1), new Location(9, 1), new Location(10, 1), new Location(11, 1), new Location(12, 1), new Location(13, 1), new Location(14, 1), new Location(15, 1), new Location(3, 2), new Location(3, 3), new Location(3, 4), new Location(3, 5), new Location(3, 6), new Location(4, 7), new Location(5, 7), new Location(6, 7), new Location(7, 7), new Location(8, 7), new Location(9, 8), new Location(10, 8));
        addObstacle(bathroom, Element.BARRIER, new Location(6, 0), new Location(6, 1), new Location(6, 2), new Location(6, 3), new Location(6, 4), new Location(7, 5), new Location(8, 5), new Location(9, 5), new Location(9, 6), new Location(9, 6), new Location(10, 7), new Location(11, 7), new Location(12, 7), new Location(13, 7), new Location(14, 6), new Location(14, 5), new Location(14, 4), new Location(14, 3), new Location(14, 2), new Location(14, 1), new Location(14, 0), new Location(7, 1));
        addObstacle(bathroom, Element.SINK, new Location(10, 0), new Location(11, 0));
        addObstacle(bedroom, Element.BED, new Location(11, 1), new Location(12, 1), new Location(13, 1), new Location(14, 1), new Location(15, 1), new Location(11, 2), new Location(11, 3), new Location(12, 3), new Location(13, 3), new Location(14, 3), new Location(15, 3));
        addObstacle(bedroom, Element.BARRIER, new Location(2, 0), new Location(2, 1), new Location(2, 2), new Location(2, 3), new Location(2, 4), new Location(2, 5), new Location(2, 6), new Location(2, 7), new Location(2, 8), new Location(10, 7), new Location(10, 8), new Location(11, 7), new Location(12, 7), new Location(13, 7), new Location(14, 7), new Location(14, 8));
        addObstacle(dock, Element.WATER, new Location(0, 3), new Location(1, 3), new Location(2, 3), new Location(3, 3), new Location(4, 3), new Location(4, 4), new Location(4, 5), new Location(5, 6), new Location(6, 6), new Location(7, 6), new Location(8, 5), new Location(8, 4), new Location(8, 3), new Location(9, 3), new Location(10, 3));
        addObstacle(dock, Element.BARRIER, new Location(10, 3), new Location(11, 3), new Location(12, 3), new Location(13, 4), new Location(13, 5), new Location(13, 6), new Location(14, 7), new Location(15, 7));
        addObstacle(beach, Element.BARRIER, new Location(0, 7), new Location(0, 8), new Location(1, 7), new Location(1, 8), new Location(2, 7), new Location(2, 8), new Location(3, 7), new Location(3, 8), new Location(4, 7), new Location(4, 8), new Location(5, 7), new Location(5, 8), new Location(6, 7), new Location(6, 8), new Location(7, 7), new Location(7, 8), new Location(8, 7), new Location(8, 8), new Location(9, 7), new Location(9, 8), new Location(10, 7), new Location(10, 8), new Location(11, 7), new Location(11, 8), new Location(12, 7), new Location(12, 8), new Location(13, 7), new Location(13, 8), new Location(14, 7), new Location(14, 8), new Location(15, 7), new Location(15, 8));
        addObstacle(street, Element.BARRIER, new Location(0, 0), new Location(0, 1), new Location(1, 0), new Location(1, 1), new Location(4, 0), new Location(4, 1), new Location(5, 0), new Location(5, 1), new Location(7, 0), new Location(7, 1), new Location(8, 0), new Location(8, 1), new Location(11, 5), new Location(12, 5), new Location(13, 5), new Location(14, 5), new Location(14, 6), new Location(14, 7), new Location(13, 8), new Location(12, 8), new Location(11, 7), new Location(11, 6), new Location(11, 5), new Location(1, 5), new Location(1, 6), new Location(1, 7), new Location(2, 5), new Location(2, 7), new Location(3, 5), new Location(3, 7), new Location(4, 6), new Location(4, 7), new Location(5, 5), new Location(5, 7), new Location(6, 5), new Location(6, 6), new Location(6, 7));
        addObstacle(recycleCenter, Element.BARRIER, new Location(1, 1), new Location(1, 2), new Location(1, 3), new Location(2, 1), new Location(2, 2), new Location(2, 3), new Location(5, 1), new Location(5, 2), new Location(5, 3), new Location(6, 1), new Location(6, 2), new Location(6, 3), new Location(9, 1), new Location(9, 2), new Location(9, 3), new Location(10, 1), new Location(10, 2), new Location(10, 3), new Location(12, 1), new Location(12, 2), new Location(12, 3), new Location(13, 1), new Location(13, 2), new Location(13, 3), new Location(14, 1), new Location(14, 2), new Location(14, 3));

        // Add NPCs to the rooms
        addNPC(street, new NPC(new Location(13, 0, Direction.SOUTH), RoomType.STREET, new Sell()));
        addNPC(recycleCenter, new NPC(new Location(4, 3, Direction.SOUTH), RoomType.RECYCLE, new Recycle()));

        // Add exits to the rooms
        addExit(home,
            new Exit(new Location(15, 2, Direction.EAST), RoomType.BEDROOM, new Location(3, 2, Direction.EAST)),
            new Exit(new Location(15, 3, Direction.EAST), RoomType.BEDROOM, new Location(3, 3, Direction.EAST)),
            new Exit(new Location(14, 6, Direction.SOUTH), RoomType.KITCHEN, new Location(14, 2, Direction.SOUTH)),
            new Exit(new Location(0, 2, Direction.WEST), RoomType.BATHROOM, new Location(13, 2, Direction.WEST)),
            new Exit(new Location(0, 3, Direction.WEST), RoomType.BATHROOM, new Location(13, 3, Direction.WEST)),
            new Exit(new Location(14, 0, Direction.NORTH), RoomType.STREET, new Location(4, 4, Direction.NORTH))
        );
        addExit(bedroom,
            new Exit(new Location(2, 2, Direction.WEST), RoomType.HOME, new Location(15, 2, Direction.WEST)),
            new Exit(new Location(2, 3, Direction.WEST), RoomType.HOME, new Location(15, 3, Direction.WEST))
        );
        addExit(kitchen,
            new Exit(new Location(14, 1, Direction.NORTH), RoomType.HOME, new Location(14, 5, Direction.NORTH)));
        addExit(bathroom,
            new Exit(new Location(14, 2, Direction.EAST), RoomType.HOME, new Location(0, 2, Direction.EAST)),
            new Exit(new Location(14, 3, Direction.EAST), RoomType.HOME, new Location(0, 3, Direction.EAST)));
        addExit(street,
            new Exit(new Location(4, 5, Direction.SOUTH), RoomType.HOME, new Location(14, 0, Direction.SOUTH)),
            new Exit(new Location(8, 8, Direction.SOUTH), RoomType.BEACH, new Location(6, 0, Direction.SOUTH)),
            new Exit(new Location(9, 8, Direction.SOUTH), RoomType.BEACH, new Location(6, 0, Direction.SOUTH)),
            new Exit(new Location(15, 3, Direction.EAST), RoomType.RECYCLE, new Location(1, 8, Direction.EAST)),
            new Exit(new Location(15, 2, Direction.EAST), RoomType.RECYCLE, new Location(1, 8, Direction.EAST)),
            new Exit(new Location(0, 3, Direction.WEST), RoomType.DOCK, new Location(15, 1, Direction.WEST)),
            new Exit(new Location(0, 2, Direction.WEST), RoomType.DOCK, new Location(15, 1, Direction.WEST)));
        addExit(dock,
            new Exit(new Location(15, 0, Direction.EAST), RoomType.STREET, new Location(0, 3, Direction.EAST)),
            new Exit(new Location(15, 1, Direction.EAST), RoomType.STREET, new Location(0, 3, Direction.EAST)));
        addExit(beach,
            new Exit(new Location(0, 0, Direction.NORTH), RoomType.STREET, new Location(9, 8, Direction.NORTH)),
            new Exit(new Location(1, 0, Direction.NORTH), RoomType.STREET, new Location(9, 8, Direction.NORTH)),
            new Exit(new Location(2, 0, Direction.NORTH), RoomType.STREET, new Location(9, 8, Direction.NORTH)),
            new Exit(new Location(3, 0, Direction.NORTH), RoomType.STREET, new Location(9, 8, Direction.NORTH)),
            new Exit(new Location(4, 0, Direction.NORTH), RoomType.STREET, new Location(9, 8, Direction.NORTH)),
            new Exit(new Location(5, 0, Direction.NORTH), RoomType.STREET, new Location(9, 8, Direction.NORTH)),
            new Exit(new Location(6, 0, Direction.NORTH), RoomType.STREET, new Location(9, 8, Direction.NORTH)),
            new Exit(new Location(7, 0, Direction.NORTH), RoomType.STREET, new Location(9, 8, Direction.NORTH)),
            new Exit(new Location(8, 0, Direction.NORTH), RoomType.STREET, new Location(9, 8, Direction.NORTH)),
            new Exit(new Location(9, 0, Direction.NORTH), RoomType.STREET, new Location(9, 8, Direction.NORTH)),
            new Exit(new Location(10, 0, Direction.NORTH), RoomType.STREET, new Location(9, 8, Direction.NORTH)),
            new Exit(new Location(11, 0, Direction.NORTH), RoomType.STREET, new Location(9, 8, Direction.NORTH)),
            new Exit(new Location(12, 0, Direction.NORTH), RoomType.STREET, new Location(9, 8, Direction.NORTH)),
            new Exit(new Location(13, 0, Direction.NORTH), RoomType.STREET, new Location(9, 8, Direction.NORTH)),
            new Exit(new Location(14, 0, Direction.NORTH), RoomType.STREET, new Location(9, 8, Direction.NORTH)),
            new Exit(new Location(15, 0, Direction.NORTH), RoomType.STREET, new Location(9, 8, Direction.NORTH)));
        addExit(recycleCenter,
            new Exit(new Location(0, 8, Direction.WEST), RoomType.STREET, new Location(15, 2, Direction.WEST)));

        // Add the rooms to our set
        rooms.addAll(Arrays.asList(home, kitchen, bathroom, bedroom, street, beach, dock, recycleCenter));

    }

    /**
     * @param room      - The {@link io.github.keepitclean.model.Room} which the obstacle should be added to.
     * @param element   - The {@link io.github.keepitclean.model.Element} to be added at the specified location.
     * @param locations - A collection of {@link io.github.keepitclean.model.Location} where the specified element should be added.
     *                  Vararg is used to allow creating all obstacles in the same line
     */
    private static void addObstacle(Room room, Element element, Location... locations) {
        // Get the rooms current occupied fields
        Element[][] occupiedFields = room.getOccupiedFields();
        // Loop thru all locations passed with the method
        for (Location loc : locations) {
            //Set the element at the specified location to the desired element.
            occupiedFields[loc.getX()][loc.getY()] = element;
        }
        // Add the updated occupied fields to the room again
        room.setOccupiedFields(occupiedFields);
    }

    /**
     * Add NPC(s) to the selected room.
     *
     * @param room - The {@link io.github.keepitclean.model.Room} which the NPC(s) should be added to.
     * @param npcs - The collection of {@link io.github.keepitclean.model.NPC} to add the to room
     */
    private static void addNPC(Room room, NPC... npcs) {
        // Get the list of current NPCs in the room
        List<NPC> npcList = room.getNpcs();
        // Loop thru every NPC passed
        for (NPC npc : npcs) {
            // Add the NPC as a obstacle
            addObstacle(room, Element.NPC, npc.getLocation());
            // Add the NPC to the list
            npcList.add(npc);
        }
        // Add the updated list of NPCs to the room
        room.setNpcs(npcList);
    }

    /**
     * Add exits for the selected room
     *
     * @param room  - The {@link io.github.keepitclean.model.Room} which the NPC(s) should be added to.
     * @param exits - The collection of {@link io.github.keepitclean.model.Exit} to add the to room
     */
    private static void addExit(Room room, Exit... exits) {
        // Get the list of current exits in the room
        List<Exit> currentExits = room.getExits();
        for (Exit exit : exits) {
            // Add the exit as a obstacle
            addObstacle(room, Element.EXIT, exit.getExitLocation());
            currentExits.add(exit);
        }
        // Add the updated list of exits to the room
        room.setExits(currentExits);
    }

    /**
     * Loops thru every room we have, and return the room with the specified room type
     *
     * @param roomType - The {@link io.github.keepitclean.model.RoomType} the {@link io.github.keepitclean.model.Room} must have
     * @return - The first {@link io.github.keepitclean.model.Room} found. There will always only be one possible entry, which never can be null
     */
    @NotNull
    public static Room getRoom(RoomType roomType) {
        for (Room room : rooms) {
            if (room.getRoomType() == roomType) {
                return room;
            }
        }
        return new Room(null);
    }

    /**
     * Add a drop to the selected room. Drops both adds an image on the ground, and a obstacle in the room.
     *
     * @param roomType - The {@link io.github.keepitclean.model.RoomType} the {@link io.github.keepitclean.model.Room} must have
     * @param drop     - The {@link io.github.keepitclean.model.Drop} to add
     */
    public static void addDrop(RoomType roomType, Drop drop) {
        Room room = getRoom(roomType);
        room.getDrops().add(drop);
        addObstacle(room, drop.getElement(), drop.getLocation());
        DrawRoom.addImage(room, drop);
        rooms.add(room);
    }

    /**
     * Remove a drop from the ground. The image of the drop will be removed, as will the obstacle on the ground
     *
     * @param roomType - The {@link io.github.keepitclean.model.RoomType} the {@link io.github.keepitclean.model.Room} must have
     * @param index    - The int index the drop have in the {@link io.github.keepitclean.model.Room} list of drops
     */
    public static void removeDrop(RoomType roomType, int index) {
        Room room = getRoom(roomType);

        Drop drop = room.getDrops().get(index);
        DrawRoom.removeImage(room, drop.getIndex());

        addObstacle(room, null, drop.getLocation());

        // Loops thru all drops backwards, and add the first found drop on the specified location to the ground obstacles.
        List<Drop> drops = room.getDrops();
        for (int i = drops.size() - 1; i >= 0; i--) {
            if (drops.get(i).getLocation().equals(drop.getLocation())) {
                addObstacle(room, drop.getElement(), drop.getLocation());
                break;
            }
        }
        room.getDrops().remove(drop);

        //Force add the room to the set of rooms.
        while (!rooms.add(room)) {
            rooms.remove(room);
        }
    }

    /**
     * Check if a player is currently standing on an exit
     *
     * @param player - The {@link io.github.keepitclean.model.Player} to check if standing at an exit
     * @return - Whether the player currently is on an exit
     */
    public static boolean isExit(Player player) {
        Room room = getRoom(player.getCurrentRoom().getRoomType());
        Location loc = player.getLocation();
        return room.getOccupiedFields()[loc.getX()][loc.getY()] == Element.EXIT;
    }

    /**
     * @param player - The {@link io.github.keepitclean.model.Player} to check if standing at an exit
     * @return - the {@link io.github.keepitclean.model.Exit} the player is standing on
     */
    @Nullable
    public static Exit getExit(Player player) {
        for (Exit exit : player.getCurrentRoom().getExits()) {
            // If the room is locked, the exit isn't valid.
            if (exit.isLocked()) {
                continue;
            }
            // If the player is at another location, the exit isn't valid.
            if (!exit.getExitLocation().equals(player.getLocation())) {
                continue;
            }
            // If the player is facing another way direction, the exit isn't valid
            if (player.getLocation().getDirection() == exit.getExitLocation().getDirection()) {
                return exit;
            }
        }
        return null;
    }

    /**
     * Loops thru every exit in the desired room, and update the exit the the new room locks
     *
     * @param from   - The {@link io.github.keepitclean.model.RoomType} the old {@link io.github.keepitclean.model.Room} must have
     * @param to     - The {@link io.github.keepitclean.model.RoomType} the new {@link io.github.keepitclean.model.Room} must have
     * @param locked - If the room is locked or unlocked.
     */
    public static void setLocked(RoomType from, RoomType to, boolean locked) {
        Room room = getRoom(from);
        for (Exit exit : room.getExits()) {
            if (exit.getNewRoomType() == to) {
                exit.setLocked(locked);
            }
        }
        rooms.add(room);
    }

    /**
     * @param roomType - The {@link io.github.keepitclean.model.RoomType} the {@link io.github.keepitclean.model.Room} must have
     * @param location - The {@link io.github.keepitclean.model.Location} the {@link io.github.keepitclean.model.NPC} must have
     * @return - The {@link io.github.keepitclean.model.NPC} at the desired location
     */
    @Nullable
    public static NPC getNPC(RoomType roomType, Location location) {
        Room room = getRoom(roomType);
        for (NPC npc : room.getNpcs()) {
            if (npc.getLocation().equals(location)) {
                return npc;
            }
        }
        return null;
    }
}
