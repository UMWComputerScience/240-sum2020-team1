import java.util.ArrayList;
import java.util.Hashtable;
class NonPlayerCharacter {

	String name;
	Hashtable<String, String> messages;
	ArrayList<Item> inventory;

	public String say(){
	/** main communication method for NPCs. Activated by the talkCommand, returns a string. 
*/
	    return null;
	}

	public String takeItem(Item item){
	/**
	* allows the player to give an item to the NPC. adds item to NPCs
	*  inventory.
	*/
	    return "You took " + item.getPrimaryName();
	}

	public Item giveItem(){
	/**
	* Takes an item from the NPC's inventory and moves it to the
	*  player's inventory.
	*/
	    Item item = new Item();
	    return item;
	}
}
