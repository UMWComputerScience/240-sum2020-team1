import java.util.ArrayList;
/** creates a talk command that is used to communicate with NPCs in the game.
*/

class TalkCommand extends Command {

	private String talkCommand;
	private String npc;
	private String topic;
	private ArrayList<NonPlayerCharacter> npcs;

/** Creates a comman that allows the player to talk to an NPC.
*/
	TalkCommand(String talkCommand) {
		npcs = GameState.instance().getAdventurersCurrentRoom().getNPCsInRoom();
		if(GameState.instance().getTest()==true){
		System.out.println(npcs);
		}
		String[] parts = talkCommand.split(":");
		if(GameState.instance().getTest()==true){
			for(int i = 0; i<parts.length;i++){
				System.out.println("parts["+i+"]:"+parts[i]);
			}
			System.out.println("Availible NPCS in this room:");
			for(int i = 0; i<npcs.size();i++){
				System.out.println(i+":"+npcs.get(i).getName());
			}
		}
		if(parts.length==3){
		System.out.println("length 3");
		}
		
	}
/** given a topic in string form, returns a message from the NPC. If
* given a topic that the NPC does not know about, the message returned
* will be the NPC's 'unknown' response.
* @Param String
* @return String
*/
	public String execute() {
		return "You did this.\n";
	}

}
