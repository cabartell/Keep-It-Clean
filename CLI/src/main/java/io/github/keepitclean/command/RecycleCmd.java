package io.github.keepitclean.command;

import io.github.keepitclean.model.Command;
import io.github.keepitclean.CommandExecutor;
import io.github.keepitclean.Game;
import io.github.keepitclean.model.ItemType;
import io.github.keepitclean.model.RoomType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RecycleCmd extends CommandExecutor {
    private Random random;

    /**
     * recycle resets inventory used if person is at a location where recycling is possible.
     * If not at correct location, recycling is not possible, user is asked to go to different location
     */
    public RecycleCmd(Game game) {
        super(game);
        this.random = new Random();
    }

    @Override
    public boolean execute(Command command) {
        if (super.getGame().getPlayer().getCurrentRoom().getRoomType() != RoomType.RECYCLE) {
            System.out.println("This command can only be used at the Recycle Center");
            return false;
        }

        if (super.getPlayer().getEnergy() < 10) {
            System.out.println("You do not have enough energy. Please sleep to regain some.");
            return false;
        }

        boolean haveItemsToRecycle = false;

        ItemType[] inventory = super.getPlayer().getInventory();
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == ItemType.TRASH) {
                super.getPlayer().removeInventory(i);
                haveItemsToRecycle = true;
            }
        }

        if (haveItemsToRecycle) {
            super.getPlayer().removeEnergy(10);
            printDialogue();
            super.getGame().setPollutionBar(super.getGame().getPollutionBar() - 3);
        } else {
            System.out.println("You did not have any items to recycle.");
        }
        super.getPlayer().printFreeInventorySpace();
        return false;
    }

    private void printDialogue() {
        try {
            System.out.println(" You > Hello. I would like to recycle all of my trash.");
            Thread.sleep(1000);
            System.out.println(" Billy > Alright. Here is a random fact for you, while you wait");
            Thread.sleep(1000);
            System.out.println(" Billy > " + getRandomFact());
            Thread.sleep(2500);
            System.out.println(" Billy > All done. Have a nice day.");
            Thread.sleep(750);
            System.out.println(" You > Thank you, and you too.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String getRandomFact() {
        return recycleFacts.get(random.nextInt(recycleFacts.size()));
    }

    private List<String> recycleFacts = new ArrayList<>(Arrays.asList(
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
