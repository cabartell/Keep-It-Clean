package io.github.keepitclean.command;

import io.github.keepitclean.model.Command;
import io.github.keepitclean.CommandExecutor;
import io.github.keepitclean.Game;
import io.github.keepitclean.model.ItemType;
import io.github.keepitclean.model.RoomType;

import java.util.Random;

public class FishCmd extends CommandExecutor {

    private final Random random;

    public FishCmd(Game game) {
        super(game);
        this.random = new Random();
    }

    @Override
    public boolean execute(Command command) {
        if (super.getPlayer().getCurrentRoom().getRoomType() != RoomType.DOCK) {
            System.out.println("This command can only be used at the Dock");
            return false;
        }

        if (super.getPlayer().getEnergy() < 5) {
            System.out.println("You are out of energy. Please sleep to regain some.");
            return false;
        }
        super.getGame().getPlayer().removeEnergy(5);

        if (this.random.nextInt(100) < super.getGame().getPollutionBar()) {
            caughtItem(ItemType.TRASH, 5);
        } else {
            caughtItem(ItemType.FISH, 2);
        }


        return false;
    }

    private void caughtItem(ItemType itemType, int pollution) {
        if (itemType == ItemType.TRASH) {
            System.out.println("It seems that you caught some trash! The ocean must be polluted...");
        }

        if (super.getPlayer().getFreeInventorySpaces() == 0) {
            super.getPlayer().getCurrentRoom().addTrash(itemType);
            super.getGame().setPollutionBar(super.getGame().getPollutionBar() + pollution);
            System.out.println("You didn't have any space left in your inventory. The item was dropped in the floor");
        } else {
            super.getPlayer().addInventory(itemType);
            if (itemType.equals(ItemType.FISH)){
                System.out.println("The fish was added to your inventory. Go to the street to sell it."); }
            else {
                System.out.println("The trash was added to your inventory. You can recycle it at the recycling center."); }
        }
        super.getPlayer().printFreeInventorySpace();
    }

        }
