package io.github.keepitclean.model;

import lombok.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Exit {
    private final Location exitLocation;
    private final RoomType newRoomType;
    private final Location startLocation;
    @Setter
    private boolean locked = false;
}
