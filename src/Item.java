
import java.util.Scanner;
import java.util.Hashtable;
import java.util.HashSet;
import java.util.Set;
/** An Item is an object that can be moved between a room and the player's inventory,
 * or directly added to the inventory through crafting or an encounter with an NPC.
 *
 * Note that an Item can also destroy an exit, transform into another item, or move
 * the player to another room. 
 */
public class Item {
    /**Empty, generic constructor that creates an Item
    **/
    public Item(){
    }

    private String primaryName;
    private int weight;
    private Hashtable<String,ItemEvent> messages;
    private Set<String> aliases;

    /**
     * Creates an Item object by reading the contents of a .zork file.
     * @throws NoItemException The .zork file does not have another Item to be read in.
     */
    Item(Scanner s) throws NoItemException,
       IllegalDungeonFormatException {
	//messages hashtable changed to have an ItemEvent as the value
        messages = new Hashtable<String,ItemEvent>();
        aliases = new HashSet<String>();

       // Read item name.
        String names[] = s.nextLine().split(",");
        if (names[0].equals(Dungeon.TOP_LEVEL_DELIM)) {
            throw new NoItemException();
        }
       this.primaryName = names[0];
	if(GameState.instance().getTest()==true){	
	System.out.println("Test Value: "+GameState.instance().getTest());
	System.out.println("PrimaryName: "+primaryName);
	System.out.println("names[0] :"+names[0]);}


        for (int i=1; i<names.length; i++) {
            aliases.add(names[i]);
        }

        // Read item weight.
        weight = Integer.valueOf(s.nextLine());
	if(GameState.instance().getTest() == true){
	System.out.println("weight: "+weight);}
	
        // Read and parse verbs lines, as long as there are more.
        String verbLine = s.nextLine();
        while (!verbLine.equals(Dungeon.SECOND_LEVEL_DELIM)) {
            if (verbLine.equals(Dungeon.TOP_LEVEL_DELIM)) {
                throw new IllegalDungeonFormatException("No '" +
                    Dungeon.SECOND_LEVEL_DELIM + "' after item.");
            }
           String[] verbParts = verbLine.split(":");
	   int istart = verbParts[0].indexOf("[");
	   int iend = verbParts[0].indexOf("]");
//	   System.out.println("Istart:"+istart);
//	   System.out.println("Iend:"+iend);
	   String verb = "";
	   String message = "";
	   String command = "";
	   String currentLine = "";
	   //String g = s.nextLine();
	   if(istart == -1 && iend == -1){
	   	command = "==no command==";
		verb = verbParts[0];
		message = verbParts[1];
		String g = s.nextLine();
		while(!g.contains(":") && !g.equals(Dungeon.SECOND_LEVEL_DELIM)){
			message += " " + g;
			g = s.nextLine();
		}
		currentLine = g;
	   } 
	   else{
		command = verbParts[0].substring(istart+1,iend);
		verb = verbParts[0].substring(0, istart);
		message= verbParts[1];
		String g = s.nextLine();
		while(!g.contains(":") && !g.equals(Dungeon.SECOND_LEVEL_DELIM)){
			message += " " + g;
			g = s.nextLine();
		}
		currentLine = g;
	   }
	    //int y = 0;
	    //verbLine = s.nextLine();
	    //while(y == 0){
	  //  	if(!verbLine.contains(":")){
	//		    message = message + verbLine;
	//		    verbLine = s.nextLine();
	    //	}
	  //  	else{
	//		    verbLine = s.nextLine();
	//		    y = 1;
	//	    }
	    //}
	    if(GameState.instance().getTest()==true){
	    System.out.println(message);
		}
	    String[] commands = command.split(",");
	   
	    ItemEvent createEvent = new ItemEvent(message,commands);	
            messages.put(verb,createEvent);
            verbLine = currentLine;
        }
   } 
	/**
	 * Returns an integer representing the item's weight.
	 */
    int getWeight(){
        return weight;
    }
	/**
	 * Returns true if the supplied string is found among this Item's aliases.  Returns false in all other cases.
	 */
    boolean goesBy(String name) {
/* returns boolean if the supplied string is found in the items primaryName attribute or alias hashset.
 */
        if (this.primaryName.equals(name)) {
            return true;
        }
        for (String alias : this.aliases) {
            if (alias.equals(name)) {
                return true;
            }
        }
        return false;
    }
	/**
	 * Returns the primary name of an Item.
	 */
    String getPrimaryName() { return this.primaryName; }
	/**
	 * Returns a message in response to a specified action.
	 */
    public String getMessageForVerb(String verb) {
        
	return messages.get(verb).getMessage();
    }
	/**
	 * Returns an item's primary name.
	 */
    public String toString() {
        return primaryName;}
    public void callEvent(String[] c, String item){
	    for(int i = 0;i<c.length;i++){
		String singleEvent = c[i];
		EventFactory.instance().parse(singleEvent,item).callEvent();
	    }
	}
    public String[] getCommand(String verb){
	return this.messages.get(verb).getCommand();
	}
}
