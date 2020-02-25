package io.github.keepitclean;

import io.github.keepitclean.handler.RoomHandler;
import io.github.keepitclean.model.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
public class Game {
    private Player player = new Player(RoomHandler.getRoom(RoomType.BEDROOM), new Location(12, 4, Direction.SOUTH));
    private int pollutionBar = 30;
    @Setter
    private int goodPollutionStreak = 0;
    private List<RoomHandler> rooms = new ArrayList<>();

    private static Game instance;

    /**
     * Private constructor, to ensure a singleton class
     *
     * @param args - The startup parameters
     */
    private Game(String[] args) {
        instance = this;
        GameView.load(args);
    }

    /**
     * Return the instance of the game.
     *
     * @return - The instance of the game
     */
    public static Game getInstance() {
        if (instance == null) {
            throw new ClassCastException("Class has not been initialized yet");
        }
        return instance;
    }

    /**
     * Initialize the instance
     *
     * @param args - The startup parameters
     */
    static void initialize(String[] args) {
        if (instance != null) {
            throw new ClassCastException("Class has already been initialized yet");
        }
        new Game(args);
    }

    /**
     * Set the pollution bar.
     * This method ensures the bar is always between 5 and 100
     *
     * @param pollutionBar - The new value
     */
    public void setPollutionBar(int pollutionBar) {
        this.pollutionBar = Math.max(5, Math.min(pollutionBar, 100));
    }
}
