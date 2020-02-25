package io.github.keepitclean.event;

import io.github.keepitclean.Game;
import io.github.keepitclean.gui.Console;
import io.github.keepitclean.handler.PlayerHandler;
import io.github.keepitclean.model.Element;
import javafx.event.Event;
import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Recycle implements EventHandler {
    private static final Random random = new Random();

    /**
     * The method called when a player interacts the recycle NPC.
     *
     * @param event - Ignored
     */
    @Override
    public void handle(Event event) {
        if (Game.getInstance().getPlayer().getEnergy() < 10) {
            Console.println("You do not have enough energy. Please sleep to regain some.");
            return;
        }

        // Loop thru the players inventory, to check if they have items to recycle
        boolean haveItemsToRecycle = false;
        Element[] inventory = Game.getInstance().getPlayer().getInventory();
        for (int i = inventory.length - 1; i >= 0; i--) {
            if (inventory[i] == Element.TRASH || inventory[i] == Element.SWAB) {
                PlayerHandler.removeInventory(Game.getInstance().getPlayer(), i);
                haveItemsToRecycle = true;
            }
        }

        if (haveItemsToRecycle) {
            Game.getInstance().getPlayer().removeEnergy(10);
            printDialogue();
            Game.getInstance().setPollutionBar(Game.getInstance().getPollutionBar() - 3);
        } else {
            Console.println("You did not have any items to recycle.");
        }
    }

    /**
     * Print the recycle dialogue in the console
     */
    private static void printDialogue() {
        Console.println("\n You > Hello. I would like to recycle all of my trash.");
        Console.println(" Billy > Alright. Here is a random fact for you, while you wait");
        Console.println(" Billy > " + getRandomFact());
        Console.println(" Billy > All done. Have a nice day.");
        Console.println(" You > Thank you, and you too.");
    }

    /**
     * @return - A random fact from the list
     */
    private static String getRandomFact() {
        return recycleFacts.get(random.nextInt(recycleFacts.size()));
    }

    private static final List<String> recycleFacts = new ArrayList<>(Arrays.asList(
        "Every year we make enough plastic film to shrink-wrap the state of Texas.",
        "The worldwide fishing industry dumps an estimated 150,000 tons of plastic into the ocean each year, including packaging, plastic nets, lines, and buoys.",
        "Plastic bags and other plastic garbage thrown into the ocean kill as many as 1 million sea creatures every year.",
        "An estimated 6.3 million of trash, much of it plastic is dumped in the world's oceans every year.",
        "Nearly every piece of plastic EVER made still exists today.",
        "90% of plastic polluting our oceans is carried by just 10 rivers.",
        "The average person eats 70,000 microplastics each year.",
        "A plastic bag is used only for 12 minutes at an average.",
        "Of all the beach litter on the planet, 73% is plastic.",
        "Every minute, one garbage truck of plastic is dumped into our oceans.",
        "By 2050 there will be more plastic in the oceans than there are fish (by weight)."
    ));

}
