import java.util.Scanner;
   /**
    * CraftableItem represents an Item that can only be acquired through crafting. 
    * The player will craft by combining two items, which returns the crafted item.
    *
    * Note that the combined items are removed from the player's inventory.
    */
class CraftableItem extends Item{
/** Removes the two ingrediant items associated with the desired item to be
* crafted from the player's inventory, and places the desired craftableItem
* into the player's inventory. Takes the ingrediant items and returns a crafted
* item.
*/
	public Item craftItem(){
	    Item item = new Item();
	  return item;
	}
/** Returns true if the player inventory contains all two of the items in the supplied
* recipe list. Returns false in all other cases.
*/
	public boolean checkPlayerInventory(){
		return true;
	}
}
