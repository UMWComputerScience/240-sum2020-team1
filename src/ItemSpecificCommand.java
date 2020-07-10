
class ItemSpecificCommand extends Command {

    private String verb;
    private String noun;
                        

    ItemSpecificCommand(String verb, String noun) {
        this.verb = verb;
        this.noun = noun;
	/**This construction is given a verb and a noun, which are an action an item can be used to perform and the name of the item, respectively.  This creates an object that is meant to carry out the action an item with the given name can be used for.
	**/
    }

    public String execute() {
        
        Item itemReferredTo = null;
        try {
            itemReferredTo = GameState.instance().getItemInVicinityNamed(noun);
        } catch (Item.NoItemException e) {
            return "There's no " + noun + " here.";
        }
        
        String msg = itemReferredTo.getMessageForVerb(verb);
        return (msg == null ? 
            "Sorry, you can't " + verb + " the " + noun + "." : msg) + "\n";
	/**Carries out the actions an item with the given name defined in the constructor can be used for.
	@throws NoItemException There is no item in the player's inventory with the given name defined in the constructor.
	@throws NullPointerException There is no action an item can take that is specified by the given verb defined in the constructor.
	**/
    }
}
