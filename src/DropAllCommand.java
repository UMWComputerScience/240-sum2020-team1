
import java.util.List;
import java.util.ArrayList;

class DropAllCommand extends Command {

    DropAllCommand() {
    }

    public String execute(){
	try{
		if(GameState.instance().getInventory().size() == 0){
			throw new NoItemException();
		}
	}
	// if (GameState.instance().getInventory().size() == 0) {
//            return "You're not carrying anything.\n";
	catch (NoItemException e){
	return "You are not carrying anything";
	}
        Room currentRoom = 
            GameState.instance().getAdventurersCurrentRoom();

        String retVal = "";

        for (Item item: new ArrayList<Item>(
                GameState.instance().getInventory())) {
            currentRoom.add(item);
	    try{
            GameState.instance().removeFromInventory(item);
	    }
	    catch(NoItemException e){
	    }
            retVal += item.getPrimaryName() + " dropped.\n";
        }

        return retVal;
    }
}
