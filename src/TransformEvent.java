class TransformEvent extends Event{
	String itemName;
	String itemTransformedName;
	Dungeon d;
	
	TransformEvent(String itemName, String itemTransformedName, Dungeon d){
	    this.itemName = itemName;
	    this.itemTransformedName = itemTransformedName;
	    this.d = d;
	}
	String execute(){
	    GameState state = GameState.instance();
	    state.removeFromInventory(state.getItemFromInventoryNamed(itemName));
	    state.addToInventory(d.getItemList().get(itemTransformedName));
	    return "";
	}
}
