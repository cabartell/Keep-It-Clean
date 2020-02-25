package io.github.keepitclean;

import io.github.keepitclean.handler.RoomHandler;

/**
 * The main class for the project.
 */
public class Start {

    /**
     * This method is called when the program starts.
     *
     * @param args - The startup parameters for the application
     */
    public static void main(String[] args) {
        RoomHandler.initialize();
        Game.initialize(args);
    }
}
