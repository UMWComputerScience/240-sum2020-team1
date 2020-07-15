
import java.util.List;
import java.util.ArrayList;

class DropAllCommand extends Command {

    DropAllCommand() {
    }

    public String execute() throws NoItemException {
	try{GameState.instance().getInventory().size() == 0;
	// if (GameState.instance().getInventory().size() == 0) {
//            return "You're not carrying anything.\n";
        }catch (NoItemException nie){
	return "You are not carrying anything";
	}
        Room currentRoom = 
            GameState.instance().getAdventurersCurrentRoom();

        String retVal = "";

        for (Item item: new ArrayList<Item>(
                GameState.instance().getInventory())) {
            currentRoom.add(item);
            GameState.instance().removeFromInventory(item);
            retVal += item.getPrimaryName() + " dropped.\n";
        }

        return retVal;
    }
}
