import java.util.ArrayList;
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
	Room currentRoom = GameState.instance().getAdventurersCurrentRoom();
	ArrayList<Item> playerInventory = GameState.instance().getInventory();
        if (itemName == null || itemName.trim().length() == 0) {
            return "Drop what?\n";
        }
	else if(itemName.equals("all")){
		for(int i = 0;i< playerInventory.size();i++){
			Item tubi = playerInventory.get(i);
			System.out.println("Dropping the "+tubi.getPrimaryName()+" to the floor.\n");	
//			System.out.println("You drop the "+tubi.getPrimaryName()+" on the floor.\n")y;
			GameState.instance().getInventory().remove(tubi);		
		  	currentRoom.add(tubi);	
			}
//		for(Item i:allItem){
//			GameState.instance().removeFromInventory(i);
//			GameState.instance().getAdventurersCurrentRoom().add(i);
//			System.out.println("You dropped the "+i.getPrimaryName()+" on the floor.\n");	

		}
//	}
		
	else{
	        try {
        	    Item theItem = GameState.instance().getItemFromInventoryNamed(
                itemName);
	            GameState.instance().removeFromInventory(theItem);
        	    GameState.instance().getAdventurersCurrentRoom().add(theItem);
	            return theItem.getPrimaryName() + " dropped.\n";
	        } catch (NoItemException e) {
        	    return "You don't have a " + itemName + ".\n";
	        }
	}
	/*An item, who name is given to this method via constructor, is meant to be dropped from the player's inventory and placed into the current Room's current item list.
	@throws NoItemException The item whose name is given to the constructor is not present in the player's current inventory.
	**/
	return "";
    }
}
