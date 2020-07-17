
import java.util.Scanner;

/**The primary interface between the player and the game. Reads input from the keyboard as words and sentances that will be interpreted as commands.*/
public class Interpreter {
	/*This class displays everything the user sees on their screen when they run Zork III.
	**/

    private static GameState state; // not strictly necessary; GameState is 
                                    // singleton
	
  public static String USAGE_MSG = 
        "Usage: Interpreter zorkFile.zork|saveFile.sav.";
/**This is the main input for the game. Reads input from the player as words and commands that let the player interact with the game.*/
    public static void main(String args[]) {
	boolean test = true;
	        if (args.length < 1) {
            System.err.println(USAGE_MSG);
            System.exit(1);
        }

        String command;
        Scanner commandLine = new Scanner(System.in);

        try {
            state = GameState.instance();
	    if(args.length == 2){
		    if (args[1].equals("t")){
			GameState.instance().setTest(true);}
	   	 else {
			GameState.instance().setTest(false);}
		}
            if (args[0].endsWith(".zork")) {
                state.initialize(new Dungeon(args[0]));
                System.out.println("\nWelcome to " + 
                    state.getDungeon().getName() + "!");
            } else if (args[0].endsWith(".sav")) {
                state.restore(args[0]);
                System.out.println("\nWelcome back to " + 
                    state.getDungeon().getName() + "!");
		//testMethod(true);
            } else {
                System.err.println(USAGE_MSG);
                System.exit(2);
            }

            System.out.print("\n" + 
                state.getAdventurersCurrentRoom().describe() + "\n");

            command = promptUser(commandLine);

            while (!command.equals("q")) {
		
    		    System.out.print(
                    CommandFactory.instance().parse(command).execute());

                command = promptUser(commandLine);
            }

            System.out.println("Bye!");

        } catch(Exception e) { 
            e.printStackTrace(); 
        }
    }
/**Reads the user's next line of input.
 * */
    private static String promptUser(Scanner commandLine) {
	
	if(GameState.instance().getHealth() == 0){
		GameState.instance().returnToStart();		
	}
        System.out.print("> ");
        return commandLine.nextLine();
    }
    /*Reads the user's next line of input.
    **/

}
