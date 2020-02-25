package io.github.keepitclean.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Element {
    BOTTLE(true),
    TRASH(true),
    FISHING_ROD(true),
    FISH(true),
    GARBAGE_BAG(true),
    BARRIER(false),
    NPC(false),
    WATER(false),
    EXIT(true),
    SWAB(true),
    SINK(false),
    BED(false);

    private boolean walkable;

}


