class TalkCommand extends Command {
/** creates a talk command that is used to communicate with NPCs in the game.
*/
	private String talkCommand;

/** provided a string, returns a TalkCommand type object that is used to
* interface with a NPC.
*/
	TalkCommand(String talkCommand) {
	}
/** given a topic in string form, returns a message from the NPC. If
* given a topic that the NPC does not know about, the message returned
* will be the NPC's 'unknown' response.
* @Param String
* @return String
*/
	public String execute() {
		return "You did this.";
	}

}
