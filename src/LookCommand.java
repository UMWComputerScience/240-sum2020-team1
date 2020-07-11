
/**Creates a command object to allow a player to view a detailed description of the room. Note
 * that this includes the room title, room description, items in the room, and the list of exits.
 */
class LookCommand extends Command {

    
	/**Creates a command that is meant to allow the player to "look around", which displays the current room name,description, potential exits, and items present in the current Room.
	**/
	
    
    LookCommand(){}
		
	/**Returns a message which displays the current room name,
	 *  description, potential exits, and items present in the current room.
	 */
    public String execute() {
        Room currRoom = GameState.instance().getAdventurersCurrentRoom();
        return "\n" + currRoom.describe(true) + "\n";
	/*Allows the player to "look around", which displays the current room name, description, potential exits, and items present in the current Room.*/
    }
}
