import java.util.Scanner;
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
		if(noun.equals("all")) {return new TakeAllCommand();}
		else{return new TakeCommand(noun);}
        }
        if (verb.equals("drop")) {
		if (noun.equals("all")) {
		return new DropAllCommand();
		} else {
            return new DropCommand(noun);
        }
	}
        if (verb.equals("i") || verb.equals("inventory")) {
            return new InventoryCommand();
        }
	if (verb.equals("health")){
		return new HealthCommand(GameState.instance().getHealth());	
	}
	if(verb.equals("score")){
		return new ScoreCommand();
	}
	if(verb.equals("wound")){
		System.out.println("How bad should I hurt you?\n");
		Scanner s1 = new Scanner(System.in);
		int smirnoff = s1.nextInt();
		return new WoundCommand(smirnoff);
	}
	if(verb.equals("wound")){
		int woundCount =Integer.parseInt("1");
		return new WoundCommand(woundCount);
	}
        if (MOVEMENT_COMMANDS.contains(verb)) {
	    GameState.instance().minusHunger();
            return new MovementCommand(verb);
        }
	if(verb.equals("rooms")){
		if(GameState.instance().getTest()==true){
			System.out.println("Executing rooms command");
		}
		return new RoomListCommand(GameState.instance().getDungeon().getRoomList());
}
        if (parts.length == 2) {
            return new ItemSpecificCommand(verb, noun);
        }
        return new UnknownCommand(command);
    }
}
