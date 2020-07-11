/** creates a talk command that is used to communicate with NPCs in the game.
*/

class TalkCommand extends Command {

	private String talkCommand;

/** Creates a comman that allows the player to talk to an NPC.
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
