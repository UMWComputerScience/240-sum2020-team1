/**When called, this class carries out an action, which is written alongside a specific item name from the .zork file.
**/
class ItemSpecificCommand extends Command {

    private String verb;
    private String noun;
                        
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
        try {
            itemReferredTo = GameState.instance().getItemInVicinityNamed(noun);
        } catch (Item.NoItemException e) {
            return "There's no " + noun + " here.";
        }
        
        String msg = itemReferredTo.getMessageForVerb(verb);
        return (msg == null ? 
            "Sorry, you can't " + verb + " the " + noun + "." : msg) + "\n";
    }
}
