package io.github.keepitclean.model;

import javafx.scene.layout.GridPane;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
@ToString(exclude = "gridPane")
public class Room {
    private final RoomType roomType;
    private List<Exit> exits = new ArrayList<>();
    private List<Drop> drops = new ArrayList<>();
    private Element[][] occupiedFields = new Element[16][9];
    private List<NPC> npcs = new ArrayList<>();
    private GridPane gridPane;

    @Override
    public boolean equals(Object o) {
        if (o instanceof Room) {
            return ((Room) o).getRoomType() == this.roomType;
        }
        if (o instanceof RoomType) {
            return o == this.roomType;
        }
        return false;
    }
}
