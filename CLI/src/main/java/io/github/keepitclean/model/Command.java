package io.github.keepitclean.model;

/**
 * This class is part of the "Ocean Cleanup io.github.keepitclean.Game" application.
 * This class holds information about a command that was issued by the user.
 * A command currently consists of two parts: a io.github.keepitclean.model.CommandWord and a string
 * (for example, if the command was "Go home", then the two parts
 * are GO and "home").
 * The way this is used is: Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is not
 * known) then the io.github.keepitclean.model.CommandWord is UNKNOWN.
 * If the command had only one word, then the second word is <null>.
 */

public class Command {
    private CommandWord commandWord;
    private String secondWord;

    public Command(CommandWord commandWord, String secondWord) {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
    }

    public CommandWord getCommandWord() {
        return this.commandWord;
    }

    public String getSecondWord() {
        return this.secondWord;
    }

    public boolean hasSecondWord() {
        return this.secondWord != null;
    }
}

