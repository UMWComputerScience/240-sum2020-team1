import java.util.ArrayList;
import java.util.Collections;
/**Creates a command to allow the player to pick up an item from the current
room and place it in their inventory.*/
class TakeCommand extends Command {

    private String itemName;

	/**This constructor, given the name of an item, creates a new command meant to search for an item in the current room's inventory, and add it to the player's personal inventory.
	@throws NoItemException The current Room has no Item of given name.
	**/
    TakeCommand(String itemName) {
        this.itemName = itemName;



    }
	/**Checks the room inventory for an item who's primary name matches the supplied string. If found, it removes it from the current room's inventory to the player's inventory.
	 * If such an item is not found, throws NoItemException.
	 * If the weight of every item in the player's inventory and the weight of the item to be picked up exceeds the players maximum load capacity,
	 * throws MaxLoadException.
	 */
    public String execute() {
        if (itemName == null || itemName.trim().length() == 0) {
            return "Take what?\n";
        }
	else if(itemName.equals("all")){
		ArrayList<Item> allItem = new ArrayList<Item>();
		Collections.copy(allItem,GameState.instance().getAdventurersCurrentRoom().getContents());
		for(Item i:allItem){
//			System.out.println(i.getPrimaryName());
			GameState.instance().getAdventurersCurrentRoom().remove(i);
			GameState.instance().addToInventory(i);
		}
		return "You picked up everything in the room.\n";
	}
	else{
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
        } catch (NoItemException e) {
            // Check and see if we have this already. If no exception is
            // thrown from the line below, then we do.
            try {
                GameState.instance().getItemFromInventoryNamed(itemName);
                return "You already have the " + itemName + ".\n";
            } catch (NoItemException e2) {
                return "There's no " + itemName + " here.\n";
            }
        }
	//return "else block";
	}
    }
}
