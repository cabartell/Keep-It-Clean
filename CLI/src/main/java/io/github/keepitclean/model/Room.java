package io.github.keepitclean.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Room class part of io.github.gruppe19.model package contains Room attributes
 * and their getters and setter as well as Room constructor.
 */

public class Room {
    private RoomType roomType;
    private List<Room> exits;
    private List<ItemType> trash;

    public Room(RoomType roomType) {
        this.roomType = roomType;
        this.exits = new ArrayList<>();
        this.trash = new ArrayList<>();
    }

    /**
     * @return - The RoomType of the room
     */
    public RoomType getRoomType() {
        return roomType;
    }

    /**
     * Add exits to multiple rooms.
     *
     * @param bothWays - Should the rooms be linked both way?
     * @param rooms    - A "list like" collection of rooms (Called vararg). Functions like a array of rooms.
     */
    public void addExit(boolean bothWays, Room... rooms) {
        for (Room room : rooms) {
            this.exits.add(room);
            if (bothWays) {
                room.addExit(false, this);
            }
        }
    }

    /**
     * @return - The description of the room.
     */
    public String getDescription() {
        return "You are " + this.roomType.getDescription() + ".\n" + getExitString();
    }

    /**
     * @return - A String informing of all the exits from the current room.
     */
    public String getExitString() {
        StringBuilder returnString = new StringBuilder("Exits: ");
        for (Room exit : this.exits) {
            returnString.append(exit.roomType.getName()).append(", ");
        }
        return returnString.toString();
    }

    /**
     * Loops thru every exit associated with the room
     *
     * @param name - The name of the exit
     * @return - The new room
     */
    public Room getExit(String name) {
        for (Room exit : this.exits) {
            if (exit.getRoomName().equalsIgnoreCase(name)) {
                return exit;
            }
        }
        return null;
    }

    /**
     * @return - The name of the room
     */
    public String getRoomName() {
        return this.roomType.getName();
    }

    /**
     * @return - List of trash in room
     */
    public List<ItemType> getTrash() {
        return this.trash;
    }

    /**
     * @param item - item to add to list of trash
     */
    public void addTrash(ItemType item) {
        this.trash.add(item);
    }

    /**
     * @param index - index number to remove from list of trash
     */
    public void removeTrash(int index) {
        this.trash.remove(index);
    }
}

