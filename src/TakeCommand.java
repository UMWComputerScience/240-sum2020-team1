
class TakeCommand extends Command {

    private String itemName;

    TakeCommand(String itemName) {
        this.itemName = itemName;
	/**This constructor, given the name of an item, creates a new command meant to search for an item in the player's current room's inventory, and add it to the player's personal inventory.
	@throws NoItemException The current Room has no Item of given name.
	**/
    }

    public String execute() {
        if (itemName == null || itemName.trim().length() == 0) {
            return "Take what?\n";
        }
        try {
            Room currentRoom = 
                GameState.instance().getAdventurersCurrentRoom();
            Item theItem = currentRoom.getItemNamed(itemName);
            if (theItem.getWeight() + 
                GameState.instance().getAdventurersCurrentWeight() > 40) {
                return "Your load is too heavy.\n";
            }
            GameState.instance().addToInventory(theItem);
            currentRoom.remove(theItem);
            return theItem.getPrimaryName() + " taken.\n";
        } catch (Item.NoItemException e) {
            // Check and see if we have this already. If no exception is
            // thrown from the line below, then we do.
            try {
                GameState.instance().getItemFromInventoryNamed(itemName);
                return "You already have the " + itemName + ".\n";
            } catch (Item.NoItemException e2) {
                return "There's no " + itemName + " here.\n";
            }
        }
    }
}
