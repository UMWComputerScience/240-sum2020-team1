class TalkCommand extends Command {
/** creates a talk command that is used to communicate with NPCs in the game.
* @author Michael Cividanes
*/
	private String talkCommand;

	/** provided a string, returns a TalkCommand type object. 
	*/
	TalkCommand(String talkCommand) {
	}
	/** execute method for TalkCommand.
	*<b style="color:red;">NEED TO REVIEW</b>
	*/
	public String execute() {
		return "You did this.";
	}

}
