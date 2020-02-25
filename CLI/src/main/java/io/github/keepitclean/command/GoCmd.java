package io.github.keepitclean.command;

import io.github.keepitclean.model.Command;
import io.github.keepitclean.CommandExecutor;
import io.github.keepitclean.Game;
import io.github.keepitclean.model.Room;
import io.github.keepitclean.model.RoomType;

public class GoCmd extends CommandExecutor {

    public GoCmd(Game game) {
        super(game);
    }

    /**
     * Go takes command word "go", checks for second work (direction) ex. "go home",
     * sets current room to room entered, asks where to go if no second word is entered
     *
     * @param command - The command sent.
     * @return - The return type is ignored.
     */
    @Override
    public boolean execute(Command command) {
        Room room = getGame().getPlayer().getCurrentRoom();
        if (!command.hasSecondWord()) {
            printInvalidExit(room);
            return false;
        }
        String direction = command.getSecondWord();
        Room nextRoom = room.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no such room! ");
            printInvalidExit(room); //no room this way
            return false;
        }

        if (nextRoom.getRoomType() == RoomType.BATHROOM) {
            System.out.println("You had explosive diarrhea yesterday, and therefore the bathroom is closed for maintenance.");
            return false;
        } else if (nextRoom.getRoomType() == RoomType.KITCHEN) {
            System.out.println("Your house is brand new, so the kitchen is closed for maintenance.");
            return false;
        }

        getGame().getPlayer().setCurrentRoom(nextRoom);
        System.out.println(nextRoom.getDescription()); //prints room description on entrance
        return false;
    }

    /**
     * Prints all exit ways available.
     *
     * @param room - The room which ways should be printed.
     */
    private void printInvalidExit(Room room) {
        System.out.println("Where do you want to go?");
        System.out.println(room.getExitString());
    }
}
