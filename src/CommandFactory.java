
import java.util.List;
import java.util.Arrays;
/**Creates command objects to allow the player to interact with the game.
 */
public class CommandFactory {

    private static CommandFactory theInstance;
    public static List<String> MOVEMENT_COMMANDS = 
        Arrays.asList("n","w","e","s","u","d" );
	/**Creates a single instance of the command factory.*/
    public static synchronized CommandFactory instance() {

        if (theInstance == null) {
            theInstance = new CommandFactory();
        }
        return theInstance;
    }
	/**Empty constructor.*/
    private CommandFactory() {
    }

/**  Takes a string and instantiates the needed sub-class object.
*/
    public Command parse(String command) {

        String parts[] = command.split(" ");
        String verb = parts[0];
        String noun = parts.length >= 2 ? parts[1] : "";
        if (verb.equals("look")) {
            return new LookCommand();
        }
        if (verb.equals("save")) {
            return new SaveCommand(noun);
        }
        if (verb.equals("take")) {
            return new TakeCommand(noun);
        }
        if (verb.equals("drop")) {
            return new DropCommand(noun);
        }
        if (verb.equals("i") || verb.equals("inventory")) {
            return new InventoryCommand();
        }
	if (verb.equals("health")){
		return new HealthCommand(GameState.instance().getHealth());
	
	}
        if (MOVEMENT_COMMANDS.contains(verb)) {
            return new MovementCommand(verb);
        }
        if (parts.length == 2) {
            return new ItemSpecificCommand(verb, noun);
        }
        return new UnknownCommand(command);
    }
}
