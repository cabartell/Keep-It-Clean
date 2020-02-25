package io.github.keepitclean.model;

public enum CommandWord {

    /**
     * public enum io.github.gruppe19.model.CommandWord contains command words and their respective Strings (user inputs)
     */
    GO("go"),
    QUIT("quit"),
    HELP("help"),
    UNKNOWN("?"),
    PICKUP("pickup"),
    STATS("stats"),
    RECYCLE("recycle"),
    SLEEP("sleep"),
    FISH("fish"),
    SELL("sell"),
    INVENTORY("inventory");


    private String commandString;

    CommandWord(String commandString) {
        this.commandString = commandString;
    }

    @Override
    public String toString() {
        return commandString;
    }
}