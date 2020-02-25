package io.github.keepitclean.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * public enum RoomType in package io.github.keepitclean.model
 * contains rooms used in the game and their names and descriptions
 * as well as RoomType constructor and getter methods for name and description Strings
 */
@AllArgsConstructor
@Getter
public enum RoomType {
    BATHROOM("background/bathroom.png"),
    BEDROOM("background/bedroom.png"),
    DOCK("background/dock.png"),
    HOME("background/home.png"),
    KITCHEN("background/kitchen.png"),
    BEACH("background/beach.png"),
    RECYCLE("background/recycle.png"),
    STREET("background/street.png");

    private final String backgroundImage;
}
