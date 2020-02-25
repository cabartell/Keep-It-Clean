package io.github.keepitclean.command;

import io.github.keepitclean.model.Command;
import io.github.keepitclean.CommandExecutor;
import io.github.keepitclean.Game;
import io.github.keepitclean.model.ItemType;
import io.github.keepitclean.model.RoomType;

import java.util.Random;

public class SleepCmd extends CommandExecutor {
    private Random random;
    private final int DAILY_COST;

    public SleepCmd(Game game) {
        super(game);
        this.random = new Random();
        this.DAILY_COST = 350;
    }

    @Override
    public boolean execute(Command command) {
        if (getGame().getPlayer().getCurrentRoom().getRoomType() != RoomType.BEDROOM) {
            System.out.println("You can only sleep in your bedroom, silly.");
            return false;
        }
        if (super.getPlayer().getEnergy() > 40) {
            System.out.println("You have too much energy to sleep");
            return false;
        }
        super.getPlayer().setEnergy(100);
        int money = super.getPlayer().getMoney() - DAILY_COST;
        super.getPlayer().setMoney(money);

        if (money < 0) {
            return true;
        }

        addTrash();
        System.out.println("A new day is here. The time is currently 08:00\n" +
                "Your energy has been refreshed, and you are ready for a new day\n" +
                "You paid the daily taxes of $" + DAILY_COST + ", and now have $" + money + " left");

        if (super.getGame().getPollutionBar() >= 10) {
            super.getGame().setGoodPollutionStreak(super.getGame().getGoodPollutionStreak() + 1);
        } else {
            super.getGame().setGoodPollutionStreak(0);
        }


        return super.getGame().getGoodPollutionStreak() > 10;
    }

    private void addTrash() {
        for (int i = 0; i < 15; i++) {
            if (this.random.nextInt(100) < super.getGame().getPollutionBar()) {
                super.getGame().getRoom(RoomType.BEACH).addTrash(ItemType.TRASH);
                super.getGame().setPollutionBar(super.getGame().getPollutionBar() + 1);
            }
        }
    }

}
