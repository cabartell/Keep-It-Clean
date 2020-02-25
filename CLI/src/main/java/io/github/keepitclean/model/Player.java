package io.github.keepitclean.model;

public class Player {

    /**
     * Player attributes
     */
    private int money;
    private int energy;
    private ItemType[] inventory;
    private Room currentRoom;


    /**
     * Constructor for the Player class.
     * Here everything is set to default values.
     * Only one player can be defined.
     *
     * @param currentRoom - The room the player start in
     */
    public Player(Room currentRoom) {
        this.energy = 100;
        this.money = 0;
        this.inventory = new ItemType[10];
        this.currentRoom = currentRoom;
    }

    /**
     * @return - The players current money.
     */
    public int getMoney() {
        return money;
    }

    /**
     * Set the current money of the player.
     *
     * @param money - The amount of money.
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * Adds money to the players balance.
     *
     * @param money - The amount of money to add.
     */
    public void addMoney(int money) {
        this.money += +money;
    }

    /**
     * Get the current energy level for the player
     *
     * @return - THe players current energy level
     */
    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void removeEnergy(int amount) {
        this.energy -= amount;
    }

    public ItemType[] getInventory() {
        return this.inventory;
    }

    /**
     * Loops thru the inventory, and add item at the fist free spot.
     *
     * @param item - The item to add to the inventory.
     */
    public void addInventory(ItemType item) {
        for (int i = 0; i < this.inventory.length; i++) {
            if (this.inventory[i] == null) {
                this.inventory[i] = item;
                return;
            }
        }
        throw new ArrayIndexOutOfBoundsException("The inventory is already full");
    }

    /**
     * Check for empty slots in inventory
     *
     * @return - number of available inventory spaces
     */
    public int getFreeInventorySpaces() {
        int i = 0;
        for (ItemType type : this.inventory) {
            if (type == null) {
                i++;
            }
        }
        return i;
    }

    public void printFreeInventorySpace() {
        System.out.println("You have " + getFreeInventorySpaces() + " / " + this.inventory.length + " spaces left in your inventory");
    }

    public void removeInventory(int index) {
        this.inventory[index] = null;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
}
