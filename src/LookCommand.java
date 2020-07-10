

class LookCommand extends Command {

    LookCommand() {
	/**Creates a command that is meant to allow the player to "look around", which displays the current room name,description, potential exits, and items present in the current Room.
	**/
    }

    public String execute() {
        Room currRoom = GameState.instance().getAdventurersCurrentRoom();
        return "\n" + currRoom.describe(true) + "\n";
	/**Allows the player to "look around", which displays the current room name, description, potential exits, and items present in the current Room.
    }
}
