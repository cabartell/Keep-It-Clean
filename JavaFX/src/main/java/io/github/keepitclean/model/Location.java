package io.github.keepitclean.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Location {
    private final int x;
    private final int y;
    @Nullable
    private Direction direction;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Location)) {
            return false;
        }
        Location loc = (Location) o;
        return loc.getX() == x && loc.getY() == y;
    }
}
