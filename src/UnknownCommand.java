/**Command object to be used when the player uses an unrecognised command.
 * */
class UnknownCommand extends Command {

    private String bogusCommand;
/**Constructs a command with an unrecognised string as it's constructor.*/
    UnknownCommand(String bogusCommand) {
        this.bogusCommand = bogusCommand;
    }
/**Returns a message letting the player know that the command they typed was unrecognesed.*/
    String execute() {
        return "I'm not sure what you mean by \"" + bogusCommand + "\".\n";
    }
}
