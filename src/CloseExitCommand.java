
class CloseExitCommand extends Command {

	private String exit;

	CloseExitCommand(String exit) {
		this.exit = exit;
		}

	public String execute() {
/** takes a string to identify the exit to lock and then changes the condition of that exit to 'locked'.
* if the exit is not found returns a message indicating so. If the exit is found, and the exit is not
* closable type of exit, then it returns a message stating that the exit can not be locked. 
* if the exit is already locked, returns a message stating that it is already locked.
	}

}
