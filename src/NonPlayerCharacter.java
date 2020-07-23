import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
/**Characters that the player can interact with in the game. May have items in their inventory or provide
 *  useful information through dialogue.*/
class NonPlayerCharacter {

	private String name;
	private Hashtable<String, String> messages;
	private ArrayList<Item> inventory;
	static int npcCount = 0;
/**creates a NonPlayerCharacter object by reading tyhe contents of a Zork file. 
* @throws a NoNonPlayerCharacterException if the .zork file does not list another
* NonPlayerCharacter to be read in.
*/

	NonPlayerCharacter(Scanner s) throws NoNonPlayerException {
	init();
	if(name != null && name.equals(Dungeon.TOP_LEVEL_DELIM)){
		throw new NoNonPlayerException();
	}
	this.name = s.nextLine();
	npcCount =npcCount+1;
	//	while(!name.equals(Dungeon.SECOND_LEVEL_DELIM)){
		
			if (name.equals(Dungeon.TOP_LEVEL_DELIM)){
			throw new NoNonPlayerException();
			}
			if(GameState.instance().getTest()==true){
				System.out.println("name:"+this.name);
				System.out.println("NPCs created:" + this.npcCount);	
			}
			String converseLine = s.nextLine();
			if(GameState.instance().getTest()==true){
				System.out.println("Conversation line:"+converseLine);
			}
			while(converseLine.contains(":")){// && !converseLine.equals(Dungeon.SECOND_LEVEL_DELIM)) {
				if(converseLine.equals(Dungeon.TOP_LEVEL_DELIM)) {
					throw new NoNonPlayerException();}
				String[] messagePart = converseLine.split(":");
				if(GameState.instance().getTest()==true){
				System.out.println(messagePart[0]);
				}
				messages.put(messagePart[0],messagePart[1]);
				if(!converseLine.equals(Dungeon.SECOND_LEVEL_DELIM)){
				converseLine = s.nextLine();
				}
				else{}
				if(GameState.instance().getTest()==true){
				//	System.out.println("SECOND_LEVEL_DELIM:"+Dungeon.SECOND_LEVEL_DELIM);
					System.out.println("Current NPC Name:"+name);
					System.out.println("Added topic["+messagePart[0]+"] to "
					+name+"'s conversation table.");}
				}
			//name = s.nextLine();
		 	if(GameState.instance().getTest()==true){
				System.out.println("NPC Created");
			}
			
		
	}
/*	void NonPlayerCharacter(ArrayList d){
		this.name = d.get(0);
		for (int i=1;i<d.length();i++){
			String converseLine = d.get(i);
			String [] messagePart = converseLine.split(":");
			this.messages.put(messagePart[0],messagePart[1]);
		}
	}
*/	
/** takes care of common initialization tasks for NonPlayerCharacter
*/
	private void init(){
	messages = new Hashtable<String, String>();
	inventory = new ArrayList<Item>();
	}
/** main communication method for NPCs returns a message in response to a
*  supplied topic.
* @author Michael Cividanes
*/
	public String say(String topic){
	    String respond = "This is the return message for the say command.";
	    return respond;
	}
/** removes an item from the player inventory and places it in the NPC's inventory.
* @throws NoItemException is item if not found in player inventory.
* @return message of the NPC aknowleding the item being given to them.
* @author Michael Cividanes
*/
	public String takeItem(Item item){
	    return "You took " + item.getPrimaryName();
	}
/**
* Takes an item from the NPC's inventory and moves it to the player's inventory.
* Returns item to be placed in the player inventory.
* @throws NoItemException if  item is not found.
*/

	public Item giveItem(){
	    Item item = new Item();
	    return item;
	}
	public String getName(){
	return name;
	}
}
