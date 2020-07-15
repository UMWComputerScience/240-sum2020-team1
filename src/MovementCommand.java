/**Command object that allows a player to move from one room to another through an unlocked and non destroyed exit.
 * */
class MovementCommand extends Command {

    private String dir;
                       

	/**Given a String resembling a direction, generates a command that is meant to attempt to move the player in that direction to a new Room.
	**/
    MovementCommand(String dir) {
        this.dir = dir;
	/*Given a String resembling a direction, generates a command that is meant to attempt to move the player in that direction to a new Room.
	**/
    }

    public String execute() {
        Room currentRoom = GameState.instance().getAdventurersCurrentRoom();
        Room nextRoom = currentRoom.leaveBy(dir);
        if (nextRoom != null) {  // could try/catch here.
            GameState.instance().setAdventurersCurrentRoom(nextRoom);
            GameState.instance().increaseScore(1);
            return "\n" + nextRoom.describe() + "\n";
	    
        } else {
            return "You can't go " + dir + ".\n";
        }
	/**Attempts to move the player in a given direction to a new Room.
	 * @throws NoExitException There is no Exit in the current Room from the given direction.*/
    }
}
