class DropEvent extends Event{
    private String itemName;
    
    DropEvent(String itemName){
	this.itemName = itemName;
    }

    String callEvent(){
	GameState state = GameState.instance();
	try{
	state.removeFromInventory(state.getItemFromInventoryNamed(itemName));
	state.getAdventurersCurrentRoom().add(state.getAdventurersCurrentRoom().getItemNamed(itemName));
	}
	catch(NoItemException e){
	}
	return "You threw something and it dropped.";
    }
}

