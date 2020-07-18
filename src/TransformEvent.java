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
		if(GameState.instance().getTest()==true){
		System.out.println("Transform event created");
		System.out.println("Command Value:"+commandValue);
		}
	}
	String callEvent(){
	    GameState state = GameState.instance();
	if(GameState.instance().getTest()==true){
		System.out.println("CallEvent called-"+itemTransformedName);}    
	try{
	   GameState.instance().removeFromInventory(state.getItemFromInventoryNamed(itemName));
		if(GameState.instance().getTest()==true){
		System.out.println("Item to remove from GameState inventory: "+itemName);}
	    state.addToInventory(state.getDungeon().getItemList().get(itemTransformedName));
		if(GameState.instance().getTest()==true){
		System.out.println("Item to add to gameState inventory: "+itemTransformedName);
		}
	    }
	    catch(NoItemException e){
		if(GameState.instance().getTest()==true){
		System.out.println("No item exception!");
		}
	    }
	    return "Your " + itemName + " turned into a " + itemTransformedName + ".";
	}
}
