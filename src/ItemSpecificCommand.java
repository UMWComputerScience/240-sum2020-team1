import java.util.List;
import java.util.Arrays;
/**When called, this class carries out an action, which is written alongside a specific item name from the .zork file.
**/
class ItemSpecificCommand extends Command {

    private String verb;
    private String noun;
    private static List<String> EAT_COMMANDS = Arrays.asList("eat","drink","consume","chug","shot gun","guzzle","devour","ingest");
                        
    /**This constructor is given a verb and a noun, which are an action that an item can be used to perform and the name of the item, respectively. This creates an object that is meant to carry out the action an item with the given name can be used for.
    **/
    ItemSpecificCommand(String verb, String noun) {
        this.verb = verb;
        this.noun = noun;
    }
    /**Carries out the actions an item with the given name defined in the constructor can be used for.
    @throws NoItemException There is no item in the player's inventory with the given name defined in the constructor.
    @throws NullPointerException There is no action an item can take that is specified by the given verb defined in the constructor.
    **/
    public String execute() {
        
        Item itemReferredTo = null;
	if(true)
        try {
            itemReferredTo = GameState.instance().getItemInVicinityNamed(noun);
        } catch (NoItemException e) {
            return "There's no " + noun + " here.";
        }
        if(itemReferredTo.getCommand(this.verb)[0].equals("==no command==")){
		System.out.println("there is no command here");
	}else{
		System.out.println("there is a command here");
		String[] commands = itemReferredTo.getCommand(this.verb);
		itemReferredTo.callEvent(commands);
	}

        String msg = itemReferredTo.getMessageForVerb(verb);
	//EventFactory.instance().parse(verb);
	
	//GameState.instance().increaseScore(2);			
	if(EAT_COMMANDS.contains(verb)){
		try{
		Item itemToRemove = GameState.instance().getItemInVicinityNamed(noun);
		GameState.instance().removeFromInventory(itemToRemove);
		GameState.instance().getAdventurersCurrentRoom().remove(itemToRemove);
		}
		catch(NoItemException nie){}
	}
        return (msg == null ? 
            "Sorry, you can't " + verb + " the " + noun + "." : msg) + "\n";
    
	}
}
