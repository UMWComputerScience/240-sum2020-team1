class TransformEvent extends Event{
	String itemName;
	String itemTransformedName;
	Dungeon d;
	
	TransformEvent(String itemName, String itemTransformedName){
	    this.itemName = itemName;
	    this.itemTransformedName = itemTransformedName;
	    this.d = GameState.instance().getDungeon();
	}
	String callEvent(){
	    GameState state = GameState.instance();
	    state.removeFromInventory(state.getItemFromInventoryNamed(itemName));
	    state.addToInventory(d.getItemList().get(itemTransformedName));
	    return "";
	}
}
