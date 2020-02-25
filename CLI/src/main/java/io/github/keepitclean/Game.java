package io.github.keepitclean;

import io.github.keepitclean.model.Command;
import io.github.keepitclean.model.Player;
import io.github.keepitclean.model.Room;
import io.github.keepitclean.model.RoomType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class is part of the "Ocean Cleanup io.github.gruppe19.Game" application
 * This class holds play() (called in main) that runs the game and methods used for actions in the game
 */
public class Game {
    private Parser parser;
    private final Player player;
    private int pollutionBar;
    private int goodPollutionStreak;
    private List<Room> rooms;

    public Game() {
        Room room = createRooms();
        this.player = new Player(room);
        this.parser = new Parser(this);
        this.pollutionBar = 30;
        this.goodPollutionStreak = 0;
        play();
    }

    /**
     * Define every room, and connect them.
     * If bothWays is set to true in addExit, both rooms will connect with each other.
     * If false, only the room where the method is called will connect with the room.
     *
     * @return - The room the player starts in
     */
    private Room createRooms() {
        Room home = new Room(RoomType.HOME);
        Room kitchen = new Room(RoomType.KITCHEN);
        Room bathroom = new Room(RoomType.BATHROOM);
        Room bedroom = new Room(RoomType.BEDROOM);
        Room street = new Room(RoomType.STREET);
        Room beach = new Room(RoomType.BEACH);
        Room dock = new Room(RoomType.DOCK);
        Room recycleCenter = new Room(RoomType.RECYCLE);

        home.addExit(false, kitchen, bathroom, bedroom);
        kitchen.addExit(false, street);
        bathroom.addExit(false, street);
        bedroom.addExit(false, street);

        kitchen.addExit(true, bathroom, bedroom);
        bathroom.addExit(true, bedroom);

        street.addExit(true, home, beach, dock, recycleCenter);

        this.rooms = new ArrayList<>();
        addRooms(home, kitchen, bathroom, bedroom, street, beach, dock, recycleCenter);

        return bedroom;
    }

    private void addRooms(Room... rooms) {
        this.rooms.addAll(Arrays.asList(rooms));
    }


    /**
     * Starts the game, and run it until a command returns true.
     * If that happen, it means the player wants to quit the game.
     */
    private void play() {
        printWelcome();
        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = parser.processCommand(command);
        }

        if (this.goodPollutionStreak > 10) {
            System.out.println("Congrats you won the game");
        } else {
            System.out.println("You lost, there aren't any fish left in the ocean ");
        }
        System.out.println("Thank you for playing. Goodbye.");
    }

    /**
     * Welcome is printed at game start
     */
    private void printWelcome() {
        Room currentRoom = this.player.getCurrentRoom();
        System.out.printf("%nWelcome to Keep It Clean! %n" +
                        "Keep It Clean is a game that teaches you about pollution and the importance of recycling! " +
                        "%nThe purpose of the game is to see how your actions impact the environment. %n%n" +

                        "Type 'help' at any time to see how all the commands work.%n" +
                        "Your objective is to reduce pollution as much as possible, while still surviving. %n" +
                        "It costs $350 every time you sleep, as a living cost.%n" +
                        "You can reduce pollution by picking up and recycling trash.%n" +
                        "Whenever you sleep, trash is generated at the beach, which you can recycle.%n" +
                        "Be aware of your energy bar, which decreases with every action. %n" +
                        "You can keep track of your money, your energy and the pollution bar with the stats command. %n%n" +

                        "In order to win the game, you will need to have the pollution bar under 10 when you sleep, ten consecutive days %n" +
                        "To get started, visit the dock, and try to catch some fish using the command 'fish'" +
                        "%n%n%s%n",
                currentRoom.getDescription());
    }

    public Parser getParser() {
        return parser;
    }

    public Player getPlayer() {
        return player;
    }

    public int getPollutionBar() {
        return pollutionBar;
    }

    public void setPollutionBar(int pollutionBar) {
        this.pollutionBar = Math.max(5, Math.min(pollutionBar, 100));
    }

    public Room getRoom(RoomType roomType) {
        for (Room room : this.rooms) {
            if (room.getRoomType() == roomType) {
                return room;
            }
        }
        return null;
    }

    public int getGoodPollutionStreak() {
        return this.goodPollutionStreak;
    }

    public void setGoodPollutionStreak(int goodPollutionStreak) {
        this.goodPollutionStreak = goodPollutionStreak;
    }
}
