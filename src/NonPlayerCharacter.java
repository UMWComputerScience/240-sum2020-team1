import java.util.ArrayList;
import java.util.Hashtable;
class NonPlayerCharacter {

	String name;
	Hashtable<String, String> messages;
	ArrayList<Item> inventory;
/** main communication method for NPCs returns a message in response to a
*  supplied string
* @author Michael Cividanes
*/
	public String say(){
	    return null;
	}
/** removes an item from the player inventory and places it in the NPC's inventory.
* @throws NoItemException is item is not found in player inventory.
* @return message of the NPC aknowleding the item being given to them.
* @author Michael Cividanes
*/
	public String takeItem(Item item){
	    return "You took " + item.getPrimaryName();
	}
/**
* Takes an item from the NPC's inventory and moves it to the player's inventory.
* @throws NoItemException if  item is not found.
*/

	public Item giveItem(){
	    Item item = new Item();
	    return item;
	}
}
