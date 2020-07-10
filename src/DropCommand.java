/**
*
*/
class DropCommand extends Command {

    private String itemName;

    DropCommand(String itemName) {
	
        this.itemName = itemName;

	/**An item, whose name is given to this constructor, is passed to a command that is meant to be dropped from the player's inventory and placed into the room's current item list.
	**/
     	 }

    public String execute() {
        if (itemName == null || itemName.trim().length() == 0) {
            return "Drop what?\n";
        }
        try {
            Item theItem = GameState.instance().getItemFromInventoryNamed(
                itemName);
            GameState.instance().removeFromInventory(theItem);
            GameState.instance().getAdventurersCurrentRoom().add(theItem);
            return theItem.getPrimaryName() + " dropped.\n";
        } catch (Item.NoItemException e) {
            return "You don't have a " + itemName + ".\n";
        }
	/**An item, who name is given to this method via constructor, is meant to be dropped from the player's inventory and placed into the current Room's current item list.
	@throws NoItemException The item whose name is given to the constructor is not present in the player's current inventory.
	**/
    }
}
