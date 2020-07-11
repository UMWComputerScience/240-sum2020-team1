
import java.util.ArrayList;
/**Command object that allows the player to see their inventoy.
 * */
class InventoryCommand extends Command {
/**Creates an inventory command.
 * */
    InventoryCommand() {
    }
/** Method to display the inventory menu to the player. 
*/
    public String execute() {
        ArrayList<String> names = GameState.instance().getInventoryNames();
        if (names.size() == 0) {
            return "You are empty-handed.\n";
        }
        String retval = "You are carrying:\n";
        for (String itemName : names) {
            retval += "   A " + itemName + "\n";
        }
        return retval;
    }
}
