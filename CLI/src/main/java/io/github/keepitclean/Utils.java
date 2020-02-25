package io.github.keepitclean;

import io.github.keepitclean.model.ItemType;

import java.util.List;

public class Utils {
    /**
     * Takes a inventory of items and display them in the console.
     * Could be the players inventory, items on the ground or other types of inventories.
     * @param items - The list of item to display
     */
    public static void listItems(String preListDisplay, List<ItemType> items) {
        StringBuilder sb = new StringBuilder();
        boolean foundItems = false;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) != null) {
                foundItems = true;
                sb.append(i + 1).append(") ").append(items.get(i)).append("\n");
            }
        }
        if (preListDisplay != null) {
            System.out.println(preListDisplay);
        }
        if (foundItems) {
            System.out.println(sb.toString());
        } else {
            System.out.println("No items was found");
        }
    }
}
