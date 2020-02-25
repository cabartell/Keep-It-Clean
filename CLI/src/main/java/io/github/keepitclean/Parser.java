package io.github.keepitclean;

import io.github.keepitclean.command.FishCmd;
import io.github.keepitclean.command.GoCmd;
import io.github.keepitclean.command.HelpCmd;
import io.github.keepitclean.command.InventoryCmd;
import io.github.keepitclean.command.PickupCmd;
import io.github.keepitclean.command.QuitCmd;
import io.github.keepitclean.command.RecycleCmd;
import io.github.keepitclean.command.SellCmd;
import io.github.keepitclean.command.SleepCmd;
import io.github.keepitclean.command.StatsCmd;
import io.github.keepitclean.command.UnknownCmd;
import io.github.keepitclean.model.Command;
import io.github.keepitclean.model.CommandWord;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * io.github.keepitclean.Parser class contains Scanner used to read user input. Reads and checks input/commands.
 */
public class Parser {
    private Scanner reader;
    private Map<String, CommandWord> commands;

    private final CommandExecutor fishCommand;
    private final CommandExecutor goCommand;
    private final CommandExecutor helpCommand;
    private final CommandExecutor inventoryCommand;
    private final CommandExecutor pickupCommand;
    private final CommandExecutor quitCommand;
    private final CommandExecutor recycleCommand;
    private final CommandExecutor sellCommand;
    private final CommandExecutor sleepCommand;
    private final CommandExecutor statsCommand;
    private final CommandExecutor unknownCommand;

    /**
     * Initialize the command classes, and add all the commands to a map
     *
     * @param game Our game class, which CommandExecutor needs access to.
     */
    Parser(Game game) {
        this.reader = new Scanner(System.in);
        this.commands = new HashMap<>();

        this.fishCommand = new FishCmd(game);
        this.goCommand = new GoCmd(game);
        this.helpCommand = new HelpCmd(game);
        this.inventoryCommand = new InventoryCmd(game);
        this.pickupCommand = new PickupCmd(game);
        this.quitCommand = new QuitCmd(game);
        this.recycleCommand = new RecycleCmd(game);
        this.sellCommand = new SellCmd(game);
        this.sleepCommand = new SleepCmd(game);
        this.statsCommand = new StatsCmd(game);
        this.unknownCommand = new UnknownCmd(game);

        for (CommandWord command : CommandWord.values()) {
            if (command != CommandWord.UNKNOWN) {
                commands.put(command.toString(), command);
            }
        }
    }

    /**
     * Parses the input, and switch it to a command.
     * The line is split at space (regex '\s+').
     * If the command have an argument, it will also be added, otherwise the argument will be parsed as null
     *
     * @return - A {link @io.github.keepitclean.model.CommandWord} representation of the command
     */
    public Command getCommand() {
        String[] args = reader.nextLine().split("\\s+");
        String secondWord = args.length > 1 ? args[1] : null;
        return new Command(getCommandWord(args[0]), secondWord);
    }

    public void showCommands() {
        for (String command : commands.keySet()) {
            System.out.print(command + ", ");
        }
        System.out.println();
    }

    /**
     * Take the input and match it against a map of all commands
     * If no command is found from the command, the command
     * {link @io.github.keepitclean.model.CommandWord.UNKNOWN} is returned.
     * Otherwise the correct command is returned.
     *
     * @param commandWord - The first input, slitted at space
     * @return - The {link @io.github.keepitclean.model.CommandWord} corresponding to the command
     */
    private CommandWord getCommandWord(String commandWord) {
        CommandWord command = this.commands.get(commandWord.toLowerCase());
        if (command != null) {
            return command;
        } else {
            return CommandWord.UNKNOWN;
        }
    }

    /**
     * Switch thru the commands, and execute the corresponding command class
     *
     * @param command - The command to be executed
     * @return - Should the game be quited
     */
    boolean processCommand(Command command) {
        switch (command.getCommandWord()) {
            case FISH:
                fishCommand.execute(command);
                break;
            case GO:
                goCommand.execute(command);
                break;
            case HELP:
                helpCommand.execute(command);
                break;
            case INVENTORY:
                inventoryCommand.execute(command);
                break;
            case PICKUP:
                pickupCommand.execute(command);
                break;
            case QUIT:
                return quitCommand.execute(command);
            case SELL:
                sellCommand.execute(command);
                break;
            case SLEEP:
                return sleepCommand.execute(command);
            case STATS:
                statsCommand.execute(command);
                break;
            case RECYCLE:
                recycleCommand.execute(command);
                break;
            case UNKNOWN:
                unknownCommand.execute(command);
                break;
        }
        return false;
    }

}
