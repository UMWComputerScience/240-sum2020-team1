import java.util.ArrayList;
/** creates a talk command that is used to communicate with NPCs in the game.
*/

class TalkCommand extends Command {
	private String talkString;
	private String[] command;
	private String npc;
	private String topic;
	private ArrayList<NonPlayerCharacter> npcs;
	private String say;


/** Creates a comman that allows the player to talk to an NPC.
*/
	TalkCommand(String talkCommand) {
		String nosay = "What? Say that again. I didn't understand that.:default";
		say = "";
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
		if(parts.length == 2){
			talkString = parts[1]+":"+"default";
			}
		else if(parts.length == 3){
			if(parts[1].equals("to")){
			talkString = parts[2]+":default";
			}
			else{
			talkString = nosay;
			}
		}
		else if(parts.length == 4){
			if(parts[1].equals("to")){
			talkString = parts[2]+":"+parts[3];
			}
		}
		else if(parts.length == 5){
		talkString = parts[2]+":"+parts[4];
		}
		else{talkString = nosay;}
		}
	
/** given a topic in string form, returns a message from the NPC. If
* given a topic that the NPC does not know about, the message returned
* will be the NPC's 'unknown' response.
* @Param String
* @return String
*/
	public String execute() {
		String[] command = talkString.split(":");
		String npcName = command[0];
		topic = command[1]; 
		if(GameState.instance().getTest()==true){
		System.out.println("Topic:"+command[1]);}
		NonPlayerCharacter n;
		try{
			n  = GameState.instance().getDungeon().getNPC(npcName);
			if(GameState.instance().getTest()==true){
			
		//	System.out.println("NPC Name:"+n.getName());
			System.out.println("Topic:"+topic);
			}
			String response =n.getName()+": "+ n.say(topic)+"\n";
			return response;}
				catch(NoNonPlayerException npc){}
//		say = n.say(topic);
		return "";
	}

}
