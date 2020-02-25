package io.github.keepitclean.command;

import io.github.keepitclean.model.Command;
import io.github.keepitclean.CommandExecutor;
import io.github.keepitclean.Game;
import io.github.keepitclean.model.ItemType;
import io.github.keepitclean.model.RoomType;

import java.util.Random;

public class SellCmd extends CommandExecutor {

    private Random random;

    public SellCmd(Game game) {
        super(game);
        this.random = new Random();
    }

    @Override
    public boolean execute(Command command) {

        if (super.getPlayer().getCurrentRoom().getRoomType() != RoomType.STREET) {
            System.out.println("This command can only be used at the street");
            return false;
        }

        if (super.getPlayer().getEnergy() < 2) {
            System.out.println("You are out of energy. Please sleep to regain some.");
            return false;
        }
        super.getGame().getPlayer().removeEnergy(2);

        int wroth = 0;
        int fish = 0;

        ItemType[] inventory = super.getPlayer().getInventory();

        for (int i = 0; i < super.getPlayer().getInventory().length; i++) {
            ItemType itemType = inventory[i];

            if (itemType != ItemType.FISH) {
                continue;
            }

            super.getPlayer().removeInventory(i);

            wroth += this.random.nextInt(50) + 25;
            fish++;

        }

        if (fish == 0) {
            System.out.println("You don't have any fish to sell");
            return false;
        }

        System.out.println("You sold " + fish + " fish. $" + wroth + " was added to your account.");
        super.getGame().getPlayer().addMoney(wroth);

        return false;

    }
}


