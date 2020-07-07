/**
 * This class is instantiated by ItemSpeccificCommand when
 * the input from the player to the interpreter is parsed
 * and read as a verb, which is specifit to an item, and
 * a noun which will be the item the player intends to
 * transform.
 *
 */
class TransformCommand extends Command {
	private String verb;
	private String noun;
	
	/**
	 * The execute method calls get item on Gamestate to
	 * manipulate the player's inventory. The item from
	 * inventory is removed and replaced with the item that
	 * the previouse item has been transformed into. A string is
	 * then output to the player, detailing that the item they
	 * performed a command on has been transformed into a new
	 * item.*/
	String execute(){

	}	
}
