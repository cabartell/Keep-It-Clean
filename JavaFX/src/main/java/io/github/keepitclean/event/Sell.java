package io.github.keepitclean.event;

import io.github.keepitclean.Game;
import io.github.keepitclean.gui.Console;
import io.github.keepitclean.handler.PlayerHandler;
import io.github.keepitclean.model.Element;
import javafx.event.Event;
import javafx.event.EventHandler;

import java.util.Random;

public class Sell implements EventHandler {

    private static final Random random = new Random();

    /**
     * The method called when a player interacts the sell NPC.
     *
     * @param event - Ignored
     */
    @Override
    public void handle(Event event) {
        if (Game.getInstance().getPlayer().getEnergy() < 2) {
            Console.println("You are out of energy. Please sleep to regain some.");
            return;
        }

        int wroth = 0;
        int fish = 0;

        // Loops thru the inventory to find all fish the player can sell;
        Element[] inventory = Game.getInstance().getPlayer().getInventory();
        for (int i = inventory.length - 1; i >= 0; i--) {
            if (inventory[i] != Element.FISH) {
                continue;
            }
            PlayerHandler.removeInventory(Game.getInstance().getPlayer(), i);
            // Plus the money the player should receive with a random number between 25 and 75
            wroth += random.nextInt(50) + 25;
            fish++;
        }

        if (fish == 0) {
            Console.println("You don't have any fish to sell");
            return;
        }
        Game.getInstance().getPlayer().removeEnergy(2);

        Console.println("You sold " + fish + " fish. $" + wroth + " was added to your account.");
        Game.getInstance().getPlayer().addMoney(wroth);
    }
}


