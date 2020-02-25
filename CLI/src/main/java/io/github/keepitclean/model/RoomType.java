package io.github.keepitclean.model;

/**
 * public enum RoomType in package io.github.gruppe19.model
 * contains rooms used in the game and their names and descriptions
 * as well as RoomType constructor and getter methods for name and description Strings
 */
public enum RoomType {
    BATHROOM("Bathroom", "in the bathroom. \n" +
            "The bathroom contains a toilet, a sink and a shower."),
    BEDROOM("Bedroom", "in your bedroom. \n" +
            "This is where you get your rest, so you'll have energy for a new day tomorrow! \n" +
            "To go to sleep, please type the command 'sleep'"),
    DOCK("Dock", "at the Dock. \n" +
            "This is where you fish to earn money for living costs. \n" +
            "To catch fish, type 'fish'"),
    HOME("Home", "at home. \n" +
            "You can go to the kitchen, bedroom or bathroom"),
    KITCHEN("Kitchen", "in the kitchen. \n" +
            "Here, you can toss your trash in the trashcan."),
    BEACH("Beach", "at the Beach. Lots of trash and plastic bottles are scattered all over the ground. \n" +
            "Type 'pickup' to help clean up the beach by picking up trash."),
    RECYCLE("RecyclingCenter", "at the recycling center. \n" +
            "Here, you can recycle everything you have picked up."),
    STREET("Street", "walking down the street. \nThis is where you can sell the fish you catch at the dock \nWhere are you headed?");

    private String name;
    private String description;

    /**
     * @param name - name of room
     * @param description - description of room printed upon entry
     */
    RoomType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * @return - name of room
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return - room description
     */
    public String getDescription() {
        return this.description;
    }

}
