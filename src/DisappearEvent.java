class DisappearEvent extends Event{
    private String itemName;

    DisappearEvent(String itemName){
	this.itemName = itemName;
    }
    
    String callEvent()	{
		if(GameState.instance().getTest()==true){
		System.out.println("Disappear event started");
		}
	GameState state = GameState.instance();
	String[] command = this.itemName.split(":");
	String itemName = command[1];
	try{
	    state.removeFromInventory(state.getItemFromInventoryNamed(itemName));
	    if(GameState.instance().getTest()==true){
                System.out.println("removed item from Game State");
                }

	}
	catch(NoItemException e){
	}
	try{
	    state.getAdventurersCurrentRoom().remove(state.getItemInVicinityNamed(itemName));
	  if(GameState.instance().getTest()==true){
                System.out.println("removed item from Game State");
                }

	}
	catch(NoItemException e){
	}
	return "Your " + itemName + " has disappeared from existence!";
    }
}
