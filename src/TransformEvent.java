class TransformEvent extends Event{
	String itemName;
	String itemTransformedName;
	
	TransformEvent(String combinedName){
            int openParan = combinedName.indexOf("(");
	    int closeParan = combinedName.indexOf(")");
	    int beginning = combinedName.indexOf("");
	    String firstItem = combinedName.substring(beginning,openParan);
	    this.itemName = firstItem;
     	    String commandValue = combinedName.substring(openParan + 1,closeParan);
	    this.itemTransformedName = commandValue;
	}
	String callEvent(){
	    GameState state = GameState.instance();
	    try{
	    state.removeFromInventory(state.getItemFromInventoryNamed(itemName));
	    state.addToInventory(state.getDungeon().getItemList().get(itemTransformedName));
	    }
	    catch(NoItemException e){
	    }
	    return "Your " + itemName + " turned into a " + itemTransformedName + ".";
	}
}
