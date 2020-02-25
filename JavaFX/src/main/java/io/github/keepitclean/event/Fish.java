package io.github.keepitclean.event;

import io.github.keepitclean.Game;
import io.github.keepitclean.gui.Console;
import io.github.keepitclean.handler.PlayerHandler;
import io.github.keepitclean.model.Element;
import javafx.event.Event;
import javafx.event.EventHandler;

import java.util.Random;

class Fish implements EventHandler {

    private static final Random random = new Random();

    /**
     * The method called when a player interacts water (fishing).
     *
     * @param event - Ignored
     */
    @Override
    public void handle(Event event) {
        if (Game.getInstance().getPlayer().getEnergy() < 5) {
            Console.println("You are out of energy. Please sleep to regain some.");
            return;
        }
        Game.getInstance().getPlayer().removeEnergy(5);

        if (random.nextInt(100) < Game.getInstance().getPollutionBar()) {
            caughtItem(Element.TRASH, 5);
        } else {
            caughtItem(Element.FISH, 2);
        }
    }

    private static void caughtItem(Element element, int pollution) {
        if (element == Element.TRASH) {
            Console.println("It seems that you caught some trash! The ocean must be polluted...");
        }

        if (PlayerHandler.addInventory(Game.getInstance().getPlayer(), element)) {
            if (element.equals(Element.FISH)) {
                Console.println("The fish was added to your inventory. Go to the street to sell it.");
            } else {
                Console.println("The trash was added to your inventory. You can recycle it at the recycling center.");
            }
        } else {
            Game.getInstance().setPollutionBar(Game.getInstance().getPollutionBar() + pollution);
            Console.println("You didn't have any space left in your inventory. The item was dropped on the floor");
        }
    }
}
