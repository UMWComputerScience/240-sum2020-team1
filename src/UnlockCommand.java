/**command object that allows the player to unlock, or lock an exit. */

class UnlockCommand extends Command {
	private String dir;

/**given a String resembling a direction, generates a command that will switch an exits locked state, either from locked to unlocked, or vice versa. */
	UnlockCommand(String dir) {
		this.dir = dir;
		}
	public String execute() {
	Exit exit = GameState.instance().getAdventurersCurrentRoom().getExit(dir);
	String returnString = "";
	if(exit.checkLockable()){
		exit.changeLock();
		if(exit.checkLockable()==true){
		returnString = "There is a loud 'klunk chunk' as you trun the key in the door, indicating that the lock has closed and the door is now locked shut.";}
		else{
		returnString = "With a lound 'thunk' the mechanism of the door lock turns over and the door is unlocked.";
	}}
	else{returnString = "That exit is not lockable.";}
	return returnString;
	}	
}
