
import java.util.List;
import java.util.ArrayList;

class TakeAllCommand extends Command {

    TakeAllCommand() {
    }

    private int totalWeight(List<Item> items) {
        int tot = 0;
        for (Item item: items) {
            tot += item.getWeight();
        }
        return tot;
    }

    public String execute(){
        Room currentRoom = 
            GameState.instance().getAdventurersCurrentRoom();
        ArrayList<Item> contents = currentRoom.getContents();
	try{
	        if (contents.size() == 0) {
        	    return "There's nothing here to take.\n";
	        }
        	if (GameState.instance().getAdventurersCurrentWeight() +
                	totalWeight(contents) > GameState.instance().getStrength()) {
      		    throw new maxLoadException();
        	}
	}
	catch (maxLoadException e){
		System.out.println("Your load is too heavy to take all that stuff.\n");
	}
	        String retVal = "";
		try{
	        for (Item item: new ArrayList<Item>(contents)) {
        	    GameState.instance().addToInventory(item);
	            currentRoom.remove(item);
        	    retVal += item.getPrimaryName() + " taken.\n";
		}
		}
		catch(maxLoadException e){}
        return retVal;
    }
}
