/*
 * Instantiated by ItemSpeccificCommand when
 * the input from the player to the interpreter is parsed
 * and read as a verb, which is specifit to an item, and
 * a noun which will be the item the player intends to
 * transform.
 *
 */
/**Command object that will allow one item to be replaced with another item to make it appear to the player that the initial item has been trasformed into the later item.*/
class TransformCommand extends Command {
	private String verb;
	private String noun;
/**
* Takes a string comprised of a verb and a noun, uses the noun to check the player inventory
* for the item identified by the noun. Will check both the primary name and the alias names 
* of the noun. If the item is found, it will check if the item is capable of performing the
* verb action. It will check the message for that action. If the item is not found, returns a message saying 
* "You don't have a [name of item]." If the item is found, but can not perform the indicated
* verb, it returns a message stating that "[name of item] can't do that."
* Includes an @throws NoItemException if the item cannot be found with the given name.
*/
	String execute(){

	}	
}
