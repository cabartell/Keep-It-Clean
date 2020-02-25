package io.github.keepitclean.gui;

import io.github.keepitclean.model.Element;
import io.github.keepitclean.model.Player;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    @Getter
    private static ListView<HBox> inventory;

    /**
     * Create a new inventoryView for the player, and add all the items the player have
     *
     * @param player - The {@link io.github.keepitclean.model.Player} the inventory belongs to
     */
    public static void initialize(Player player) {
        inventory = new ListView<>();
        inventory.setFixedCellSize(90);
        inventory.getItems().setAll(getInventoryView(player));
    }

    /**
     * Update the view of the players inventory
     *
     * @param player - The {@link io.github.keepitclean.model.Player} the inventory belongs to
     */
    public static void updateInventory(Player player) {
        inventory.getItems().setAll(getInventoryView(player));
    }

    /**
     * Get the inventory view of the player
     *
     * @param player - The {@link io.github.keepitclean.model.Player} to be displayed
     * @return - The list of {@link javafx.scene.layout.HBox} to be displayed
     */
    private static List<HBox> getInventoryView(Player player) {
        Element[] inventory = player.getInventory();
        List<HBox> list = new ArrayList<>();
        for (Element element : inventory) {
            if (element == null) {
                continue;
            }
            ImageView icon = new ImageView(new Image("items/" + element.name() + ".png"));
            // Capitalize the word. Instead of "FISH", it now becomes "Fish"
            Label name = new Label(element.name().substring(0, 1).toUpperCase() + element.name().substring(1).toLowerCase());
            name.setFont(new Font(15));
            list.add(new HBox(icon, name));
        }
        return list;
    }
}
