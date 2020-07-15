class DropEvent extends Event{
    private String itemName;
    
    DropEvent(String itemName){
	this.itemName = itemName;
    }

    String callEvent(){
	GameState state = GameState.instance();
	try{
	state.getAdventurersCurrentRoom().add(state.getItemFromInventoryNamed(itemName));
	state.removeFromInventory(state.getItemFromInventoryNamed(itemName));
	}
	catch(NoItemException e){
	}
	return "You dropped your " + itemName + ".";
    }
}

