class TransformEvent extends Event{
	String itemName;
	String itemTransformedName;
	Dungeon d;
	
	TransformEvent(String combinedName){
	    String result[] = combinedName.split(",");
	    this.itemName = result[0];
	    this.itemTransformedName = result[1];
	    this.d = GameState.instance().getDungeon();
	}
	String callEvent(){
	    GameState state = GameState.instance();
	    state.removeFromInventory(state.getItemFromInventoryNamed(itemName));
	    state.addToInventory(d.getItemList().get(itemTransformedName));
	    return "";
	}
}
