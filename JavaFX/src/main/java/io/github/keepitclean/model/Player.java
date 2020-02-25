package io.github.keepitclean.model;

import javafx.scene.Node;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "playerView")
public class Player {

    private int money = 0;
    private int energy = 100;
    private Element[] inventory = new Element[10];
    private Room currentRoom;
    private Location location;
    private Node playerView;

    public Player(Room currentRoom, Location location) {
        this.currentRoom = currentRoom;
        this.location = location;
    }

    public void addMoney(int money) {
        this.money += +money;
    }

    public void removeEnergy(int amount) {
        this.energy -= amount;
    }

}
