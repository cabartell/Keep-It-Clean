package io.github.keepitclean.command;

import io.github.keepitclean.model.Command;
import io.github.keepitclean.CommandExecutor;
import io.github.keepitclean.Game;

public class HelpCmd extends CommandExecutor {

    public HelpCmd(Game game) {
        super(game);
    }

    /**
     * Help prints all commands and laughs at you for being confused
     */
    @Override
    public boolean execute(Command command) {
        System.out.printf("You seem a little confused...%nYour command words are:%n");
        super.getGame().getParser().showCommands();
        System.out.printf("%n" +
                " - Help: Type help to see all the available commands%n" +
                " - Quit: Obviously, this quits the game.%n" +
                " - Stats: See the player stats %n - sell: sell your fish%n" +
                " - Inventory: Show you what item you have in your inventory%n" +
                " - Sleep: Take a nap, refresh your energy%n" +
                " - Go: The go command is used to navigate the map. To go to a room, type 'go <room name> (ie. 'go street)%n" +
                " - Pickup: Type 'pickup' in a room to see the trash on the ground, %n" +
                "           Type 'pickup' followed by the number of the piece of trash on the list you wish to pick up%n" +
                " - Fish: Can only be used at the dock. %n" +
                "         This is how you earn money to pay for living costs and buy items. %n" +
                "         When fishing, you might pick up plastic.%n" +
                "         The chance of picking up plastic depends on the pollution level.%n" +
                "         The pollution level depends on how much trash is present around the map and how good you are at recycling. %n"
        );
        return false;
    }
}
