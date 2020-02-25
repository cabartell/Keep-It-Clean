package io.github.keepitclean.model;

import javafx.event.Event;
import javafx.event.EventHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NPC {

    private Location location;
    private RoomType roomType;
    private EventHandler<Event> event;

}


