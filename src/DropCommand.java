/**
* Command object that allows the player to remove an item from the player's inventory and place it in the room's inventory.
*/
class DropCommand extends Command {

    private String itemName;
/**Command that takes the name of an item that will then be removed from the player's inventory and placed in the room inventory.
 * */
    DropCommand(String itemName) {
	
        this.itemName = itemName;

	/*An item, whose name is given to this constructor, is passed to a command that is meant to be dropped from the player's inventory and placed into the room's current item list.
	*
	* */
     	 }
/**Removes the item that corresponds with the commands item name from the player inventory to the current room's inventory.
 * @throws NoItemException The item whose name is given to the constructor is not present in the player's current inventory.
 * */
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
        } catch (NoItemException e) {
            return "You don't have a " + itemName + ".\n";
        }
	/*An item, who name is given to this method via constructor, is meant to be dropped from the player's inventory and placed into the current Room's current item list.
	@throws NoItemException The item whose name is given to the constructor is not present in the player's current inventory.
	**/
    }
}
