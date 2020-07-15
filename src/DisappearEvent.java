class DisappearEvent extends Event{
    private String itemName;

    DisappearEvent(String itemName){
	this.itemName = itemName;
    }
    
    String callEvent(){
	GameState state = GameState.instance();
	try{
	    state.removeFromInventory(state.getItemFromInventoryNamed(itemName));
	}
	catch(NoItemException e){
	}
	return "";
    }
}
