/**Creates a command to lock an exit. */
class CloseExitCommand extends Command {

	private String exit;
	/**Takes a string to determine what exit will be closed.
	 */
	CloseExitCommand(String exit) {
		this.exit = exit;
		}

	 
	/** Takes a string to identify the exit to lock and then changes the condition of that exit to 'locked'.
	* If the exit is not found returns a message indicating so. If the exit is found, and the exit is not a
	* closable type of exit, then it returns a message stating that the exit can not be locked. 
	* If the exit is already locked, returns a message stating that it is already locked.
	* If an exit is destroyed, returns a message detailing that the exit is destroyed.
	*/
	public String execute(){
	return "Close exit command";
	}

}
