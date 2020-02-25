package io.github.keepitclean.model;

import lombok.*;

@RequiredArgsConstructor
@Getter
@ToString
public class Drop {
    private final Element element;
    private final Location location;
    @Setter
    private int index;
}
