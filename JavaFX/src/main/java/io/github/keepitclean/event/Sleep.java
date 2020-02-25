package io.github.keepitclean.event;

import io.github.keepitclean.Game;
import io.github.keepitclean.GameView;
import io.github.keepitclean.gui.Console;
import io.github.keepitclean.handler.RoomHandler;
import io.github.keepitclean.model.Drop;
import io.github.keepitclean.model.Element;
import io.github.keepitclean.model.Location;
import io.github.keepitclean.model.RoomType;
import javafx.event.Event;
import javafx.event.EventHandler;

import java.util.Random;

class Sleep implements EventHandler {
    private static final Random random = new Random();
    private static final int DAILY_COST = 350;

    /**
     * The method called when a player interacts with the bed.
     *
     * @param event - Ignored
     */
    @Override
    public void handle(Event event) {
        if (Game.getInstance().getPlayer().getEnergy() > 40) {
            Console.println("You have too much energy to sleep");
            return;
        }
        Game.getInstance().getPlayer().setEnergy(100);
        int money = Game.getInstance().getPlayer().getMoney() - DAILY_COST;
        Game.getInstance().getPlayer().setMoney(money);

        if (money < 0) {
            GameView.stopGame(false);
            return;
        }

        Console.println("A new day is here. The time is currently 08:00\n" +
            "Your energy has been refreshed, and you are ready for a new day\n" +
            "You paid the daily taxes of $" + DAILY_COST + ", and now have $" + money + " left");

        // Add a day to the good pollution score, if the score is low enough
        if (Game.getInstance().getPollutionBar() <= 15) {
            Game.getInstance().setGoodPollutionStreak(Game.getInstance().getGoodPollutionStreak() + 1);
        } else {
            Game.getInstance().setGoodPollutionStreak(0);
        }

        // Check if the pollution score have been good for 5 consecutive days
        if (Game.getInstance().getGoodPollutionStreak() > 5) {
            GameView.stopGame(true);
            return;
        }
        RoomHandler.setLocked(RoomType.HOME, RoomType.STREET, true);
        addTrash();
    }

    /**
     * Add a random amount of trash to the beach.
     * The chance of trash to be added depends on the pollution score.
     */
    private static void addTrash() {
        // Add up to 15 items on the ground
        for (int i = 0; i < 15; i++) {
            if (random.nextInt(100) < Game.getInstance().getPollutionBar()) {
                Location loc = getRandomLocation();
                // Get a random location where the trash can be added
                for (int j = 0; j < 100; j++) {
                    // If there aren't any elements on the ground, the trash can be added
                    if (RoomHandler.getRoom(RoomType.BEACH).getOccupiedFields()[loc.getX()][loc.getY()] == null) {
                        RoomHandler.addDrop(RoomType.BEACH, new Drop(Element.TRASH, loc));
                        Game.getInstance().setPollutionBar(Game.getInstance().getPollutionBar() + 1);
                        break;
                    }
                    loc = getRandomLocation();
                }
            }
        }
    }

    /**
     * Get er random location.
     *
     * @return - A random {@link io.github.keepitclean.model.Location}
     */
    private static Location getRandomLocation() {
        int x = random.nextInt(16);
        int y = random.nextInt(9);
        return new Location(x, y, null);
    }

}
