import java.util.ArrayList;
import java.util.Hashtable;
/**Characters that the player can interact with in the game. May have items in their inventory or provide
 *  useful information through dialogue.*/
class NonPlayerCharacter {

	String name;
	Hashtable<String, String> messages;
	ArrayList<Item> inventory;
/** main communication method for NPCs returns a message in response to a
*  supplied topic.
* @author Michael Cividanes
*/
	public String say(){
	    return null;
	}
/** removes an item from the player inventory and places it in the NPC's inventory.
* @throws NoItemException is item if not found in player inventory.
* @return message of the NPC aknowleding the item being given to them.
* @author Michael Cividanes
*/
	public String takeItem(Item item){
	    return "You took " + item.getPrimaryName();
	}
/**
* Takes an item from the NPC's inventory and moves it to the player's inventory.
* Returns item to be placed in the player inventory.
* @throws NoItemException if  item is not found.
*/

	public Item giveItem(){
	    Item item = new Item();
	    return item;
	}
}
